package data.interfaces;

public interface IAppNavigation {
    public static final int WELCOME_PAGE = 0;
    public static final int LOGIN_PAGE = 1;
    public static final int SIGNUP_PAGE = 2;
    public static final int MAIN_APP_PAGE = 3;

    void showPage(int pageName);

    void run();

}