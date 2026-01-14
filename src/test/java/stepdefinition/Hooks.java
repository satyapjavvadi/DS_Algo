package stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import DriverManager.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.ScreenShot;

public class Hooks {

	private WebDriver driver;

	@Before(order = 0)
	public void setup() {
		// Load config file
		Properties prop = ConfigReader.initializeProperties();

		// Prefer TestNG parameter (set in TestRunner via System.setProperty),
		// else fallback to config.properties
		String browser = System.getProperty("browserName", prop.getProperty("browserName"));

		// Launch browser via DriverFactory
		driver = DriverFactory.launchBrowser(browser);

		// Navigate to bsdr URL
		driver.get(prop.getProperty("baseURL"));

	}

	@Before(value = "@Getstarted", order = 1)
	public void GetstartedAction() {
		// WebDriver driver = DriverFactory.getDriver();
		PageObjectManager pom = new PageObjectManager();
		pom.getLaunchPage().clickGetStartedButton();
	}

	@Before(value = "@Login", order = 2)
	public void performLogin() throws IOException {
		PageObjectManager pom = new PageObjectManager();

		String filePath = ConfigReader.getProperty("xlPath");
		String sheetName = ConfigReader.getProperty("sheetName");

		// Get first valid_login row from Excel
		List<Map<String, String>> rows = ExcelReader.getScenarioRows(filePath, sheetName, "valid_login");

		Map<String, String> row = rows.get(0);

		String username = row.get("username");
		String password = row.get("password");
		String method = row.get("submission_method");
		String expected = row.get("expected_message");

		// Reuse Page Object helpers
		pom.getLoginPage().navigateToLoginPage();
		pom.getLoginPage().login(username, password, method);

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
	public  void tearDown() {
		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
			DriverFactory.mydriver.remove(); //
		}
	}

}

