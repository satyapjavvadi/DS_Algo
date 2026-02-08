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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverManager.DriverFactory;
import utils.JSUtils;
import utils.WaitUtils;

public class DataStructurePage {
	private WebDriver driver;
	private WaitUtils wait;
	
	@FindBy(xpath = "//*[contains(@class,'text-white')]")
	private List<WebElement> headings;	//static content

	@FindBy(xpath = "//a[@class='list-group-item']")
	private List<WebElement> dataStr_subtopicslinks;	//subtopic links under topics covered

	@FindBy(xpath = "//a[contains(text(),'Try')]")
	private WebElement tryhere_button;

	@FindBy(xpath = "//button[text()='Run']")
	WebElement run_Button;
	
	@FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
	private WebElement Practicequestionslink;

	@FindBy(xpath = "//div[@class='list-group']")
	private List<WebElement> questionslist;
	
	@FindBy(xpath = "//div[@class='CodeMirror-scroll']")
	WebElement codeEditor;

	@FindBy(xpath = "//button[text()='Run']")
	WebElement runBtn;
	
	@FindBy(id = "output")
	WebElement output;
	

    @FindBy(xpath = "//span[@role='presentation']//span")
    private WebElement codeArea;

	public DataStructurePage() {
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
		wait.waitForVisibilityOfAll(dataStr_subtopicslinks);
		List<String> subtopiclinks = new ArrayList<String>();
		for (WebElement topiclink : dataStr_subtopicslinks) {
			if (topiclink.isDisplayed() && topiclink.isEnabled()) {
				subtopiclinks.add(topiclink.getText().trim());
			}
		}
		return subtopiclinks;
	}

	public void clickTopicLink(String topicName) {
		wait.waitForVisibilityOfAll(dataStr_subtopicslinks);
		for (WebElement link : dataStr_subtopicslinks) {
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
	
	public boolean checktryherebutton_clickable() {
		return WaitUtils.isClickable(driver, tryhere_button, 10);
	}
	
	public boolean checkrunbutton_displayed() {
		return WaitUtils.isVisible(driver, run_Button, 10);
	}
	
	public boolean checkrunbutton_clickable() {
		return WaitUtils.isClickable(driver, run_Button, 10);
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

	public void enterCode(String code_type) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("var cm = document.querySelector('.CodeMirror').CodeMirror;" + "cm.setValue(arguments[0]);",
				code_type);
	}
 
	public void clickrunBtn() {
		run_Button.click();
	}
	public String getRunBtnText() {
		return run_Button.getText();
	}
	
	public String getErrAlert() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		return alertText;
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
}	

	
	


	/*WebDriver driver;

	public DataStructurePage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='data-structures-introduction']")
	WebElement dataStructureIntroGetStarted;

	@FindBy(xpath = "//p[text()='Topics Covered']")
	WebElement topicsCoveredText;

	@FindBy(tagName = "h4")
	WebElement dataStructureIntroTitle;

	@FindBy(xpath = "//a[text()='Time Complexity']")
	WebElement timeComplexityLink;

	@FindBy(xpath = "//a[text()='Try here>>>']")
	WebElement tryHereBtn;

	@FindBy(xpath = "//button[text()='Run']")
	WebElement runBtn;

	@FindBy(xpath = "//div[@class='CodeMirror-cursors']")
	WebElement codeEditor;

	@FindBy(id = "output")
	WebElement output;

	@FindBy(xpath = "//a[text()='Practice Questions']")
	WebElement practiceQuestionsLink;

	@FindBy(xpath = "//div[@class='container']")
	List<WebElement> practiceQuestions;

	public void clickTryherebtn() {
		tryHereBtn.click();
	}

	public void isTryHereBtnVisible() {
		if (tryHereBtn.isDisplayed()) {
			System.out.println("Try Here button is visible");
		} else {
			System.out.println("Try Here button is not visible");
		}
	}

	public void isTryHereBtnClickable() {
		if (tryHereBtn.isEnabled()) {
			System.out.println("Try Here button is clickable");
		} else {
			System.out.println("Try Here button is not clickable");
		}
	}

	public void clickTimeComplexityLink() {
		timeComplexityLink.click();
	}

	public void code() {
		codeEditor.sendKeys("print('Hello')");
		System.out.println("Code entered");
	}

	public void enterCode(String codeType) {
		if (codeType.equalsIgnoreCase("valid")) {
			codeEditor.sendKeys("print('Hello')");
			System.out.println("Valid code entered");
		} else if (codeType.equalsIgnoreCase("invalid")) {
			codeEditor.sendKeys("text");
			System.out.println("Invalid code entered");
		}
	}

	public String outputText(String codeType) {
		if (codeType.equalsIgnoreCase("valid")) {
			System.out.println("Output for valid code displayed" + output.getText());
			return output.getText();
		}

		if (codeType.equalsIgnoreCase("invalid")) {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			System.out.println("Alert handled for invalid code" + alertText);
			alert.accept();
			return alertText;
		} else {
			return "";
		}
	}

	public String getOutputText() {
		return output.getText();
	}

	public String getTimeComplexityText() {
		return timeComplexityLink.getText();
	}

	public String getRunBtnText() {
		return runBtn.getText();
	}

	public void clickrunBtn() {
		runBtn.click();
	}

	public String getErrAlert() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		return alertText;
	}

	public void isTabVisible() {
		if (timeComplexityLink.isDisplayed()) {
			System.out.println("Time Complexity link is visible");
		} else {
			System.out.println("Time Complexity link is not visible");
		}
	}

	public void isTabClickable() {
		if (timeComplexityLink.isEnabled()) {
			System.out.println("Time Complexity link is clickable");
		} else {
			System.out.println("Time Complexity link is not clickable");
		}
	}

	public void clickDataStructureIntroGetStartedBtn() {
		dataStructureIntroGetStarted.click();
	}

	public void clickPracticeQuestionsLink() {
		practiceQuestionsLink.click();
	}

	public List<String> getPracticeQuestionsList() {
		List<String> questions = new ArrayList<>();
		for (WebElement question : practiceQuestions) {
			questions.add(question.getText());
		}
		return questions;
	}

	public String getHeading(String Heading) {

		switch (Heading) {
		case "Data Structures-Introduction":
			dataStructureIntroTitle.getText();
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

}
*/
