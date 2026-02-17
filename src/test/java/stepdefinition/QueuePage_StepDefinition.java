package stepdefinition;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ElementUtil;

public class QueuePage_StepDefinition {
	private final PageObjectManager pom;
	private static final Logger logger = LogManager.getLogger(DriverFactory.class);

	public QueuePage_StepDefinition(PageObjectManager pom) {
		this.pom = pom;

	}

	@Given("authenticated user has navigated to the {string} ui")
	public void authenticated_user_has_navigated_to_the_ui(String moduleName) {
		pom.getHomePage().clickGetStarted(moduleName);
		logger.info("In DS module: {}", ElementUtil.getTitle());
	}

	@Then("the user should be able to see {string} in Queue page")
	public void the_user_should_be_able_to_see_in_queue_page(String expectedText) {
		List<String> headings = pom.getQueuePage().getheadingtext();
		logger.info("Queue page heading: {}", headings);
		boolean found = false;

		for (String heading : headings) {
			if (heading.equalsIgnoreCase(expectedText)) {
				found = true;
				break;
			}
		}

		Assert.assertTrue(found, "Expected heading '" + expectedText + "' not found. Actual headings: " + headings);
	}

	@Then("the user should be able to see Queue topics links under Topics_Covered")
	public void the_user_should_be_able_to_see_queue_topics_links_under_topics_covered(DataTable dataTable) {
		List<String> actualSubtopics = pom.getQueuePage().subtopiclinks();
		logger.info("Actual Subtopic links in queue page: {}", actualSubtopics);

		List<String> expectedSubtopics = dataTable.asList();
		logger.info("Expected Subtopic links in Queue page:{}", expectedSubtopics);

		Assert.assertEquals(actualSubtopics, expectedSubtopics,
				"Mismatch in subtopic link texts in Queue page: " + dataTable.toString());
	}

	@When("the user selects Queue {string} Topics Covered")
	public void the_user_selects_queue_topics_covered(String topic) {
		pom.getQueuePage().clickTopicLink(topic);
	}

	@Then("Queue {string} content should be present")
	public void queue_content_should_be_present(String pageurltext) {
		Assert.assertTrue(ElementUtil.getURL().contains(pageurltext),
				"URL does not contain expected text: " + pageurltext);
	}

	@Given("the user is on the {string} subtopic Queue page")
	public void the_user_is_on_the_subtopic_Queue_page(String topicPage) {
		pom.getQueuePage().clickTopicLink(topicPage);

		logger.info("checking Queuetopicpage: {}", topicPage);
	}

	@Then("the Try here>>> button should be visible in queue ui")
	public void the_try_here_button_should_be_visible_in_queue_ui() {
		Assert.assertTrue(pom.getQueuePage().checktryherebutton_displayed(), " try here button not visible ");
	}

	@Given("User is on the queue subtopic {string} page")
	public void user_is_on_the_queue_subtopic_page(String topicPage) {
		pom.getQueuePage().clickTopicLink(topicPage);
		Assert.assertTrue(pom.getQueuePage().getheadingtext().contains(topicPage), "user is not on Queuesubtopic page");
		logger.info("checking Queuetopicpage: {}", topicPage);
	}

	@When("User clicks the {string} button in queue ui")
	public void user_clicks_the_buttonin_queue_ui(String buttonText) {
		pom.getQueuePage().clickTryHereButton();
	}

	@Then("User must be navigated to queue code editor")
	public void user_must_be_navigated_to_queue_code_editor() {

		Assert.assertTrue(ElementUtil.getURL().contains("tryEditor"), "user is not on tryeditor screen");

	}

	@When("User clicks on {string} link under Queue Topics Covered section")
	public void user_clicks_on_link_under_Queue_topics_covered_section(String topicPage) {
		pom.getQueuePage().clickTopicLink(topicPage);

	}

	@Then("User must see {string} clickable link displayed in the side navigation bar in Queue UI")
	public void user_must_see_clickable_link_displayed_in_the_side_navigation_bar_in_queue_ui(String linkText) {
		boolean islinkvisible = pom.getQueuePage().isPracticeQuestionLinkVisible();
		Assert.assertTrue(islinkvisible, "Expected link '" + linkText + "' is not visible in side navigation bar");

		boolean islinkenabled = pom.getQueuePage().isPracticeQuestionLinkEnabled();
		Assert.assertTrue(islinkenabled, "Expected link '" + linkText + "' is not enabled in side navigation bar");
	}

	@When("User clicks on Practice Questions link in Queue {string} UI")
	public void user_clicks_on_practice_questions_link_in_queue_ui(String topicPage) {
		pom.getQueuePage().clickTopicLink(topicPage);
		pom.getQueuePage().clickPracticeQuestionsLink();
	}

	@Then("User must be navigated to Queue {string}")
	public void user_must_be_navigated_to_queue(String pageName) {
		logger.info("Queue Title actual: {} Exp{}", ElementUtil.getTitle(), pageName);
		Assert.assertTrue(ElementUtil.getTitle().contains(pageName));
	}

	@Then("User must see list of question in practice question of Queue")
	public void user_must_see_list_of_question_in_practice_question_of_queue() {
		List<String> questions = pom.getQueuePage().getQuestionsList();

		Assert.assertTrue(!questions.isEmpty(),
				"No questions are displayed in Practice Questions section of queue module");

	}
}
