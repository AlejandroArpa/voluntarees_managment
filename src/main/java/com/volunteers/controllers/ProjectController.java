package com.volunteers.controllers;

import com.volunteers.entities.Project;
import com.volunteers.entities.User;
import com.volunteers.pesistence.database.DAO.ProjectDAO;

import java.util.List;
import java.util.Scanner;


import java.sql.Date;

public class ProjectController {

    public void createProject(User user, Scanner scanner) {
        if (!user.getRole().name().equals("PUBLICANTE")) {
            System.out.println("Solo los Publicantes pueden crear proyectos.");
            return;
        }

        System.out.println("Ingrese el título del proyecto:");
        String title = scanner.nextLine();

        System.out.println("Ingrese la descripción del proyecto:");
        String description = scanner.nextLine();

        System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
        String startDateInput = scanner.nextLine();

        System.out.println("Ingrese la fecha de finalización (YYYY-MM-DD):");
        String endDateInput = scanner.nextLine();

        Date startDate = Date.valueOf(startDateInput);
        Date endDate = Date.valueOf(endDateInput);

        Project project = new Project(0, title, description, startDate, endDate, user.getId());
        ProjectDAO.addProject(project);
    }

    public void listProjectsByUser(User user) {
        var projects = ProjectDAO.getProjectsByUserId(user.getId());
        for (Project project : projects) {
            System.out.println("ID: " + project.getId());
            System.out.println("Título: " + project.getTitle());
            System.out.println("Descripción: " + project.getDescription());
            System.out.println("Fechas: " + project.getStartDate() + " - " + project.getEndDate());
            System.out.println("--------------------------");
        }
    }

    public void listAvailableProjects() {
        var projects = ProjectDAO.getAllProjects();
        for (Project project : projects) {
            System.out.println("ID: " + project.getId());
            System.out.println("Título: " + project.getTitle());
            System.out.println("Descripción: " + project.getDescription());
            System.out.println("Fechas: " + project.getStartDate() + " - " + project.getEndDate());
            System.out.println("--------------------------");
        }
    }

    public void listProjects() {

        List<Project> projects = ProjectDAO.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No hay ningún proyecto.");
        } else {
            projects.forEach(System.out::println);
        }
    }
}
