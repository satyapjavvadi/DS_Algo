package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ConfigReader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LaunchPage {

	private static JavascriptExecutor js;
	private WebDriver driver;
	


	// Locators
	@FindBy(xpath = "//*[text()='Preparing for the Interviews']")
	WebElement InterviewText;

	@FindBy(linkText = "Get Started")
	WebElement getStartedButton;

	@FindBy(xpath = "//*[contains(text(),'Copyright@NumpyNinja2021')]")
	WebElement copyrightInfo;

	@FindBy(tagName = "button")
	private List<WebElement> buttons;

	@FindBy(xpath = "//nav[@class='navbar navbar-expand-md navbar-light bg-light']//a[normalize-space() != '']")
	private List<WebElement> linkList;


	// Constructor
	public LaunchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	public void verifyBrowserIsOpen() {
		System.out.println("Browser is already launched via hooks setup");

		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized");
		}
	}

	public void verifyLaunchUrl() {
		String expectedUrl = ConfigReader.initializeProperties().getProperty("baseURL");
		String actualUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl, "User did not land on the expected DS Algo portal URL");
	}

	public boolean isPreparingTextVisible() {
		return InterviewText.isDisplayed();
	}

	public boolean isGetStartedButtonVisible(String buttonText) {
		return getStartedButton.isDisplayed();
	}

	public boolean isGetStartedButtonEnabled(String buttonText) {
		return getStartedButton.isEnabled();
	}

	public void clickGetStartedButton() {
		getStartedButton.click();
	}

	public boolean isCopyrightVisible(String copyrightText) {
		return copyrightInfo.isDisplayed();
	}

	public String getCurrentUrl() {

		String actualUrl = driver.getCurrentUrl();
		System.out.println("Navigation to home page successful: " + actualUrl);
		return driver.getCurrentUrl();
	}


	public  String getAllFieldSpellings( ) {
		js = (JavascriptExecutor) driver;
		String text = (String) js.executeScript("return document.documentElement.innerText;");
		return text;
	}

	public List<String> getButtonText() {
	 	List<String> buttonTexts = new ArrayList<>();
		for (WebElement button : buttons) {
			buttonTexts.add(button.getText());
		}
		return buttonTexts;
	}

	public void clickButtonByText(String buttonText) {
		for (WebElement button : buttons) {
			if (button.getText().toLowerCase().equals(buttonText.toLowerCase())) {
				button.click();
				break;
			}
		}
	}

	public Set<String> getAllDropDownLinks() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Set<String> linkTexts =  linkList.stream()
				.map(WebElement::getText)
				.filter(text -> !text.trim().isEmpty())
				.collect(Collectors.toSet());

				System.out.println("actualLinkText = [" + String.join(", ", linkTexts) + "]");
		return linkTexts;
	}
}
