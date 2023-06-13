package GUI.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        taskTableModel = new DefaultTableModel(new Object[] { "Title", "Desc.", "Due Date", "Priority", "Done" }, 0);
        taskTable = new MyTable(taskTableModel);
        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

        // Set custom row renderer
        taskTable.setDefaultRenderer(Object.class, new TaskTableRowRenderer());
        // Add action listener for table row click
        taskTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    editTaskDialog(selectedRow);
                }
            }
        });

        // Add components to the TaskTab panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add dummy tasks to the table
        taskList = createDummyTasks();
        for (Task task : taskList) {
            taskTableModel.addRow(
                    new Object[] { task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(),
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
            Timestamp dueDate = dialog.getDueDate();
            if (title == null || title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Create a new task object
            Task newTask = new Task(title, description, completed, dueDate, priority);
            taskList.add(newTask);

            // Add the task to the table
            taskTableModel.addRow(new Object[] { newTask.getTitle(), newTask.getDescription(), newTask.getDueDate(),
                    newTask.getPriority(),
                    newTask.isCompleted() });
            System.out.println("Added task: " + newTask.getTitle() + " " + newTask.getDueDate() + " "
                    + newTask.getPriority() + " " + newTask.isCompleted() + " " + newTask.getDueDate());
        }
    }

    private void editTaskDialog(int row) {
        String title = (String) taskTableModel.getValueAt(row, 0);
        String description = (String) taskTableModel.getValueAt(row, 1);
        Timestamp dueDate = (Timestamp) taskTableModel.getValueAt(row, 2);
        Priority priority = (Priority) taskTableModel.getValueAt(row, 3);
        boolean completed = (boolean) taskTableModel.getValueAt(row, 4);

        TaskInputDialog dialog = new TaskInputDialog((Frame) SwingUtilities.getWindowAncestor(this), title,
                description, priority, completed, dueDate);
        dialog.setVisible(true);

        if (dialog.isClickedOk()) {
            String newTitle = dialog.getTitle();
            String newDescription = dialog.getDescription();
            boolean newCompleted = dialog.isCompleted();
            Priority newPriority = dialog.getPriority();
            Timestamp newDueDate = dialog.getDueDate();
            if (newTitle == null || newTitle.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Update the task object
            Task task = taskList.get(row);
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            task.setCompleted(newCompleted);
            task.setPriority(newPriority);
            task.setDueDate(newDueDate);

            // Update the table
            taskTableModel.setValueAt(newTitle, row, 0);
            taskTableModel.setValueAt(newDescription, row, 1);
            taskTableModel.setValueAt(newDueDate, row, 2);
            taskTableModel.setValueAt(newPriority, row, 3);
            taskTableModel.setValueAt(newCompleted, row, 4);
            System.out.println("Updated task: " + task.getTitle() + " " + task.getDueDate() + " "
                    + task.getPriority() + " " + task.isCompleted() + " " + task.getDueDate());
        }

    }

    private List<Task> createDummyTasks() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("OOP Lab Final", "Get prepared before 15th June", false,
                Timestamp.valueOf(LocalDateTime.now()), Priority.LOW));
        tasks.add(new Task("MAT-201 CT", "Do some maths", true, Timestamp.valueOf(LocalDateTime.now()), Priority.HIGH));
        tasks.add(new Task("Task 3", "Description 3", false, Timestamp.valueOf(LocalDateTime.now()), Priority.MEDIUM));
        tasks.add(new Task("Task 4", "Description 3", true, Timestamp.valueOf(LocalDateTime.now()), Priority.MEDIUM));
        return tasks;
    }

    private class TaskTableRowRenderer extends DefaultTableCellRenderer {
        private static final Color COMPLETED_COLOR = new Color(0, 0, 0, 60);

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            boolean completed = (boolean) table.getValueAt(row, 4);
            JLabel label = (JLabel) component; // Cast to JLabel
            label.setHorizontalAlignment(SwingConstants.CENTER);

            if (column == 4) { // Check if it's the "Done" column
                if (completed) {
                    label.setText("\u2713"); // Display a tick symbol (✓)
                } else {
                    label.setText("\u2718"); // Display a cross symbol (✘)
                }
            }

            if (column == 2) { // check if the Due Date column
                Timestamp date = (Timestamp) table.getValueAt(row, 2);
                label.setText(date.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")));
            }

            if (isSelected) {
                component.setBackground(table.getSelectionBackground());
                label.setForeground(table.getSelectionForeground());
            } else if (completed) {
                component.setBackground(COMPLETED_COLOR);
                label.setForeground(Color.WHITE);
            } else {
                component.setBackground(table.getBackground());
                label.setForeground(table.getForeground());
            }

            return component;
        }
    }

}
