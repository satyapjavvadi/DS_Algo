package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DataStructures {

	WebDriver driver;
	@FindBy(linkText = "Get Started")
	WebElement getStartedBtn;

	@FindBy(xpath = "//a[text()='Sign in']")
	WebElement logInLink;

	// mamta.chavan0785@gmail.com
	// Chakuli123$

	@FindBy(id = "id_username")
	WebElement username;

	@FindBy(id = "id_password")
	WebElement password;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement login;

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
	
	@FindBy(xpath ="//div[@class='container']")
	List<WebElement> practiceQuestions;
	
	public DataStructures(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void getlogin() {
		getStartedBtn.click();
		logInLink.click();
		username.sendKeys("mamta.chavan0785@gmail.com");
		password.sendKeys("Chakuli123$");
		login.click();
	}
	
	

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
		
		//codeEditor.clear();
	
		if (codeType.equalsIgnoreCase("valid")) {
			
		codeEditor.sendKeys("print('Hello')");
		System.out.println("Valid code entered");
		}
		else if (codeType.equalsIgnoreCase("invalid")) {
			codeEditor.sendKeys("text");

			System.out.println("Invalid code entered");}
		}
	
	public String outputText(String codeType) {
		if (codeType.equalsIgnoreCase("valid")) {
			System.out.println("Output for valid code displayed" + output.getText());
			return output.getText();
			} 
		
		 if (codeType.equalsIgnoreCase("invalid"))
			
			{
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert handled for invalid code" + alertText);
				alert.accept();
				return alertText;
			}
		 else {
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
		public String getkHeading(String Heading) {

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
