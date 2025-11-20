package stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BaseTopicPage;
import pages.PageObjectManager;
import utils.JSUtils;
import utils.TopicUrlRegistry;
import utils.WaitUtils;

public class CommonTopicStepDefinition {

	private final WebDriver driver;
	private final PageObjectManager pom;
	private BaseTopicPage topicPage;
    private String selectedTopic;

	public CommonTopicStepDefinition() {
		driver = DriverFactory.getDriver(); // ✅ get driver from DriverFactory
		pom = new PageObjectManager(driver); // ✅ pass driver into PageObjectManager
		topicPage = pom.getCurrentTopicPage(driver.getCurrentUrl());
	}

	@When("the user scrolls through the content")
	public void the_user_scrolls_through_the_content() {
		BaseTopicPage topicPage = pom.getCurrentTopicPage(driver.getCurrentUrl());
		topicPage.scrollToBottom();
	}

	@Then("the \"Try here>>>\" button should be visible below the {string} content")
	public void the_try_here_button_should_be_visible(String sectionName) {
		BaseTopicPage topicPage = pom.getCurrentTopicPage(driver.getCurrentUrl());
		Assert.assertTrue(topicPage.isTryHereButtonVisible(),
				"\"Try here>>>\" button is not visible below section: " + sectionName);
	}

	@When("the user activates the \"Try here>>>\" button")
	public void the_user_activates_the_button() {
		BaseTopicPage topicPage = pom.getCurrentTopicPage(driver.getCurrentUrl());
		topicPage.clickTryHereButton();
	}

	@Then("the code editor should open")
	public void the_code_editor_should_open() {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("/tryEditor"),
				"Expected code editor URL to contain '/tryEditor', but got: " + currentUrl);
	}

	@Given("the signed-in user is on the {string} page")
	public void the_signed_in_user_has_navigated_to_the_linked_list_page(String currentPage) {
		driver.get("https://dsportalapp.herokuapp.com/linked-list/introduction/");

		// Use WaitUtils instead of inline WebDriverWait
		WaitUtils.waitForPageLoad(driver, 10);

		// Example: scroll to bottom if needed
		JSUtils.scrollToBottom(driver);
	}

	@Given("the user is on the {string} page")
	public void the_user_is_on_the_page(String topicName) {
		String path = TopicUrlRegistry.getUrlForTopic(topicName);
		driver.get("https://dsportalapp.herokuapp.com" + path);

		WaitUtils.waitForPageLoad(driver, 10);
		JSUtils.scrollToBottom(driver);

	}

	@When("the user selects {string} from the sidebar")
	public void the_user_selects_from_the_sidebar(String topicName) {
		topicPage.clickSidebarTopic(topicName);
		selectedTopic = topicName;

	}

	@Then("the relevant topic content should appear {string}")
	public void the_relevant_topic_content_should_appear(String topicName) {
		Assert.assertTrue(topicPage.waitForTopicContent(topicName),
				"❌ Page loaded with boilerplate only — no content or error message displayed for: " + topicName);
	}

	@When("the user selects practice question {string}")
	public void the_user_selects_practice_question(String questionName) {
		WebElement link = WaitUtils.waitForClickable(driver, By.linkText(questionName), 10);
		JSUtils.scrollIntoView(driver, link); // ensure it’s visible
		link.click();
	}

	@Then("an appropriate error message should be displayed if the page fails to load")
	public void verify_practice_page() {
		Assert.assertTrue(topicPage.isPracticePageValid(),
				"❌ Practice Questions page is either blank or showing wrong content.");
	}

}
