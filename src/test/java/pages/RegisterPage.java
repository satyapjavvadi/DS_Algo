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
	
	//home page elements
	@FindBy(xpath = "//a[text()='Sign out']")
	private WebElement signouttext;
	

	//Actions
	public void getstartedbtn() {
		getstartedbtn.click();
	}
	
	public String Registerlink() {
		Registerlink.click();
		String actualurl =driver.getCurrentUrl();
		
	String expectedurl = "https://dsportalapp.herokuapp.com/register";
	
	Assert.assertEquals(actualurl, expectedurl, "User did not land on the expected Registerpage URL");
	System.out.println("User is on Register page"+actualurl);
	
	return actualurl;
		
	}
	public void Register_valid_credentials() {
		usernamefield.sendKeys("testqa");
		passwordField.sendKeys("Qaninja@123");
		confirmPasswordField.sendKeys("Qaninja@123");
	}
	public void verifyHomePageNavigation() {
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.contains("home")) {
            throw new AssertionError("User not navigated to home page!");
        }
	}
        
	public void Register_without_username() {
		usernamefield.sendKeys("");
		passwordField.sendKeys("Qaninja@123");
		confirmPasswordField.sendKeys("Qaninja@123");
	}
	
	public String getusername_validationmessage() {
		//validationMessage is an HTML5 attribute 
		//that stores the browser’s native message for required fields (like “Please fill out this field”).
		return usernamefield.getAttribute("validationMessage");
	}
	
	public void Register_with_specialcharusername() {
		usernamefield.sendKeys("!@");
		passwordField.sendKeys("Qaninja@123");
		confirmPasswordField.sendKeys("Qaninja@123");
	}
	
	public String  getalertmessagetext() {
		return alertMessage.getText().trim();
		
	}
	
	public void Register_without_password() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("");
		confirmPasswordField.sendKeys("");
	}
	
	public String getpassword_validationmessage() {
		return passwordField.getAttribute("validationMessage");
	}
	public void Register_without_confirmpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("Qaninja@123");
		confirmPasswordField.sendKeys("");
	}
	public String getconfirmpassword_validationmessage() {
		return confirmPasswordField.getAttribute("validationMessage");
	}
	public void Register_with_numericpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("123");
		confirmPasswordField.sendKeys("123");
	}
	
	public void Register_with_specialcharpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("!@");
		confirmPasswordField.sendKeys("!@");
	}
	
	public void Register_with_shortpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("A@1");
		confirmPasswordField.sendKeys("A@1");
	}
	public void Register_with_mismatchpassword() {
		usernamefield.sendKeys("qatest");
		passwordField.sendKeys("Ninja@123");
		confirmPasswordField.sendKeys("A@1");
	}
	
	public void click_logout() {
		loginlink.click();
	}
	
	
}
