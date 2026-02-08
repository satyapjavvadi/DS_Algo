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
import utils.ElementUtil;
import utils.ExcelReader;
import utils.TestContext;

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
	@Given("the registered user has navigated to the home page")//glued
	public void navigateTohomePage() {
		System.out.println(ElementUtil.getURL());
	}

	@When("the user clicks sign in link")
	public void clickSignIn(){
		pom.getHomePage().clickSignInButton();
	}

	//Scenario starts here
	@When("the user {string} with {string}")
	public void preformsLogin(String submissionMethod , String scenarioType){

		pom.getLoginPage().login( submissionMethod ,  scenarioType);
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

       Assert.assertEquals(pom.getLoginPage().getDisplayedErrorMessage(expectedInField), TestContext.testData.get("expected_message"),
			   "Mismatch for invalid login scenario: " + TestContext.testData.get("username") + "/" + TestContext.testData.get("password"));
	}

	// Valid login attempts from Excel
	@Given("the user provides valid credentials from Excel")
	public void validLoginFromExcel() throws IOException {
		//runExcelDrivenLogin("valid_login");
	}



    // Helper method to reduce duplication

}
