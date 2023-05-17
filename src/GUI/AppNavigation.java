package GUI;

import javax.swing.*;

import GUI.pages.*;
import data.interfaces.IAppNavigation;

public class AppNavigation implements IAppNavigation {
    private JFrame frame;
    private WelcomePage welcomePage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private MainAppPage mainAppPage;

    public AppNavigation() {
        frame = new JFrame("My Diary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        welcomePage = new WelcomePage(this);
        loginPage = new LoginPage(this);
        signupPage = new SignupPage(this);
        mainAppPage = new MainAppPage(this);

        showWelcomePage();
    }

    @Override
    public void showWelcomePage() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(welcomePage);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void showLoginPage() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(loginPage);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void showSignupPage() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(signupPage);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void showMainAppPage() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(mainAppPage);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

}
