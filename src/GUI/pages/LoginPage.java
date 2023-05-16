package GUI.pages;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data.interfaces.IAppNavigation;

public class LoginPage extends JPanel {
    private IAppNavigation appNavigation;

    public LoginPage(IAppNavigation _appNavigation) {
        appNavigation = _appNavigation;

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::loginButtonActionPerformed);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        appNavigation.showMainAppPage();
    }
}
