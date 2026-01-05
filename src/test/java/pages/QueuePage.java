package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.JSUtils;
import utils.WaitUtils;

public class QueuePage {
	private WebDriver driver;

	public QueuePage(WebDriver driver) {
		this.driver = driver;
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
	
		/*
		 * public void queueGetStarted() { queueGetStarted.click(); }
		 */
		public void queueGetStarted() {
		    WaitUtils.waitForClickable(driver, queueGetStarted, 10);
		    JSUtils.scrollIntoView(driver, queueGetStarted);
		    JSUtils.clickElement(driver, queueGetStarted);
		}
		
		public String Queuepage_link_Check() {

			String actualurl = driver.getCurrentUrl();

			return actualurl;
		}
		public void loginpage() {
			driver.get("https://dsportalapp.herokuapp.com/login");
		}
		public void goto_queuemainpage() {
			 driver.get("https://dsportalapp.herokuapp.com/queue/");
		}
		
		//public String Queuepage_main() {
			//driver.get(ConfigReader.);
			
		//}

		/*
		 * public List<String> getheadingtext() { List<String> headingtexts = new
		 * ArrayList<String>(); for (WebElement heading : headings) {
		 * headingtexts.add(heading.getText().trim()); } return headingtexts; }
		 */
		public List<String> getheadingtext() {
		    WaitUtils.waitForVisibilityOfAll(driver, headings, 10);

		    List<String> headingtexts = new ArrayList<>();
		    for (WebElement heading : headings) {
		        headingtexts.add(heading.getText().trim());
		    }
		    return headingtexts;
		}
		
		/*
		 * public List<String> subtopiclinks() { List<String> subtopiclinks = new
		 * ArrayList<String>(); for (WebElement topiclink : queue_subtopicslinks) { if
		 * (topiclink.isDisplayed() && topiclink.isEnabled()) {
		 * subtopiclinks.add(topiclink.getText().trim()); } } return subtopiclinks; }
		 */
		public List<String> subtopiclinks() {

		    WaitUtils.waitForVisibilityOfAll(driver, queue_subtopicslinks, 10);

		    List<String> subtopicTexts = new ArrayList<>();
		    for (WebElement topiclink : queue_subtopicslinks) {
		        subtopicTexts.add(topiclink.getText().trim());
		    }
		    return subtopicTexts;
		}

		/*
		 * public void clicktopiclink(String topicname) { for (WebElement link :
		 * queue_subtopicslinks) { if
		 * (link.getText().trim().equalsIgnoreCase(topicname)) { link.click();
		 * 
		 * return; } } throw new NoSuchElementException("Topic link not found: " +
		 * topicname); }
		 */
		
		public void clicktopiclink(String topicname) {

		    WaitUtils.waitForVisibilityOfAll(driver, queue_subtopicslinks, 10);
		    JSUtils.scrollToLoadAllTopics(driver);

		    for (WebElement link : queue_subtopicslinks) {
		        if (link.getText().trim().equalsIgnoreCase(topicname)) {
		        	JSUtils.scrollIntoView(driver, link);
		            JSUtils.clickElement(driver, link);
		            //WaitUtils.waitForClickable(driver, link, 10).click();
		            return;
		        }
		    }
		    throw new NoSuchElementException("Topic link not found: " + topicname);
		}
		public void scrolltobottom() {
			JSUtils.scrollToBottom(driver);
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}

		/*
		 * public boolean checktryherebutton_displayed() { try { return
		 * tryhere_button.isDisplayed();
		 * 
		 * } catch (Exception e) { System.out.println("try here button exception" + e);
		 * return false;
		 * 
		 * }
		 * 
		 * }
		 */
		public boolean checktryherebutton_displayed() {
			JSUtils.scrollToBottom(driver);
		    return WaitUtils.isVisible(driver, tryhere_button, 20);
		}
		
		public void clickTryHereButton() {
		    WaitUtils.waitForClickable(driver, tryhere_button, 10);
		    JSUtils.scrollIntoView(driver, tryhere_button);
		    JSUtils.clickElement(driver, tryhere_button);
		}


		/*
		 * public void clickTryHereButton() { tryhere_button.click(); }
		 */

		//public void enterCode(String code) {
			//coding_area.clear();
			//coding_area.sendKeys(code);
		//}
		
		public void enterCode(String code) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("var cm = document.querySelector('.CodeMirror').CodeMirror;" +
					"cm.setValue(arguments[0]);",
					code);
		}

		public void clickRunButton() {
			run_button.click();
			
		}

		public String getOutput_text() {
			//try {
				return outputconsole.getText().trim();
			//} catch (NoSuchElementException e) {
				//System.out.println("outputconsole exception" + e);
				//return "";
			//}
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
		                    return true;   // At least one question visible
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
