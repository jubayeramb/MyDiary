package GUI.pages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.components.MyButton;
import GUI.components.MyInputField;
import GUI.components.MyInputPasswordField;
import data.interfaces.IAppNavigation;

public class SignupPage extends JPanel {
    private IAppNavigation appNavigation;
    private MyInputField firstNameField;
    private MyInputField lastNameField;
    private MyInputField emailField;
    private MyInputField phoneField;
    private MyInputPasswordField passwordField;
    private MyInputPasswordField confirmPasswordField;
    private MyButton signupButton;
    private MyButton loginButton;
    private MyButton backButton;

    public SignupPage(IAppNavigation _appNavigation) {
        appNavigation = _appNavigation;

        // setLayout(new GridBagLayout());

        // GridBagConstraints constraints = new GridBagConstraints();
        // constraints.gridx = 0;
        // constraints.gridy = 0;
        // constraints.weighty = 1.0;
        // constraints.anchor = GridBagConstraints.CENTER;

        setLayout(new BorderLayout());

        JLabel signupLabel = new JLabel("Signup");
        signupLabel.setFont(new Font("Arial", Font.BOLD, 36));
        signupLabel.setHorizontalAlignment(JLabel.CENTER);
        signupLabel.setBorder(new EmptyBorder(0, 0, 50, 0));

        JPanel signupPanel = new JPanel(new GridLayout(8, 2, -100, 10));
        JPanel signupPanelWrapper = new JPanel(new FlowLayout());

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new MyInputField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new MyInputField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new MyInputField();
        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new MyInputField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new MyInputPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new MyInputPasswordField();

        signupButton = new MyButton("Signup");
        loginButton = new MyButton("Login");
        backButton = new MyButton("Back");

        signupPanel.add(firstNameLabel);
        signupPanel.add(firstNameField);
        signupPanel.add(lastNameLabel);
        signupPanel.add(lastNameField);
        signupPanel.add(emailLabel);
        signupPanel.add(emailField);
        signupPanel.add(phoneLabel);
        signupPanel.add(phoneField);
        signupPanel.add(passwordLabel);
        signupPanel.add(passwordField);
        signupPanel.add(confirmPasswordLabel);
        signupPanel.add(confirmPasswordField);
        signupPanel.add(new JLabel()); // Empty label for spacing
        signupPanel.add(signupButton);

        signupPanelWrapper.add(signupPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(backButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(signupLabel, BorderLayout.NORTH);
        mainPanel.add(signupPanelWrapper, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        add(mainPanel, BorderLayout.CENTER);

        signupButton.addActionListener(this::signupButtonActionPerformed);
        loginButton.addActionListener(this::loginButtonActionPerformed);
        backButton.addActionListener(this::backButtonActionPerformed);
    }

    public void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public void setFocusOnFirstNameField() {
        firstNameField.requestFocus();
    }

    public void setSignupButtonEnabled(boolean enabled) {
        signupButton.setEnabled(enabled);
    }

    private void signupButtonActionPerformed(ActionEvent e) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Perform signup logic here
        // You can replace the print statements with your own logic
        System.out.println("Signup:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confirmPassword);

        appNavigation.showMainAppPage();
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        appNavigation.showLoginPage();
    }

    private void backButtonActionPerformed(ActionEvent e) {
        appNavigation.showWelcomePage();
    }

}
