package GUI.pages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import GUI.components.MyButton;
import GUI.components.MyInputField;
import GUI.components.MyInputPasswordField;
import data.interfaces.IAppNavigation;

public class LoginPage extends JPanel {
    private IAppNavigation appNavigation;
    private MyInputField usernameField;
    private MyInputPasswordField passwordField;
    private MyButton signupButton;
    private MyButton loginButton;
    private MyButton backButton;

    public LoginPage(IAppNavigation _appNavigation) {
        appNavigation = _appNavigation;

        setLayout(new BorderLayout());

        // // Create constraints for center alignment
        // GridBagConstraints constraints = new GridBagConstraints();
        // constraints.gridx = 0;
        // constraints.gridy = 0;
        // constraints.weighty = 1.0;
        // constraints.anchor = GridBagConstraints.CENTER;

        // Create a panel for the signup form
        JPanel logiPanel = new JPanel(new GridLayout(3, 2, -100, 10));
        logiPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new MyInputField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new MyInputPasswordField();

        loginButton = new MyButton("Login");
        signupButton = new MyButton("Signup");
        backButton = new MyButton("Back");

        logiPanel.add(usernameLabel);
        logiPanel.add(usernameField);
        logiPanel.add(passwordLabel);
        logiPanel.add(passwordField);
        logiPanel.add(new JLabel()); // Empty label for spacing
        logiPanel.add(loginButton);

        JPanel loginWrapper = new JPanel(new FlowLayout());
        loginWrapper.add(logiPanel);

        // Add the signup and back buttons to the bottom of the LoginPage
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        buttonPanel.add(signupButton);
        buttonPanel.add(backButton);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 36));
        loginLabel.setBorder(new EmptyBorder(50, 0, 50, 0));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        mainPanel.add(loginLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(loginWrapper, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // Add action listener to the signup button
        loginButton.addActionListener(this::loginButtonActionPerformed);
        signupButton.addActionListener(this::signupButtonActionPerformed);
        backButton.addActionListener(this::backButtonActionPerformed);
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        appNavigation.showMainAppPage();
    }

    private void signupButtonActionPerformed(ActionEvent e) {
        appNavigation.showSignupPage();
    }

    private void backButtonActionPerformed(ActionEvent e) {
        appNavigation.showWelcomePage();
    }
}
