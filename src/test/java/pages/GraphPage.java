package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GraphPage {
	
		WebDriver driver;
		
		@FindBy(xpath = "//a[text()='Sign in']")
		WebElement logInLink;// I will remove after satya's review

		@FindBy(id = "id_username") // I will remove after satya's review
		WebElement username;// I will remove after satya's review

		@FindBy(id = "id_password") // I will remove after satya's review
		WebElement password;// I will remove after satya's review

		@FindBy(xpath = "//input[@value='Login']")
		WebElement login;
		
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
		
		@FindBy(xpath ="//p[@align='center']")
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
		
		
		@FindBy(xpath ="//div[@class='container']")
		List<WebElement> practiceQuestions;
		

		public GraphPage(WebDriver driver) {
			this.driver =driver;
			PageFactory.initElements(driver, this);
			
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
			js.executeScript("var cm = document.querySelector('.CodeMirror').CodeMirror;" +
					"cm.setValue(arguments[0]);",
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
				}
			catch (NoAlertPresentException e) {
			return null;	
			}
		}
		
		public void clickLogIn() {
		logInLink.click();
		username.sendKeys("mamta.chavan0785@gmail.com");
		password.sendKeys("Chakuli123$");
		login.click();
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
	
	/*
					public void isTopicClickable(String Topic_Link) {
			switch (Topic_Link) {
			case "Graph":
				if (graphLink.isEnabled()) {
					System.out.println("Graph link is clickable");
				} else {
					System.out.println("Graph link is not clickable");
				}
				break;
			case "Graph Representations":
				if (graphRepresentationsLink.isEnabled()) {
					System.out.println("Graph Representations link is clickable");
				} else {
					System.out.println("Graph Representations link is not clickable");
				}
				break;
			default:
				System.out.println("Invalid Topic Link");
				break;
			}
		}
			
		public void isTopicVisible(String Topic) {
			switch (Topic) {
			case "Graph":
				if (graphLink.isDisplayed()) {
					System.out.println("Graph topic is visible");
				} else {
					System.out.println("Graph topic is not visible");
				}
				break;
			case "Graph Representations":
				if (graphRepresentationsLink.isDisplayed()) {
					System.out.println("Graph Representations topic is visible");
				} else {
					System.out.println("Graph Representations topic is not visible");
				}
				break;
			default:
				System.out.println("Invalid Topic");
				break;
			}
		}
		*/
		
		
}
