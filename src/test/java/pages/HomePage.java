package pages;

import DriverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class HomePage {
	private WebDriver driver;

	@FindBy(css = "div.alert[role='alert']")
	private WebElement loginAlert;
	@FindBy(xpath = "//a[@href='/login']")
	private WebElement loginButton;
	@FindBy(css = "div.alert[role='alert']")
	private WebElement registerAlert;

	public HomePage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	public boolean getHomePage() {
		return driver.getCurrentUrl().contains("/home");
	}

	public String getLoginSuccessMessage() {
		return WaitUtils.getVisibleText(driver, loginAlert, 10);
	}

	public void clickSignInButton(){
		loginButton.click();
	}
	
	public String getRegisterSuccessMessage() {
		return WaitUtils.getVisibleText(driver, registerAlert, 10);
	}

	public String getSuccessRegisterMessage() {
        return registerAlert.getText().trim();
    }

}
