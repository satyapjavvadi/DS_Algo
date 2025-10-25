package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;

	// Locators
	@FindBy(id = "id_username")
	WebElement usernameField;

	@FindBy(id = "id_password")
	WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;

	@FindBy(tagName = "a")
	List<WebElement> allLinks;

	@FindBy(css = ".alert, .error, .errormessage")
	WebElement errorMessage;

	@FindBy(linkText = "Register")
	WebElement registerLink;

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Input
	public void enterUsername(String username) {
		if (username != null && !username.isEmpty()) {
			usernameField.sendKeys(username);
		}
	}

	public void enterPassword(String password) {
		if (password != null && !password.isEmpty()) {
			passwordField.sendKeys(password);
		}
	}

	// Submission
	public void clickLoginButton() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
	}

	public void pressEnterToSubmit() {
		passwordField.sendKeys(Keys.ENTER);
	}

	public boolean isOnLoginPage() {
		return driver.getCurrentUrl().contains("/login");
	}

	public boolean isLoginPageVisible() {
		return usernameField.isDisplayed() && passwordField.isDisplayed();
	}

	public boolean isRegisterLinkVisible() {
		return registerLink.isDisplayed();
	}

	public String getAllFieldSpellings() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String text = (String) js.executeScript("return document.documentElement.innerText;");
		return text;
	}

	// Reusable login helper
	public void login(String username, String password, String method) {
		enterUsername(username);
		enterPassword(password);

		switch (method.toLowerCase().trim()) {
		case "submits the login form":
		case "initiates login":
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

	public WebElement getUsernameField() {
		return usernameField;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}

	public String getDisplayedErrorMessage() {
		return errorMessage.getText().trim();
	}

}
