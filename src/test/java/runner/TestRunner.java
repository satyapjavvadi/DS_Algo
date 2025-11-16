package runner;


import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        plugin = {"pretty",
                "html:target/DS_Algo.html", "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failedrerun.txt"
        }, //reporting purpose
         
        monochrome = true,  //console output color
         //which tag to run
        features = {"C:\\Users\\prash\\github\\DS_Algo\\src\\test\\resources\\features\\DataStructures.feature"},//location of feature files
        glue = "stepdefinition")//location of step definition files
        


public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {

        return super.scenarios();
    }


}
