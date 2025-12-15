package stepdefinition;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.TopicNameNormalizer;
import utils.TopicUrlRegistry;
import utils.WaitUtils;

public class LinkedListPageStepDefinition {

	private final PageObjectManager pom;
	private String selectedTopic = "";

	public LinkedListPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;

	}

	// Background
	@Given("the signed-in user has navigated to the Linked List page")
	public void the_signed_in_user_has_navigated_to_the_linked_list_page() {
		pom.getLinkedListPage().navigateToLinkedListPage();
	}

	// Static content
	@Then("the user should be able to see {string}")
	public void the_user_should_be_able_to_see(String expectedText) {
		boolean isVisible = pom.getLinkedListPage().isTextVisible(expectedText);
		System.out.println("Checking visibility of: " + expectedText + " → " + isVisible);
		Assert.assertTrue(isVisible, "Text not visible: " + expectedText);
	}

	// Topics Covered
	@Then("the user should be able to see Linked List topics as clickable links under {string}")
	public void verifyTopics(String sectionTitle) {
		pom.getLinkedListPage().assertTopicsMatch(TopicUrlRegistry.getLinkedListExpectedTopics());
	}

	// Navigating to topic pages
	@When("the user selects {string} under Topics Covered")
	public void the_user_selects_under_topics_covered(String topic) {
		pom.getLinkedListPage().clickTopic(topic);
	}

	@Then("the {string} content should be visible")
	public void the_content_should_be_visible(String expectedText) {
		Assert.assertTrue(pom.getLinkedListPage().containsKeyword(expectedText),
				"Expected content not visible: " + expectedText);
	}

	// Sidebar navigation

	@Given("the user is on the {string} page")
	public void the_user_is_on_the_topic_page(String topicName) {
		String normalized = TopicNameNormalizer.normalize(topicName);
		String expectedPath = TopicUrlRegistry.getUrlForTopic(normalized);

		if (expectedPath == null) {
			throw new IllegalArgumentException("No URL mapping found for topic: " + normalized);
		}
		String baseUrl = ConfigReader.getProperty("baseURL");
		WebDriver driver = pom.getDriver();
		driver.get(baseUrl + expectedPath);

		new WebDriverWait(driver, Duration.ofSeconds(15)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	@When("the user selects {string} from the sidebar")
	public void the_user_selects_from_sidebar(String topicName) {
		pom.getLinkedListPage().clickSidebarTopic(topicName);
		selectedTopic = TopicNameNormalizer.normalize(topicName);
	}

	@Then("the related topic content URL for {string} should appear")
	public void the_related_topic_content_should_appear(String topicName) {
		String normalized = TopicNameNormalizer.normalize(topicName);
		String expectedPath = TopicUrlRegistry.getUrlForTopic(normalized);

		if (expectedPath == null) {
			throw new IllegalArgumentException("No URL mapping found for topic: " + normalized);
		}

		WebDriver driver = pom.getDriver();
		WaitUtils.waitForUrlContains(driver, expectedPath, 10);

		Assert.assertTrue(pom.getLinkedListPage().isCurrentUrlMatching(expectedPath),
				"URL did not match for topic: " + normalized);
	}

	@Then("the relevant topic content should appear {string}")
	public void the_relevant_topic_content_should_appear(String topicName) {
		Assert.assertTrue(pom.getLinkedListPage().waitForTopicContent(topicName),
				"❌ Page loaded with boilerplate only — no content or error message displayed for: " + topicName);
	}

	// Try here button
	@When("the user scrolls through the content")
	public void the_user_scrolls_through_the_content() {
		pom.getLinkedListPage().scrollToBottom();
	}

	@Then("the \"Try here>>>\" button should be visible below the {string} content")
	public void the_try_here_button_should_be_visible(String sectionName) {
		Assert.assertTrue(pom.getLinkedListPage().isTryHereButtonVisible(),
				"\"Try here>>>\" button is not visible below section: " + sectionName);
	}

	@When("the user activates the {string} button")
	public void the_user_activates_the_button(String button) {
		pom.getLinkedListPage().clickTryHereButton();
	}

	@Then("the code editor should open")
	public void the_code_editor_should_open() {
		Assert.assertTrue(pom.getLinkedListPage().isCodeEditorVisible(), "Code editor not visible");
	}

	@Given("the user runs all Try Editor scenarios from {string} sheet")
	public void runAllTryEditorScenariosFromSheet(String sheetName) {
		String xlPath = ConfigReader.getProperty("xlPath");
		List<Map<String, String>> testData = ExcelReader.getTestData(xlPath, sheetName);

		for (Map<String, String> row : testData) {
			String topicPage = row.get("scenario_type");
			String codeSnippet = row.get("code_snippet");
			if (codeSnippet == null || codeSnippet.trim().isEmpty())
				continue;

			String expectedOutput = row.get("expected_output");
			String errorMessage = row.get("error_message");

			String baseUrl = ConfigReader.getProperty("baseURL");
			String url = baseUrl + TopicUrlRegistry.getUrlForTopic(topicPage);
			WebDriver driver = pom.getDriver();
			driver.get(url);

			pom.getLinkedListPage().clickTryHereButton();

			WaitUtils.enterCodeInTryEditor(driver, codeSnippet);
			WaitUtils.clickRunButton(driver);
			WaitUtils.validateOutputOrError(driver, expectedOutput, errorMessage);
		}
	}

	// Scrolling through code explanations
	@When("the user scrolls down")
	public void the_user_scrolls_down() {
		pom.getLinkedListPage().scrollToBottom();
	}

	@Then("the {string} should be visible")
	public void the_expected_content_should_be_visible(String keywords) {
		pom.getLinkedListPage().containsKeyword(keywords);
	}

	// Error handling
	@Given("the user is on the Linked List Introduction page")
	public void the_user_is_on_the_linked_list_introduction_page() {
		// Navigate directly to the Introduction page
		pom.getLinkedListPage().navigateToIntroductionPage();
		Assert.assertTrue(pom.getLinkedListPage().isSidebarVisible(), "Sidebar is not visible on Introduction page");
	}

	@Then("an appropriate error message should be displayed if the page fails to load")
	public void verify_error_or_missing_content() {
		String pageText = pom.getLinkedListPage().getBodyText();
		System.out.println("Page text:\n" + pageText);

		// Define known boilerplate content
		List<String> boilerplate = Arrays.asList("NumpyNinja", "Data Structures", "ValidUser", "Sign out");

		// Count how many boilerplate items are present
		long boilerplateCount = boilerplate.stream().filter(pageText::contains).count();

		// If all boilerplate items are present and nothing else, it's a silent failure
		boolean isSilentFailure = boilerplateCount == boilerplate.size();

		Assert.assertFalse(isSilentFailure,
				"Page loaded with boilerplate only — no content or error message displayed.");

	}

}
