package stepdefinition;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import DriverManager.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.ScreenShot;

public class Hooks {
	
	WebDriver driver;
	
	@Before
	public void setup()
	{
		Properties prop = ConfigReader.initializeProperties();
		DriverFactory.launchBrowser(prop.getProperty("browserName"));
		driver = DriverFactory.getDriver();
		


	}
	
	@AfterStep
    public void screenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = ScreenShot.takeScreenshot(driver, scenario.getName());
            // Attach screenshot to Extent report
            scenario.attach(screenshotPath.getBytes(), "image/png", "Failed Step Screenshot");
        }
    }
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
	
	
	

}
