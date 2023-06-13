package GUI.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import data.enums.Priority;

public class TaskInputDialog extends JDialog {
    private MyInputField titleField;
    private MyTextAreaField descriptionArea;
    private JComboBox<Priority> priorityComboBox;
    private JCheckBox completedCheckBox;
    private Priority selectedPriority;
    private boolean clickedOk = false;

    private DatePicker dueDatePicker;
    private TimePicker dueTimePicker;

    public TaskInputDialog(Frame owner) {
        super(owner, "Add Task", true);
        setLayout(new BorderLayout());

        // Create form components
        JLabel titleLabel = new JLabel("Title:");
        titleField = new MyInputField();

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionArea = new MyTextAreaField();
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityComboBox = new JComboBox<>(Priority.values());

        JLabel completedLabel = new JLabel("Completed:");
        completedCheckBox = new JCheckBox();

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDatePicker = new DatePicker();
        JLabel dueTimeLabel = new JLabel("Due Time:");
        dueTimePicker = new TimePicker();

        // Create the buttons
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this::addButtonActionPerformed);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);

        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkboxPanel.add(completedLabel);
        checkboxPanel.add(completedCheckBox);

        // Create the panel for the form
        JPanel formPanel = new JPanel(new GridLayout(11, 1, 0, 0));
        formPanel.add(titleLabel);
        formPanel.add(titleField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionScrollPane);
        formPanel.add(priorityLabel);
        formPanel.add(priorityComboBox);
        formPanel.add(dueDateLabel);
        formPanel.add(dueDatePicker);
        formPanel.add(dueTimeLabel);
        formPanel.add(dueTimePicker);
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

        setPreferredSize(new Dimension(500, 600));

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

    public boolean isClickedOk() {
        return clickedOk;
    }

    public Timestamp getDueDate() {
        LocalDate selectedDate = dueDatePicker.getDate();
        LocalTime selectedTime = dueTimePicker.getTime();
        LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, selectedTime);
        return Timestamp.valueOf(selectedDateTime);
    }

    private void addButtonActionPerformed(ActionEvent e) {
        selectedPriority = (Priority) priorityComboBox.getSelectedItem();
        clickedOk = true;
        dispose();
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        dispose();
    }
}
