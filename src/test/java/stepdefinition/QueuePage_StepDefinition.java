package stepdefinition;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import org.testng.Assert;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.PageObjectManager;

import utils.ConfigReader;
import utils.ExcelReader;

public class QueuePage_StepDefinition {
	private final PageObjectManager pom;

	private final String filePath;
	private final String sheetName;


	public QueuePage_StepDefinition(PageObjectManager pom) {
		this.pom = pom;
		filePath = ConfigReader.getProperty("xlPath");
		sheetName = ConfigReader.getProperty("QueuePage");

	}
	
	  @Given("The registered user has navigated to the Queue page") 
	  public void the_registered_user_has_navigated_to_the_queue_page() {
	  pom.getqueuepage().loginpage();
	  pom.getLoginPage().enterUsername("validUser");
	  pom.getLoginPage().enterPassword("validPass");
	  pom.getLoginPage().clickLoginButton();
	  pom.getqueuepage().queueGetStarted();
	  
	  System.out.println("user is on Queue page" +
	  pom.getqueuepage().Queuepage_link_Check());
	  Assert.assertTrue(pom.getqueuepage().Queuepage_link_Check().contains("queue")
	  , "user is not on Queue page"); }
	 


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

		Assert.assertEquals(actualSubtopics,
			     expectedSubtopics,
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
		Assert.assertTrue(pom.getqueuepage().checktryherebutton_displayed(),buttonText + " try here button not visible ");
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
	
	@When("User validates Try Editor using Excel data")
	public void validate_try_editor_using_excel_data() throws IOException {

	    // Read data from Excel
	    

	    List<Map<String, String>> queueData =
	    		ExcelReader.readDataFromExcel(filePath, sheetName);

	    // Loop through each row of data
	    for (Map<String, String> row : queueData) {

	    	 String codeType  = row.get("scenario_type");
	        String topic     = row.get("topic_page");
	        String code      = row.get("code");
	        String expected  = row.get("expected_result");

	        System.out.println("Executing test for topic: " + topic + " CodeType: " + codeType );
	       pom.getqueuepage().goto_queuemainpage();
	        //Navigating to the topic page
	        pom.getqueuepage().clicktopiclink(topic);

	        // opening Try Editor and enter the code
	        pom.getqueuepage().clickTryHereButton();
	        pom.getqueuepage().enterCode(code);
	        pom.getqueuepage().clickRunButton();

	        //  Validating output or error
	        if (codeType.equalsIgnoreCase("valid")) {
	            String actualOutput = pom.getqueuepage().getOutput_text();
	            

	            Assert.assertTrue(
	                actualOutput.contains(expected ),
	                "\n Valid code FAILED \nExpected: " + expected + "\nActual: " + actualOutput );
	           

	            System.out.println("Valid code passed. Output matched.");

	        } else { // invalid code
	            String actualError = pom.getqueuepage().getAlerttext();

	            Assert.assertTrue(
	                actualError.contains(expected),
	                "\n Invalid code FAILED \nExpected Error: " + expected + "\nActual Error: " + actualError
	            );

	            System.out.println(" Invalid code passed. Error message matched.");
	        }
	    }
	}
	
	@Then("Validation must be completed successfully")
	public void validation_must_be_completed_successfully() {
	    System.out.println(" All Try Editor validations executed successfully");
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
