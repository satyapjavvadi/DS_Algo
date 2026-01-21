package stepdefinition;

import java.util.List;

import io.cucumber.datatable.DataTable;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.PageObjectManager;
import utils.ElementUtil;

public class TreePage_StepDefinition {
	private final PageObjectManager pom;

	public TreePage_StepDefinition(PageObjectManager pom) {
		this.pom = pom;

	}

	@Given("signed in user has navigated to the {string} UI")
	public void signed_in_user_has_navigated_to_the_ui(String moduleName) {
		pom.getHomePage().clickGetStarted(moduleName);
		System.out.println("In DS module : " + ElementUtil.getTitle());
	}

	@Then("the user should be able to see {string} in Tree page")
	public void the_user_should_be_able_to_see_in_tree_page(String expectedText) {
		List<String> headings = pom.getTreePage().getheadingtext();
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

	@Then("the user should be able to see Tree topics links under Topics_Covered")
	public void the_user_should_be_able_to_see_tree_topics_links_under_topics_covered(DataTable dataTable) {
		List<String> actualSubtopics = pom.getTreePage().subtopiclinks();
		System.out.println("Actual Subtopic links in Tree page: " + actualSubtopics);

		List<String> expectedSubtopics = dataTable.asList();
		System.out.println("Expected Subtopic links in Tree page: " + expectedSubtopics);

		Assert.assertEquals(actualSubtopics, expectedSubtopics,
				"Mismatch in subtopic link texts in Tree page: " + dataTable.toString());
	}

	@When("the user selects Tree {string} Topics Covered")
	public void the_user_selects_tree_topics_covered(String topic) {
		pom.getTreePage().clickTopicLink(topic);
	}

	@Then("Tree {string} content should be present")
	public void tree_content_should_be_present(String pageurltext) {
		Assert.assertTrue(ElementUtil.getURL().contains(pageurltext),
				"URL does not contain expected text: " + pageurltext);
	}

	@Given("the user is on the {string} subtopic Tree page")
	public void the_user_is_on_the_subtopic_Tree_page(String topicPage) {
		pom.getTreePage().clickTopicLink(topicPage);

		System.out.println("check " + topicPage);
	}

	@Then("the Try here>>> button should be visible in tree ui")
	public void the_try_here_button_should_be_visible_in_tree_ui() {
		Assert.assertTrue(pom.getTreePage().checktryherebutton_displayed(), " try here button not visible in ");
	}

	@Given("User is on the tree subtopic {string} page")
	public void user_is_on_the_tree_subtopic_page(String topicPage) {
		pom.getTreePage().clickTopicLink(topicPage);
		Assert.assertTrue(pom.getTreePage().getheadingtext().contains(topicPage), "user is not on Treesubtopic page");
		System.out.println("check " + topicPage);
	}

	@When("User clicks the {string} button in tree ui")
	public void user_clicks_the_buttonin_tree_ui(String buttonText) {
		pom.getTreePage().clickTryHereButton();
	}

	@Then("User must be navigated to tree code editor")
	public void user_must_be_navigated_to_tree_code_editor() {

		Assert.assertTrue(ElementUtil.getURL().contains("tryEditor"), "user is not on tryeditor screen");

	}

	@When("User clicks on {string} link under Tree Topics Covered section")
	public void user_clicks_on_link_under_Tree_topics_covered_section(String topicPage) {
		pom.getTreePage().clickTopicLink(topicPage);

	}

	@Then("User must see {string} clickable link displayed in the side navigation bar in Tree UI")
	public void user_must_see_clickable_link_displayed_in_the_side_navigation_bar_in_tree_ui(String linkText) {
		boolean islinkvisible = pom.getTreePage().isPracticeQuestionLinkVisible();
		Assert.assertTrue(islinkvisible, "Expected link '" + linkText + "' is not visible in side navigation bar");

		boolean islinkenabled = pom.getArrayPage().isPracticeQuestionLinkEnabled();
		Assert.assertTrue(islinkenabled, "Expected link '" + linkText + "' is not enabled in side navigation bar");
	}

	@When("User clicks on Practice Questions link in Tree {string} UI")
	public void user_clicks_on_practice_questions_link_in_tree_ui(String topicPage) {
		pom.getTreePage().clickTopicLink(topicPage);
		pom.getTreePage().clickPracticeQuestionsLink();
	}

	@Then("User must be navigated to tree {string}")
	public void user_must_be_navigated_to_tree(String pageName) {
		System.out.println("Print Title actual : " + ElementUtil.getTitle() + " Exp " + pageName);
		Assert.assertTrue(ElementUtil.getTitle().contains(pageName));
	}

	@Then("User must see list of question in practice question of Tree")
	public void user_must_see_list_of_question_in_practice_question_of_tree() {
		List<String> questions = pom.getTreePage().getQuestionsList();

		Assert.assertTrue(!questions.isEmpty(), "No questions are displayed in Practice Questions section of tree module");

	}

}
