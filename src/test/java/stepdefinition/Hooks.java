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

	WebDriver driver;

	@Before (order = 0)
	public void setup() {
		Properties prop = ConfigReader.initializeProperties();
		driver = DriverFactory.launchBrowser(prop.getProperty("browserName"));

		driver.get(prop.getProperty("baseURL"));

	}
	
	@Before(value = "Getstarted", order = 1)
	
	public void GetstartedAction() {
		PageObjectManager pom = new PageObjectManager();
		pom.getLaunchpage().clickGetStartedButton();
	}
	@AfterStep
	public void screenShot(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotPath = ScreenShot.takeScreenshot(driver, scenario.getName());
			// Attach screenshot to Extent report
			scenario.attach(screenshotPath.getBytes(), "image/png", "Failed Step Screenshot");

			// File sourcePath= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
			// scenario.attach(fileContent, "image/png", "image");
		}
	}

	// INSTEAD OF SAVING SCREENSHOTS AS FILES AND THEN READING THEM, CAPTURE
	// SCREENSHOTS AS BYTES AND ATTACH THEM DIRECTLY.
	// THIS AVOIDS FILE CLUTTER AND IS FASTER

	@After
	public void tearDown() {
		driver.quit();
	}

}
