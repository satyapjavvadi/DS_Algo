package stepdefinition;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.PageObjectManager;

import utils.ConfigReader;

public class QueuePage_StepDefinition {
	private final PageObjectManager pom;

	private final String filePath;
	private final String sheetName;

	public QueuePage_StepDefinition(PageObjectManager pom) {
		this.pom = pom;
		filePath = ConfigReader.getProperty("xlPath");
		sheetName = ConfigReader.getProperty("sheetName");

	}

	@Given("The registered user has navigated to the Queue page")
	public void the_registered_user_has_navigated_to_the_queue_page() {
		pom.getqueuepage().navigateToQueueUI();

		System.out.println("user is on Queue page" + pom.getqueuepage().Queuepage_link_Check());
		Assert.assertTrue(pom.getqueuepage().Queuepage_link_Check().contains("queue"), "user is not on Queue page");
	}

	@Then("the user should be able to see {string} in Queue page")
	public void the_user_should_be_able_to_see_in_queue_page(String expectedText) {
		List<String> headings = pom.getqueuepage().getheadingtext();
		System.out.println(headings);
		boolean found = false;

		for (String heading : headings) {
			if (heading.equalsIgnoreCase(expectedText)) {
				found = true;
				break;
			}
		}

		Assert.assertTrue(found, "Expected heading '" + expectedText + "' not found. Actual headings: " + headings);
	}

	@Then("the user should be able to see Queue topics as clickable links under {string} section")
	public void the_user_should_be_able_to_see_queue_topics_as_clickable_links_under_section(String links) {
		List<String> actualSubtopics = pom.getqueuepage().subtopiclinks();
		System.out.println("Actual Subtopic links in queue page: " + actualSubtopics);

		List<String> expectedSubtopics = Arrays.asList("Implementation of Queue in Python",
				"Implementation using collections.deque", "Implementation using array", "Queue Operations");

		System.out.println("Expected Subtopic links in queue page: " + expectedSubtopics);

		Assert.assertEquals(actualSubtopics, expectedSubtopics,
				"Mismatch in subtopic link texts in queue page: " + links);
	}

	@When("the user selects {string} from Topics Covered")
	public void the_user_selects_from_topics_covered(String topic) {
		pom.getqueuepage().clicktopiclink(topic);
	}

	@Then("the {string} content must be present")
	public void the_content_must_be_present(String pageurltext) {
		String currenturl = pom.getqueuepage().Queuepage_link_Check();
		Assert.assertTrue(currenturl.contains(pageurltext), "URL does not contain expected text: " + pageurltext);
	}

	@Given("the user is on the {string} UI")
	public void the_user_is_on_the_ui(String topicPage) {
		pom.getqueuepage().clicktopiclink(topicPage);
		Assert.assertTrue(pom.getqueuepage().getheadingtext().contains(topicPage), "user is not on Queuesubtopic page");
		System.out.println("topic page: " + topicPage);
	}

	@When("the user scrolls down the page")
	public void the_user_scrolls_down_the_page() {
		pom.getqueuepage().scrolltobottom();
	}

	@Then("the {string} button must be visible below the page content")
	public void the_button_must_be_visible_below_the_page_content(String buttonText) {
		System.out.println("try here buton check");
		Assert.assertTrue(pom.getqueuepage().checktryherebutton_displayed(),
				buttonText + " try here button not visible ");
	}

	@Given("User is on the queue subtopic {string} UI")
	public void user_is_on_the_queue_subtopic_ui(String topicPage) {
		pom.getqueuepage().clicktopiclink(topicPage);
		Assert.assertTrue(pom.getqueuepage().getheadingtext().contains(topicPage), "user is not on queuesubtopic page");

	}

	@When("User clicks {string} button")
	public void user_clicks_button(String buttonText) {
		pom.getqueuepage().clickTryHereButton();
	}

	@Then("User must navigate to code editor")
	public void user_must_navigate_to_code_editor() {
		System.out.println("try editor URL:" + pom.getqueuepage().Queuepage_link_Check());
		Assert.assertTrue(pom.getqueuepage().Queuepage_link_Check().contains("tryEditor"),
				"user is not on tryeditor screen");

	}

	@Given("user is on topic Queue {string} page")
	public void user_is_on_topic_queue_page(String topicPage) {
		pom.getqueuepage().clicktopiclink(topicPage);
	}

	@When("user clicks try here button and runs {string} code in the editor")
	public void user_clicks_try_here_button_and_runs_code_in_the_editor(String scenarioType) {

		pom.getqueuepage().runqueue_editor(scenarioType);
	}

	@Then("result should be {string}")
	public void result_should_be(String expected) {
		String actual = pom.getqueuepage().getQueueEditorResult();

		Assert.assertEquals(actual, expected);
	}

	@Given("the user is on {string} page")
	public void the_user_is_on_page(String topicName) {
		pom.getqueuepage().clicktopiclink(topicName);

	}

	@When("user clicks {string} from the sidebar")
	public void user_clicks_from_the_sidebar(String linkText) {
		pom.getqueuepage().clickPracticeQuestionsLink();
	}

	@Then("page loads with list of questions")
	public void page_loads_with_list_of_questions() {
		boolean questionsVisible = pom.getqueuepage().isQuestionsListDisplayed();
		Assert.assertTrue(questionsVisible, "ERROR: No questions were displayed on the Queue Practice Questions page.");
	}

}
