package GUI.pages;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data.interfaces.IAppNavigation;

public class SignupPage extends JPanel {
    private IAppNavigation appNavigation;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signupButton;

    public SignupPage(IAppNavigation _appNavigation) {
        appNavigation = _appNavigation;

        setLayout(new GridBagLayout());

        // Create constraints for center alignment
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;

        // Create a panel for the signup form
        JPanel signupPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        signupButton = new JButton("Signup");
        signupPanel.add(usernameLabel);
        signupPanel.add(usernameField);
        signupPanel.add(passwordLabel);
        signupPanel.add(passwordField);
        signupPanel.add(new JLabel()); // Empty label for spacing
        signupPanel.add(signupButton);

        // Add the signup panel to the center of the SignupPage
        add(signupPanel, constraints);

        // Add action listener to the signup button
        signupButton.addActionListener(this::signupButtonActionPerformed);
    }

    private void signupButtonActionPerformed(ActionEvent e) {
        // Perform signup logic here
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // You can replace the print statements with your own logic
        System.out.println("Signup:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // After signup logic, navigate to the main app page
        appNavigation.showMainAppPage();
    }

}
