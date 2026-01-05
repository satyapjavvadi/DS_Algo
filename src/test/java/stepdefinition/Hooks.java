package stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.ExcelReader;
import utils.ScreenShot;
import utils.TestContext;

public class Hooks {

	private  WebDriver driver;
	PageObjectManager pom;
	@Before(order = 0)
	public void setup() {

		Properties prop = ConfigReader.initializeProperties();
		ExcelReader.readDataFromExcel(
				prop.getProperty("xlPath"),
				prop.getProperty("loginsheetName")
		);

		String browser = System.getProperty(
				"browserName",
				prop.getProperty("browserName")
		);

		DriverFactory.launchBrowser(browser);
		DriverFactory.getDriver().get(prop.getProperty("baseURL"));
		DriverFactory.launchBrowser(browser);
		DriverFactory.getDriver().get(prop.getProperty("baseURL"));

	}

	@Before(value = "@Getstarted", order = 1)
	public void GetstartedAction() {
		// WebDriver driver = DriverFactory.getDriver();
	     pom = new PageObjectManager();
		pom.getLaunchPage().clickGetStartedButton();
	}

	@Before(value = "@Login", order = 2)
	public void performLogin() throws IOException {
		pom.getHomePage().clickSignInButton();
		pom.getLoginPage().login(" Submits the login form ","valid_login");

	}

	@AfterStep
	public void screenShot(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot =
					ScreenShot.takeScreenshotAsBytes(
							DriverFactory.getDriver(),
							scenario.getName()
					);

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
