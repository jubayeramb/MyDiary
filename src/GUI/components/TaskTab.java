package GUI.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import data.entities.Task;
import data.enums.Priority;

public class TaskTab extends JPanel {
    private MyTable taskTable;
    private DefaultTableModel taskTableModel;
    private MyInputField searchField;
    private boolean isSearching = false;
    private List<Task> taskList;

    public TaskTab() {
        setLayout(new BorderLayout());

        // Add Task button
        MyButton addTaskButton = new MyButton("Add Task");
        addTaskButton.addActionListener(this::addTaskButtonActionPerformed);

        // Add a search bar
        searchField = new MyInputField(300, 30);
        searchField.setLayout(new BorderLayout());
        searchField.addActionListener(this::searchButtonActionPerformed);

        MyButton searchButton = new MyButton("Search");
        searchButton.addActionListener(this::searchButtonActionPerformed);

        JLabel searchIcon = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/assets/images/search-icon.png"));
        Image image = imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        searchIcon.setIcon(new ImageIcon(image));
        searchIcon.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        searchIcon.setBackground(new Color(0, 0, 0, 50));
        searchField.add(searchIcon, BorderLayout.EAST);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.WEST);
        searchPanel.add(searchButton, BorderLayout.EAST);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(addTaskButton, BorderLayout.EAST);
        topPanel.add(searchPanel, BorderLayout.WEST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

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
        renderTaskTable();
    }

    private void addTaskButtonActionPerformed(ActionEvent e) {
        TaskInputDialog dialog = new TaskInputDialog((Frame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);

        if (dialog.isClickedOk()) {
            String title = dialog.getTitle();
            String description = dialog.getDescription();
            boolean completed = dialog.isCompleted();
            Priority priority = dialog.getPriority();
            Timestamp dueDate;
            if (title == null || title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                dueDate = dialog.getDueDate();
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "Due Date cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Create a new task object
            Task newTask = new Task(title, description, completed, dueDate, priority);
            taskList.add(newTask);

            // Add the task to the table
            renderTaskTable();
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
            renderTaskTable();
            System.out.println("Updated task: " + task.getTitle() + " " + task.getDueDate() + " "
                    + task.getPriority() + " " + task.isCompleted() + " " + task.getDueDate());
        }
        taskTable.clearSelection();
    }

    private void searchButtonActionPerformed(ActionEvent e) {
        String searchText = searchField.getText();
        if (isSearching && (searchText == null || searchText.isEmpty())) {
            isSearching = false;
            searchField.setText("");
            // Clear the table
            taskTableModel.setRowCount(0);
            for (Task task : taskList) {
                taskTableModel.addRow(
                        new Object[] { task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(),
                                task.isCompleted() });
            }
            return;
        } else if (searchText == null || searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Search text cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Clear the table
        taskTableModel.setRowCount(0);
        // Add the filtered tasks to the table
        for (Task task : taskList) {
            searchText = searchText.trim().toLowerCase();
            boolean isMatched = task.getTitle().toLowerCase().contains(searchText) || task.getDescription()
                    .toLowerCase().contains(searchText)
                    || task.getPriority().toString().toLowerCase().contains(searchText);
            if (isMatched) {
                taskTableModel.addRow(new Object[] { task.getTitle(), task.getDescription(), task.getDueDate(),
                        task.getPriority(), task.isCompleted() });
            }
        }
        isSearching = true;
    }

    private void renderTaskTable() {
        // Sort the taskList based on priority and due date
        taskList.sort((t1, t2) -> {
            int priorityComparison = t2.getPriority().compareTo(t1.getPriority());
            if (priorityComparison != 0) {
                return priorityComparison;
            }
            return t1.getDueDate().compareTo(t2.getDueDate());
        });

        // Clear the table
        taskTableModel.setRowCount(0);

        for (Task task : taskList) {
            taskTableModel.addRow(
                    new Object[] { task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(),
                            task.isCompleted() });
        }
    }

    private List<Task> createDummyTasks() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("OOP Lab Final", "Get prepared before 15th June", false,
                Timestamp.valueOf(LocalDateTime.of(2023, 06, 14, 22, 00)), Priority.HIGH));
        tasks.add(new Task("MAT-201 CT", "Do some maths", true,
                Timestamp.valueOf(LocalDateTime.of(2023, 06, 15, 12, 00)), Priority.MEDIUM));
        tasks.add(new Task("CSE-201 Presentation", "Take some prep on Intreface & Multithreading", false,
                Timestamp.valueOf(LocalDateTime.of(2023, 06, 16, 8, 00)), Priority.LOW));
        tasks.add(new Task("CHE Lab Final", "Take some prep on CHE lab exp.", true,
                Timestamp.valueOf(LocalDateTime.of(2023, 06, 15, 22, 00)), Priority.HIGH));

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
