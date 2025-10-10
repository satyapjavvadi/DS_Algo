package pages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private final WebDriver driver;
    private LaunchPage launchPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;


    }

    public LaunchPage getLaunchpage() {
        if(launchPage == null){
            launchPage = new LaunchPage(driver);
        }

        return launchPage;
    }
}
