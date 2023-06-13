package GUI.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NoteInputDialog extends JDialog {
    private MyInputField titleField;
    private MyTextAreaField contentArea;
    private boolean clickedOk = false;

    public NoteInputDialog(Frame owner) {
        super(owner, "Add Task", true);
        setLayout(new BorderLayout());

        // Create form components
        JLabel titleLabel = new JLabel("Title:");
        titleField = new MyInputField();

        JLabel descriptionLabel = new JLabel("Content:");
        contentArea = new MyTextAreaField();
        JScrollPane descriptionScrollPane = new JScrollPane(contentArea);

        // Create the buttons
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this::addButtonActionPerformed);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);

        // Create the panel for the form
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 0, 0));
        formPanel.add(titleLabel);
        formPanel.add(titleField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionScrollPane);

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

        setPreferredSize(new Dimension(400, 250));

        pack();
        setLocationRelativeTo(owner);
    }

    public String getTitle() {
        return titleField.getText();
    }

    public String getContent() {
        return contentArea.getText();
    }

    public boolean isClickedOk() {
        return clickedOk;
    }

    private void addButtonActionPerformed(ActionEvent e) {
        clickedOk = true;
        dispose();
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        dispose();
    }
}
