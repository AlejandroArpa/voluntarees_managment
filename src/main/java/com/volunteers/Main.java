package com.volunteers;

import com.volunteers.entities.User;
import com.volunteers.services.AuthService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
//        ProjectService projectService = new ProjectService();

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
                    case 1 -> authService.registerUser(scanner);
//                    case 2 -> currentUser = authService.login(scanner);
                    case 0 -> {
                        System.out.println("Adios.");
                        return;
                    }
                    default -> System.out.println("Opción invalida");
                }
            }
        }
    }
//    private static void publicanteMenu(Scanner scanner, ProjectService projectService, InscriptionService inscriptionService, User user) {
//        System.out.println("Menú Publicante");
//        System.out.println("1. Crear proyecto");
//        System.out.println("2. Ver mis proyectos");
//        System.out.println("3. Ver voluntarios inscritos en un proyecto");
//        System.out.println("0. Cerrar sesión");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1 -> projectService.createProject(scanner, user);
//            case 2 -> projectService.listProjectsByUser(user);
//            case 3 -> inscriptionService.listVolunteersByProject(scanner, user);
//            case 0 -> System.out.println("Sesión cerrada.");
//            default -> System.out.println("Opción inválida.");
//        }
//    }
//
//    private static void voluntarioMenu(Scanner scanner, ProjectService projectService, InscriptionService inscriptionService, User user) {
//        System.out.println("Menú Voluntario");
//        System.out.println("1. Ver proyectos disponibles");
//        System.out.println("2. Inscribirse en un proyecto");
//        System.out.println("3. Ver mis inscripciones");
//        System.out.println("0. Cerrar sesión");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1 -> projectService.listAvailableProjects();
//            case 2 -> inscriptionService.enrollInProject(scanner, user);
//            case 3 -> inscriptionService.listUserInscriptions(user);
//            case 0 -> System.out.println("Sesión cerrada.");
//            default -> System.out.println("Opción inválida.");
//        }
//    }
}
