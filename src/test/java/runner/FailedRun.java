package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "html:target/DS_Algo.html", "json:target/cucumber.json",
                "rerun:target/failedrerun.txt"
        }, //reporting purpose
        monochrome = true,  //console output color
        features = {"@target/failedrerun.txt"}, //location of feature files
        glue = "stepdefinition") //location of step definition files


public class FailedRun extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {

        return super.scenarios();
    }
}


