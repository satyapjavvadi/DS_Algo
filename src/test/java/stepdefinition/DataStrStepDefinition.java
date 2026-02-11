package stepdefinition;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ElementUtil;

public class DataStrStepDefinition {
	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

	public DataStrStepDefinition(PageObjectManager pom) {
		this.pom = pom;
	}

	@Then("the user should be able to see {string}")
	public void the_user_should_be_able_to_see(String expectedText) {
		List<String> headings = pom.getDataStructurePage().getheadingtext();
		logger.info("Headings :", headings);
		boolean found = false;

		for (String heading : headings) {
			if (heading.equalsIgnoreCase(expectedText)) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(found, "Expected text '" + expectedText + "' not found in the headings: " + headings);
	}

	@Given("The user is in the tryEditor page")
	public void the_user_is_in_the_try_editor_page() {
		pom.getDataStructurePage().clickTopicLink("time complexity");
		pom.getDataStructurePage().clickTryHereButton();
	}

	@When("The user clicks the Run button without entering the code in the Editor")
	public void the_user_clicks_the_run_button_without_entering_the_code_in_the_editor() {
		pom.getDataStructurePage().clickrunBtn();
	}

	@Then("The user should able to get the error message {string}")
	public void the_user_should_able_to_get_the_error_message(String ErrorMsg) {
		Assert.assertEquals(ErrorMsg, pom.getDataStructurePage().getErrAlert(),
				"Expected error alert not found. Actual message: " + pom.getDataStructurePage().getErrAlert());
	}

	@When("The user clicks the Practice Questions tab")
	public void the_user_clicks_the_practice_questions_tab() {
		pom.getDataStructurePage().clickPracticeQuestionsLink();
	}

	@Then("The user should be redirected to list of Practice Questions of Data structures-Introduction")
	public void the_user_should_be_redirected_to_list_of_practice_questions_of_data_structures_introduction() {
		List<String> questions = pom.getDataStructurePage().getQuestionsList();
		Assert.assertTrue(!questions.isEmpty(),
				"No questions are displayed in Practice Questions section of Linked List module");
	}

	@When("The user clicks {string} topic")
	public void the_user_clicks_topic(String topicUrl) {
		pom.getDataStructurePage().clickTopicLink(topicUrl);

	}

	@Then("The user should be redirected to {string} page")
	public void the_user_should_be_redirected_to_page(String pageurltext) {
		String expected = pageurltext.toLowerCase().replace(" ", "-");
		Assert.assertTrue(ElementUtil.getURL().contains(expected),
				"URL does not contain expected text: " + pageurltext);

	}

	@Then("The user should be redirected to a page having an try Editor with a Run button to test")
	public void the_user_should_be_redirected_to_a_page_having_an_try_editor_with_a_run_button_to_test() {
		Assert.assertTrue(ElementUtil.getURL().contains("tryEditor"), "user is not on tryeditor screen");

		Assert.assertTrue(pom.getDataStructurePage().getRunBtnText().contains("Run"),
				"Run button is not present in Try Editor page");
	}

	@When("The user clicks Try Here button")
	public void the_user_clicks_try_here_button() {
		pom.getDataStructurePage().clickTryHereButton();
	}

	@Given("The user is in the {string} tab") // glued
	public void the_user_is_in_the_tab(String topicUrl) {
		pom.getDataStructurePage().clickTopicLink(topicUrl);
	}

	@Then("Try here tab should be visible and clickable") //
	public void try_here_tab_should_be_visible_and_clickable() {
		Assert.assertTrue(pom.getDataStructurePage().checktryherebutton_displayed(),
				"Run button is not visible in Try Editor page");
		Assert.assertTrue(pom.getDataStructurePage().checktryherebutton_clickable(),
				"Run button is not clickable in Try Editor page");
	}

}