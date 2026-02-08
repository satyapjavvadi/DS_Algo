package stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.datatable.DataTable;
import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GraphPage;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ElementUtil;

public class GraphStepDefinition {
	private PageObjectManager pom;
	private WebDriver driver;
	private GraphPage graphPage;
	private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
	
	public GraphStepDefinition() {
		driver = DriverFactory.getDriver();
		pom = new PageObjectManager();
		graphPage = pom.getGraphPage();

	}
	

@Then("Topics under the topics covered should be visible and clickable")
public void topics_under_the_topics_covered_should_be_visible_and_clickable(DataTable dataTable) {
	List<String> actualSubtopics = graphPage.subtopiclinks();
	logger.info("Actual Subtopic links in LinkedList page: " + actualSubtopics);

	List<String> expectedSubtopics = dataTable.asList();
	logger.info("Expected Subtopic links in LinkedList page: " + expectedSubtopics);

	Assert.assertEquals(actualSubtopics, expectedSubtopics,
			"Mismatch in subtopic link texts in LinkedList page: " + dataTable.toString());

}

	@When("The user clicks the Practice Questions button")
	public void the_user_clicks_the_practice_questions_button() {
		graphPage.clickTopicLink("Graph");
		graphPage.clickPracticeQuestionsLink();
	}

	@Then("The user should be redirected to list of Practice Questions of Graph page.")
	public void the_user_should_be_redirected_to_list_of_practice_questions_of_graph_page() {
		List<String> questions = graphPage.getQuestionsList();
		Assert.assertTrue(!questions.isEmpty(),
				"No questions are displayed in Practice Questions section of Linked List module");

	}
	
	@When("The user clicks {string} page")
	public void the_user_clicks_page(String topicUrl) {
		graphPage.clickTopicLink(topicUrl);
	}

	@Then("Try here>>> button should be visible and clickable below the {string} content")
	public void try_here_button_should_be_visible_and_clickable_below_the_content(String topic) {
		Assert.assertTrue(graphPage.checktryherebutton_displayed(), " try here button not visible in ");
		Assert.assertTrue(graphPage.checktryherebutton_clickable(), " try here button not clickable in ");
	}
 
	@When("The user clicks Try Here button in {string} in page")
	public void the_user_clicks_try_here_button_in_in_page(String topicUrl) {
		graphPage.clickTopicLink(topicUrl);
		graphPage.clickTryHereButton();
	}

	@When("The user clicks {string} button in {string} page")
	public void the_user_clicks_button_in_page(String string, String string2) {

	}

	@Given("User is in tryEditor page of {string}")
	public void user_is_in_try_editor_page_of(String topicPage) {
		graphPage.clickTopicLink(topicPage);
		graphPage.clickTryHereButton();
	}

	@When("User enters {string} code in the Try Editor and clicks on {string} button")//glued
	public void user_enters_code_in_the_try_editor_and_clicks_on_button(String code_type, String button)
			throws IOException {
		String valid = "print('Hello')";
		String invalid = "invalid";
		if (code_type.equalsIgnoreCase("valid")) {
			graphPage.enterCode(valid);
		} else if (code_type.equalsIgnoreCase("invalid")) {
			graphPage.enterCode(invalid);
		}
		graphPage.clickrunBtn();

	}

	@Then("User must see {string} in the UI")//glued
	public void user_must_see_in_the_ui(String expected_result) {

		if (expected_result.contains("error popup")) {
			String output = graphPage.getErrorPopupText();
			Assert.assertTrue(output.contains("NameError"));
			System.out.println("Output: " + output);
		} else {
			String output1 = graphPage.getOutputText();
			System.out.println("Output: " + output1);
			Assert.assertEquals(expected_result, output1);

		}
	}

	@When("The user clicks Try Here button in {string} page")
	public void the_user_clicks_try_here_button_in_page(String topicUrl) {
	graphPage.clickTopicLink(topicUrl);
	graphPage.clickTryHereButton();
	}
	
	@Then("Run button should be visible and clickable")
	public void run_button_should_be_visible_and_clickable() {
	Assert.assertTrue(graphPage.checkrunbutton_displayed(), "Run button is not visible in Try Editor page");
	Assert.assertTrue(graphPage.checkrunbutton_clickable(), "Run button is not clickable in Try Editor page");
	}
	
	@Given("The user is in the tryEditor page in {string} module")
	public void the_user_is_in_the_try_editor_page_in_module(String topicUrl) {	  
		graphPage.clickTopicLink(topicUrl);
		graphPage.clickTryHereButton();
}

	@When("The user clicks {string} tab under Topics covered")
	public void the_user_clicks_tab_under_topics_covered(String topicUrl) {
		graphPage.clickTopicLink(topicUrl);
	}

	@Then("The user should be redirected to {string} page with related details")
	public void the_user_should_be_redirected_to_page_with_related_details(String pageurltext) {
		String expected = pageurltext.toLowerCase().replace(" ", "-");
		Assert.assertTrue(ElementUtil.getURL().contains(expected),
				"URL does not contain expected text: " + pageurltext);
	}

	@Then("the user should be able to see {string} in graph page")
	public void the_user_should_be_able_to_see_in_graph_page(String expectedText) {
		List<String> headings = graphPage.getheadingtext();
		logger.info("Headings :",headings);
		boolean found = false;

		for (String heading : headings) {
			if (heading.equalsIgnoreCase(expectedText)) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(found, "Expected text '" + expectedText + "' not found in the headings: " + headings);
	}
}
