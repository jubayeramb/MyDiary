package GUI.pages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import GUI.components.MyButton;
import GUI.components.NoteTab;
import GUI.components.TaskTab;
import data.interfaces.IAppNavigation;

public class MainAppPage extends JPanel {
    private IAppNavigation appNavigation;
    private JTabbedPane tabbedPane;
    private TaskTab taskTab;
    private NoteTab noteTab;
    private MyButton logoutButton;
    private MyButton refreshButton;

    public MainAppPage(IAppNavigation _appNavigation) {
        appNavigation = _appNavigation;

        setLayout(new BorderLayout());

        // Create a customized JTabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 16));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setBackground(Color.DARK_GRAY);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the Task tab
        taskTab = new TaskTab();
        tabbedPane.addTab("Task", null, taskTab, "Task");

        // Create the Note tab
        noteTab = new NoteTab();
        tabbedPane.addTab("Note", null, noteTab, "Note");

        // Create the logout button
        logoutButton = new MyButton("Logout");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        // Create the refresh button
        refreshButton = new MyButton("Refresh");
        refreshButton.addActionListener(this::refreshButtonActionPerformed);

        // Create the panel for the bottom buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(refreshButton);
        buttonPanel.add(logoutButton);

        // Add the tabbed pane to the main panel
        add(tabbedPane, BorderLayout.CENTER);
        // Add the button panel to the bottom of the main panel
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        appNavigation.logout();
        appNavigation.showPage(IAppNavigation.WELCOME_PAGE);
    }

    private void refreshButtonActionPerformed(ActionEvent e) {
        // TODO: Implement the action for refreshing the task and note tabs
    }
}
