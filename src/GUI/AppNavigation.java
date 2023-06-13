package GUI;

import javax.swing.*;

import GUI.pages.*;
import data.interfaces.IAppNavigation;

public class AppNavigation extends JFrame implements IAppNavigation {
    private WelcomePage welcomePage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private MainAppPage mainAppPage;

    public AppNavigation() {
        super("My Diary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/assets/images/diary.png"));
        setIconImage(imageIcon.getImage());

        welcomePage = new WelcomePage(this);
        loginPage = new LoginPage(this);
        signupPage = new SignupPage(this);
        mainAppPage = new MainAppPage(this);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                welcomePage.resizeImage();
            }
        });

        showPage(WELCOME_PAGE);
    }

    @Override
    public void showPage(int pageName) {
        JPanel pageToShow = null;
        switch (pageName) {
            case WELCOME_PAGE:
                pageToShow = welcomePage;
                break;
            case LOGIN_PAGE:
                pageToShow = loginPage;
                break;
            case SIGNUP_PAGE:
                pageToShow = signupPage;
                break;
            case MAIN_APP_PAGE:
                pageToShow = mainAppPage;
                break;
        }

        if (pageToShow != null) {
            getContentPane().removeAll();
            getContentPane().add(pageToShow);
            revalidate();
            repaint();
        }
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }
}
