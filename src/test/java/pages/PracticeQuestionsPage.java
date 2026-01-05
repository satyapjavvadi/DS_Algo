package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class PracticeQuestionsPage {

	private WebDriver driver;

	public PracticeQuestionsPage(WebDriver driver) {
		this.driver = driver;
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
		
	    try {
	   
	    	return questionText.isDisplayed();
	    	}
	    catch (Exception e) { 
	    	return false;
	    	}
	}


	public boolean isCodeEditorVisible() {
		try {
			WebElement codespace = WaitUtils.waitForVisibility(driver, code_editor, 10);
			return codespace.isDisplayed();
			
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isRunButtonVisible() {
		try {
			WebElement runbtn = WaitUtils.waitForVisibility(driver, Run_button, 10);
			return runbtn.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isSubmitButtonVisible() {
		try {
			WebElement submitbtn = WaitUtils.waitForVisibility(driver, Submit_button, 10);
			return submitbtn.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	
	
	

}
