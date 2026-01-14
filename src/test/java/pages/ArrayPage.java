package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverManager.DriverFactory;
import utils.ConfigReader;
import utils.JSUtils;
import utils.WaitUtils;

public class ArrayPage {
	private WebDriver driver;

	// locators

	@FindBy(xpath = "//*[@class='bg-secondary text-white']")
	private List<WebElement> headings;

	@FindBy(xpath = "//a[@class='list-group-item']")
	private List<WebElement> array_subtopicslinks;

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

	@FindBy(linkText = "Arrays in Python")
	WebElement arraysInPythonLink;

	@FindBy(xpath = "//a[@href='array']")
	WebElement arrayGetStarted;

	// action

	public ArrayPage(WebDriver driver) {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(this.driver, this);
	}

	private void navigateTo(String path, String expectedTitle) {
		driver.get(ConfigReader.getProperty("baseURL") + path);
		WaitUtils.waitForTitleContains(driver, expectedTitle, 10);
	}

	public void navigateToArrayUI() {
		navigateTo("array", "Array");
	}

	public void arrayGetStarted() {
		arrayGetStarted.click();
	}

	public String Arraypage_link_Check() {

		WaitUtils.waitForPageLoad(driver, 10);
		return driver.getCurrentUrl();
	}

	public List<String> getheadingtext() {
		WaitUtils.waitForVisibilityOfAll(driver, headings, 10);
		List<String> headingtexts = new ArrayList<String>();
		for (WebElement heading : headings) {
			headingtexts.add(heading.getText().trim());
		}
		return headingtexts;
	}

	public List<String> subtopiclinks() {
		WaitUtils.waitForVisibilityOfAll(driver, array_subtopicslinks, 10);
		List<String> subtopiclinks = new ArrayList<String>();
		for (WebElement topiclink : array_subtopicslinks) {
			if (topiclink.isDisplayed() && topiclink.isEnabled()) {
				subtopiclinks.add(topiclink.getText().trim());
			}
		}
		return subtopiclinks;
	}

	public void clicktopiclink(String topicname) {
		WaitUtils.waitForVisibilityOfAll(driver, array_subtopicslinks, 10);
		for (WebElement link : array_subtopicslinks) {
			if (link.getText().trim().equalsIgnoreCase(topicname)) {
				JSUtils.scrollIntoView(driver, link);
				WaitUtils.waitForClickable(driver, link, 10).click();

				return;
			}
		}
		throw new NoSuchElementException("Topic link not found: " + topicname);
	}

	public void scrolltobottom() {
		JSUtils.scrollToBottom(driver);
	}

	public boolean checktryherebutton_displayed(String buttonText, String section) {
		return WaitUtils.isVisible(driver, tryhere_button, 10);

	}

	public void clickTryHereButton() {
		JSUtils.scrollIntoView(driver, tryhere_button);
		WaitUtils.waitForClickable(driver, tryhere_button, 10).click();

	}

	public void clickArraysInPython() {
		JSUtils.scrollIntoView(driver, arraysInPythonLink);
		WaitUtils.waitForClickable(driver, arraysInPythonLink, 10).click();

	}

	public boolean isPracticeQuestionLinkVisible() {
		return WaitUtils.isVisible(driver, Practicequestionslink, 10);
	}

	public boolean isPracticeQuestionLinkEnabled() {
		return Practicequestionslink.isEnabled();
	}

	public void clickPracticeQuestionsLink() {
		JSUtils.scrollIntoView(driver, Practicequestionslink);
		WaitUtils.waitForClickable(driver, Practicequestionslink, 10).click();
	}

	public boolean isQuestionsListDisplayed() {
		try {
			WaitUtils.waitForVisibilityOfAll(driver, questionslist, 10);
			return !questionslist.isEmpty();
		} catch (Exception e) {
			System.out.println("Questions list not found: " + e);
			return false;
		}
	}

}
