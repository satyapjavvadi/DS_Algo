package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverManager.DriverFactory;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.JSUtils;
import utils.TestContext;
import utils.WaitUtils;

public class QueuePage {
	private WebDriver driver;

	public QueuePage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	// locators

	@FindBy(xpath = "//*[@class='bg-secondary text-white']")
	private List<WebElement> headings;

	@FindBy(xpath = "//a[@class='list-group-item']")
	private List<WebElement> queue_subtopicslinks;

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

	@FindBy(xpath = "//a[@href='queue']")
	WebElement queueGetStarted;

	// action

	private void navigateTo(String path, String expectedTitle) {
		driver.get(ConfigReader.getProperty("baseURL") + path);
		WaitUtils.waitForTitleContains(driver, expectedTitle, 10);
	}

	public void navigateToQueueUI() {
		navigateTo("queue", "Queue");
	}

	public String Queuepage_link_Check() {

		String actualurl = driver.getCurrentUrl();

		return actualurl;
	}

	public void goto_queuemainpage() {
		driver.get("https://dsportalapp.herokuapp.com/queue/");
	}

	public List<String> getheadingtext() {
		WaitUtils.waitForVisibilityOfAll(driver, headings, 10);

		List<String> headingtexts = new ArrayList<>();
		for (WebElement heading : headings) {
			headingtexts.add(heading.getText().trim());
		}
		return headingtexts;
	}

	public List<String> subtopiclinks() {

		WaitUtils.waitForVisibilityOfAll(driver, queue_subtopicslinks, 10);

		List<String> subtopicTexts = new ArrayList<>();
		for (WebElement topiclink : queue_subtopicslinks) {
			subtopicTexts.add(topiclink.getText().trim());
		}
		return subtopicTexts;
	}

	public void clicktopiclink(String topicname) {

		WaitUtils.waitForVisibilityOfAll(driver, queue_subtopicslinks, 10);
		JSUtils.scrollToLoadAllTopics(driver);

		for (WebElement link : queue_subtopicslinks) {

			String text = link.getText().trim();
			System.out.println("Link text: '" + text + "'");

			if (text.equalsIgnoreCase(topicname.trim())) {
				System.out.println("topic from excel: '" + topicname + "'");

				try {
					link.click();
				} catch (Exception e) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
				}
				return;
			}
		}

		throw new NoSuchElementException("Topic link not found on Queue page: " + topicname);
	}

	public void scrolltobottom() {
		JSUtils.scrollToBottom(driver);
	}

	public boolean checktryherebutton_displayed() {
		JSUtils.scrollToBottom(driver);
		return WaitUtils.isVisible(driver, tryhere_button, 20);
	}

	public void clickTryHereButton() {
		WaitUtils.waitForClickable(driver, tryhere_button, 10);
		JSUtils.scrollIntoView(driver, tryhere_button);
		JSUtils.clickElement(driver, tryhere_button);
	}

	public void enterCode(String code) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("var cm = document.querySelector('.CodeMirror').CodeMirror;" + "cm.setValue(arguments[0]);",
				code);

	}

	public void clickRunButton() {
		run_button.click();

	}

	public void runqueue_editor(String scenarioType) {
		TestContext.testData = ExcelReader.getTestData(scenarioType);
		Map<String, String> data = TestContext.testData;

		String topicPage = data.get("topic_page");
		String code = data.get("code");
		String expectedResult = data.get("expected_result");

		navigateToQueueUI();
		System.out.println(topicPage);
		clicktopiclink(topicPage);

		clickTryHereButton();

		enterCode(code);

		clickRunButton();

		// Storing expected result for assertion step
		TestContext.testData.put("expected_result", expectedResult);
	}

	public String getQueueEditorResult() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			String alertText = alert.getText().trim();
			alert.accept();
			return alertText;
		} catch (Exception ignored) {
		}

		// Check console area
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement output = wait.until(ExpectedConditions.visibilityOf(outputconsole));
			String text = output.getText().trim();

			if (!text.isEmpty()) {
				return text;
			}
		} catch (Exception ignored) {
		}
		return "NO_OUTPUT_FOUND";
	}

	public String getOutput_text() {

		return outputconsole.getText().trim();

	}

	public String getAlerttext() {
		Alert alert = driver.switchTo().alert();
		String alertmsgString = alert.getText();
		alert.accept();
		return alertmsgString;

	}

	public boolean isPracticeQuestionLinkVisible() {
		return Practicequestionslink.isDisplayed();
	}

	public boolean isPracticeQuestionLinkEnabled() {
		return Practicequestionslink.isEnabled();
	}

	public void clickPracticeQuestionsLink() {
		WaitUtils.waitForClickable(driver, Practicequestionslink, 10);
		JSUtils.scrollIntoView(driver, Practicequestionslink);
		JSUtils.clickElement(driver, Practicequestionslink);

	}

	public boolean isQuestionsListDisplayed() {
		try {
			if (questionslist.size() > 0) {

				WaitUtils.waitForVisibilityOfAll(driver, questionslist, 10);

				for (WebElement q : questionslist) {
					if (q.isDisplayed()) {
						return true; // At least one question visible
					}
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println("Questions list not found: " + e);
			return false;
		}
	}

}
