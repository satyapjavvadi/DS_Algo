package stepdefinition;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ElementUtil;
import utils.TestContext;

public class LoginPageStepDefinition {

	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(LoginPageStepDefinition.class);

	public LoginPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;
	}

	// Background step
	@Given("the registered user has navigated to the home page")
	public void navigateTohomePage() {
		String currentUrl = ElementUtil.getURL();
		logger.info("Navigated to Home Page URL: {}", currentUrl);
	}

	@When("the user clicks sign in link")
	public void clickSignIn() {
		pom.getHomePage().clickSignInButton();
		logger.info("Clicked Sign In link on Home Page");
	}

	// Scenario step
	@When("the user {string} with {string}")
	public void preformsLogin(String submissionMethod, String scenarioType) {
		logger.info("Performing login using method '{}' for scenario '{}'", submissionMethod, scenarioType);
		pom.getLoginPage().login(submissionMethod, scenarioType);
	}

	@Then("the user should be redirected to the Home Page with a message {string}")
	public void verifyLoginSuccess(String expectedMessage) {
		pom.getLoginPage().waitForHomeRedirect();
		String actualMessage = pom.getHomePage().getLoginSuccessMessage();
		logger.info("Expected login success message: '{}', Actual: '{}'", expectedMessage, actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage, "Login success message mismatch");
	}

	// Register link visibility
	@When("the login page is displayed")
	public void verifyLoginPageVisible() {
		boolean visible = pom.getLoginPage().isLoginPageVisible();
		logger.info("Login page visibility: {}", visible);
		Assert.assertTrue(visible, "Login page is not visible");
	}

	@Then("the user should be able to see the {string} link")
	public void verifyRegisterLink(String linkText) {
		boolean visible = pom.getLoginPage().isRegisterLinkVisible();
		logger.info("Register link '{}' visibility: {}", linkText, visible);
		Assert.assertTrue(visible, "Register link not visible");
	}

	@Then("the appropriate error messages should be displayed in {string}")
	public void verifyErrorMessages(String expectedInField) throws IOException {
		String actualErrorMessage = pom.getLoginPage().getDisplayedErrorMessage(expectedInField);
		String expectedErrorMessage = TestContext.testData.get("expected_message");

		logger.info("Verifying error message for field '{}'. Expected: '{}', Actual: '{}'",
				expectedInField, expectedErrorMessage, actualErrorMessage);

		Assert.assertEquals(actualErrorMessage, expectedErrorMessage,
				"Mismatch for invalid login scenario: "
						+ TestContext.testData.get("username") + "/" + TestContext.testData.get("password"));
	}

	// Input field validations
	@Then("User must see {int} input fields in Login UI")
	public void User_must_see_input_fields_in_Login_ui(Integer expectedLabelCount) {
		int actualCount = pom.getLoginPage().getInputFieldCount();
		logger.info("Login page input field count: {}, Expected: {}", actualCount, expectedLabelCount);
		Assert.assertEquals(actualCount, expectedLabelCount, "Input field count mismatch");
	}

	@Then("User must see {int} button in Login UI")
	public void user_must_see_button_in_Login_ui(Integer expectedButtonCount) {
		int actualCount = pom.getLoginPage().getButtonCount();
		logger.info("Login page button count: {}, Expected: {}", actualCount, expectedButtonCount);
		Assert.assertEquals(actualCount, expectedButtonCount, "Button count mismatch in Login page");
	}

	@Then("User should be able to see button with text {string} in Login UI")
	public void user_should_be_able_to_see_button_with_text_in_Login_ui(String expectedText) {
		List<String> actualButtons = pom.getLoginPage().getButtonText();
		logger.info("Checking button text presence. Expected: '{}', Actual: {}", expectedText, actualButtons);
		Assert.assertTrue(actualButtons.contains(expectedText),
				"Button text mismatch. Expected: " + expectedText + ", Actual: " + actualButtons);
	}

	@Then("User must see labels with text in Login UI")
	public void user_must_see_labels_with_text_in_Login_ui(DataTable dataTable) {
		List<String> expectedLabels = dataTable.asList();
		List<String> actualLabels = pom.getLoginPage().getLoginLabelNames();

		logger.info("Verifying Login UI labels. Expected: {}, Actual: {}", expectedLabels, actualLabels);
		Assert.assertEquals(actualLabels, expectedLabels,
				"Label text mismatch. Expected: " + expectedLabels + ", Actual: " + actualLabels);
	}

}