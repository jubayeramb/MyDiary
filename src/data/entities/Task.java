package data.entities;

import java.sql.Timestamp;
import java.util.UUID;

public class Task {
    private UUID id;
    private UUID userId;
    private String title;
    private String description;
    private boolean isCompleted;
    private int priority;
    private Timestamp dueDate;
    private Timestamp createdAt;

    public Task(String title, String description, boolean isCompleted, Timestamp dueDate, int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 3");
        }
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
        this.priority = priority;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 3");
        }
        this.priority = priority;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return title;
    }
}
