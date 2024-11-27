package com.volunteers.pesistence.database.DAO;

import com.volunteers.entities.Project;
import com.volunteers.entities.Role;
import com.volunteers.entities.User;
import com.volunteers.pesistence.database.DatabaseConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscriptionDAO {
    public static List<User> getUsersByProjectId(int projectId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.* FROM users u " +
                "JOIN inscriptions i ON u.id = i.user_id " +
                "WHERE i.project_id = ?";

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
            System.out.println("Error al obtener usuarios inscritos en el proyecto: " + e.getMessage());
        }
        return users;
    }

    public static void enrollUserInProject(int userId, int projectId) {
        String query = "INSERT INTO inscriptions (user_id, project_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, projectId);
            stmt.executeUpdate();
            System.out.println("Usuario inscrito en el proyecto exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al inscribir usuario en proyecto: " + e.getMessage());
        }
    }
    public static List<Project> getProjectsByUserId(int userId) {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT p.* FROM projects p " +
                "JOIN inscriptions i ON p.id = i.project_id " +
                "WHERE i.user_id = ?";

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
            System.out.println("Error al obtener proyectos en los que el usuario está inscrito: " + e.getMessage());
        }
        return projects;
    }
}
