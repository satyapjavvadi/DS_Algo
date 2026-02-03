package stepdefinition;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ElementUtil;
import utils.ExcelReader;
import utils.TestContext;
import utils.WaitUtils;

public class StackPageStepDefinition {

	private final PageObjectManager pom;
	WaitUtils wait = new WaitUtils();

	public StackPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;
	}

	@Given("the registered user has navigated to the {string} page")
	public void user_navigates_to_stack_page(String moduleName) {
		pom.getHomePage().clickGetStarted(moduleName);
		System.out.println("In Stack module : " + ElementUtil.getTitle());
	}

	@Then("the user should be able to see {string} in Stack page")
	public void the_user_should_be_able_to_see_in_stack_page(String expectedText) {
		List<String> headings = pom.getStackPage().getHeadingText();
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

	@Then("the user should be able to see Stack topics as clickable links under Topics Covered")
	public void the_user_should_be_able_to_see_Stack_topics_as_clickable_links_under_topics_covered(
			DataTable dataTable) {
		List<String> actualSubtopics = pom.getStackPage().getSubtopicLinks();
		System.out.println("Actual Subtopic links in Stack page: " + actualSubtopics);

		List<String> expectedSubtopics = dataTable.asList();
		System.out.println("Expected Subtopic links in Stack page: " + expectedSubtopics);

		for (int i = 0; i < expectedSubtopics.size(); i++) {
			Assert.assertEquals(actualSubtopics.get(i).trim().toLowerCase(),
					expectedSubtopics.get(i).trim().toLowerCase(),
					"Mismatch in subtopic link texts in Stack page: " + dataTable.toString());
		}
	}

	@When("the user selects Stack {string} under Topics Covered")
	public void the_user_selects_under_topics_covered(String topic) {

		System.out.println("Page Title: " + ElementUtil.getTitle());
		System.out.println("Current URL: " + ElementUtil.getURL());
		pom.getStackPage().clickTopicLink(topic);
	}

	@Then("Stack {string} content should be visible")
	public void linked_list_content_should_be_visible(String pageurltext) {
		String expectedSlug = pageurltext.toLowerCase().replace(" ", "-");
		Assert.assertTrue(ElementUtil.getURL().contains(expectedSlug),
				"URL does not contain expected text: " + pageurltext);
	}

	@When("the user scrolls through the {string} content")
	public void the_user_selects_topicss_covered(String topic) {
		pom.getStackPage().clickTopicLink(topic);
	}

	@Then("the Try here>>> button should be visible below the content in Stack")
	public void the_try_here_button_should_be_visible_below_the_content() {
		Assert.assertTrue(pom.getStackPage().isTryHereButtonDisplayed(), " try here button not visible in ");
	}

	@Given("the user is on the Stack {string} page")
	public void the_user_is_on_the_page(String topicPage) {
		pom.getStackPage().clickTopicLink(topicPage);

		System.out.println("check " + topicPage);
	}

	@When("the user activates the {string} button in Stack page")
	public void user_clicks_the_buttonin_stack_ui(String buttonText) {
		pom.getStackPage().clickTryHereButton();
	}

	@Then("the code editor should open")
	public void user_must_be_navigated_to_Stack_code_editor() {

		Assert.assertTrue(ElementUtil.getURL().contains("tryEditor"), "user is not on tryeditor screen");

	}

	@Given("the user runs all Try Editor scenarios from stack sheet")
	public void load_stack_data() {
		TestContext.testDataList = ExcelReader.readDataFromExcel("StackPageContent");
	}

	@When("User runs all Stack Try Editor scenarios")
	public void run_all_scenarios() {

		for (Map<String, String> row : TestContext.testDataList) {

			String topic = row.get("scenario_type");
			String code = row.get("code_snippet");

			// Skip invalid or junk rows
			if (topic == null || topic.trim().isEmpty() || topic.equalsIgnoreCase("Null value in cred")) {
				System.out.println("Skipping invalid topic: " + topic);
				continue;
			}

			pom.getStackPage().goToStackPage();
			wait.waitForPageTitle("Stack");

			System.out.println("Navigated to Stack. Page Title: " + ElementUtil.getTitle());

			pom.getHomePage().clickGetStarted("Stack");
			pom.getStackPage().navigateToTryEditorFromTopic(topic);
			pom.getStackPage().enterCode(code);
			pom.getStackPage().clickRunButton();

			String actual = pom.getStackPage().getOutputText();
			row.put("actual_output", actual);
		}
	}

	@Then("All Stack Try Editor results must match expected output")
	public void validate_results() {

		for (Map<String, String> row : TestContext.testDataList) {

			String expected = row.get("expected_output");
			String error = row.get("error_message");
			String actual = row.get("actual_output");

			assert actual.contains(expected) || actual.contains(error)
					: "Mismatch for scenario: " + row.get("scenario_type");
		}
	}

	@When("the user selects Practice Questions link in Stack {string}")
	public void user_clicks_on_practice_questions_link_in_Stack_ui(String topicPage) {
		pom.getStackPage().clickTopicLink(topicPage);
		pom.getStackPage().clickPracticeQuestionsLink();
	}

	@Then("the user must see list of questions in {string} of Stack")
	public void user_must_see_list_of_questions_in_stack(String pageName) {
		System.out.println("Print Title actual : " + ElementUtil.getTitle() + " Exp " + pageName);
		Assert.assertTrue(ElementUtil.getTitle().toLowerCase().contains(pageName.toLowerCase()),
				"Title mismatch: expected " + pageName + " but found " + ElementUtil.getTitle());

	}

	@When("User clicks on Practice Questions link in Stack {string} UI")
	public void user_clicks_on_practice_questions_link_in_stack_ui(String topicPage) {
		pom.getStackPage().clickTopicLink(topicPage);
		pom.getStackPage().clickPracticeQuestionsLink();

	}

	@Then("User must see list of practice questions of Stack")
	public void user_must_see_list_of_questions_in_practice_questions_of_Stack() {
		List<String> questions = pom.getStackPage().getQuestionsList();
		Assert.assertTrue(!questions.isEmpty(),
				"No questions are displayed in Practice Questions section of Stack module");

	}

	@When("User clicks on {string} link under Stack Topics Covered section")
	public void user_clicks_on_link_under_stack_topics_covered_section(String topicPage) {
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
