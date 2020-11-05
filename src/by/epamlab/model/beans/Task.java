package by.epamlab.model.beans;

import java.sql.Date;

public class Task {

    private int idTask;
    private String description;
    private Date date;

    public Task(int idTask, String description, Date date) {
        this.idTask = idTask;
        this.description = description;
        this.date = date;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
