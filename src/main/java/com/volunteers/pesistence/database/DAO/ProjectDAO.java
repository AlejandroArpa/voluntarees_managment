package com.volunteers.pesistence.database.DAO;

import com.volunteers.entities.Project;
import com.volunteers.entities.Role;
import com.volunteers.entities.User;
import com.volunteers.pesistence.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProjectDAO {
    public static void addProject(Project project) {
        String query = "INSERT INTO projects (title, description, start_date, end_date, created_by) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription());
            stmt.setDate(3, project.getStartDate());
            stmt.setDate(4, project.getEndDate());
            stmt.setInt(5, project.getCreatedBy());

            stmt.executeUpdate();
            System.out.println("Proyecto agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar proyecto: " + e.getMessage());
        }
    }

    public static List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Project project = new Project(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getInt("created_by")
                );
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener proyectos: " + e.getMessage());
        }
        return projects;
    }

    public static Project getProjectById(int projectId) {
        String query = "SELECT * FROM projects WHERE id = ?";
        Project project = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, projectId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    project = new Project(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getDate("start_date"),
                            rs.getDate("end_date"),
                            rs.getInt("created_by")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el proyecto por ID: " + e.getMessage());
        }
        return project;
    }

    public static List<User> getUsersByProjectId(int projectId) {
        List<User> users = new ArrayList<>();
        String query = """
                SELECT u.id, u.name, u.email, u.role
                FROM users u
                JOIN inscriptions i ON u.id = i.user_id
                WHERE i.project_id = ?;
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, projectId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Role role = Role.valueOf(rs.getString("role").toUpperCase());
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            role
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios por proyecto: " + e.getMessage());
        }
        return users;
    }

    public static List<Project> getProjectsByUserId(int userId) {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects WHERE created_by = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getDate("start_date"),
                            rs.getDate("end_date"),
                            rs.getInt("created_by")
                    );
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener proyectos por usuario: " + e.getMessage());
        }
        return projects;
    }

}
