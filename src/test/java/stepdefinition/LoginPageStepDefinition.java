package stepdefinition;

import java.io.IOException;
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
import utils.ConfigReader;
import utils.ElementUtil;
import utils.TestContext;

public class LoginPageStepDefinition {

	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

	private final String filePath;
	private final String sheetName;

	public LoginPageStepDefinition(PageObjectManager pom) {

		this.pom = pom;

		filePath = ConfigReader.getProperty("xlPath");
		sheetName = ConfigReader.getProperty("sheetName");
	}

	// Background
	@Given("the registered user has navigated to the home page")
	public void navigateTohomePage() {
		logger.info(ElementUtil.getURL());
	}

	@When("the user clicks sign in link")
	public void clickSignIn() {
		pom.getHomePage().clickSignInButton();
	}

	// Scenario starts here
	@When("the user {string} with {string}")
	public void preformsLogin(String submissionMethod, String scenarioType) {

		pom.getLoginPage().login(submissionMethod, scenarioType);
	}

	@Then("the user should be redirected to the Home Page with a message {string}")
	public void verifyLoginSuccess(String expectedMessage) {
		pom.getLoginPage().waitForHomeRedirect();
		Assert.assertEquals(pom.getHomePage().getLoginSuccessMessage(), expectedMessage);
	}

	// Register link visibility
	@When("the login page is displayed")
	public void verifyLoginPageVisible() {
		Assert.assertTrue(pom.getLoginPage().isLoginPageVisible(), "Login page is not visible");
	}

	@Then("the user should be able to see the {string} link")
	public void verifyRegisterLink(String linkText) {
		Assert.assertTrue(pom.getLoginPage().isRegisterLinkVisible(), "Register link not visible");
	}

	@Then("the appropriate error messages should be displayed in {string}")
	public void verifyErrorMessages(String expectedInField) throws IOException {

		Assert.assertEquals(pom.getLoginPage().getDisplayedErrorMessage(expectedInField),
				TestContext.testData.get("expected_message"), "Mismatch for invalid login scenario: "
						+ TestContext.testData.get("username") + "/" + TestContext.testData.get("password"));
	}

	// Valid login attempts from Excel
	@Given("the user provides valid credentials from Excel")
	public void validLoginFromExcel() throws IOException {
		// runExcelDrivenLogin("valid_login");
	}

	// Non functional testcases

	@Then("User must see {int} input fields in Login UI")
	public void User_must_see_input_fields_in_Login_ui(Integer expectedlabelcount) {
		int actuallabel_count = pom.getLoginPage().getInputFieldCount();
		logger.info("total input field in Login page is :" + actuallabel_count);
		Assert.assertEquals(actuallabel_count, expectedlabelcount, "label count mismatch");
	}

	@Then("User must see {int} button in Login UI")
	public void user_must_see_button_in_Login_ui(Integer expectedbuttoncount) {
		int actualbuttoncount = pom.getLoginPage().getButtonCount();
		logger.info("total buttons in Login page is :" + actualbuttoncount);
		Assert.assertEquals(actualbuttoncount, expectedbuttoncount, "Button count in Register page is not 1");
	}

	@Then("User should be able to see button with text {string} in Login UI")
	public void user_should_be_able_to_see_button_with_text_in_Login_ui(String expectedtext) {
		List<String> actualButtons = pom.getLoginPage().getButtonText();
		Assert.assertTrue(actualButtons.contains(expectedtext),
				"button text mismatch , Expected: " + expectedtext + " but found: " + actualButtons);
		logger.info("button label present in Login ui is:" + actualButtons);
	}

	@Then("User must see labels with text in Login UI")
	public void user_must_see_labels_with_text_in_Login_ui(DataTable dataTable) {

		List<String> expectedlabel_names = dataTable.asList();
		Assert.assertEquals(pom.getLoginPage().getLoginLabelNames(), expectedlabel_names,
				"Label text mismatch , Expected: " + expectedlabel_names + " but found: "
						+ pom.getLoginPage().getLoginLabelNames());

	}

}
