package stepdefinition;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ArrayPage;
import pages.PageObjectManager;

public class ArrayPage_StepDefinition {
	private final PageObjectManager pom;
	 WebDriver driver;
	 private ArrayPage arrayPage;

	public ArrayPage_StepDefinition() {
		pom = new PageObjectManager();
		driver=DriverFactory.getDriver();
		arrayPage=pom.getarraypage();
		
	}

	@Given("The registered user has navigated to the Array page")
	public void the_registered_user_has_navigated_to_the_array_page() {
		driver.get("https://dsportalapp.herokuapp.com/login");
		pom.getLoginPage().enterUsername("validUser");
		pom.getLoginPage().enterPassword("validPass");
		pom.getLoginPage().clickLoginButton();
		pom.getarraypage().arrayGetStarted();

		//driver.get("https://dsportalapp.herokuapp.com/array");
		
		System.out.println("user is on Array page" + pom.getarraypage().Arraypage_link_Check());
		Assert.assertTrue(pom.getarraypage().Arraypage_link_Check().contains("array"), "user is not on Array page");
	}

	
		
	

	@Then("the user should be able to see {string} in Array page")
	public void the_user_should_be_able_to_see_in_array_page(String expectedText) {
		List<String> headings = pom.getarraypage().getheadingtext();
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

	@When("the user selects {string} Topics Covered")
	public void the_user_selects_topics_covered(String topic) {
		pom.getarraypage().clicktopiclink(topic);
	}

	@Then("the {string} content should be present")
	public void the_content_should_be_present(String pageurltext) {
		String currenturl = pom.getarraypage().Arraypage_link_Check();
		Assert.assertTrue(currenturl.contains(pageurltext), "URL does not contain expected text: " + pageurltext);
	}

	@Given("the user is on the {string} subtopic array page")
	public void the_user_is_on_the_subtopic_array_page(String topicPage) {
		pom.getarraypage().clicktopiclink(topicPage);
		Assert.assertTrue(pom.getarraypage().getheadingtext().contains(topicPage),
				"user is not on Arraysubtopic page");
		System.out.println("check "+topicPage);
	}

	@When("the user scrolls the array topic page")
	public void the_user_scrolls_the_array_topic_page() {
		pom.getarraypage().scrolltobottom();
	}

	@Then("the {string} button should be visible below the {string} content")
	public void the_button_should_be_visible_below_the_content(String buttonText, String section) {
		Assert.assertTrue(pom.getarraypage().checktryherebutton_displayed(buttonText, section),
				buttonText + " try here button not visible in " + section);
	}
	
	@Given("User is on the array subtopic {string} page")
    public void user_is_on_the_array_subtopic_page(String topicPage) {
		pom.getarraypage().clicktopiclink(topicPage);
		Assert.assertTrue(pom.getarraypage().getheadingtext().contains(topicPage),
				"user is not on Arraysubtopic page");
		System.out.println("check "+topicPage);
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

	

	@When("User clicks on {string} link under Topics Covered section")
	public void user_clicks_on_link_under_topics_covered_section(String topicPage) {
		pom.getarraypage().clicktopiclink(topicPage);

	}

	@Then("User must see {string} clickable link displayed in the side navigation bar in UI")
	public void user_must_see_clickable_link_displayed_in_the_side_navigation_bar_in_ui(String linkText) {
		boolean islinkvisible = pom.getarraypage().isPracticeQuestionLinkVisible();
		Assert.assertTrue(islinkvisible, "Expected link '" + linkText + "' is not visible in side navigation bar");
		
		boolean islinkenabled = pom.getarraypage().isPracticeQuestionLinkEnabled();
		Assert.assertTrue(islinkenabled, "Expected link '" + linkText + "' is not enabled in side navigation bar");
	}


	@When("User clicks on {string} link in Array {string} UI")
	public void user_clicks_on_link_in_Array_ui(String linkText, String topicPage) {
		pom.getarraypage().clicktopiclink(topicPage);
		pom.getarraypage().clickPracticeQuestionsLink();
	}

	@Then("User must be navigated to {string} link containing list of questions")
	public void user_must_be_navigated_to_link_containing_list_of_questions(String pageName) {
		boolean iscontextpresent = pom.getarraypage().isQuestionsListDisplayed();
		Assert.assertTrue(iscontextpresent, "The " + pageName + " link does not contain any questions.");
	}

}
