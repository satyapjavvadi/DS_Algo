package stepdefinition;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;

public class ArrayPage_StepDefinition {
	private final PageObjectManager pom;
	 WebDriver driver;

	public ArrayPage_StepDefinition() {
		pom = new PageObjectManager();
	}

	@Given("The registered user has navigated to the Array page")
	public void the_registered_user_has_navigated_to_the_array_page() {
		//write login page code 
		//write home page code cicking of get started button in array card
		System.out.println("user is on Array page" + pom.getarraypage().Arraypage_link_Check());
		Assert.assertEquals(pom.getarraypage().Arraypage_link_Check().contains("array"), "user is not on Array page");
	}

	@Then("the user should be able to see {string}")
	public void the_user_should_be_able_to_see(String expectedText) {
		List<String> headings = pom.getarraypage().getheadingtext();
		boolean found = false;

		for (String heading : headings) {
			if (heading.equalsIgnoreCase(expectedText)) {
				found = true;
				break;
			}
		}

		Assert.assertTrue(found, "Expected heading '" + expectedText + "' not found. Actual headings: " + headings);
	}

	@Then("the user should be able to see Array topics as clickable links under {string}")
	public void the_user_should_be_able_to_see_array_topics_as_clickable_links_under(String links) {
		List<String> actualSubtopics = pom.getarraypage().subtopiclinks();
		System.out.println("Actual Subtopic links in Array page: " + actualSubtopics);

		List<String> expectedSubtopics = Arrays.asList("Arrays in Python", "Arrays Using List",
				"Basic Operations in Lists", "Applications of Array");

		System.out.println("Expected Subtopic links in Array page: " + expectedSubtopics);

		Assert.assertEquals(actualSubtopics, expectedSubtopics,
				"Mismatch in subtopic link texts in Array page: " + links);
	}

	@When("the user selects {string} under Topics Covered")
	public void the_user_selects_under_topics_covered(String topic) {
		pom.getarraypage().clicktopiclink(topic);
	}

	@Then("the {string} content should be present")
	public void the_content_should_be_present(String pageurltext) {
		String currenturl = pom.getarraypage().Arraypage_link_Check();
		Assert.assertEquals(currenturl.contains(pageurltext), "URL does not contain expected text: " + pageurltext);
	}

	@Given("the user is on the {string} page")
	public void the_user_is_on_the_page(String topicPage) {
		Assert.assertEquals(pom.getarraypage().Arraypage_link_Check().contains(topicPage),
				"user is not on Arraysubtopic page");
	}

	@When("the user scrolls through the content")
	public void the_user_scrolls_through_the_content() {
		pom.getarraypage().scrolltobottom();
	}

	@Then("the {string} button should be visible below the {string} content")
	public void the_button_should_be_visible_below_the_content(String buttonText, String section) {
		Assert.assertTrue(pom.getarraypage().checktryherebutton_displayed(buttonText, section),
				buttonText + " tey here button not visible in " + section);
	}

	@When("User clicks the {string} button")
	public void user_clicks_the_button(String buttonText) {
		pom.getarraypage().clickTryHereButton();
	}

	@Then("User must be navigated to code editor")
	public void user_must_be_navigated_to_code_editor() {
		System.out.println("try editor URL:" + pom.getarraypage().Arraypage_link_Check());
		Assert.assertTrue(pom.getarraypage().Arraypage_link_Check().contains("tryEditor"),
				"user is not on tryeditor screen");

	}

	@Given("User is in {string} UI")
	public void user_is_in_ui(String topicPage) {
		pom.getarraypage().clicktopiclink(topicPage);
		Assert.assertEquals(pom.getarraypage().Arraypage_link_Check().contains(topicPage),
				"user is not on Arraysubtopic page");

	}

	@When("User enters {string} code in the Try Editor and clicks on {string} button")
	public void user_enters_code_in_the_try_editor_and_clicks_on_button(String codeType, String buttonName) {
		// need to enter code to read data from excel

		pom.getarraypage().enterCode(codeType);
		System.out.println("Entered " + codeType + " code into Try Editor.");
		pom.getarraypage().clickRunButton();
	}

	@Then("User must see {string} in the UI")
	public void user_must_see_in_the_ui(String expectedResult) {
		String output_text = pom.getarraypage().getOutput_text();
		if (!output_text.isEmpty()) {
			Assert.assertTrue(output_text.contains(expectedResult),
					"Expected output not found in console. Expected: " + expectedResult + ", Actual: " + output_text);

		}

		else {
			String alertText = "";
			try {
				alertText = pom.getarraypage().getAlerttext();
			} catch (NoAlertPresentException e) {

			}
			Assert.assertTrue(alertText.contains(expectedResult),
					"Expected error message not found in alert. Expected: " + expectedResult + ", Actual: "
							+ alertText);
		}
	}

	@When("User clicks on {string} link under Topics Covered section")
	public void user_clicks_on_link_under_topics_covered_section(String topicPage) {
		pom.getarraypage().subtopiclinks();

	}

	@Then("User must see {string} link displayed in the side navigation bar in UI")
	public void user_must_see_link_displayed_in_the_side_navigation_bar_in_ui(String linkText) {
		boolean islinkvisible = pom.getarraypage().isPracticeQuestionLinkVisible();
		Assert.assertTrue(islinkvisible, "Expected link '" + linkText + "' is not visible in side navigation bar");
	}

	@Then("User must view {string} as clickable link in side nav bar")
	public void user_must_view_as_clickable_link_in_side_nav_bar(String linkText) {
		boolean islinkenabled = pom.getarraypage().isPracticeQuestionLinkVisible();
		Assert.assertTrue(islinkenabled, "Expected link '" + linkText + "' is not enabled in side navigation bar");
	}

	@When("User clicks on {string} link in Array sub topic UI")
	public void user_clicks_on_link_in_array_sub_topic_ui(String linkText) {
		pom.getarraypage().clickPracticeQuestionsLink();
	}

	@Then("User must be navigated to {string} link containing list of questions")
	public void user_must_be_navigated_to_link_containing_list_of_questions(String pageName) {
		boolean iscontextpresent = pom.getarraypage().isPracticeQuestionsListNotEmpty();
		Assert.assertTrue(iscontextpresent, "The " + pageName + " link does not contain any questions.");
	}

}
