package stepdefinition;

import java.util.stream.Stream;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.PageObjectManager;

public class LoginPageStepDefinition {

	private final PageObjectManager pom;
	WebDriver driver;
	private LoginPage loginPage;

	private String username;
	private String password;

	public LoginPageStepDefinition() {
		
		driver = DriverFactory.getDriver();
		pom = new PageObjectManager(driver);
		
		loginPage = pom.getLoginPage();

	}

	// Background
	@Given("the registered user has navigated to the login page")
	public void the_registered_user_has_navigated_to_the_login_page() {

		driver.get("https://dsportalapp.herokuapp.com/login");

		String actualUrl = driver.getCurrentUrl();
		System.out.println("Navigated to Login page: " + actualUrl);
		Assert.assertTrue(actualUrl.contains("/login"), "Navigation failed: Not on login page");

	}

	// Invalid login attempts
	@Given("the user provides {string} and {string}")
	public void the_user_provides_credentials(String username, String password) {
		this.username = username;
		this.password = password;

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);

	}

	@When("the user {string}")
	public void the_user_submits_by_method(String method) {
		loginPage.login(username, password, method);
	}

	@Then("the error message {string} should be displayed")
	public void the_error_message_should_be_displayed(String expectedError) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String usernameMessage = (String) js.executeScript("return arguments[0].validationMessage;",
				loginPage.getUsernameField());

		String passwordMessage = (String) js.executeScript("return arguments[0].validationMessage;",
				loginPage.getPasswordField());

		if (expectedError.equalsIgnoreCase("Invalid username and password")) {
			// App-specific error message (not native validation)
			String actualError = loginPage.getDisplayedErrorMessage(); // You may need to implement this
			Assert.assertEquals(actualError.trim().toLowerCase(), expectedError.trim().toLowerCase(),
					"App error message mismatch");
		} else {
			boolean matchFound = Stream.of(usernameMessage, passwordMessage)
					.anyMatch(msg -> msg.trim().equalsIgnoreCase(expectedError.trim()));
			Assert.assertTrue(matchFound, "Expected validation message not found: " + expectedError);
		}

	}

	@When("the user presses Enter")
	public void the_user_presses_enter() {
		loginPage.pressEnterToSubmit();
	}

	// Successful login
	@Given("the user provides valid credentials")
	public void the_user_provides_valid_credentials() {
		username = "validUser";
		password = "validPass";
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@When("the user submits the login form")
	public void the_user_submits_the_login_form() {
		loginPage.clickLoginButton();
	}

	@When("the user confirms login using Enter")
	public void the_user_confirms_login_using_enter() {
		loginPage.pressEnterToSubmit();
	}

	@Then("the user should be redirected to the Home Page with a message {string}")
	public void the_user_should_be_redirected_to_the_home_page_with_a_message(String expectedMessage) {
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Redirected to: " + actualUrl);
		Assert.assertTrue(actualUrl.contains("/home"), "User is not redirected to the home page");
	}

	// Register link visibility
	@When("the login page is displayed")
	public void the_login_page_is_displayed() {
		Assert.assertTrue(loginPage.isLoginPageVisible(), "Login page is not visible");
	}

	@Then("the user should be able to see the {string} link")
	public void the_user_should_be_able_to_see_the_link(String linkText) {
		Assert.assertTrue(loginPage.isRegisterLinkVisible(), "Register link not visible");
	}

	@When("the user initiates login")
	public void the_user_initiates_login() {
		loginPage.clickLoginButton();
	}

}
