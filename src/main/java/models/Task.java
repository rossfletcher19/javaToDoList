package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {

    private String description;
    private boolean completed;
    private int id;
    private int categoryId;
    private LocalDateTime createdAt;



    public Task(String description, int categoryId) {
        this.description = description;
        this.completed = false;
        this.categoryId = categoryId;
        this.createdAt = LocalDateTime.now();

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (completed != task.completed) return false;
        if (id != task.id) return false;
        return description != null ? description.equals(task.description) : task.description == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + id;
        return result;
    }

    public String getDescription() {
        return description;
    }



    public boolean getCompleted(){
        return this.completed;
    }


    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
