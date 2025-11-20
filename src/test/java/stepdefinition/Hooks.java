package stepdefinition;

import java.util.Properties;

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

	private WebDriver driver;

	@Before(order = 0)
	public void setup() {
		Properties prop = ConfigReader.initializeProperties();
		driver = DriverFactory.launchBrowser(prop.getProperty("browserName"));

		driver.get(prop.getProperty("baseURL"));

	}

	@Before(value = "@Getstarted", order = 1)
	public void GetstartedAction() {
		// WebDriver driver = DriverFactory.getDriver();
		PageObjectManager pom = new PageObjectManager(driver);
		pom.getLaunchPage().clickGetStartedButton();
	}

	@AfterStep
	public void screenShot(Scenario scenario) {
		if (scenario.isFailed()) {
			// Capture screenshot as bytes
			byte[] screenshot = ScreenShot.takeScreenshotAsBytes(driver, scenario.getName());

			// Attach directly to Cucumber report
			scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
		}
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
