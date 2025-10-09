package stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LaunchPage;

public class LaunchPageStepDefinition {

	WebDriver driver = DriverFactory.getDriver();
	LaunchPage launchPage = new LaunchPage(DriverFactory.getDriver());

	@Given("the user has a browser open")
	public void the_user_has_a_browser_open() {

		launchPage.verifyBrowserIsOpen();

	}

	@When("the user enters the correct DS Algo portal URL")
	public void the_user_enters_the_correct_ds_algo_portal_url() {

		launchPage.verifyLaunchUrl();

	}

	@Then("the user should be able to see the DS Algo portal")
	public void the_user_should_be_able_to_see_the_ds_algo_portal() {

		Assert.assertTrue(launchPage.isPreparingTextVisible(), "Interview prep text not visible");

	}

	@Given("the user is on the DS Algo Portal page")
	public void the_user_is_on_the_ds_algo_portal_page() {

		launchPage.verifyLaunchUrl();

	}

	@When("the launch page loads")
	public void the_launch_page_loads() {
		Assert.assertTrue(launchPage.isPreparingTextVisible(), "Interview prep text not visible");

	}

	@Then("the user should be able to see {string}")
	public void the_user_should_be_able_to_see(String prepInterviews) {

		Assert.assertTrue(launchPage.isPreparingTextVisible(), "Text '" + prepInterviews + "' not visible");

	}

	@Then("the user should be able to see {string} button")
	public void the_user_should_be_able_to_see_button(String getStartedText) {

		Assert.assertTrue(launchPage.isGetStartedButtonVisible(getStartedText),
				"Button '" + getStartedText + "' not visible");

	}

	@Then("the user should be able to see {string} copyrightInfo")
	public void the_user_should_be_able_to_see_copyrightInfo(String copyrightInfo) {

		WebElement copyright = driver.findElement(By.xpath("//*[contains(text(),'Copyright@NumpyNinja2021')]"));
		Assert.assertTrue(copyright.isDisplayed(), "Text '" + copyrightInfo + " ' not visible");

	}

	@When("the user clikcs the {string} button")
	public void the_user_clikcs_the_button(String GetStartedButtonText) {

		launchPage.clickGetStartedButton(GetStartedButtonText);
		Assert.assertTrue(launchPage.isGetStartedButtonEnabled(GetStartedButtonText),
				"Get Started button not clickable");

	}

	@Then("the user should be navigated to home page")
	public void the_user_should_be_navigated_to_home_page() {

		launchPage.getCurrentUrl();
	}

}
