package stepdefinition;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;

import utils.ConfigReader;
import utils.ExcelReader;
import utils.TestContext;

public class TreePage_StepDefinition {
	private final PageObjectManager pom;

	private final String filePath;
	private final String sheetName;

	public TreePage_StepDefinition(PageObjectManager pom) {
		this.pom = pom;
		filePath = ConfigReader.getProperty("xlPath");
		sheetName = ConfigReader.getProperty("TreePage");
	}

	@Given("The registered user has navigated to the Tree page")
	public void the_registered_user_has_navigated_to_the_tree_page() {
		pom.gettreepage().navigateToTreeUI();

		System.out.println("user is on Tree page" + pom.gettreepage().Treepage_link_Check());
		Assert.assertTrue(pom.gettreepage().Treepage_link_Check().contains("tree"), "user is not on Tree page");
	}

	@Then("the user should be able to see {string} in Tree page")
	public void the_user_should_be_able_to_see_in_tree_page(String expectedText) {
		List<String> headings = pom.gettreepage().getheadingtext();
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

	@Then("user must see all tree subtopics from excel")
	public void user_must_see_all_tree_subtopics_from_excel() {

		List<Map<String, String>> treeData = ExcelReader.readDataFromExcel(filePath, sheetName);

		List<String> actualSubtopics = pom.gettreepage().subtopiclinks();

		for (Map<String, String> row : treeData) {

			String expectedSubtopic = row.get("scenario_type");

			Assert.assertTrue(actualSubtopics.contains(expectedSubtopic), "Missing subtopic: " + expectedSubtopic);

		}
		System.out.println("all tree subtopic links are validated  in tree page");
	}

	@When("user navigates to Tree topic {string}")
	public void user_navigates_to_tree_topic(String scenarioType) {
		pom.gettreepage().navigateToTreeTopic(scenarioType);
	}

	@When("user should land on the correct Tree topic page")
	public void user_should_land_on_the_correct_tree_topic_page() {

		Map<String, String> data = TestContext.testData;

		String expectedUrlPart = data.get("page_url");
		System.out.println("expectedurl" + expectedUrlPart);

		String actualUrl = pom.gettreepage().Treepage_link_Check();
		System.out.println("actualurl" + actualUrl);

		Assert.assertTrue(actualUrl.contains(expectedUrlPart),
				"Expected URL to contain: " + expectedUrlPart + " but found: " + actualUrl);
	}

	@Given("the user is in Tree {string} page")
	public void the_user_is_in_tree_page(String topicPage) {
		pom.gettreepage().clicktopiclink(topicPage);
		Assert.assertTrue(pom.gettreepage().getheadingtext().contains(topicPage), "user is not on Treesubtopic page");
		System.out.println("Tree topic page: " + topicPage);
	}

	@When("the user scrolls  the Tree page")
	public void the_user_scrolls_the_tree_page() {
		pom.gettreepage().scrolltobottom();
	}

	@Then("the {string} button should be visible below the page content")
	public void the_button_should_be_visible_below_the_page_content(String buttonText) {
		System.out.println("Tree try here buton check");
		Assert.assertTrue(pom.gettreepage().checktryherebutton_displayed(),
				buttonText + " try here button not visible in tree page ");
	}

	@Given("User is on the {string} page")
	public void user_is_on_the_page(String topicPage) {
		pom.gettreepage().clicktopiclink(topicPage);
		Assert.assertTrue(pom.gettreepage().getheadingtext().contains(topicPage), "user is not on Tree subtopic page");
	}

	@When("User clicks {string} button in UI")
	public void user_clicks_button_in_ui(String buttonText) {
		pom.gettreepage().clickTryHereButton();
	}

	@Then("User must be navigated to code editor UI")
	public void user_must_be_navigated_to_code_editor_ui() {
		System.out.println("try editor URL:" + pom.gettreepage().Treepage_link_Check());
		Assert.assertTrue(pom.gettreepage().Treepage_link_Check().contains("tryEditor"),
				"user is not on tryeditor screen");

	}

	@Given("user is on tree topic {string} page")
	public void user_is_on_tree_topic_page(String topicName) {
		pom.gettreepage().clicktopiclink(topicName);
	}

	@When("user clicks {string} present in sidebar")
	public void user_clicks_present_in_sidebar(String string) {
		pom.gettreepage().clickPracticeQuestionsLink();
	}

	@Then("page must load with list of questions")
	public void page_must_load_with_list_of_questions() {
		boolean questionsVisible = pom.gettreepage().isQuestionsListDisplayed();
		Assert.assertTrue(questionsVisible, "ERROR: No questions were displayed on the Tree Practice Questions page.");
	}
}
