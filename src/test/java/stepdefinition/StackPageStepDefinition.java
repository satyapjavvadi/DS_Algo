package stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.JSUtils;
import utils.TopicUrlRegistry;
import utils.WaitUtils;

public class StackPageStepDefinition {

	private final PageObjectManager pom;
	private final String filePath;

	public StackPageStepDefinition(PageObjectManager pom) {

		this.pom = pom;
		filePath = ConfigReader.getProperty("xlPath");
	}

	@Given("the registered user has navigated to the Stack page")
	public void the_signed_in_user_has_navigated_to_the_stack_page() {
		// Because Hooks already logged in, you can directly navigate
		pom.getStackPage().navigateToStackPage();

	}

	@Then("the user should be able to see static content from Excel")
	public void verify_static_content_from_excel() throws IOException {
		List<Map<String, String>> rows = ExcelReader.getScenarioRows(filePath, "StackPageContent", "static_content");

		for (Map<String, String> row : rows) {
			String expectedText = row.get("expected_text");
			WebDriver driver = pom.getDriver();
			Assert.assertTrue(driver.getPageSource().contains(expectedText),
					"❌ Expected text not found: " + expectedText);
			System.out.println("→ " + expectedText);
		}
	}

	@Then("the user should be able to navigate to each Stack topic from Excel")
	public void verify_stack_landing_page_links() throws IOException {
		List<Map<String, String>> rows = ExcelReader.getScenarioRows(filePath, "StackPageContent", "topic_navigation");

		List<String> expectedTopics = rows.stream().map(row -> row.get("topic").trim()).toList();

		List<String> actualTopics = pom.getStackPage().getVisibleTopicTexts();

		for (String expected : expectedTopics) {
			Assert.assertTrue(actualTopics.contains(expected), "❌ Missing topic link on Stack page: " + expected);
		}
		System.out.println("✅ All expected topic links are present on Stack landing page.");
	}

	@When("the user validates scrollable content from Excel")
	public void verify_scrollable_topic_content_from_excel() throws IOException {
		List<Map<String, String>> rows = ExcelReader.getScenarioRows(filePath, "StackPageContent", "scroll_validation");

		for (Map<String, String> row : rows) {
			String topicPage = row.get("topic_page").trim();
			String expectedContent = row.get("expected_content").trim();

			String path = TopicUrlRegistry.getStackPath(topicPage);
			WebDriver driver = pom.getDriver();
			driver.get(ConfigReader.getProperty("baseURL") + "stack/" + path);

			JSUtils.scrollToBottom(driver);
			String pageText = pom.getStackPage().getPageText();

			pom.getStackPage().assertKeywordsPresent(pageText, expectedContent, topicPage);
		}
	}

	@Given("the user is on the Stack Introduction page")
	public void the_user_is_on_intro_page() {
		WebDriver driver = pom.getDriver();
		driver.get(ConfigReader.getProperty("baseURL") + "stack/");
		WaitUtils.waitForPageLoad(driver, 10);
	}

}
