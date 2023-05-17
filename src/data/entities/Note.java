package data.entities;

import java.sql.Timestamp;
import java.util.UUID;

public class Note {
    private UUID id;
    private String title;
    private String content;
    private Timestamp createdAt;

    public Note(String title, String content) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return createdAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return title;
    }
}
