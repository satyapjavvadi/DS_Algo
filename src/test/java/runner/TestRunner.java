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
      //  tags = "@Sing",
        features = {"C:\\Users\\prash\\github\\DS_Algo\\src\\test\\resources\\features"},//location of feature files
        glue = "stepdefinition")//location of step definition files
       

       // tags = "@Registernonfunctional or @Getstarted",//location of feature files
//or @refFeature

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {

        return super.scenarios();
    }


}