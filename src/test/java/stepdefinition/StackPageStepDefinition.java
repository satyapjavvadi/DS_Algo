package stepdefinition;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import pages.StackPage;
import utils.ExcelReader;

public class StackPageStepDefinition {

	private final PageObjectManager pom;
	WebDriver driver;
	private StackPage stackPage;

	public StackPageStepDefinition() {

		driver = DriverFactory.getDriver();
		pom = new PageObjectManager(driver);

		stackPage = pom.getStackPage();

	}

	@Given("the registered user has navigated to the Stack page")
	public void the_signed_in_user_has_navigated_to_the_stack_page() {

		driver.get("https://dsportalapp.herokuapp.com/login");
		pom.getLoginPage().enterUsername("validUser");
		pom.getLoginPage().enterPassword("validPass");
		pom.getLoginPage().clickLoginButton();

		driver.get("https://dsportalapp.herokuapp.com/stack");
	}

	@Then("the user should be able to see static content from Excel")
	public void verify_static_content_from_excel() throws IOException {
		List<String> expectedTexts = ExcelReader.getExpectedTexts("StackPageScenarios.xlsx", "expected_text");

		for (String text : expectedTexts) {
			boolean visible = driver.getPageSource().contains(text);
			Assert.assertTrue(visible, "Expected text not found: " + text);
		}

		System.out.println("Validating static content:");
		for (String text : expectedTexts) {
			System.out.println("→ " + text);
		}

	}

	@Then("the user should be able to navigate to each Stack topic from Excel")
	public void verify_stack_landing_page_links() throws IOException {
		List<Map<String, String>> rows = ExcelReader.getTopicNavigationRows("src/test/resources/DS_ExcelData.xlsx",
				"StackPageContent");

		List<String> expectedTopics = rows.stream().map(row -> row.get("topic").trim()).collect(Collectors.toList());

		List<String> actualTopics = pom.getStackPage().getVisibleTopicTexts();

		for (String expected : expectedTopics) {
			Assert.assertTrue(actualTopics.contains(expected), "❌ Missing topic link on Stack page: " + expected);
		}

		System.out.println("✅ All expected topic links are present on Stack landing page.");
	}

	@When("the user validates scrollable content from Excel")
	public void verify_scrollable_topic_content_from_excel() throws IOException {
		List<Map<String, String>> rows = ExcelReader.getScrollValidationRows("src/test/resources/DS_ExcelData.xlsx",
				"StackPageContent");

		for (Map<String, String> row : rows) {
			String topicPage = row.get("topic_page").trim();
			String expectedContent = row.get("expected_content").trim();

			String path = switch (topicPage) {
			case "Operations in Stack" -> "operations-in-stack";
			case "Stack Applications" -> "stack-applications";
			case "Implementation" -> "implementation";
			default -> throw new IllegalArgumentException("Unknown topic page: " + topicPage);
			};

			driver.get("https://dsportalapp.herokuapp.com/stack/" + path);

			// Scroll down
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			// Wait for body to be present
			WebElement body = new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

			// Extract page text
			String pageText = body.getText().toLowerCase();

			// Validate each keyword
			String[] keywords = expectedContent.toLowerCase().split(",");
			for (String keyword : keywords) {
				Assert.assertTrue(pageText.contains(keyword.trim()),
						"❌ Missing keyword: " + keyword + " on page: " + topicPage);
			}

			System.out.println("✅ Verified scroll content for: " + topicPage);
		}
	}

}
