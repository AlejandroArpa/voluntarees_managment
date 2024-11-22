package com.volunteers.services;

import com.volunteers.entities.Project;
import com.volunteers.entities.User;
import com.volunteers.pesistence.database.DAO.ProjectDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ProjectService {

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

    public void listAllProjects() {
        List<Project> projects = ProjectDAO.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No hay proyectos disponibles.");
        } else {
            projects.forEach(System.out::println);
        }
    }

    public void listUserProjects(User user) {
        if (!user.getRole().name().equals("PUBLICANTE")) {
            System.out.println("Solo los Publicantes pueden listar sus proyectos.");
            return;
        }

        List<Project> projects = ProjectDAO.getProjectsByUserId(user.getId());
        if (projects.isEmpty()) {
            System.out.println("No has creado ningún proyecto.");
        } else {
            projects.forEach(System.out::println);
        }
    }
}
