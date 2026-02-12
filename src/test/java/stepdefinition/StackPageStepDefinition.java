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
import utils.ElementUtil;
import utils.WaitUtils;

public class StackPageStepDefinition {

	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);


	public StackPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;
	}

	@Given("the registered user has navigated to the {string} page")
	public void user_navigates_to_stack_page(String moduleName) {
		pom.getHomePage().clickGetStarted(moduleName);
        logger.info("Navigated to module : {}", ElementUtil.getTitle());
	}

	@Then("the user should be able to see {string} in Stack page")
	public void the_user_should_be_able_to_see_in_stack_page(String expectedText) {
		List<String> headings = pom.getStackPage().getHeadingText();
		logger.info("Headings: {}", headings);
		boolean found = false;

		for (String heading : headings) {
			if (heading.equalsIgnoreCase(expectedText)) {
				found = true;
				break;
			}
		}

		Assert.assertTrue(found, "Expected heading '" + expectedText + "' not found. Actual headings: " + headings);
	}

	@Then("the user should be able to see Stack topics as clickable links under Topics Covered")
	public void the_user_should_be_able_to_see_Stack_topics_as_clickable_links_under_topics_covered(
			DataTable dataTable) {
		List<String> actualSubtopics = pom.getStackPage().getSubtopicLinks();
        logger.info("Actual Subtopic links in Stack page: {}", actualSubtopics);

		List<String> expectedSubtopics = dataTable.asList();
        logger.info("Expected Subtopic links in Stack page: {}", expectedSubtopics);

		for (int i = 0; i < expectedSubtopics.size(); i++) {
			Assert.assertEquals(actualSubtopics.get(i).trim().toLowerCase(),
					expectedSubtopics.get(i).trim().toLowerCase(),
					"Mismatch in subtopic link texts in Stack page: " + dataTable.toString());
		}
	}

	@When("the user selects Stack {string} under Topics Covered")
	public void the_user_selects_under_topics_covered(String topic) {

        logger.info("Page Title: {}", ElementUtil.getTitle());
        logger.info("Current URL: {}", ElementUtil.getURL());
		pom.getStackPage().clickTopicLink(topic);
	}

	@Then("Stack {string} content should be visible")
	public void linked_list_content_should_be_visible(String pageurltext) {
		String expectedSlug = pageurltext.toLowerCase().replace(" ", "-");
		logger.info("Stack content should be visible : {}", expectedSlug);
		Assert.assertTrue(ElementUtil.getURL().contains(expectedSlug),
				"URL does not contain expected text: " + pageurltext);
	}

	@When("the user scrolls through the {string} content")
	public void the_user_selects_topics_covered(String topic) {
		logger.info("user_selects_topics_covered : {}", topic);
		pom.getStackPage().clickTopicLink(topic);
	}

	@Then("the Try here>>> button should be visible below the content in Stack")
	public void the_try_here_button_should_be_visible_below_the_content() {
		logger.info("Try here>>> button should be visible below the content in Stack");
		Assert.assertTrue(pom.getStackPage().isTryHereButtonDisplayed(), " try here button not visible in ");
	}

	@Given("the user is on the Stack {string} page")
	public void the_user_is_on_the_page(String topicPage) {
		pom.getStackPage().clickTopicLink(topicPage);

        logger.info("check {}", topicPage);
	}

	@When("the user activates the {string} button in Stack page")
	public void user_clicks_the_button_in_stack_ui(String buttonText) {
		logger.info("user activates the {} button in Stack page",buttonText);
		pom.getStackPage().clickTryHereButton();
	}

	@Then("the code editor should open")
	public void user_must_be_navigated_to_Stack_code_editor() {
		logger.info("user_must_be_navigated_to_Stack_code_editor");
		Assert.assertTrue(ElementUtil.getURL().contains("tryEditor"), "user is not on try editor screen");

	}

	@When("the user selects Practice Questions link in Stack {string}")
	public void user_clicks_on_practice_questions_link_in_Stack_ui(String topicPage) {
		logger.info("user_clicks_on_practice_questions_link_in_Stack_ui");
		pom.getStackPage().clickTopicLink(topicPage);
		pom.getStackPage().clickPracticeQuestionsLink();
	}

	@Then("the user must see list of questions in {string} of Stack")
	public void user_must_see_list_of_questions_in_stack(String pageName) {

        logger.info("user must see list of questions of Stack: {}", pageName);
		Assert.assertTrue(ElementUtil.getTitle().toLowerCase().contains(pageName.toLowerCase()),
				"Title mismatch: expected " + pageName + " but found " + ElementUtil.getTitle());
	}

	@When("User clicks on Practice Questions link in Stack {string} UI")
	public void user_clicks_on_practice_questions_link_in_stack_ui(String topicPage) {

		logger.info("User clicks on Practice Questions link in Stack: {}", topicPage);
		pom.getStackPage().clickTopicLink(topicPage);
		pom.getStackPage().clickPracticeQuestionsLink();

	}

	@Then("User must see list of practice questions of Stack")
	public void user_must_see_list_of_questions_in_practice_questions_of_Stack() {

		List<String> questions = pom.getStackPage().getQuestionsList();

		logger.info("User must see list of practice questions of Stack: {}", questions);
		Assert.assertTrue(!questions.isEmpty(),
				"No questions are displayed in Practice Questions section of Stack module");
	}

	@When("User clicks on {string} link under Stack Topics Covered section")
	public void user_clicks_on_link_under_stack_topics_covered_section(String topicPage) {

		logger.info("User clicks on link under Stack Topics Covered  : {}", topicPage);
		pom.getStackPage().clickTopicLink(topicPage);

	}

	@Then("User must see {string} clickable link displayed in the side navigation bar in Stack")
	public void user_must_see_clickable_link_displayed_in_the_side_navigation_bar_in_Stack(String linkText) {
		boolean islinkvisible = pom.getStackPage().isPracticeQuestionLinkVisible();

		
		Assert.assertTrue(islinkvisible, "Expected link '" + linkText + "' is not visible in side navigation bar");

		boolean islinkenabled = pom.getStackPage().isPracticeQuestionLinkEnabled();
		Assert.assertTrue(islinkenabled, "Expected link '" + linkText + "' is not enabled in side navigation bar");
	}

}
