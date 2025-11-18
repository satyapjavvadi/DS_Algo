package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeQuestionsEditorPage {

	private WebDriver driver;

	public PracticeQuestionsEditorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// locators
	@FindBy(xpath = "//div[@class='list-group']")
	private List<WebElement> questionslist;

	@FindBy(xpath = "//div[@class='CodeMirror cm-s-default']")
	private WebElement code_editor;

	@FindBy(xpath = "//button[text()='Run']")
	private WebElement Run_button;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement Submit_button;

	@FindBy(xpath = "//pre[@id='output']")
	private WebElement Output_console;

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

	// need clarity in getting question text to compare

	public boolean isCodeEditorVisible() {
		try {
			return code_editor.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isRunButtonVisible() {
		try {
			return Run_button.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isSubmitButtonVisible() {
		try {
			return Submit_button.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void Entercodeineditor(String code) {
		code_editor.clear();
		code_editor.sendKeys(code);
	}

	public void clickRunButton() {
		Run_button.click();
	}

	public void clickSubmitButton() {
		Submit_button.click();
	}

	public String getOutputText() {
		try {
			return Output_console.getText().trim();
		} catch (NoSuchElementException e) {
			return "";
		}
	}

	public String getAlertMessage() {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		return text;

	}
	
	

}
