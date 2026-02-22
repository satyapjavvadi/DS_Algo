package stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import DriverManager.DriverFactory;
import pages.PageObjectManager;

import java.io.IOException;
import java.util.List;

public class RegisterPageStep {
	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(RegisterPageStep.class);

	public RegisterPageStep(PageObjectManager pom) {
		this.pom = pom;

	}

	// Functional Steps ******************************
	@When("User clicks {string} link in home page")
	public void user_clicks_register_link_in_home_page(String pageInfo) {
		pom.getHomePage().navigatetoPages(pageInfo);
		logger.info("User clicking link: {}", pageInfo);
	}

	@Given("user register using {string} with {string}")
	public void user_tries_register_using_with(String submissionMethod, String scenarioType) {
		pom.getRegisterPage().register(submissionMethod, scenarioType);
	}

	@Then("user should be redirected to  Home Page with a message {string}")
	public void user_should_be_redirected_to_home_page_with_a_message(String expectedmessage) {
		String actualMessage = pom.getHomePage().getAlertMessage();

		Assert.assertTrue(actualMessage.contains(expectedmessage),
				"Expected message to contain: [" + expectedmessage + "] but found [" + actualMessage + "]");
		logger.info("Validating home page alert message");
	}

	@Then("appropriate message {string} should be displayed {string}")
	public void appropriate_message_should_be_displayed(String expectedMessage, String scenarioType) {

		String actualMessage = pom.getRegisterPage().getRegisterErrorMessage(scenarioType);

		logger.info("Validating error message for scenario: {} | Expected: {} | Actual: {}", scenarioType,
				expectedMessage, actualMessage);

		Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Validation message mismatch");

	}

	// ********************** Non -Functional Testing scenarios *************** //
	@Then("User must see {int} input fields in Register UI")
	public void User_must_see_input_fields_in_register_ui(Integer expectedlabelcount) {
		int actuallabel_count = pom.getRegisterPage().getInputFieldCount();
		logger.info("total input field in Register page is: {}", actuallabel_count);
		Assert.assertEquals(actuallabel_count, expectedlabelcount, "label count mismatch");
	}

	@Then("User must see links with text in Register UI")
	public void user_must_see_links_with_text_in_register_ui(DataTable dataTable) {
		List<String> expectedlink_names = dataTable.asList();
		List<String> actuallink_names = pom.getRegisterPage().getRegisterPageLinkText();
		logger.info("Validating Register page links. Expected: {} | Actual: {}", expectedlink_names, actuallink_names);

		Assert.assertEquals(actuallink_names, expectedlink_names,
				"Link text mismatch , Expected: " + expectedlink_names + " but found: " + actuallink_names);
	}

	@Then("User must see {int} button in Register UI")
	public void user_must_see_button_in_register_ui(Integer expectedbuttoncount) {
		int actualbuttoncount = pom.getRegisterPage().getButtonCount();
		logger.info("total buttons in Register page is: {}", actualbuttoncount);
		Assert.assertEquals(actualbuttoncount, expectedbuttoncount, "Button count in Register page is not 1");
	}

	@Then("User should be able to see button with text {string} in Register UI")
	public void user_should_be_able_to_see_button_with_text_in_register_ui(String expectedtext) {
		List<String> actualButtons = pom.getRegisterPage().getButtonText();
		Assert.assertTrue(actualButtons.contains(expectedtext),
				"button text mismatch , Expected: " + expectedtext + " but found: " + actualButtons);
		logger.info("button label present in register ui is: {}", actualButtons);
	}

	@Then("User must see labels with text in Register UI")
	public void user_must_see_labels_with_text_in_register_ui(DataTable dataTable) {

		List<String> expectedlabel_names = dataTable.asList();

		logger.info("Validating label texts. Expected: {} | Actual: {}", expectedlabel_names,
				pom.getRegisterPage().getRegisterLabelNames());

		Assert.assertEquals(pom.getRegisterPage().getRegisterLabelNames(), expectedlabel_names,
				"Label text mismatch , Expected: " + expectedlabel_names + " but found: "
						+ pom.getRegisterPage().getRegisterLabelNames());

	}

	@Then("User must see {string} company name in top nav bar of  Register UI")
	public void user_must_see_company_name_in_top_nav_bar_of_register_ui(String expectedFromFeature) {

		Assert.assertEquals(pom.getRegisterPage().getCompanyName().trim(), expectedFromFeature,
				"Company name mismatch! Expected: " + expectedFromFeature);
		logger.info("Validating company name. Expected: {} | Actual: {}", expectedFromFeature,
				pom.getRegisterPage().getCompanyName().trim());

	}

	@Then("User must see list items for password entry field in Register UI")
	public void user_must_see_list_items_for_password_entry_field_in_register_ui(DataTable dataTable)
			throws IOException {
		List<String> dataTableList = dataTable.asList(String.class);
		List<String> actualList = pom.getRegisterPage().getPasswordRequirementsText();
		Assert.assertEquals(actualList, dataTableList, "Password help texts do not match");
		logger.info("Validating password requirement list. Expected: {} | Actual: {}", dataTableList, actualList);
	}

}
