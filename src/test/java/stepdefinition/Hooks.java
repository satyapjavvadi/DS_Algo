package stepdefinition;

import java.io.IOException;

import java.util.Properties;

import io.cucumber.java.*;


import DriverManager.DriverFactory;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ExcelReader;

import utils.ScreenShot;


public class Hooks {

	
	PageObjectManager pom;
	@Before(order = 0)
	public void setup() {

		Properties prop = ConfigReader.initializeProperties();
		
		  ExcelReader.readDataFromExcel( prop.getProperty("xlPath"),
		  prop.getProperty("loginsheetName") );
		 
		String browser = System.getProperty(
				"browserName",
				prop.getProperty("browserName")
		);

	DriverFactory.launchBrowser(browser);

		
		DriverFactory.getDriver().get(prop.getProperty("baseURL"));
		

	}

	@Before(value = "@Getstarted", order = 1)
	public void GetstartedAction() {
		 //WebDriver driver = DriverFactory.getDriver();
	     pom = new PageObjectManager();
		pom.getLaunchPage().clickGetStartedButton();
	}
	@Before(value = "@negativeRegister", order = 1)
	public void loadRegisternegativeFunctionalData() {
	    Properties prop = ConfigReader.initializeProperties();
	    ExcelReader.readDataFromExcel(
	            prop.getProperty("xlPath"),
	            prop.getProperty("RegisterfunctionalsheetName"));
	}
	
	@Before(value = "@positiveRegister", order = 1)
	public void loadRegisterpositiveFunctionalData() {
	    Properties prop = ConfigReader.initializeProperties();
	    ExcelReader.readDataFromExcel(
	            prop.getProperty("xlPath"),
	            prop.getProperty("RegisterfunctionalsheetName"));
	}
	
	@Before(value = "@queueEditor", order = 3)
	public void queueEditorData() {
	    Properties prop = ConfigReader.initializeProperties();
	    ExcelReader.readDataFromExcel(
	            prop.getProperty("xlPath"),
	            prop.getProperty("QueuePage"));
	}
	
	@Before(value = "@Treetopics", order = 3)
	public void Treepageurl() {
	    Properties prop = ConfigReader.initializeProperties();
	    ExcelReader.readDataFromExcel(
	            prop.getProperty("xlPath"),
	            prop.getProperty("TreePage"));
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
