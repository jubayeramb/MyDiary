package data.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import data.entities.Task;
import data.enums.Priority;

public class TaskDAO {
    private Connection connection;

    public TaskDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                boolean isCompleted = resultSet.getBoolean("is_completed");
                Timestamp dueDate = resultSet.getTimestamp("due_date");
                Priority priority = Priority.valueOf(resultSet.getString("priority"));
                Timestamp createdAt = resultSet.getTimestamp("created_at");

                Task task = new Task(id, title, description, isCompleted, dueDate, priority);
                task.setCreatedAt(createdAt);
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public void createTask(Task task) {
        String query = "INSERT INTO tasks (id, title, description, is_completed, due_date, priority, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, task.getId().toString());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setTimestamp(5, task.getDueDate());
            statement.setString(6, task.getPriority().toString());
            statement.setTimestamp(7, task.getCreatedAt());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(Task task) {
        String query = "UPDATE tasks SET title = ?, description = ?, is_completed = ?, due_date = ?, " +
                "priority = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setBoolean(3, task.isCompleted());
            statement.setTimestamp(4, task.getDueDate());
            statement.setString(5, task.getPriority().toString());
            statement.setString(6, task.getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(UUID taskId) {
        String query = "DELETE FROM tasks WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, taskId.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
