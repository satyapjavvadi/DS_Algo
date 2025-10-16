package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;

public class RegisterPage {
	private WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

//locators
	@FindBy(xpath = "//button[text()='Get Started']")
	private WebElement getstartedbtn;

	@FindBy(xpath = "//a[text()=' Register ']")
	private WebElement Registerlink;

	@FindBy(id = "id_username")
	private WebElement usernamefield;

	@FindBy(id = "id_password1")
	private WebElement passwordField;

	@FindBy(id = "id_password2")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@value='Register']")
	private WebElement Registerbtn;

	@FindBy(xpath = "//a[text()='Login ']")
	private WebElement loginlink;

	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement alertMessage;

	// home page elements
	@FindBy(xpath = "//a[text()='Sign out']")
	private WebElement signouttext;

	// Actions

	public String validate_Registerlink() {
		getstartedbtn.click();
		Registerlink.click();
		String actualurl = driver.getCurrentUrl();

		String expectedurl = "https://dsportalapp.herokuapp.com/register";

		Assert.assertEquals(actualurl, expectedurl, "User did not land on the expected Registerpage URL");
		System.out.println("User is on Register page" + actualurl);

		return actualurl;

	}

	public void Register_with_valid_credentials() {
		usernamefield.sendKeys("testqa5");
		passwordField.sendKeys("Qaninja@123");
		confirmPasswordField.sendKeys("Qaninja@123");
		Registerbtn.click();
	}

	public void verifyHomePageNavigation() {

		String actualurl = driver.getCurrentUrl();
		String expectedurl = "https://dsportalapp.herokuapp.com/home";

		Assert.assertEquals(actualurl, expectedurl, "User did not land on the expected Homepage URL");
		System.out.println("User is on Home page" + actualurl);
	}

	public void Register_without_username() {
		usernamefield.sendKeys("");
		passwordField.sendKeys("Qaninja@123");
		confirmPasswordField.sendKeys("Qaninja@123");
		Registerbtn.click();
	}

	public String username_validationmessage() {
		// validationMessage is an HTML5 attribute
		// that stores the browser’s native message for required fields (like “Please
		// fill out this field”).
		return usernamefield.getAttribute("validationMessage");
	}

	public void Register_with_specialcharusername() {
		usernamefield.sendKeys("!@");
		passwordField.sendKeys("Qaninja@123");
		confirmPasswordField.sendKeys("Qaninja@123");
		Registerbtn.click();
	}

	public String Error_messagetext() {
		return alertMessage.getText().trim();

	}

	public void Register_without_password() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("");
		confirmPasswordField.sendKeys("");
		Registerbtn.click();
	}

	public String password_validationmessage() {
		return passwordField.getAttribute("validationMessage");
	}

	public void Register_without_confirmpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("Qaninja@123");
		confirmPasswordField.sendKeys("");
		Registerbtn.click();
	}

	public String getconfirmpassword_validationmessage() {
		return confirmPasswordField.getAttribute("validationMessage");
	}

	public void Register_with_numericpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("123");
		confirmPasswordField.sendKeys("123");
		Registerbtn.click();
	}

	public void Register_with_specialcharpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("!@");
		confirmPasswordField.sendKeys("!@");
		Registerbtn.click();
	}

	public void Register_with_shortpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("A@1");
		confirmPasswordField.sendKeys("A@1");
		Registerbtn.click();
	}

	public void Register_with_mismatchpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("Ninja@123");
		confirmPasswordField.sendKeys("A@1");
		Registerbtn.click();
	}

	public void click_logout() {
		loginlink.click();
	}

}
