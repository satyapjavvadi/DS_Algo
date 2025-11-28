package stepdefinition;

import org.openqa.selenium.WebDriver;
import DriverManager.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ScreenShot;

public class Hooks {

    WebDriver driver;

    @Before(order = 0)
    public void setup() {
        DriverFactory.initDriver(); // browser is already set in TestRunner
        driver = DriverFactory.getDriver();
        driver.get("https://dsportalapp.herokuapp.com/");
    }

    @Before(value = "@Getstarted", order = 1)
    public void GetstartedAction() {
        PageObjectManager pom = new PageObjectManager();
        pom.getLaunchpage().clickGetStartedButton();
    }

    @AfterStep
    public void screenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenShot.takeScreenshotAsBytes(driver);
            scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
        }
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
