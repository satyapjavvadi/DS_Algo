package runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/DS_Algo.html", "json:target/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "rerun:target/failedrerun.txt"}, // reporting purpose
        monochrome = true, // console output color
        features = {"src/test/resources/features"}, // or @Getstarted",//location of feature files
        tags = "@Registerfeature",
        glue = "stepdefinition") // location of step definition files

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {

        return super.scenarios();
    }

    @BeforeClass
    @Parameters("browserName")
    public void setBrowser(@Optional("chrome") String browserName) {
        System.setProperty("browserName", browserName);
    }

}
