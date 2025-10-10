package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;

public class LaunchPage {
	
	private WebDriver driver;
	
	// Constructor
	public LaunchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(xpath = "//*[text()='Preparing for the Interviews']")
	WebElement InterviewText;

	@FindBy(linkText = "Get Started")
	WebElement getStartedButton;

	@FindBy(xpath = "//*[contains(text(),'Copyright@NumpyNinja2021')]")
	WebElement copyrightInfo;

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

	public void clickGetStartedButton(String buttonText) {
		getStartedButton.click();
	}

	public boolean isCopyrightVisible(String copyrightText) {
		return copyrightInfo.isDisplayed();
	}

	public String getCurrentUrl() {
		String expectedHomeUrl = "https://dsportalapp.herokuapp.com/home";
		String actualUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedHomeUrl, "User was not navigated to the home page");
		System.out.println("Navigation to home page successful: " + actualUrl);
		return driver.getCurrentUrl();
	}

}
