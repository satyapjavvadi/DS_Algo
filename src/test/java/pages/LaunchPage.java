package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.JSUtils;

public class LaunchPage extends BaseTopicPage {

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
		super(driver);
		PageFactory.initElements(driver, this);

	}

	public void BrowserIsOpen() {
		System.out.println("Browser is already launched via hooks setup");

		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized");
		}
	}

	public boolean isPreparingTextVisible() {
		return InterviewText.isDisplayed();
	}

	public boolean isGetStartedButtonVisible() {
		return getStartedButton.isDisplayed();
	}

	public boolean isGetStartedButtonEnabled() {
		return getStartedButton.isEnabled();
	}

	public void clickGetStartedButton() {
		getStartedButton.click();
	}

	public boolean isCopyrightInfoVisible() {
		return copyrightInfo.isDisplayed();
	}

	public String getCurrentUrl() {

		String actualUrl = driver.getCurrentUrl();
		System.out.println("Navigation to launch page successful: " + actualUrl);
		return driver.getCurrentUrl();
	}

	public String getAllFieldSpellings() {
		return JSUtils.getPageInnerText(driver);
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

}
