package pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.JSUtils;
import utils.WaitUtils;

public abstract class BaseTopicPage {

	protected WebDriver driver;

	@FindBy(tagName = "body")
	protected WebElement bodyText;

	@FindBy(css = "a.list-group-item")
	protected List<WebElement> topicLinks;

	@FindBy(xpath = "//*[text()='Try here>>>']")
	protected WebElement tryHereButton;

	@FindBy(css = ".CodeMirror")
	protected WebElement codeEditor;

	@FindBy(tagName = "code")
	protected List<WebElement> codeBlocks;

	@FindBy(xpath = "//div[contains(@class,'col-2')]//a")
	protected List<WebElement> links;

	@FindBy(css = ".error-message, .alert")
	WebElement errorMessage;

	@FindBy(xpath = "//*[self::h1 or self::h2 or self::h3 or self::h4 or self::p]")
	private List<WebElement> headers;

	@FindBy(xpath = "//h4")
	private List<WebElement> h4Headers;

	@FindBy(xpath = "//p")
	private List<WebElement> paragraphs;

	@FindBy(linkText = "Practice Questions")
	private WebElement practiceQuestionsLink;

	public BaseTopicPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isStaticTextVisible(String expectedKeyword) {
		return driver.getPageSource().toLowerCase().contains(expectedKeyword.toLowerCase());
	}

	/*
	 * public void scrollToBottom() { ((JavascriptExecutor)
	 * driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); try
	 * { Thread.sleep(500); } catch (InterruptedException e) {
	 * Thread.currentThread().interrupt(); } }
	 */

	public void scrollToBottom() {
		JSUtils.scrollToBottom(driver); // reuse your utility

		// Wait until the bottom element (like footer) is visible instead of
		// Thread.sleep
		// By footerLocator = By.tagName("footer"); // adjust to a real bottom element
		// WaitUtils.waitForVisibility(driver, footerLocator, 5);
	}

	public Set<String> getAllDropDownLinks() {
		Set<String> linkTexts = new HashSet<>();
		for (WebElement link : topicLinks) {
			if (link.isDisplayed()) {
				linkTexts.add(link.getText().trim());
			}
		}
		return linkTexts;
	}

	public void clickTopic(String topicName) {
		System.out.println("Current URL: " + driver.getCurrentUrl());
		System.out.println("Looking for topic: " + topicName);
		System.out.println("Sidebar links found: " + links.size());

		for (WebElement link : links) {
			String text = link.getText().trim();
			System.out.println("→ " + text);
			if (text.equalsIgnoreCase(topicName)) {
				link.click();
				return;
			}
		}
		throw new NoSuchElementException("Topic link not found: " + topicName);
	}

	public boolean isContentKeywordVisible(String keyword) {
		return bodyText.getText().toLowerCase().contains(keyword.toLowerCase());
	}

	public boolean isTryHereButtonVisible() {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// WebElement button =
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Try
		// here>>>")));
		return tryHereButton.isDisplayed();
	}

	public void clickTryHereButton() {
		tryHereButton.click();
	}

	public boolean isCodeEditorVisible() {
		return codeEditor.isDisplayed();
	}

	public boolean isKeywordVisibleAnywhere(String keyword) {
		for (WebElement header : headers) {
			if (header.getText().toLowerCase().contains(keyword.toLowerCase())) {
				return true;
			}
		}

		for (WebElement code : codeBlocks) {
			if (code.getText().contains(keyword)) {
				return true;
			}
		}
		return false;
	}

	public void clickSidebarTopic(String topicName) {
		System.out.println("Looking for sidebar topic: " + topicName);
		System.out.println("Sidebar links found: " + links.size());

		for (WebElement link : links) {
			String text = link.getText().trim();
			System.out.println("→ " + text);
			if (text.equalsIgnoreCase(topicName)) {
				link.click();
				return;
			}
		}
		throw new IllegalStateException("Sidebar link not found for topic: " + topicName);
	}

	public boolean waitForTopicContent(String expectedText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		By contentLocator = By.xpath(
				"//h4[contains(translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
						+ expectedText.toLowerCase() + "')] | "
						+ "//p[contains(translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
						+ expectedText.toLowerCase() + "')]");

		WebElement contentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(contentLocator));

		System.out.println("Page body text:\n" + bodyText.getText());
		return contentElement.getText().toLowerCase().contains(expectedText.toLowerCase());
	}

	public boolean isSilentFailureDetected() {
		// Check for meaningful content blocks
		List<WebElement> contentBlocks = driver.findElements(By.cssSelector(".container .row .col"));
		boolean hasSubstantialText = contentBlocks.stream().map(e -> e.getText().trim())
				.anyMatch(text -> text.length() > 50 && !text.equalsIgnoreCase("Practice Questions"));

		// Check for visible error messages
		boolean hasError = driver.findElements(By.cssSelector(".alert-danger, .error-message")).stream()
				.anyMatch(e -> e.isDisplayed() && !e.getText().trim().isEmpty());

		// Fail if neither content nor error is present
		return !(hasSubstantialText || hasError);
	}

	public boolean isPageContentMissing() {
		String text = bodyText.getText().trim();

		String[] boilerplateMarkers = { "NumpyNinja", "Data Structures", "ValidUser Sign out" };

		if (text.isEmpty())
			return true;

		long meaningfulLines = Arrays.stream(text.split("\\R")).map(String::trim).filter(line -> !line.isEmpty())
				.filter(line -> Arrays.stream(boilerplateMarkers).noneMatch(line::contains)).count();

		return meaningfulLines == 0;
	}

	public boolean isPracticePageValid() {
		String text = bodyText.getText().trim();

		// Case 1: Blank page (boilerplate only)
		if (isPageContentMissing()) {
			return false;
		}

		// Case 2: Misrouted content (shows topic explanations instead of questions)
		if (text.contains("Linked lists are an ordered collection")
				|| text.contains("Implementation of linked list in python")
				|| text.contains("Insertion in a Linked List") || text.contains("Applications of Stack")) {
			return false;
		}

		// Case 3: Real practice questions (look for keywords like "Question", "Solve",
		// "Try here>>>")
		return text.contains("Question") || text.contains("Solve") || text.contains("Try here>>>");
	}

	public void clickPracticeQuestions() {
		WebElement link = WaitUtils.waitForClickable(driver, practiceQuestionsLink, 10);
		link.click();
	}

	public boolean isErrorMessageVisible() {
		return errorMessage.isDisplayed();
	}

}
