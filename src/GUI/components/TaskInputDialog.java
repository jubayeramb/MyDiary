package GUI.components;

import javax.swing.*;

import data.enums.Priority;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TaskInputDialog extends JDialog {
    private InputField titleField;
    private MyTextAreaField descriptionArea;
    private JComboBox<Priority> priorityComboBox;
    private JCheckBox completedCheckBox;
    private Priority selectedPriority;

    public TaskInputDialog(Frame owner) {
        super(owner, "Add Task", true);
        setLayout(new BorderLayout());

        // Create form components
        JLabel titleLabel = new JLabel("Title:");
        titleField = new InputField();

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionArea = new MyTextAreaField();
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityComboBox = new JComboBox<>(Priority.values());

        JLabel completedLabel = new JLabel("Completed:");
        completedCheckBox = new JCheckBox();

        // Create the buttons
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this::addButtonActionPerformed);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);

        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkboxPanel.add(completedLabel);
        checkboxPanel.add(completedCheckBox);

        // Create the panel for the form
        JPanel formPanel = new JPanel(new GridLayout(7, 1, 0, 0));
        formPanel.add(titleLabel);
        formPanel.add(titleField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionScrollPane);
        formPanel.add(priorityLabel);
        formPanel.add(priorityComboBox);
        formPanel.add(checkboxPanel);

        // Create the panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Add components to the dialog
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(400, 400));

        pack();
        setLocationRelativeTo(owner);
    }

    public String getTitle() {
        return titleField.getText();
    }

    public String getDescription() {
        return descriptionArea.getText();
    }

    public Priority getPriority() {
        return selectedPriority;
    }

    public boolean isCompleted() {
        return completedCheckBox.isSelected();
    }

    private void addButtonActionPerformed(ActionEvent e) {
        selectedPriority = (Priority) priorityComboBox.getSelectedItem();
        dispose();
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        dispose();
    }
}
