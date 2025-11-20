package pages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.JSUtils;
import utils.WaitUtils;

public class LinkedListPage extends BaseTopicPage {

	private static final String LINKED_LIST_URL = "https://dsportalapp.herokuapp.com/linked-list";

	@FindBy(css = "a[href='/linked-list/practice']")
	WebElement practiceBtn;

	@FindBy(tagName = "body")
	WebElement pageBodyText;

	@FindBy(xpath = "//*[self::h4 or self::p][@class='bg-secondary text-white']")
	private List<WebElement> headings;

	@FindBy(linkText = "Try here>>>")
	private WebElement tryHereButton;

	@FindBy(xpath = "//*[self::h1 or self::h2 or self::h3 or self::h4 or self::p]")
	private List<WebElement> headers;

	public LinkedListPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigateToLinkedListPage() {
		driver.get(LINKED_LIST_URL);
	}

	public boolean isTextVisible(String expectedText) {
		for (WebElement heading : headings) {
			if (heading.getText().trim().equalsIgnoreCase(expectedText.trim())) {
				JSUtils.scrollIntoView(driver, heading); // use utility
				return heading.isDisplayed();
			}
		}
		return false;
	}

	public void scrollToLoadAllTopics() {
		JSUtils.scrollToLoadAllTopics(driver);
	}

	public Set<String> getAllDropDownLinks() {

		scrollToLoadAllTopics();
		Set<String> linkTexts = new HashSet<>();
		for (WebElement link : topicLinks) {
			if (link.isDisplayed()) {
				linkTexts.add(link.getText().trim());
			}
		}
		return linkTexts;
	}

	public void clickTopic(String topicName) {
		for (WebElement link : topicLinks) {
			if (link.getText().trim().equalsIgnoreCase(topicName.trim())) {
				// Wait until clickable
				WaitUtils.waitForClickable(driver, link, 10);

				// Scroll into view before clicking
				JSUtils.scrollIntoView(driver, link);

				link.click();
				return;
			}
		}
		throw new NoSuchElementException("Topic link not found: " + topicName);
	}

	public boolean isContentKeywordVisible(String keyword) {

		return bodyText.getText().toLowerCase().contains(keyword.toLowerCase());
	}

	public boolean isCurrentUrlMatching(String expectedPath) {
		return driver.getCurrentUrl().contains(expectedPath);
	}

	public void scrollToBottom() {
		JSUtils.scrollToBottom(driver); // scroll down
		WaitUtils.waitForPageLoad(driver, 5); // wait for lazy load instead of Thread.sleep
	}

	public boolean isTryHereButtonVisible() {
		return tryHereButton.isDisplayed();
	}

	public void clickTryHereButton() {
		tryHereButton.click();
	}

	public boolean isCodeEditorVisible() {
		return codeEditor.isDisplayed();
	}

	public void clickPracticeQuestions() {
		// Check current URL first
		String currentUrl = driver.getCurrentUrl();
		if (!currentUrl.contains("/linked-list")) {
			throw new IllegalStateException("Not on Linked List page!");
		}

		practiceBtn.click();
	}

	public boolean isSilentFailureDetected() {
		String pageText = pageBodyText.getText();
		// System.out.println("Page text:\n" + pageText);

		List<String> boilerplate = Arrays.asList("NumpyNinja", "Data Structures", "ValidUser", "Sign out");
		long boilerplateCount = boilerplate.stream().filter(pageText::contains).count();

		return boilerplateCount == boilerplate.size();
	}

	public boolean isErrorMessageVisible() {
		return errorMessage.isDisplayed();
	}
}