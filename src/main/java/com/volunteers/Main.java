package com.volunteers;

import com.volunteers.controllers.InscriptionController;
import com.volunteers.controllers.ProjectController;
import com.volunteers.entities.Role;
import com.volunteers.entities.User;
import com.volunteers.controllers.AuthController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthController authController = new AuthController();
        ProjectController projectController = new ProjectController();
        InscriptionController inscriptionController = new InscriptionController();

        System.out.println("Bienvenido al sistema de voluntarios");
        User currentUser = null;

        while (true) {
            if (currentUser == null ) {
                System.out.println("1. Registrarse");
                System.out.println("2. Iniciar sesión");
                System.out.println("0. Salir");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch ( choice ) {
                    case 1 -> authController.registerUser(scanner);
                    case 2 -> {
                        currentUser = authController.login(scanner);
                        if (currentUser != null) {
                            if(currentUser.getRole() == Role.PUBLICANTE)
                                publicanteMenu(scanner, projectController, inscriptionController, currentUser);
                            else {
                                voluntarioMenu(scanner, projectController, inscriptionController, currentUser);
                            }
                            currentUser = null;
                        }

                    }
                    case 0 -> {
                        System.out.println("Adios.");
                        return;
                    }
                    default -> System.out.println("Opción invalida");
                }
            }
        }
    }
    private static void publicanteMenu(Scanner scanner, ProjectController projectController, InscriptionController inscriptionController, User user) {
        while (true){
            System.out.println("Menú Publicante");
            System.out.println("1. Crear proyecto");
            System.out.println("2. Ver mis proyectos");
            System.out.println("3. Ver voluntarios inscritos en un proyecto");
            System.out.println("0. Cerrar sesión");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1 -> projectController.createProject(user, scanner);
            case 2 -> projectController.listProjectsByUser(user);
            case 3 -> {
                System.out.println("Ingrese el id del proyecto: ");
                int projectId = scanner.nextInt();
                scanner.nextLine();
                inscriptionController.listVolunteersByProject(scanner, projectId);
            }
                case 0 -> {
                    System.out.println("Sesión cerrada.");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
//
    private static void voluntarioMenu(Scanner scanner, ProjectController projectController, InscriptionController inscriptionController, User user) {
        while (true){
            System.out.println("Menú Voluntario");
            System.out.println("1. Ver proyectos disponibles");
            System.out.println("2. Inscribirse en un proyecto");
            System.out.println("3. Ver mis inscripciones");
            System.out.println("0. Cerrar sesión");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> projectController.listAvailableProjects();
                case 2 -> inscriptionController.enrollInProject(scanner, user);
                case 3 -> inscriptionController.listUserInscriptions(user);
                case 0 -> {
                    System.out.println("Sesión cerrada.");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
