package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.JSUtils;
import utils.WaitUtils;

public class LoginPage {

	private WebDriver driver;

	// Locators
	@FindBy(id = "id_username")
	private WebElement usernameField;

	@FindBy(id = "id_password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(css = ".alert, .error, .errormessage")
	private WebElement errorMessage;

	@FindBy(linkText = "Register")
	private WebElement registerLink;

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToLoginPage() {
		driver.get(ConfigReader.getProperty("baseURL") + "login");
		WaitUtils.waitForVisibility(driver, usernameField, 10);
	}

	public void enterUsername(String username) {
		WebElement field = WaitUtils.waitForVisibility(driver, usernameField, 10);
		field.clear();
		field.sendKeys(username);
	}

	public void enterPassword(String password) {
		if (password != null && !password.isEmpty()) {
			passwordField.clear();
			passwordField.sendKeys(password);
		}
	}

	// Submission
	public void clickLoginButton() {
		JSUtils.clickElement(driver, loginButton);
	}

	public void pressEnterToSubmit() {
		passwordField.sendKeys(Keys.ENTER);
	}

	// Helpers
	public boolean isOnLoginPage() {
		return driver.getCurrentUrl().contains("/login");
	}

	public boolean isLoginPageVisible() {
		return usernameField.isDisplayed() && passwordField.isDisplayed();
	}

	public boolean isRegisterLinkVisible() {
		return registerLink.isDisplayed();
	}

	public String getDisplayedErrorMessage() {
		return errorMessage.getText().trim();
	}

	// Reusable login helper
	public void login(String username, String password, String method) {
		enterUsername(username);
		enterPassword(password);

		switch (method.toLowerCase().trim()) {
		case "submits the login form":
		case "initiates login":
		case "submits the login form with mouse click":
			clickLoginButton();
			break;
		case "presses enter":
		case "confirms login using enter":
			pressEnterToSubmit();
			break;
		default:
			throw new IllegalArgumentException("Unknown submission method: " + method);
		}
	}

	public void waitForHomeRedirect() {
		WaitUtils.waitForUrlContains(driver, "/home", 10);
	}

}
