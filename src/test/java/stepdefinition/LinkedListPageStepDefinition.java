package stepdefinition;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ElementUtil;

public class LinkedListPageStepDefinition {

	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(LinkedListPageStepDefinition.class);

	public LinkedListPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;
	}

	@Then("the user should be able to see {string} in LinkedList page")
	public void the_user_should_be_able_to_see_in_tree_page(String expectedText) {
		List<String> headings = pom.getLinkedListPage().getheadingtext();
		logger.info("LinkedList page headings: {}", headings);

		boolean found = headings.stream().anyMatch(h -> h.equalsIgnoreCase(expectedText));
		Assert.assertTrue(found, "Expected heading '" + expectedText + "' not found. Actual headings: " + headings);
		logger.info("Verified heading '{}' is present in LinkedList page", expectedText);
	}

	@Then("the user should be able to see Linked List topics as clickable links under Topics Covered")
	public void the_user_should_be_able_to_see_Linked_List_topics_as_clickable_links_under_topics_covered(
			DataTable dataTable) {
		List<String> actualSubtopics = pom.getLinkedListPage().subtopiclinks();
		List<String> expectedSubtopics = dataTable.asList();

		logger.info("Actual Subtopic links: {}", actualSubtopics);
		logger.info("Expected Subtopic links: {}", expectedSubtopics);

		Assert.assertEquals(actualSubtopics, expectedSubtopics,
				"Mismatch in subtopic link texts in LinkedList page");
		logger.info("Verified Linked List topics links under Topics Covered");
	}

	@When("the user selects Linked List {string} under Topics Covered")
	public void user_clicks_on_link_under_Linked_List_topics_covered(String topicPage) {
		pom.getLinkedListPage().clickTopicLink(topicPage);
		logger.info("Clicked Linked List topic '{}'", topicPage);
	}

	@Then("Linked List {string} content should be visible")
	public void linked_list_content_should_be_visible(String pageurltext) {
		String expectedSlug = pageurltext.toLowerCase().replace(" ", "-");
		String currentUrl = ElementUtil.getURL();
		logger.debug("Current URL: {}, Expected slug: {}", currentUrl, expectedSlug);

		Assert.assertTrue(currentUrl.contains(expectedSlug),
				"URL does not contain expected text: " + pageurltext);
		logger.info("Verified Linked List content page for '{}'", pageurltext);
	}

	@Then("the Try here>>> button should be visible below the content")
	public void the_try_here_button_should_be_visible_below_the_content() {
		boolean isVisible = pom.getLinkedListPage().checktryherebutton_displayed();
		logger.info("Try Here button visible status: {}", isVisible);
		Assert.assertTrue(isVisible, "Try Here button not visible in LinkedList page");
	}

	@Given("the user is on the {string} page")
	public void the_user_is_on_the_page(String topicPage) {
		pom.getLinkedListPage().clickTopicLink(topicPage);
		logger.info("User navigated to LinkedList page '{}'", topicPage);
	}

	@When("the user activates the Try here button in Linked List page")
	public void user_clicks_the_button_in_tree_ui() {
		pom.getLinkedListPage().clickTryHereButton();
		logger.info("Clicked Try Here button in Linked List page");
	}

	@Then("User should see the code {string} open")
	public void user_must_be_navigated_to_Linked_List_code_editor(String expectedText) {
		String currentUrl = ElementUtil.getURL();
		logger.debug("Current URL: {}, Expected to contain: {}", currentUrl, expectedText);

		Assert.assertTrue(currentUrl.contains(expectedText), "User is not on TryEditor screen");
		logger.info("User is on TryEditor screen for LinkedList code: '{}'", expectedText);
	}

	@When("User clicks on {string} link under Linked List Topics Covered section")
	public void user_clicks_on_link_under_linked_list_topics_covered_section(String topicPage) {
		pom.getLinkedListPage().clickTopicLink(topicPage);
		logger.info("Clicked Linked List topic '{}' under Topics Covered section", topicPage);
	}

	@Then("User must see {string} clickable link displayed in the side navigation bar in Linked List UI")
	public void user_must_see_clickable_link_displayed_in_the_side_navigation_bar_in_Linked_List_ui(String linkText) {
		boolean isLinkVisible = pom.getLinkedListPage().isPracticeQuestionLinkVisible();
		logger.info("Practice Question link '{}' visibility: {}", linkText, isLinkVisible);

		Assert.assertTrue(isLinkVisible, "Expected link '" + linkText + "' is not visible in side navigation bar");
		logger.info("Verified side navigation bar link '{}' is visible", linkText);
	}

	@When("the user selects Practice Questions link in Linked List {string}")
	public void user_clicks_on_practice_questions_link_in_Linked_List_ui(String topicPage) {
		pom.getLinkedListPage().clickTopicLink(topicPage);
		pom.getLinkedListPage().clickPracticeQuestionsLink();
		logger.info("Clicked Practice Questions link in Linked List for topic '{}'", topicPage);
	}

	@Then("User must see title as {string} of Linked List")
	public void user_must_see_list_of_questions_in_Linked_List(String pageName) {
		String actualTitle = ElementUtil.getTitle();
		logger.info("Actual page title: {}, Expected title: {}", actualTitle, pageName);

		Assert.assertTrue(actualTitle.toLowerCase().contains(pageName.toLowerCase()),
				"Title mismatch: expected '" + pageName + "' but found '" + actualTitle + "'");
		logger.info("Verified Linked List page title matches expected title '{}'", pageName);
	}

	@Then("User must see list of practice questions of Linked List")
	public void user_must_see_list_of_questions_in_practice_questions_of_Linked_List() {
		List<String> questions = pom.getLinkedListPage().getQuestionsList();
		logger.info("Practice Questions in Linked List module: {}", questions);

		Assert.assertFalse(questions.isEmpty(),
				"No questions are displayed in Practice Questions section of Linked List module");
		logger.info("Verified that practice questions are displayed in Linked List module");
	}

}