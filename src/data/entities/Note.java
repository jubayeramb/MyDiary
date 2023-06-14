package data.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Note {
    private UUID id;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Note(String title, String content) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
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
