package stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ExcelReader;

public class LoginPageStepDefinition {

	private final PageObjectManager pom;

	private final String filePath;
	private final String sheetName;

	public LoginPageStepDefinition(PageObjectManager pom) {

		this.pom = pom;

		filePath = ConfigReader.getProperty("xlPath");
		sheetName = ConfigReader.getProperty("sheetName");
	}

	// Background
	@Given("the registered user has navigated to the login page")
	public void navigateToLoginPage() {
		pom.getLoginPage().navigateToLoginPage();
		Assert.assertTrue(pom.getLoginPage().isOnLoginPage(), "Navigation failed: Not on login page");
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

	// Invalid login attempts from Excel
	@Given("the user attempts invalid login from Excel")
	public void invalidLoginFromExcel() throws IOException {
		runExcelDrivenLogin("invalid_login");
	}

	@Then("the appropriate error messages should be displayed")
	public void verifyErrorMessages() throws IOException {
		List<Map<String, String>> rows = ExcelReader.getScenarioRows(filePath, sheetName, "invalid_login");
		for (Map<String, String> row : rows) {
			Assert.assertEquals(pom.getLoginPage().getDisplayedErrorMessage(), row.get("expected_message"),
					"Mismatch for invalid login scenario: " + row.get("username") + "/" + row.get("password"));
		}
	}

	// Valid login attempts from Excel
	@Given("the user provides valid credentials from Excel")
	public void validLoginFromExcel() throws IOException {
		runExcelDrivenLogin("valid_login");
	}

	// Helper method to reduce duplication
	private void runExcelDrivenLogin(String scenarioType) throws IOException {
		List<Map<String, String>> rows = ExcelReader.getScenarioRows(filePath, sheetName, scenarioType);

		for (Map<String, String> row : rows) {
			pom.getLoginPage().navigateToLoginPage();
			pom.getLoginPage().login(row.get("username"), row.get("password"), row.get("submission_method"));

			if (scenarioType.equals("valid_login")) {
				pom.getLoginPage().waitForHomeRedirect();
				Assert.assertEquals(pom.getHomePage().getLoginSuccessMessage(), row.get("expected_message"),
						"Mismatch for valid login scenario: " + row.get("username"));
			} else {
				Assert.assertEquals(pom.getLoginPage().getDisplayedErrorMessage(), row.get("expected_message"),
						"Error message mismatch for user: " + row.get("username"));
			}
		}
	}
}