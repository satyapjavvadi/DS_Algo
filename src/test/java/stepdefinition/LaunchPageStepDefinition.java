package stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.PageObjectManager;

public class LaunchPageStepDefinition {

	private final PageObjectManager pom;
	WebDriver driver ;


	public LaunchPageStepDefinition() {

		pom = new PageObjectManager();

	}


	@Given("the user has a browser open")
	public void the_user_has_a_browser_open() {

		pom.getLaunchpage().verifyBrowserIsOpen();

	}

	@When("the user enters the correct DS Algo portal URL")
	public void the_user_enters_the_correct_ds_algo_portal_url() {

		pom.getLaunchpage().verifyLaunchUrl();

	}

	@Then("the user should be able to see the DS Algo portal")
	public void the_user_should_be_able_to_see_the_ds_algo_portal() {

		Assert.assertTrue(pom.getLaunchpage().isPreparingTextVisible(), "Interview prep text not visible");

	}

	@Given("the user is on the DS Algo Portal page")
	public void the_user_is_on_the_ds_algo_portal_page() {

		pom.getLaunchpage().verifyLaunchUrl();

	}

	@When("the launch page loads")
	public void the_launch_page_loads() {
		Assert.assertTrue(pom.getLaunchpage().isPreparingTextVisible(), "Interview prep text not visible");

	}

	@Then("the user should be able to see {string}")
	public void the_user_should_be_able_to_see(String prepInterviews) {

		Assert.assertTrue(pom.getLaunchpage().isPreparingTextVisible(), "Text '" + prepInterviews + "' not visible");

	}

	@Then("the user should be able to see {string} button")
	public void the_user_should_be_able_to_see_button(String getStartedText) {

		Assert.assertTrue(pom.getLaunchpage().isGetStartedButtonVisible(getStartedText),
				"Button '" + getStartedText + "' not visible");

	}

	@Then("the user should be able to see {string} copyrightInfo")
	public void the_user_should_be_able_to_see_copyrightInfo(String copyrightInfo) {

		WebElement copyright = driver.findElement(By.xpath("//*[contains(text(),'Copyright@NumpyNinja2021')]"));
		Assert.assertTrue(copyright.isDisplayed(), "Text '" + copyrightInfo + " ' not visible");

	}

	@When("the user clicks the {string} button")
	public void the_user_clikcs_the_button(String GetStartedButtonText) {

		pom.getLaunchpage().clickGetStartedButton();
		Assert.assertTrue(pom.getLaunchpage().isGetStartedButtonEnabled(GetStartedButtonText),
				"Get Started button not clickable");

	}

	@Then("the user should be navigated to home page")
	public void the_user_should_be_navigated_to_home_page() {

		pom.getLaunchpage().getCurrentUrl();
	}


}
