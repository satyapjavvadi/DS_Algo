package pages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import DriverManager.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.JSUtils;
import utils.TopicNameNormalizer;
import utils.WaitUtils;

public class LinkedListPage {

	private WebDriver driver;

	@FindBy(css = "a.list-group-item")
	List<WebElement> topicLinks;

	@FindBy(css = ".error-message, .alert")
	WebElement errorMessage;

	@FindBy(xpath = "//a[contains(text(),'Try here>>>')]")
	WebElement tryHereButton;

	@FindBy(css = ".CodeMirror")
	WebElement codeEditor;

	@FindBy(tagName = "body")
	WebElement bodyText;

	@FindBy(css = "li.list-group-item a")
	private List<WebElement> sidebarLinks;

	@FindBy(tagName = "code")
	List<WebElement> codeBlocks;

	@FindBy(css = "a[href$='/practice']")
	private WebElement practiceQuestionsLink;

	@FindBy(css = "div.content")
	private WebElement topicContent;

	@FindBy(xpath = "//button[text()='Run']")
	WebElement runButton;

	@FindBy(id = "output")
	WebElement outputArea;

	public LinkedListPage( ) {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	private void navigateTo(String path, String expectedTitle) {
		driver.get(ConfigReader.getProperty("baseURL") + path);
		WaitUtils.waitForTitleContains(driver, expectedTitle, 10);
	}

	public void navigateToLinkedListPage() {
		navigateTo("linked-list", "Linked List");
	}

	public void navigateToIntroductionPage() {
		navigateTo("linked-list/introduction", "Introduction");
	}

	public boolean isSidebarVisible() {
		return WaitUtils.waitForVisibilityOfAll(driver, sidebarLinks, 10);
	}

	public boolean isTopicsCoveredVisible(String expectedText) {

		return driver.getPageSource().contains(expectedText);
	}

	public Set<String> getAllDropDownLinks() {

		JSUtils.scrollToLoadAllTopics(driver);
		Set<String> linkTexts = new HashSet<>();
		for (WebElement link : topicLinks) {
			if (link.isDisplayed()) {
				linkTexts.add(link.getText().trim());
			}
		}
		return linkTexts;
	}

	public void clickSidebarTopic(String topicName) {
		String normalized = TopicNameNormalizer.normalize(topicName);

		if (normalized.equalsIgnoreCase("Practice Questions")) {
			WebElement link = WaitUtils.waitForClickable(driver, practiceQuestionsLink, 10);
			JSUtils.scrollIntoView(driver, link);
			link.click();
		} else {
			for (WebElement link : sidebarLinks) {
				if (link.getText().trim().equalsIgnoreCase(normalized)) {
					WebElement clickableLink = WaitUtils.waitForClickable(driver, link, 10);
					JSUtils.scrollIntoView(driver, clickableLink);
					clickableLink.click();
					return;
				}
			}
			throw new NoSuchElementException("Sidebar link not found: " + normalized);
		}
	}

	// Verify current URL contains expected path
	public boolean isCurrentUrlMatching(String expectedPath) {
		String currentUrl =  driver.getCurrentUrl();
		return currentUrl != null && currentUrl.toLowerCase().contains(expectedPath.toLowerCase());
	}

	public void clickTopic(String topicName) {
		for (WebElement link : topicLinks) {
			if (link.getText().trim().equalsIgnoreCase(topicName.trim())) {
				link.click();
				return;
			}
		}
		throw new NoSuchElementException("Topic link not found: " + topicName);
	}

	public void scrollToBottom() {
		JSUtils.scrollToBottom(driver);
	}

	public boolean isTryHereButtonVisible() {
		try {
			WebElement button = WaitUtils.waitForVisibility(driver, tryHereButton, 10);
			JSUtils.scrollIntoView(driver, button);
			return button.isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void clickTryHereButton() {
		WebElement button = WaitUtils.waitForClickable(driver, tryHereButton, 10);
		JSUtils.scrollIntoView(driver, button);
		button.click();
	}

	public boolean isCodeEditorVisible() {
		return codeEditor.isDisplayed();
	}

	public boolean waitForTopicContent(String expectedText) {
		String lower = expectedText.toLowerCase();

		// Build case-insensitive locator
		By contentLocator = By.xpath("//*[self::h1 or self::h2 or self::h3 or self::h4 or self::p]"
				+ "[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
				+ lower + "')]");

		WebElement contentElement = WaitUtils.waitForVisibility(driver, contentLocator, 10);

		return contentElement.getText().toLowerCase().contains(lower);
	}

	public boolean isSilentFailureDetected() {
		String pageText = bodyText.getText();
		List<String> boilerplate = Arrays.asList("NumpyNinja", "Data Structures", "ValidUser", "Sign out");
		return boilerplate.stream().allMatch(pageText::contains);
	}

	public boolean isTextVisible(String text) {
		if (text.equalsIgnoreCase("Topics Covered")) {
			return isTopicsCoveredVisible(text);
		}
		String pageText = bodyText.getText().toLowerCase();
		return pageText.contains(text.toLowerCase().trim());
	}

	// helper: compare actual vs expected
	public void assertTopicsMatch(Set<String> expectedTopics) {
		Set<String> actualTopics = getAllDropDownLinks();

		// normalize case/whitespace
		Set<String> actualNormalized = actualTopics.stream().map(s -> s.toLowerCase().trim())
				.collect(Collectors.toSet());
		Set<String> expectedNormalized = expectedTopics.stream().map(s -> s.toLowerCase().trim())
				.collect(Collectors.toSet());

		if (!actualNormalized.equals(expectedNormalized)) {
			Set<String> missing = new HashSet<>(expectedNormalized);
			missing.removeAll(actualNormalized);

			Set<String> extra = new HashSet<>(actualNormalized);
			extra.removeAll(expectedNormalized);

			Assert.fail("Mismatch in Linked List topics.\nMissing: " + missing + "\nExtra: " + extra);
		}
	}

	public boolean containsKeyword(String keyword) {
		String lower = keyword.toLowerCase();
		return bodyText.getText().toLowerCase().contains(lower) || WaitUtils.isKeywordInHeaders(driver, lower)
				|| WaitUtils.isKeywordInElements(codeBlocks, lower);
	}

	public String getBodyText() {
		return bodyText.getText();
	}

}
