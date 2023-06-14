package GUI.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
        super(owner, "Add Note", true);
        setLayout(new BorderLayout());
        createUIComponents("Add");
        pack();
        setLocationRelativeTo(owner);
    }

    public NoteInputDialog(Frame owner, String title, String content) {
        super(owner, "Update Note", true);
        setLayout(new BorderLayout());
        createUIComponents("Update");
        titleField.setText(title);
        contentArea.setText(content);
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

    private void createUIComponents(String btnText) {
        // Create form components
        JLabel titleLabel = new JLabel("Title:");
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(titleLabel);
        titleField = new MyInputField();

        JLabel contJLabel = new JLabel("Content:");
        JPanel contentLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentLabelPanel.add(contJLabel);

        contentArea = new MyTextAreaField();
        JScrollPane descriptionScrollPane = new JScrollPane(contentArea);
        descriptionScrollPane.setPreferredSize(new Dimension(350, 300));

        // Create the buttons
        JButton addButton = new JButton(btnText.isEmpty() ? "Add" : btnText);
        addButton.addActionListener(this::addButtonActionPerformed);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(titlePanel);
        formPanel.add(titleField);
        formPanel.add(new JLabel(" "));
        formPanel.add(contentLabelPanel);
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

        setPreferredSize(new Dimension(400, 450));
    }

}
