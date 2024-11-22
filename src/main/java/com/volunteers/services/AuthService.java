package com.volunteers.services;

import com.volunteers.entities.Role;
import com.volunteers.entities.User;
import com.volunteers.pesistence.database.DAO.UserDAO;
import com.volunteers.pesistence.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AuthService {


    public void registerUser(Scanner scanner) {
        System.out.println("Registro de usuario");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Rol (VOLUNTARIO o PUBLICANTE): ");
        String role = scanner.nextLine().toUpperCase();

        if (!role.equals("VOLUNTARIO") && !role.equals("PUBLICANTE")) {
            System.out.println("Rol inválido.");
            return;
        }
        User user = new User(0 ,name, email, password, Role.valueOf(role));
        UserDAO.addUser(user);
    }

    public User login(Scanner scanner) {
        System.out.println("Iniciar sesión");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Inicio de sesión exitoso.");
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))
                );
            } else {
                System.out.println("Credenciales inválidas.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            return null;
        }
    }
}
