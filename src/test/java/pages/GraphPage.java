package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverManager.DriverFactory;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.TestContext;
//import utils.GraphExcelreader;
import utils.WaitUtils;

public class GraphPage {

	WebDriver driver;

	@FindBy(xpath = "//a[@href='graph']")
	WebElement graphGetStarted;

	@FindBy(xpath = "//p[text()='Topics Covered']")
	WebElement topicsCoveredText;

	@FindBy(tagName = "h4")
	WebElement graphTitle;

	@FindBy(xpath = "//a[@href='graph']")
	WebElement graphLink;

	@FindBy(xpath = "//a[text()='Graph Representations']")
	WebElement graphRepresentationsLink;

	@FindBy(xpath = "//p[@align='center']")
	WebElement graphTopic;

	@FindBy(xpath = "//p[text()='Graph Representations']")
	WebElement graphRepresentationsTopic;

	@FindBy(xpath = "//a[text()='Practice Questions']")
	WebElement practiceQuestionsLink;

	@FindBy(xpath = "//a[text()='Try here>>>']")
	WebElement tryHereBtn;

	@FindBy(xpath = "//div[@class='CodeMirror-scroll']")
	WebElement codeEditor;

	@FindBy(xpath = "//button[text()='Run']")
	WebElement runBtn;
	@FindBy(id = "output")
	WebElement output;

	@FindBy(xpath = "//div[@class='container']")
	List<WebElement> practiceQuestions;

	public GraphPage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);

	}
	

	public void navigateToGraphPage() {
		driver.get(ConfigReader.getProperty("baseURL")+"/home");
	//	WaitUtils.waitForVisibility(driver, usernameField, 10);
	}


	public List<String> getPracticeQuestionsList() {
		List<String> questions = new ArrayList<>();
		for (WebElement question : practiceQuestions) {
			questions.add(question.getText());
		}
		return questions;
	}

	public void enterCode(String code_type) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("var cm = document.querySelector('.CodeMirror').CodeMirror;" + "cm.setValue(arguments[0]);",
				code_type);
	}

	public void clickRunButton() {
		runBtn.click();
	}

	public String getOutputText() {
		return output.getText().trim();
	}

	public String getErrorPopupText() {
		try {
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			alert.accept();
			return msg;
		} catch (NoAlertPresentException e) {
			return null;
		}
	}

	public void clickGraphGetStarted() {
		graphGetStarted.click();
	}

	public String getHeading(String Heading) {

		switch (Heading) {
		case "Graph":
			graphTitle.getText();
			break;
		case "Topics Covered":
			topicsCoveredText.getText();
			break;
		default:
			System.out.println("Invalid Heading");
			break;
		}
		return Heading;
	}

	public String getTopicLink(String Topic_Link) {

		switch (Topic_Link) {
		case "Graph":
			graphLink.getText();
			System.out.println(graphLink.getText());
			break;
		case "Graph Representations":
			graphRepresentationsLink.getText();
			System.out.println(graphRepresentationsLink.getText());
			break;
		default:
			System.out.println("Invalid Topic Link");
			break;
		}
		return Topic_Link;
	}

	public String getTopicUrl(String Topic_Url) {

		switch (Topic_Url) {
		case "Graph":
			graphTopic.getText();
			System.out.println(graphTopic.getText());
			break;
		case "Graph Representations":
			graphRepresentationsTopic.getText();
			System.out.println(graphRepresentationsTopic.getText());
			break;
		default:
			System.out.println("Invalid Topic Url");
		}
		return Topic_Url;
	}

	public WebElement getTopic(String Topic) {

		switch (Topic) {
		case "Graph":
			return graphLink;
		case "Graph Representations":
			return graphRepresentationsLink;
		default:
			throw new RuntimeException("Invalid Topic" + Topic);
		}
	}

	public boolean isTopicEnabled(String Topic_Link) {
		WebElement topicElement = getTopic(Topic_Link);
		return topicElement.isEnabled();

	}

	public boolean isTopicDisplayed(String Topic) {
		WebElement topicElement = getTopic(Topic);
		return topicElement.isDisplayed();
	}

	public void clickTopic(String Topic_Link) {
		WebElement topicElement = getTopic(Topic_Link);
		topicElement.click();
	}

	public void clickTopicUrl(String Topic_Url) {
		WebElement topicElement = getTopic(Topic_Url);
		topicElement.click();
	}

	public boolean isTryHereBtnVisible() {
		return tryHereBtn.isDisplayed();
	}

	public boolean isTryHereBtnClickable() {
		return tryHereBtn.isEnabled();
	}

	public boolean isRunBtnVisible() {
		return runBtn.isDisplayed();
	}

	public boolean isRunBtnClickable() {
		return runBtn.isEnabled();
	}

	public void clickTryherebtn() {
		tryHereBtn.click();
	}

	public void clickPracticeQuestionsLink() {
		practiceQuestionsLink.click();
	}
	
   /* // Reusable excel try editor helper
	public void enterCodeExcel(String code_type) {
		TestContext.testData = ExcelReader.getTestData(code_type);
		 
		topicPage(TestContext.testData.get("topicPage"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("var cm = document.querySelector('.CodeMirror').CodeMirror;" + "cm.setValue(arguments[0]);",
				code_type);
	}
	
	public void login(String method, String scenarioType) {
        TestContext.testData = ExcelReader.getTestData(scenarioType);
 
        enterUsername(TestContext.testData.get("username"));
        enterPassword(TestContext.testData.get("password"));

        switch (method.toLowerCase().trim()) {
            case "submits the login form":
            case "initiates login":
            case "submits the login form with mouse click":
                clickLoginButton();
                break;
            case "presses enter":
            case "confirms login using enter":
                pressEnterToSubmit();
                break;
            default:
                throw new IllegalArgumentException("Unknown submission method: " + method);
        }
    }*/
}