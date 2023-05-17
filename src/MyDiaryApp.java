import GUI.AppNavigation;
import data.interfaces.IAppNavigation;

public class MyDiaryApp {

    public static void main(String[] args) {
        IAppNavigation appNavigation = new AppNavigation();
        appNavigation.run();
    }
}
