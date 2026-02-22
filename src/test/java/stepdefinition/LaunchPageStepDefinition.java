package stepdefinition;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;

public class LaunchPageStepDefinition {

	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(LaunchPageStepDefinition.class);

	public LaunchPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;
	}

	@Given("the user has a browser open")
	public void the_user_has_a_browser_open() {
		pom.getLaunchPage().browserIsOpen();
		logger.info("Browser is open and ready");
	}

	@When("the user enters the correct DS Algo portal URL")
	public void the_user_enters_the_correct_ds_algo_portal_url() {
		String currentUrl = pom.getLaunchPage().getCurrentUrl();
		logger.info("Navigated to URL: {}", currentUrl);
	}

	@Then("the user should be on the DS Algo Portal page")
	public void the_user_should_be_on_the_ds_algo_portal_page(DataTable dataTable) {
		String expectedTextInURL = dataTable.cell(0, 0);
		String currentUrl = pom.getLaunchPage().getCurrentUrl();
		logger.debug("Current URL: {}, Expected to contain: {}", currentUrl, expectedTextInURL);
		Assert.assertTrue(currentUrl.contains(expectedTextInURL), "User is not on the DS Algo Portal page");
		logger.info("Verified user is on DS Algo Portal page");
	}

	@Then("the user should be able to see the content text on the Launch page")
	public void theUserShouldBeAbleToSeeTheContentTextOnTheLaunchPage(DataTable dataTable) {
		List<String> expectedTexts = dataTable.asList(String.class);
		for (String expectedText : expectedTexts) {
			boolean visible = pom.getLaunchPage().doesPageContainText(expectedText);
			logger.debug("Checking visibility of content text: '{}' => {}", expectedText, visible);
			Assert.assertTrue(visible, "Content text not visible: " + expectedText);
		}
		logger.info("Verified all expected content texts are visible on Launch page");
	}

	@Then("the user should be able to see {int} button on the Launch page")
	public void theUserShouldBeAbleToSeeButtonOnTheLaunchPage(int expectedButtonCount) {
		int actualButtonCount = pom.getLaunchPage().getButtonCount();
		logger.debug("Expected button count: {}, Actual button count: {}", expectedButtonCount, actualButtonCount);
		Assert.assertEquals(actualButtonCount, expectedButtonCount, "Button count does not match.");
		logger.info("Verified button count on Launch page matches expected count");
	}

	@Then("the user should be able to see button with text {string}")
	public void theUserShouldBeAbleToSeeButtonWithText(String expectedButtonText) {
		boolean isPresent = pom.getLaunchPage().doesPageContainButtonWithText(expectedButtonText);
		logger.debug("Checking if button with text '{}' is present: {}", expectedButtonText, isPresent);
		Assert.assertTrue(isPresent, "Button text does not match: " + expectedButtonText);
		logger.info("Verified button with text '{}' is visible on Launch page", expectedButtonText);
	}

	// *****************************************************************************//
	@When("the user clicks the {string} button")
	public void the_user_clicks_the_button(String buttonText) {
		pom.getLaunchPage().clickButtonByText(buttonText);
		boolean isEnabled = pom.getLaunchPage().isButtonEnabled(buttonText);
		logger.info("Clicked button '{}' | Enabled status: {}", buttonText, isEnabled);
		Assert.assertTrue(isEnabled, "Button not clickable: " + buttonText);
	}

}