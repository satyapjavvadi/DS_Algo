package pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverManager.DriverFactory;
import utils.WaitUtils;

public class PracticeQuestionsPage {

	private WebDriver driver;

	public PracticeQuestionsPage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	// locators
	@FindBy(xpath = "//div[@class='list-group']/a")
	private List<WebElement> questionslist;

	@FindBy(xpath = "//p")
	private WebElement questionText;

	@FindBy(xpath = "//div[contains(@class,'CodeMirror')]")
	private WebElement code_editor;

	@FindBy(xpath = "//button[text()='Run']")
	private WebElement Run_button;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement Submit_button;

	// action

	public void clickProblemLink(String problemName) {
		for (WebElement link : questionslist) {
			if (link.getText().trim().equalsIgnoreCase(problemName)) {
				link.click();
				return;
			}
		}
		throw new NoSuchElementException("Problem link not found: " + problemName);
	}

	public boolean getDisplayedQuestion() {

		

			return questionText.isDisplayed();
		
	}

	public boolean isCodeEditorVisible() {
		
			WebElement codespace = WaitUtils.waitForVisibility(driver, code_editor, 10);
			return codespace.isDisplayed();

		
	}

	public boolean isRunButtonVisible() {
		
			WebElement runbtn = WaitUtils.waitForVisibility(driver, Run_button, 10);
			return runbtn.isDisplayed();
		
	}

	public boolean isSubmitButtonVisible() {
		
			WebElement submitbtn = WaitUtils.waitForVisibility(driver, Submit_button, 10);
			return submitbtn.isDisplayed();
		
	}

}
