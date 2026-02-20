package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverManager.DriverFactory;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.JSUtils;
import utils.TestContext;
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

	@FindBy(xpath = ("//div[@role='alert']"))
	private WebElement error;

	@FindBy(linkText = "Register")
	private WebElement registerLink;
	
	@FindBy(xpath = "//label")
	private List<WebElement> labelList;
	
	@FindBy(xpath = "//input[@type='submit']")
	private List<WebElement> buttonElements;

	// Constructor
	public LoginPage() {
		this.driver = DriverFactory.getDriver();
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
		JSUtils.clickElement(loginButton);
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

	public String getDisplayedErrorMessage(String inField) {

		String errorMessage = null;

		switch (inField) {
		case "username field":
			errorMessage = usernameField.getAttribute("validationMessage");

			break;
		case "password field":
			errorMessage = passwordField.getAttribute("validationMessage");

			break;
		case "alert":
			errorMessage = error.getText();
			break;
		}
		return errorMessage;
	}

	// Reusable login helper
	public void login(String method, String scenarioType) {
		TestContext.testData = ExcelReader.getTestData(scenarioType);

		enterUsername(TestContext.testData.get("username"));
		enterPassword(TestContext.testData.get("password"));

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
	
	//Non functional testcases
	
	public int getInputFieldCount() {
		return labelList.size();
	}
	
	public int getButtonCount() {
		return buttonElements.size();
	}
	
	public List<String> getButtonText() {
		return buttonElements.stream()
				.map(btn -> {
					String text = btn.getText();
					if (text == null || text.isEmpty()) text = btn.getAttribute("value");
					return text != null ? text.trim() : "";
				})
				.filter(s -> !s.isEmpty())
				.toList();
	}
	
	public List<String> getLoginLabelNames() {
		return labelList.stream()
				.map(WebElement::getText)
				.map(String::trim)
				.toList();
	}
}
