package GUI.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.components.MyButton;
import data.interfaces.IAppNavigation;

public class WelcomePage extends JPanel {
    private IAppNavigation appNavigation;
    private JLabel imageLabel;

    public WelcomePage(IAppNavigation appNavigation) {
        this.appNavigation = appNavigation;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create the welcome label
        JLabel welcomeLabel = new JLabel("My Diary!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);

        // Create the image label
        imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/images/diary1.png"));
        imageLabel.setIcon(imageIcon);

        // Create the login button
        MyButton loginButton = new MyButton("Login");
        loginButton.addActionListener(this::loginButtonActionPerformed);

        // Create the signup button
        MyButton signupButton = new MyButton("Signup");
        signupButton.addActionListener(this::signupButtonActionPerformed);

        // Create the panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        JPanel layoutPanel = new JPanel(new GridBagLayout());
        layoutPanel.setBackground(Color.WHITE);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        layoutPanel.add(imageLabel, constraints);
        layoutPanel.setMaximumSize(new Dimension(600, 400));
        add(welcomeLabel, BorderLayout.NORTH);
        add(layoutPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeImage();
            }
        });
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        appNavigation.showLoginPage();
    }

    private void signupButtonActionPerformed(ActionEvent e) {
        appNavigation.showSignupPage();
    }

    private void resizeImage() {
        ImageIcon imageIcon = (ImageIcon) imageLabel.getIcon();
        if (imageIcon != null) {
            // Get the current size of the image label
            int width = imageLabel.getWidth();
            int height = imageLabel.getHeight();

            // Scale the image to fit the label's size while preserving aspect ratio
            ImageIcon scaledImageIcon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
            imageIcon.getImage().flush();
            imageLabel.setIcon(scaledImageIcon);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 400); // Set the preferred size of the panel
    }
}
