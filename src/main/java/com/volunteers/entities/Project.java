package com.volunteers.entities;

import java.sql.Date;

public class Project {
    private int id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int createdBy;

    public Project(int id, String title, String description, Date startDate, Date endDate, int createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "ID=" + id +
                ", Título='" + title + '\'' +
                ", Descripción='" + description + '\'' +
                ", Inicio=" + startDate +
                ", Fin=" + endDate +
                '}';
    }
}
