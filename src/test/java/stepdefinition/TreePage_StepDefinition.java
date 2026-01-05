package stepdefinition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import pages.TreePage;
import utils.ConfigReader;
import utils.ExcelReader;

public class TreePage_StepDefinition {
	private final PageObjectManager pom;

	private final String filePath;
	private final String sheetName;
	

	public TreePage_StepDefinition(PageObjectManager pom) {
		this.pom = pom;
		filePath = ConfigReader.getProperty("xlPath");
		sheetName =ConfigReader.getProperty("TreePage");
	}

		@Given("The registered user has navigated to the Tree page")
		public void the_registered_user_has_navigated_to_the_tree_page() {
			pom.getqueuepage().loginpage();
			  pom.getLoginPage().enterUsername("validUser");
			  pom.getLoginPage().enterPassword("validPass");
			  pom.getLoginPage().clickLoginButton(); 
			  pom.gettreepage().treeGetStarted();
			  
			  System.out.println("user is on Tree page" +
			  pom.gettreepage().Treepage_link_Check());
			  Assert.assertTrue(pom.gettreepage().Treepage_link_Check().contains("tree")
			  , "user is not on Tree page"); 
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

		   
		    List<Map<String, String>> treeData =
		            ExcelReader.readDataFromExcel(filePath, sheetName);

		    List<String> actualSubtopics = pom.gettreepage().subtopiclinks();

		    for (Map<String, String> row : treeData) {

		        if (!"Tree subtopic".equalsIgnoreCase(row.get("scenario_type")))
		            continue;

		        String expectedSubtopic = row.get("expected_subtopic");

		        Assert.assertTrue(
		                actualSubtopics.contains(expectedSubtopic),
		                "Missing subtopic: " + expectedSubtopic
		        );
		        
		       
		    }
		    System.out.println("all subtopic links are validated  in tree page");
		}
		
		@When("user should land on the correct Tree topic page")
		public void user_should_land_on_the_correct_tree_topic_page() {

		    List<Map<String, String>> excelData =
		            ExcelReader.readDataFromExcel(filePath, sheetName);

		    

		    for (Map<String, String> row : excelData) {

		        String topic = row.get("expected_subtopic").trim();
		        String expectedUrlPart = row.get("page_url").trim();

		       

		        pom.gettreepage().clicktopiclink(topic);

		        String actualUrl = pom.gettreepage().Treepage_link_Check();

		        Assert.assertTrue(
		                actualUrl.toLowerCase().contains(expectedUrlPart.toLowerCase()),
		                "URL mismatch for topic: " + topic
		                        + " | Expected to contain: " + expectedUrlPart
		                        + " | Found: " + actualUrl
		        );

		        pom.gettreepage().navigateBack();
		    }
		    System.out.println("Tree page url are validated");
		}

		/*
		 * @When("the user selects {string} under Topics Covered in Tree page") public
		 * void the_user_selects_under_topics_covered_in_tree_page(String topic) {
		 * pom.gettreepage().clicktopiclink(topic); }
		 * 
		 * @Then("the {string} text must match with page title") public void
		 * the_text_must_match_with_page_title(String pageurltext) { String currenturl =
		 * pom.gettreepage().Treepage_link_Check();
		 * System.out.println("Tree page link"+currenturl);
		 * Assert.assertTrue(currenturl.contains(pageurltext),
		 * "URL does not contain expected text: " + pageurltext); }
		 */
		
		

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
			Assert.assertTrue(pom.gettreepage().checktryherebutton_displayed(),buttonText + " try here button not visible in tree page ");
		}

		@Given("User is on the {string} page")
		public void user_is_on_the_page( String topicPage) {
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
		public void user_is_on_tree_topic_page( String topicName) {
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
