package GUI.pages;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.interfaces.IAppNavigation;

public class WelcomePage extends JPanel {
    private IAppNavigation appNavigation;

    public WelcomePage(IAppNavigation _appNavigation) {
        appNavigation = _appNavigation;

        setLayout(new GridBagLayout());

        // Create constraints for center alignment
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;

        // Create a panel for the welcome message
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome to My Diary!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);

        // Add the welcome panel to the center of the WelcomePage
        add(welcomePanel, constraints);

        // Create constraints for button alignment
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Signup");
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        // Add the button panel below the welcome panel
        add(buttonPanel, constraints);

        // Add action listeners to the buttons
        loginButton.addActionListener(this::loginButtonActionPerformed);
        signupButton.addActionListener(this::signupButtonActionPerformed);
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        appNavigation.showLoginPage();
    }

    private void signupButtonActionPerformed(ActionEvent e) {
        appNavigation.showSignupPage();
    }

}
