package GUI.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import data.entities.Task;
import data.enums.Priority;

public class TaskTab extends JPanel {
    private MyTable taskTable;
    private DefaultTableModel taskTableModel;
    private List<Task> taskList;

    public TaskTab() {
        setLayout(new BorderLayout());

        // Create the "Add Task" button and add it to the top right corner
        MyButton addTaskButton = new MyButton("Add Task");
        addTaskButton.addActionListener(this::addTaskButtonActionPerformed);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(addTaskButton);

        // Create the table model and table for displaying tasks
        taskTableModel = new DefaultTableModel(new Object[] { "Title", "Due Date", "Priority", "Done" }, 0);
        taskTable = new MyTable(taskTableModel);
        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

        // Add components to the TaskTab panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add dummy tasks to the table
        taskList = createDummyTasks();
        for (Task task : taskList) {
            taskTableModel.addRow(new Object[] { task.getTitle(), task.getDueDate(), task.getPriority(),
                    task.isCompleted() });
        }
    }

    private void addTaskButtonActionPerformed(ActionEvent e) {
        TaskInputDialog dialog = new TaskInputDialog((Frame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);

        if (dialog.isClickedOk()) {
            String title = dialog.getTitle();
            String description = dialog.getDescription();
            boolean completed = dialog.isCompleted();
            Priority priority = dialog.getPriority();
            if (title == null || title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Create a new task object
            Task newTask = new Task(title, description, completed, Timestamp.valueOf(LocalDateTime.now()), priority);
            taskList.add(newTask);

            // Add the task to the table
            taskTableModel.addRow(new Object[] { newTask.getTitle(), newTask.getDueDate(), newTask.getPriority(),
                    newTask.isCompleted() });
            System.out.println("Added task: " + newTask.getTitle() + " " + newTask.getDueDate() + " "
                    + newTask.getPriority() + " " + newTask.isCompleted());
        }
    }

    private List<Task> createDummyTasks() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("Task 1", "Description 1", false, Timestamp.valueOf(LocalDateTime.now()), Priority.LOW));
        tasks.add(new Task("Task 2", "Description 2", false, Timestamp.valueOf(LocalDateTime.now()), Priority.HIGH));
        tasks.add(new Task("Task 3", "Description 3", false, Timestamp.valueOf(LocalDateTime.now()), Priority.MEDIUM));
        return tasks;
    }
}
