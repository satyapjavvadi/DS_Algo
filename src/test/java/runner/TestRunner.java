package runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import DriverManager.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "html:target/DS_Algo.html", "json:target/cucumber.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "rerun:target/failedrerun.txt" }, // reporting
																													// purpose

		monochrome = true, // console output color
		features = { "C:\\Users\\prash\\github\\DS_Algo\\src\\test\\resources\\features\\DataStructures.feature" }, // location
																													// of
																													// feature
																													// files
		glue = "stepdefinition") // location of step definition files
//or @refFeature

public class TestRunner extends AbstractTestNGCucumberTests {
	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void defineBrowser(String browser) {
		DriverFactory.setBrowser(browser);
	}
}
/*
 * @Override
 * 
 * @DataProvider(parallel = false) public Object[][] scenarios() {
 * 
 * return super.scenarios(); }
 * 
 * 
 * }
 */