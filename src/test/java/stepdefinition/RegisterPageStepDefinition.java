package stepdefinition;



import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ConfigReader;


public class RegisterPageStepDefinition {
	private final PageObjectManager pom;

	private final String filePath;
	private final String sheetName;

	public RegisterPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;
		filePath = ConfigReader.getProperty("xlPath");
		sheetName = ConfigReader.getProperty("sheetName");
	}

	@When("User clicks Register link in home page")
	public void user_clicks_register_link_in_home_page() {
		pom.getregisterpage().Registerlink_click();
	}

	@Then("user lands on Register page")
	public void user_lands_on_Register_page() {
		System.out.println("user is on Register page: " + pom.getregisterpage().Current_link_Check());

		Assert.assertTrue(pom.getregisterpage().Current_link_Check().contains("register"),
				"user is not on Register page");

	}

	@Given("user tries register using {string} with {string}")
	public void user_tries_register_using_with(String submissionMethod, String scenarioType) {
		pom.getregisterpage().register(submissionMethod, scenarioType);
	}

	@Then("user should be redirected to  Home Page with a message {string}")
	public void user_should_be_redirected_to_home_page_with_a_message(String expectedmessage) {
		pom.getregisterpage().waitForHomeRedirect();
		String actualMessage = pom.getHomePage().getSuccessRegisterMessage();

		Assert.assertTrue(actualMessage.contains(expectedmessage),
				"Expected message to contain: [" + expectedmessage + "] but found [" + actualMessage + "]");
	}

	@When("user tries registration {string} with {string}")
	public void user_tries_registration_with(String method, String scenarioType) {

		pom.getregisterpage().registerUser(method, scenarioType);
	}

	@Then("appropriate message {string} should be displayed")
	public void appropriate_message_should_be_displayed(String expectedmessaage) {

		String actualMessage = pom.getregisterpage().getRegisterErrorMessage();

		Assert.assertEquals(actualMessage.trim(), expectedmessaage.trim(), "Validation message mismatch");
	}

	
}
