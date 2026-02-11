package stepdefinition;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import io.cucumber.datatable.DataTable;
import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ElementUtil;

public class GraphStepDefinition {
	private PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

	public GraphStepDefinition(PageObjectManager pom) {
		this.pom = pom;

	}

	@Then("Topics under the topics covered should be visible and clickable")
	public void topics_under_the_topics_covered_should_be_visible_and_clickable(DataTable dataTable) {
		List<String> actualSubtopics = pom.getGraphPage().subtopiclinks();
		logger.info("Actual Subtopic links in LinkedList page: " + actualSubtopics);

		List<String> expectedSubtopics = dataTable.asList();
		logger.info("Expected Subtopic links in LinkedList page: " + expectedSubtopics);

		Assert.assertEquals(actualSubtopics, expectedSubtopics,
				"Mismatch in subtopic link texts in LinkedList page: " + dataTable.toString());

	}

	@When("The user clicks the Practice Questions button")
	public void the_user_clicks_the_practice_questions_button() {
		pom.getGraphPage().clickTopicLink("Graph");
		pom.getGraphPage().clickPracticeQuestionsLink();
	}

	@Then("The user should be redirected to list of Practice Questions of Graph page.")
	public void the_user_should_be_redirected_to_list_of_practice_questions_of_graph_page() {
		List<String> questions = pom.getGraphPage().getQuestionsList();
		Assert.assertTrue(!questions.isEmpty(),
				"No questions are displayed in Practice Questions section of Linked List module");

	}

	@When("The user clicks {string} page")
	public void the_user_clicks_page(String topicUrl) {
		pom.getGraphPage().clickTopicLink(topicUrl);
	}

	@Then("Try here>>> button should be visible and clickable below the {string} content")
	public void try_here_button_should_be_visible_and_clickable_below_the_content(String topic) {
		Assert.assertTrue(pom.getGraphPage().checktryherebutton_displayed(), " try here button not visible in ");
		Assert.assertTrue(pom.getGraphPage().checktryherebutton_clickable(), " try here button not clickable in ");
	}

	@When("The user clicks Try Here button in {string} in page")
	public void the_user_clicks_try_here_button_in_in_page(String topicUrl) {
		pom.getGraphPage().clickTopicLink(topicUrl);
		pom.getGraphPage().clickTryHereButton();
	}

	@When("The user clicks Try Here button in {string} page")
	public void the_user_clicks_try_here_button_in_page(String topicUrl) {
		pom.getGraphPage().clickTopicLink(topicUrl);
		pom.getGraphPage().clickTryHereButton();
	}

	@Then("Run button should be visible and clickable")
	public void run_button_should_be_visible_and_clickable() {
		Assert.assertTrue(pom.getGraphPage().checkrunbutton_displayed(),
				"Run button is not visible in Try Editor page");
		Assert.assertTrue(pom.getGraphPage().checkrunbutton_clickable(),
				"Run button is not clickable in Try Editor page");
	}

	@Given("The user is in the tryEditor page in {string} module")
	public void the_user_is_in_the_try_editor_page_in_module(String topicUrl) {
		pom.getGraphPage().clickTopicLink(topicUrl);
		pom.getGraphPage().clickTryHereButton();
	}

	@When("The user clicks {string} tab under Topics covered")
	public void the_user_clicks_tab_under_topics_covered(String topicUrl) {
		pom.getGraphPage().clickTopicLink(topicUrl);
	}

	@Then("The user should be redirected to {string} page with related details")
	public void the_user_should_be_redirected_to_page_with_related_details(String pageurltext) {
		String expected = pageurltext.toLowerCase().replace(" ", "-");
		Assert.assertTrue(ElementUtil.getURL().contains(expected),
				"URL does not contain expected text: " + pageurltext);
	}

	@Then("the user should be able to see {string} in graph page")
	public void the_user_should_be_able_to_see_in_graph_page(String expectedText) {
		List<String> headings = pom.getGraphPage().getheadingtext();
		logger.info("Headings :", headings);
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
