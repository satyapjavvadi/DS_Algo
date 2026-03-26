package stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
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
        logger.info("Clicked link '{}' on Home Page", pageInfo);
    }

    @Given("user register using {string} with {string}")
    public void user_tries_register_using_with(String submissionMethod, String scenarioType) {
        logger.info("Performing registration using '{}' method for scenario '{}'", submissionMethod, scenarioType);
        pom.getRegisterPage().register(submissionMethod, scenarioType);
    }

    @Then("user should be redirected to  Home Page with a message {string}")
    public void user_should_be_redirected_to_home_page_with_a_message(String expectedMessage) {
        String actualMessage = pom.getHomePage().getAlertMessage();
        logger.info("Verifying redirect message. Expected: '{}', Actual: '{}'", expectedMessage, actualMessage);

        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected message to contain: [" + expectedMessage + "] but found [" + actualMessage + "]");
    }

    @Then("appropriate message {string} should be displayed {string}")
    public void appropriate_message_should_be_displayed(String expectedMessage, String scenarioType) {
        String actualMessage = pom.getRegisterPage().getRegisterErrorMessage(scenarioType);
        logger.info("Validating error message for scenario '{}'. Expected: '{}', Actual: '{}'",
                scenarioType, expectedMessage, actualMessage);

        Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Validation message mismatch");
    }

    //********************** Non-Functional Testing scenarios *************** //
    @Then("User must see {int} input fields in Register UI")
    public void User_must_see_input_fields_in_register_ui(Integer expectedLabelCount) {
        int actualCount = pom.getRegisterPage().getInputFieldCount();
        logger.info("Register page input field count: {}, Expected: {}", actualCount, expectedLabelCount);

        Assert.assertEquals(actualCount, expectedLabelCount, "Input field count mismatch");
    }

    @Then("User must see links with text in Register UI")
    public void user_must_see_links_with_text_in_register_ui(DataTable dataTable) {
        List<String> expectedLinks = dataTable.asList();
        List<String> actualLinks = pom.getRegisterPage().getRegisterPageLinkText();

        logger.info("Validating Register page links. Expected: {}, Actual: {}", expectedLinks, actualLinks);
        Assert.assertEquals(actualLinks, expectedLinks,
                "Link text mismatch. Expected: " + expectedLinks + ", Actual: " + actualLinks);
    }

    @Then("User must see {int} button in Register UI")
    public void user_must_see_button_in_register_ui(Integer expectedButtonCount) {
        int actualCount = pom.getRegisterPage().getButtonCount();
        logger.info("Register page button count: {}, Expected: {}", actualCount, expectedButtonCount);

        Assert.assertEquals(actualCount, expectedButtonCount, "Button count mismatch in Register page");
    }

    @Then("User should be able to see button with text {string} in Register UI")
    public void user_should_be_able_to_see_button_with_text_in_register_ui(String expectedText) {
        List<String> actualButtons = pom.getRegisterPage().getButtonText();
        logger.info("Checking button text presence. Expected: '{}', Actual: {}", expectedText, actualButtons);

        Assert.assertTrue(actualButtons.contains(expectedText),
                "Button text mismatch. Expected: " + expectedText + ", Actual: " + actualButtons);
    }

    @Then("User must see labels with text in Register UI")
    public void user_must_see_labels_with_text_in_register_ui(DataTable dataTable) {
        List<String> expectedLabels = dataTable.asList();
        List<String> actualLabels = pom.getRegisterPage().getRegisterLabelNames();

        logger.info("Validating Register page labels. Expected: {}, Actual: {}", expectedLabels, actualLabels);
        Assert.assertEquals(actualLabels, expectedLabels,
                "Label text mismatch. Expected: " + expectedLabels + ", Actual: " + actualLabels);
    }

    @Then("User must see {string} company name in top nav bar of  Register UI")
    public void user_must_see_company_name_in_top_nav_bar_of_register_ui(String expectedCompany) {
        String actualCompany = pom.getRegisterPage().getCompanyName().trim();
        logger.info("Validating company name in top nav bar. Expected: '{}', Actual: '{}'", expectedCompany, actualCompany);

        Assert.assertEquals(actualCompany, expectedCompany, "Company name mismatch");
        logger.info("Company name validated correctly");
    }

    @Then("User must see list items for password entry field in Register UI")
    public void user_must_see_list_items_for_password_entry_field_in_register_ui(DataTable dataTable) throws IOException {
        List<String> expectedList = dataTable.asList(String.class);
        List<String> actualList = pom.getRegisterPage().getPasswordRequirementsText();

        logger.info("Validating password entry field list items. Expected: {}, Actual: {}", expectedList, actualList);
        Assert.assertEquals(actualList, expectedList, "Password help texts do not match");
    }

}