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



public class ArrayPage {
	private WebDriver driver;

	public ArrayPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

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

	// action

	public String Arraypage_link_Check() {

		String actualurl = driver.getCurrentUrl();

		return actualurl;
	}

	public List<String> getheadingtext() {
		List<String> headingtexts = new ArrayList<String>();
		for (WebElement heading : headings) {
			headingtexts.add(heading.getText().trim());
		}
		return headingtexts;
	}

	public List<String> subtopiclinks() {
		List<String> subtopiclinks = new ArrayList<String>();
		for (WebElement topiclink : array_subtopicslinks) {
			if (topiclink.isDisplayed() && topiclink.isEnabled()) {
				subtopiclinks.add(topiclink.getText().trim());
			}
		}
		return subtopiclinks;
	}

	public void clicktopiclink(String topicname) {
		for (WebElement link : array_subtopicslinks) {
			if (link.getText().trim().equalsIgnoreCase(topicname)) {
				link.click();
				
				return;
			}
		}
		throw new NoSuchElementException("Topic link not found: " + topicname);
	}

	public void scrolltobottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public boolean checktryherebutton_displayed(String buttonText, String section) {
		try {
			return tryhere_button.isDisplayed();

		} catch (Exception e) {
			System.out.println("try here button exception" + e);
			return false;

		}

	}

	public void clickTryHereButton() {
		tryhere_button.click();
	}

	public void enterCode(String code) {
		coding_area.clear();
		coding_area.sendKeys(code);
	}

	public void clickRunButton() {
		run_button.click();
		
	}

	public String getOutput_text() {
		try {
			return outputconsole.getText().trim();
		} catch (NoSuchElementException e) {
			System.out.println("outputconsole exception" + e);
			return "";
		}
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
		Practicequestionslink.click();
	}

	public boolean isPracticeQuestionsListNotEmpty() {
	    return !questionslist.isEmpty();
	}

}
