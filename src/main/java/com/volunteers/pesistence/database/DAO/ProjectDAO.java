package com.volunteers.pesistence.database.DAO;

import com.volunteers.entities.Project;
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
            System.out.println("Error al obtener proyectos del usuario: " + e.getMessage());
        }
        return projects;
    }
}
