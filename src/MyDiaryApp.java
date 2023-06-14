import GUI.AppNavigation;
import data.DB.Database;
import data.interfaces.IAppNavigation;

public class MyDiaryApp {

    public static void main(String[] args) {
        new Database();
        IAppNavigation appNavigation = new AppNavigation();
        appNavigation.run();
    }
}
