package data.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import data.entities.Note;

public class NoteDAO {
    private Connection connection;

    public NoteDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        String query = "SELECT * FROM notes";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                Note note = new Note(title, content);
                note.setId(id);
                note.setCreatedAt(createdAt);
                note.setUpdatedAt(updatedAt);
                notes.add(note);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public void createNote(Note note) {
        String query = "INSERT INTO notes (id, title, content, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, note.getId().toString());
            statement.setString(2, note.getTitle());
            statement.setString(3, note.getContent());
            statement.setTimestamp(4, note.getCreatedAt());
            statement.setTimestamp(5, note.getUpdatedAt());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNote(Note note) {
        String query = "UPDATE notes SET title = ?, content = ?, updated_at = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.setTimestamp(3, note.getUpdatedAt());
            statement.setString(4, note.getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNote(UUID noteId) {
        String query = "DELETE FROM notes WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, noteId.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
