package stepdefinition;

import java.util.List;

import io.cucumber.datatable.DataTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.PageObjectManager;
import utils.ElementUtil;

public class ArrayPage_StepDefinition {
	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(ArrayPage_StepDefinition.class);

	public ArrayPage_StepDefinition(PageObjectManager pom) {
		this.pom = pom;

	}

	@Given("The registered user has navigated to the {string} page")
	public void the_registered_user_has_navigated_to_the_array_page(String moduleName) {
		pom.getHomePage().clickGetStarted(moduleName);
		logger.info("In DS module: {}", ElementUtil.getTitle());
	}

	@Then("the user should be able to see {string} in Array page")
	public void the_user_should_be_able_to_see_in_array_page(String expectedText) {
		List<String> headings = pom.getArrayPage().getheadingtext();
		logger.info("Headings in Array page: {}", headings);
		boolean found = false;

		for (String heading : headings) {
			if (heading.equalsIgnoreCase(expectedText)) {
				found = true;
				break;
			}
		}

		Assert.assertTrue(found, "Expected heading '" + expectedText + "' not found. Actual headings: " + headings);
	}

	@Then("the user should be able to see Array topics links under Topics_Covered")
	public void the_user_should_be_able_to_see_array_topics_as_clickable_links_under(DataTable dataTable) {
		List<String> actualSubtopics = pom.getArrayPage().subtopiclinks();
		logger.info("Actual Subtopic links in Array page: {}", actualSubtopics);

		List<String> expectedSubtopics = dataTable.asList();
		logger.info("Expected Subtopic links in Array page: {}", expectedSubtopics);

		Assert.assertEquals(actualSubtopics, expectedSubtopics,
				"Mismatch in subtopic link texts in Array page: " + dataTable.toString());
	}

	@When("the user selects {string} Topics Covered")
	public void the_user_selects_topics_covered(String topic) {
		pom.getArrayPage().clickTopicLink(topic);
		logger.info("Selecting Array topic: {}", topic);
	}

	@Then("the {string} content should be present")
	public void the_content_should_be_present(String pageurltext) {
		Assert.assertTrue(ElementUtil.getURL().contains(pageurltext),
				"URL does not contain expected text: " + pageurltext);
		logger.info("Validating URL contains: {}", pageurltext);

	}

	@Given("the user is on the {string} subtopic array page")
	public void the_user_is_on_the_subtopic_array_page(String topicPage) {
		pom.getArrayPage().clickTopicLink(topicPage);

		logger.info("checking arraytopic page: {}", topicPage);
	}

	@Then("the Try here>>> button should be visible")
	public void the_button_should_be_visible_below_the_content() {
		Assert.assertTrue(pom.getArrayPage().checktryherebutton_displayed(), " try here button not visible in ");
		logger.info("Checking visibility of Try Here button");
	}

	@Given("User is on the array subtopic {string} page")
	public void user_is_on_the_array_subtopic_page(String topicPage) {
		pom.getArrayPage().clickTopicLink(topicPage);
		Assert.assertTrue(pom.getArrayPage().getheadingtext().contains(topicPage), "user is not on Arraysubtopic page");
		logger.info("checking arraytopic page: {}", topicPage);
	}

	@When("User clicks the {string} button")
	public void user_clicks_the_button(String buttonText) {
		pom.getArrayPage().clickTryHereButton();
		logger.info("Clicking Try Here button");
	}

	@Then("User must be navigated to code editor")
	public void user_must_be_navigated_to_code_editor() {

		Assert.assertTrue(ElementUtil.getURL().contains("tryEditor"), "user is not on tryeditor screen");
		logger.info("Current URL: {}", ElementUtil.getURL());

	}

	@When("User clicks on {string} link under Topics Covered section")
	public void user_clicks_on_link_under_topics_covered_section(String topicPage) {
		pom.getArrayPage().clickTopicLink(topicPage);

	}

	@Then("User must see {string} clickable link displayed in the side navigation bar in UI")
	public void user_must_see_clickable_link_displayed_in_the_side_navigation_bar_in_ui(String linkText) {
		boolean islinkvisible = pom.getArrayPage().isPracticeQuestionLinkVisible();
		Assert.assertTrue(islinkvisible, "Expected link '" + linkText + "' is not visible in side navigation bar");

		boolean islinkenabled = pom.getArrayPage().isPracticeQuestionLinkEnabled();
		Assert.assertTrue(islinkenabled, "Expected link '" + linkText + "' is not enabled in side navigation bar");

		logger.info("Checking enabled Practice Questions link is displayed");
	}

	@When("User clicks on Practice Questions link in Array {string} UI")
	public void user_clicks_on_link_in_Array_ui(String topicPage) {
		pom.getArrayPage().clickTopicLink(topicPage);
		pom.getArrayPage().clickPracticeQuestionsLink();
		logger.info("practice question link is clicked in Array page");
	}

	@Then("User must be navigated to {string}")
	public void user_must_be_navigated_to_link_containing_list_of_questions(String pageName) {
		logger.info("Array Title actual: {} Exp{}", ElementUtil.getTitle(), pageName);
		Assert.assertTrue(ElementUtil.getTitle().contains(pageName));
	}

	@Then("User must see list of question in practice question of array")
	public void ListOfQuestionInPracticeQuestionOfArray(DataTable dataTable) {
		List<String> expectedList = dataTable.asList();
		List<String> actualList = pom.getArrayPage().getQuestionsList();

		Assert.assertEquals(actualList, expectedList);
		logger.info("Expected Questions: {}", expectedList);
		logger.info("Actual Questions: {}", actualList);

	}

	@Given("User has navigated to Practice Question UI from Array page")
	public void registered_user_has_navigated_to_practice_question_ui_from_array_page() {
		pom.getArrayPage().clickTopicLink("Arrays in Python");
		pom.getArrayPage().clickPracticeQuestionsLink();

		Assert.assertTrue(ElementUtil.getURL().contains("practice"), "user is not on Array practice questions page");
		logger.info("Navigating to Practice Questions from Array page");
	}

	@When("User clicks on {string} link in Practice Questions UI")
	public void user_clicks_on_problem_link_in_practice_questions_ui(String problemName) {
		pom.getArrayPage().clickProblemLink(problemName);
		logger.info("Clicking problem link: {}", problemName);
	}

	@Then("User must see {string} button in assessment page")
	public void userMustSeeButtonInAssessmentPage(String expectedButtonText) {
		boolean flag = false;
		switch (expectedButtonText) {
		case "Run":
			flag = pom.getArrayPage().getButtonTextAssesmentPage(expectedButtonText);
			break;
		case "Submit":
			flag = pom.getArrayPage().isSubmitButtonPresent();
			break;
		}
		Assert.assertTrue(flag);
		logger.info("Validating button presence on assessment page: {}", expectedButtonText);

	}

	@When("User clicks on submit link after reaching {string}")
	public void userClicksOnSubmitLinkAfterReaching(String problemName) {
		pom.getArrayPage().clickProblemLink(problemName);
		pom.getArrayPage().submitProblem();
		logger.info("Submitting problem: {}", problemName);

	}

	@Then("User must see {string} in output")
	public void userMustSeeInOutput(String expectedOutput) {
		Assert.assertTrue(pom.getArrayPage().getConsoleOutput().contains(expectedOutput));
		logger.info("Validating expected output: {}", expectedOutput);
	}

	@Given("User is on the Try editor")
	public void userIsOnTheTryEditor() {
		pom.getArrayPage().clickTopicLink("Arrays in Python");
		pom.getArrayPage().clickTryHereButton();
		logger.info("Navigating to Try Editor from Arrays UI");
	}

	@When("User clicks the Run button after entering {string}")
	public void userClicksTheButtonAfterEntering(String codeDetails) {
		pom.getTryEditorPage().runCode(codeDetails);
		logger.info("Running code in Try Editor");
	}

}
