package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverManager.DriverFactory;
import utils.JSUtils;
import utils.NavigationUtil;
import utils.WaitUtils;

public class LinkedListPage {
	private WaitUtils wait;
	private WebDriver driver;

	// locators

	@FindBy(xpath = "//*[@class='bg-secondary text-white']")
	private List<WebElement> headings;

	@FindBy(xpath = "//a[@class='list-group-item']")
	private List<WebElement> LinkedList_subtopicslinks;

	@FindBy(xpath = "//a[contains(text(),'Try')]")
	private WebElement tryhere_button;

	@FindBy(xpath = "//button[text()='Run']")
	private WebElement run_button;

	@FindBy(xpath = "//pre[@id='output']")
	private WebElement outputconsole;

	@FindBy(xpath = "//form[@id='answer_form']")
	private WebElement coding_area;

	@FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
	private WebElement Practicequestionslink;

	@FindBy(xpath = "//div[@class='list-group']")
	private List<WebElement> questionslist;

	@FindBy(xpath = "//a[@href='linked-list']")
	WebElement LinkedListGetStarted;

	// action

	public LinkedListPage() {
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
		return headingtexts;
	}

	public List<String> subtopiclinks() {
		wait.waitForVisibilityOfAll(LinkedList_subtopicslinks);
		List<String> subtopiclinks = new ArrayList<String>();
		for (WebElement topiclink : LinkedList_subtopicslinks) {
			if (topiclink.isDisplayed() && topiclink.isEnabled()) {
				subtopiclinks.add(topiclink.getText().trim());
			}
		}
		return subtopiclinks;
	}

	public void clickTopicLink(String topicName) {
		wait.waitForVisibilityOfAll(LinkedList_subtopicslinks);
		for (WebElement link : LinkedList_subtopicslinks) {
			if (link.getText().trim().equalsIgnoreCase(topicName)) {
				JSUtils.scrollIntoView(link);
				wait.waitForClickable(link).click();
				return;
			}
		}
		throw new NoSuchElementException("Topic link not found: " + topicName);
	}

	public boolean checktryherebutton_displayed() {
		return WaitUtils.isVisible(driver, tryhere_button, 10);
	}

	public void clickTryHereButton() {
		JSUtils.scrollIntoView(tryhere_button);
		wait.waitForClickable(tryhere_button).click();
	}

	public boolean isPracticeQuestionLinkVisible() {
		JSUtils.scrollIntoView(Practicequestionslink);
		return WaitUtils.isVisible(driver, Practicequestionslink, 10);
	}

	public boolean isPracticeQuestionLinkEnabled() {
		return Practicequestionslink.isEnabled();
	}

	public void clickPracticeQuestionsLink() {
		JSUtils.scrollIntoView(Practicequestionslink);
		wait.waitForClickable(Practicequestionslink).click();
	}

	public List<String> getQuestionsList() {
		try {
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
				return;
			}
		}

	}

	public void navigateToTryEditor(String topicPage) {
		clickTopicLink(topicPage);
		wait.waitForClickable(tryhere_button).click();
		wait.waitForPageLoad();
	}

	public void enterCode(String codeSnippet) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		act.moveToElement(coding_area).click().perform();

		js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue('');");
		js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);", codeSnippet);
	}

	public void clickRunButton() {
		wait.waitForClickable(run_button).click();
	}

	public String getOutputText() {

		// 1. Check for alert first
		String alertText = handleAlertIfPresent();
		if (alertText != null) {
			System.out.println("Alert result: " + alertText);
			return alertText;
		}

		// 2. Otherwise read console output
		wait.waitForPageLoad();
		String result = wait.waitForCodeMirrorOutput("output", 120);
		System.out.println("Submission result: " + result);
		return result;
	}

	public String handleAlertIfPresent() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			alert.accept();
			return alertText;
		} catch (NoAlertPresentException e) {
			return null;
		}
	}

	public void goToLinkedListPage() {
		NavigationUtil.goTo("linked-list");
	}

}
