package stepdefinition;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;

public class LaunchPageStepDefinition {

	private final PageObjectManager pom;
	WebDriver driver;

	public LaunchPageStepDefinition() {

		driver = DriverFactory.getDriver();
		pom = new PageObjectManager(driver);
		driver = DriverFactory.getDriver();

	}

	@Given("the user has a browser open")
	public void the_user_has_a_browser_open() {

		pom.getLaunchPage().BrowserIsOpen();

	}

	@When("the user enters the correct DS Algo portal URL")
	public void the_user_enters_the_correct_ds_algo_portal_url() {

		System.out.println("Current URL: " + pom.getLaunchPage().getCurrentUrl());

	}

	@Then("the user should be on the DS Algo Portal page")
	public void the_user_should_be_on_the_ds_algo_portal_page() {

		System.out.println("Current URL: " + pom.getLaunchPage().getCurrentUrl());
		Assert.assertTrue(pom.getLaunchPage().getCurrentUrl().contains("dsportalapp"),
				"User is not on the DS Algo Portal page");

	}

	@Then("the user should be able to see the content text on the Launch page")
	public void theUserShouldBeAbleToSeeTheContentTextOnTheLaunchPage(DataTable dataTable) {
		List<String> expectedTexts = dataTable.asList(String.class);
		String actualText = pom.getLaunchPage().getAllFieldSpellings();
		System.out.println("actualText = " + actualText);

		for (String expectedText : expectedTexts) {
			System.out.println("expectedText = " + expectedText);
			Assert.assertTrue(actualText.contains(expectedText), "content text is not visible");
		}
	}

	@Then("the user should be able to see {int} button on the Launch page")
	public void theUserShouldBeAbleToSeeButtonOnTheLaunchPage(int expectedButtonCount) {
		int actualButtonCount = pom.getLaunchPage().getButtonText().size();
		System.out.println("actualButtonCount = " + actualButtonCount);
		Assert.assertEquals(actualButtonCount, expectedButtonCount, "Button count does not match.");
	}

	@Then("the user should be able to see {string} copyrightInfo")
	public void the_user_should_be_able_to_see_copyrightInfo(String copyrightInfo) {

		Assert.assertTrue(pom.getLaunchPage().isCopyrightInfoVisible(), "Copyright Info is not visible");

	}

	@When("the user clicks the {string} button")
	public void the_user_clicks_the_button(String GetStartedButtonText) {

		pom.getLaunchPage().clickGetStartedButton();
		Assert.assertTrue(pom.getLaunchPage().isGetStartedButtonEnabled(), "Get Started button not clickable");

	}

	@Then("the user should be able to see button with text {string}")
	public void theUserShouldBeAbleToSeeButtonWithText(String expectedButtonText) {
		List<String> actualButtonText = pom.getLaunchPage().getButtonText();
		System.out.println("actualButtonText = " + actualButtonText);
		Assert.assertTrue(actualButtonText.contains(expectedButtonText), "Button text does not match.");
	}

}
