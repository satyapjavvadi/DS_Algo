package stepdefinition;

import java.util.Arrays;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LinkedListPage;
import pages.PageObjectManager;

public class LinkedListPageStepDefinition {

	private final PageObjectManager pom;
	private final WebDriver driver;
	private LinkedListPage linkedListPage;

	String initialTopic = "";
	String selectedTopic = "";

	public LinkedListPageStepDefinition() {

		driver = DriverFactory.getDriver();
		pom = new PageObjectManager(driver);

		linkedListPage = pom.getLinkedListPage();

	}

	@Given("the signed-in user has navigated to the Linked List page")
	public void the_signed_in_user_has_navigated_to_the_linked_list_page() {
		pom.getLoginPage().navigateToLoginPage();
		pom.getLoginPage().enterUsername("validUser");
		pom.getLoginPage().enterPassword("validPass");
		pom.getLoginPage().clickLoginButton();

		pom.getLinkedListPage().navigateToLinkedListPage();
	}

	@Then("the user should be able to see {string}")
	public void the_user_should_be_able_to_see(String expectedText) {
		LinkedListPage linkedListPage = pom.getLinkedListPage();
		boolean isVisible = linkedListPage.isTextVisible(expectedText);

		// System.out.println("Checking visibility of: " + expectedText + " → " +
		// isVisible);
		Assert.assertTrue(isVisible, "❌ Text not visible: " + expectedText);
	}

	@Then("the user should be able to see Linked List topics as clickable links under {string}")
	public void the_user_should_be_able_to_see_linked_list_topics_as_clickable_links_under(String sectionTitle) {

		Set<String> actualLinkTexts = linkedListPage.getAllDropDownLinks();
		Set<String> expectedLinkTexts = Set.of("Introduction", "Creating Linked LIst", "Types of Linked List",
				"Implement Linked List in Python", "Traversal", "Insertion", "Deletion");

		// System.out.println("Expected topics: " + expectedLinkTexts);
		// System.out.println("actualLinkTexts = " + actualLinkTexts);
		Assert.assertTrue(actualLinkTexts.containsAll(expectedLinkTexts), "Not all expected links are present.");
	}

	@When("the user selects {string} under Topics Covered")
	public void the_user_selects_under_topics_covered(String topic) {

		linkedListPage.clickTopic(topic);
	}

	@Then("the {string} content should be visible")
	public void the_content_should_be_visible(String expectedText) {

		boolean isVisible = linkedListPage.isContentKeywordVisible(expectedText);

		Assert.assertTrue(isVisible, "Expected content not visible: " + expectedText);
	}

	@When("the user scrolls down")
	public void the_user_scrolls_down() {

		linkedListPage.scrollToBottom();
	}

	@Then("the {string} should be visible")
	public void the_expected_content_should_be_visible(String keywords) {
		boolean found = Arrays.stream(keywords.split(",")).map(String::trim)
				.anyMatch(linkedListPage::isKeywordVisibleAnywhere);

		Assert.assertTrue(found,
				"None of the keywords '" + keywords + "' found in headings, paragraphs, or code blocks.");
	}

}
