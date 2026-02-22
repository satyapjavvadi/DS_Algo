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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import DriverManager.DriverFactory;
import utils.JSUtils;
import utils.WaitUtils;

public class ArrayPage {
	private WaitUtils wait;
	private WebDriver driver;
	private static final Logger logger = LoggerFactory.getLogger(ArrayPage.class);

	// locators
	@FindBy(xpath = "//*[@class='bg-secondary text-white']")
	private List<WebElement> headings;

	@FindBy(xpath = "//a[@class='list-group-item']")
	private List<WebElement> array_subtopicslinks;

	@FindBy(xpath = "//a[contains(text(),'Try')]")
	private WebElement tryhere_button;

	@FindBy(xpath = "//button")
	private WebElement run_button;

	@FindBy(xpath = "//pre[@id='output']")
	private WebElement outputconsole;

	@FindBy(xpath = "//form[@id='answer_form']")
	private WebElement coding_area;

	@FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
	private WebElement Practicequestionslink;

	@FindBy(xpath = "//div[@class='list-group']")
	private List<WebElement> questionslist;

	@FindBy(xpath = "//*[@type='submit']")
	private List<WebElement> submit;

	@FindBy(xpath = "//div[@align='left']")
	private WebElement output;

	// action

	public ArrayPage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(this.driver, this);
		wait = new WaitUtils();
	}

	public List<String> getheadingtext() {
		wait.waitForVisibilityOfAll(headings);
		List<String> headingtexts = new ArrayList<String>();
		for (WebElement heading : headings) {
			headingtexts.add(heading.getText().trim());
		}
		logger.info("Headings found: {}", headingtexts);
		return headingtexts;
	}

	public List<String> subtopiclinks() {
		wait.waitForVisibilityOfAll(array_subtopicslinks);
		List<String> subtopiclinks = new ArrayList<String>();
		for (WebElement topiclink : array_subtopicslinks) {
			if (topiclink.isDisplayed() && topiclink.isEnabled()) {
				subtopiclinks.add(topiclink.getText().trim());
			}
		}
		logger.info("Subtopic links found: {}", subtopiclinks);
		return subtopiclinks;
	}

	public void clickTopicLink(String topicName) {
		wait.waitForVisibilityOfAll(array_subtopicslinks);
		for (WebElement link : array_subtopicslinks) {
			if (link.getText().trim().equalsIgnoreCase(topicName)) {
				JSUtils.scrollIntoView(link);
				wait.waitForClickable(link).click();
				logger.info("Successfully clicked topic link: {}", topicName);
				return;
			}
		}
		throw new NoSuchElementException("Topic link not found: " + topicName);
	}

	public boolean checktryherebutton_displayed() {
		logger.info("Checking visibility of Try Here button");
		return WaitUtils.isVisible(driver, tryhere_button, 10);
	}

	public void clickTryHereButton() {
		logger.info("Clicking Try Here button");
		JSUtils.scrollIntoView(tryhere_button);

		wait.waitForClickable(tryhere_button).click();
	}

	public boolean isPracticeQuestionLinkVisible() {
		logger.info("Checking if Practice Questions link is visible");
		return WaitUtils.isVisible(driver, Practicequestionslink, 10);
	}

	public boolean isPracticeQuestionLinkEnabled() {
		logger.info("Checking if Practice Questions link is enabled");
		return Practicequestionslink.isEnabled();
	}

	public void clickPracticeQuestionsLink() {
		logger.info("Clicking Practice Questions link");
		JSUtils.scrollIntoView(Practicequestionslink);
		wait.waitForClickable(Practicequestionslink).click();
	}

	public List<String> getQuestionsList() {
		try {
			logger.info("Fetching list of practice questions");
			wait.waitForVisibilityOfAll(questionslist);
			return questionslist.stream().map(WebElement::getText).collect(Collectors.toList());

		} catch (Exception e) {
			System.out.println("Questions list not found: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	public void clickProblemLink(String problemName) {
		for (WebElement eachQuestion : questionslist) {
			String questionName = eachQuestion.getText();
			if (questionName.equalsIgnoreCase(problemName)) {
				eachQuestion.click();
				logger.info("Successfully clicked problem: {}", problemName);
				return;
			}
		}

	}

	public boolean getButtonTextAssesmentPage(String buttonText) {
		String button = run_button.getText();
		logger.info("Validating button text. Expected: {}, Actual: {}", buttonText, button);
		if (button.equalsIgnoreCase(buttonText)) {
			return true;
		} else
			return false;

	}

	public boolean isSubmitButtonPresent() {
		logger.info("Submit button presence check");
		if (submit.size() > 0) {
			return true;
		} else
			return false;
	}

	public void submitProblem() {
		logger.info("Submitting problem");
		for (WebElement eachType : submit) {
			eachType.click();
			return;
		}
	}

	public String getConsoleOutput() {
		logger.info("Fetching console output");
		wait.waitForPageLoad();

		String result = wait.waitForCodeMirrorOutput("output", 120);
		logger.info("Submission result: {}", result);
		return result;
	}

}
