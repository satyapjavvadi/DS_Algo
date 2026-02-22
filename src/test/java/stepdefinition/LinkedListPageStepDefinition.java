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

public class LinkedListPageStepDefinition {
	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(LinkedListPageStepDefinition.class);


	public LinkedListPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;

	}

	@Then("the user should be able to see {string} in LinkedList page")
	public void the_user_should_be_able_to_see_in_tree_page(String expectedText) {
		List<String> headings = pom.getLinkedListPage().getheadingtext();
		logger.info("Headings :",headings);
		boolean found = false;

		for (String heading : headings) {
			if (heading.equalsIgnoreCase(expectedText)) {
				found = true;
				break;
			}
		}

		Assert.assertTrue(found, "Expected heading '" + expectedText + "' not found. Actual headings: " + headings);
	}

	@Then("the user should be able to see Linked List topics as clickable links under Topics Covered")
	public void the_user_should_be_able_to_see_Linked_List_topics_as_clickable_links_under_topics_covered(
			DataTable dataTable) {
		List<String> actualSubtopics = pom.getLinkedListPage().subtopiclinks();
        logger.info("Actual Subtopic links in LinkedList page: {}", actualSubtopics);

		List<String> expectedSubtopics = dataTable.asList();
        logger.info("Expected Subtopic links in LinkedList page: {}", expectedSubtopics);

		Assert.assertEquals(actualSubtopics, expectedSubtopics,
				"Mismatch in subtopic link texts in LinkedList page: " + dataTable);
	}

	@When("the user selects Linked List {string} under Topics Covered")
	public void user_clicks_on_link_under_Linked_List_topics_covered(String topicPage) {
		pom.getLinkedListPage().clickTopicLink(topicPage);

	}

	@Then("Linked List {string} content should be visible")
	public void linked_list_content_should_be_visible(String pageurltext) {
		String expectedSlug = pageurltext.toLowerCase().replace(" ", "-");
		Assert.assertTrue(ElementUtil.getURL().contains(expectedSlug),
				"URL does not contain expected text: " + pageurltext);
	}

	@When("the user selects {string} topics covered")
	public void the_user_selects_topics_covered(String topic) {
		pom.getLinkedListPage().clickTopicLink(topic);
	}

	@Then("the Try here>>> button should be visible below the content")
	public void the_try_here_button_should_be_visible_below_the_content() {
		logger.info("Try here button visible in");
		Assert.assertTrue(pom.getLinkedListPage().checktryherebutton_displayed(), " try here button not visible in ");
		logger.atError();
	}

	@Given("the user is on the {string} page")
	public void the_user_is_on_the_page(String topicPage) {
		pom.getLinkedListPage().clickTopicLink(topicPage);

        logger.info("check {}", topicPage);
	}

	@When("the user activates the Try here button in Linked List page")
	public void user_clicks_the_button_in_tree_ui() {
		pom.getLinkedListPage().clickTryHereButton();
	}

	@Then("User should see the code {string} open")
	public void user_must_be_navigated_to_Linked_List_code_editor(String expectedText) {
		logger.info("user is not on try editor screen");
		Assert.assertTrue(ElementUtil.getURL().contains(expectedText), "user is not on tryeditor screen");
		logger.atError();
	}

	@When("User clicks on {string} link under Linked List Topics Covered section")
	public void user_clicks_on_link_under_linked_list_topics_covered_section(String topicPage) {
		pom.getLinkedListPage().clickTopicLink(topicPage);
		logger.info("Linked List Topics Covered section");
	}

	@Then("User must see {string} clickable link displayed in the side navigation bar in Linked List UI")
	public void user_must_see_clickable_link_displayed_in_the_side_navigation_bar_in_Linked_List_ui(String linkText) {
		boolean isLinkVisible = pom.getLinkedListPage().isPracticeQuestionLinkVisible();

		logger.info("Practice Question visibility check : {}",isLinkVisible);
		Assert.assertTrue(isLinkVisible, "Expected link '" + linkText + "' is not visible in side navigation bar");

	}

	@When("the user selects Practice Questions link in Linked List {string}")
	public void user_clicks_on_practice_questions_link_in_Linked_List_ui(String topicPage) {
		logger.info("Selects Practice Questions link in Linked List");
		pom.getLinkedListPage().clickTopicLink(topicPage);
		pom.getLinkedListPage().clickPracticeQuestionsLink();
	}

	@Then("User must see title as {string} of Linked List")
	public void user_must_see_list_of_questions_in_Linked_List(String pageName) {
        logger.info("Print Title actual : {} Exp {}", ElementUtil.getTitle(), pageName);
		Assert.assertTrue(ElementUtil.getTitle().toLowerCase().contains(pageName.toLowerCase()),
				"Title mismatch: expected " + pageName + " but found " + ElementUtil.getTitle());

	}

	@Then("User must see list of practice questions of Linked List")
	public void user_must_see_list_of_questions_in_practice_questions_of_Linked_List() {
		List<String> questions = pom.getLinkedListPage().getQuestionsList();

		logger.info("Practice Questions section of Linked List module : {}",questions);
        Assert.assertFalse(questions.isEmpty(), "No questions are displayed in Practice Questions section of Linked List module");

	}

}
