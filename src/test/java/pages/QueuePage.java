package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DriverManager.DriverFactory;
import utils.JSUtils;
import utils.WaitUtils;

public class QueuePage {
	private final WaitUtils wait;
	private final WebDriver driver;
	private static final Logger logger = LoggerFactory.getLogger(QueuePage.class);

	@FindBy(xpath = "//*[@class='bg-secondary text-white']")
	private List<WebElement> headings;

	@FindBy(xpath = "//a[@class='list-group-item']")
	private List<WebElement> queue_subTopicsLinks;

	@FindBy(xpath = "//a[contains(text(),'Try')]")
	private WebElement tryhere_button;

	@FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
	private WebElement PracticeQuestionsLink;

	@FindBy(xpath = "//div[@class='list-group']")
	private List<WebElement> questionslist;

	// action

	public QueuePage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(this.driver, this);
		wait = new WaitUtils();
	}

	public List<String> getHeadingText() {
		wait.waitForVisibilityOfAll(headings);
		List<String> headingTexts = new ArrayList<>();
		for (WebElement heading : headings) {
			headingTexts.add(heading.getText().trim());
		}
		return headingTexts;
	}

	public List<String> subTopicLinks() {
		wait.waitForVisibilityOfAll(queue_subTopicsLinks);
		List<String> subTopic = new ArrayList<>();
		for (WebElement topicLink : queue_subTopicsLinks) {
			if (topicLink.isDisplayed() && topicLink.isEnabled()) {
				subTopic.add(topicLink.getText().trim());
			}
		}
		return subTopic;
	}

	public void clickTopicLink(String topicName) {
		wait.waitForVisibilityOfAll(queue_subTopicsLinks);
		for (WebElement link : queue_subTopicsLinks) {
			if (link.getText().trim().equalsIgnoreCase(topicName)) {
				JSUtils.scrollIntoView(link);
				wait.waitForClickable(link).click();
				return;
			}
		}
		throw new NoSuchElementException("Topic link not found: " + topicName);
	}

	public boolean checkTryhereButton_Display() {
		return WaitUtils.isVisible(driver, tryhere_button, 10);
	}

	public void clickTryHereButton() {
		JSUtils.scrollIntoView(tryhere_button);
		wait.waitForClickable(tryhere_button).click();
	}

	public boolean isPracticeQuestionLinkVisible() {
		return WaitUtils.isVisible(driver, PracticeQuestionsLink, 10);
	}

	public boolean isPracticeQuestionLinkEnabled() {
		return PracticeQuestionsLink.isEnabled();
	}

	public void clickPracticeQuestionsLink() {
		JSUtils.scrollIntoView(PracticeQuestionsLink);
		wait.waitForClickable(PracticeQuestionsLink).click();
	}

	public List<String> getQuestionsList() {
		try {
			wait.waitForVisibilityOfAll(questionslist);
			return questionslist.stream().map(WebElement::getText).collect(Collectors.toList());
		} catch (Exception e) {
			logger.info("Questions list not found: {} ", e.getMessage());
			return Collections.emptyList();
		}
	}


}
