package pages;

import DriverManager.DriverFactory;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private final WebDriver driver;
    private LaunchPage launchPage;

    public PageObjectManager() {
        this.driver = DriverFactory.getDriver();;


    }

    public LaunchPage getLaunchpage() {
        if(launchPage == null){
            launchPage = new LaunchPage(driver);
        }

        return launchPage;
    }
}
