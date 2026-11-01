package model;

import java.time.Instant;


public class Task {
    private final int id;
    private String description;
    private Status status;
    private final Instant createdAt;
    private Instant updatedAt;

    public Task(int id, String description, Status status, Instant createdAt, Instant updatedAt){
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void setDescription(String newDescription){
        description = newDescription;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}


