package pages;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverManager.DriverFactory;
import utils.JSUtils;
import utils.WaitUtils;

public class StackPage {

	private final WebDriver driver;
	private final WaitUtils wait;

	@FindBy(xpath = "//*[@class='bg-secondary text-white']")
	private List<WebElement> headings;

	@FindBy(xpath = "//a[@class='list-group-item']")
	private List<WebElement> topicLinks;

	@FindBy(xpath = "//a[contains(text(),'Try')]")
	private WebElement tryHereButton;

	@FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
	private WebElement practiceQuestionsLink;

	@FindBy(xpath = "//div[@class='list-group']")
	private List<WebElement> questionsList;

	public StackPage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
		this.wait = new WaitUtils();
	}

	public List<String> getHeadingText() {
		wait.waitForVisibilityOfAll(headings);
		return headings.stream().map(e -> e.getText().trim()).collect(Collectors.toList());
	}

	public List<String> getSubtopicLinks() {
		wait.waitForVisibilityOfAll(topicLinks);
		return topicLinks.stream().map(e -> e.getText().trim()).collect(Collectors.toList());
	}

	public void clickTopicLink(String topicName) {
		System.out.println("Debug: Topic links found:");
		for (WebElement link : topicLinks) {
			System.out.println("-> [" + link.getText().trim() + "]");
		}

		for (WebElement link : topicLinks) {
			if (link.getText().trim().equalsIgnoreCase(topicName)) {
				JSUtils.scrollIntoView(link);
				wait.waitForClickable(link).click();
				return;
			}
		}
		throw new NoSuchElementException("Topic link not found: " + topicName);
	}

	public boolean isTryHereButtonDisplayed() {
		return WaitUtils.isVisible(driver, tryHereButton, 10);
	}

	public void clickTryHereButton() {
		JSUtils.scrollIntoView(tryHereButton);
		wait.waitForClickable(tryHereButton).click();
	}

	public boolean isPracticeQuestionLinkVisible() {
		JSUtils.scrollIntoView(practiceQuestionsLink);
		return WaitUtils.isVisible(driver, practiceQuestionsLink, 10);
	}

	public boolean isPracticeQuestionLinkEnabled() {
		return practiceQuestionsLink.isEnabled();
	}

	public void clickPracticeQuestionsLink() {
		JSUtils.scrollIntoView(practiceQuestionsLink);
		wait.waitForClickable(practiceQuestionsLink).click();
	}

	public List<String> getQuestionsList() {
		try {
			wait.waitForVisibilityOfAll(questionsList);
			return questionsList.stream().map(e -> e.getText().trim()).collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println("Questions list not found: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
