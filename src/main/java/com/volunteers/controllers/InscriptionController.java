package com.volunteers.controllers;


import com.volunteers.entities.Project;
import com.volunteers.entities.User;
import com.volunteers.pesistence.database.DAO.ProjectDAO;

import com.volunteers.pesistence.database.DAO.InscriptionDAO;
import java.util.List;
import java.util.Scanner;

public class InscriptionController {
    public void listVolunteersByProject(Scanner scanner, int projectId){
        if(ProjectDAO.getProjectById(projectId)!= null){
            List<User> usersInscripted = ProjectDAO.getUsersByProjectId(projectId);
            if (usersInscripted.isEmpty()) {
                System.out.println("No hay voluntarios inscritos en este proyecto.");
            } else {
                System.out.println("Voluntarios inscritos en el proyecto:");
                for (User user : usersInscripted) {
                    System.out.println("ID: " + user.getId());
                    System.out.println("Nombre: " + user.getName());
                    System.out.println("Correo: " + user.getEmail());
                    System.out.println("Rol: " + user.getRole());
                    System.out.println("----------------------------");
                }
            }
        } else {
            System.out.println("El proyecto no existe.");
        }
    }
    public void enrollInProject(Scanner scanner, User user) {
        System.out.println("Ingrese el ID del proyecto al que desea inscribirse:");
        int projectId = scanner.nextInt();
        scanner.nextLine();

        InscriptionDAO.enrollUserInProject(user.getId(), projectId);
        System.out.println("Te has inscrito exitosamente en el proyecto.");
    }

    public void listUserInscriptions(User user) {
        var projects = InscriptionDAO.getProjectsByUserId(user.getId());
        for (Project project : projects) {
            System.out.println("ID: " + project.getId());
            System.out.println("Título: " + project.getTitle());
            System.out.println("Descripción: " + project.getDescription());
            System.out.println("Fechas: " + project.getStartDate() + " - " + project.getEndDate());
            System.out.println("--------------------------");
        }
    }

}
