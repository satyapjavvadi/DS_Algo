package pages;

import DriverManager.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

import java.util.List;

public class HomePage {
	private WebDriver driver;

	@FindBy(css = "div.alert[role='alert']")
	private WebElement loginAlert;
	@FindBy(xpath = "//a[@href='/login']")
	private WebElement loginButton;

	@FindBy(xpath = "//ul")
	private WebElement parent;

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

	public void navigatetoPages(String expectedPage){
		WaitUtils.waitForVisibility(driver,parent,10);
		List<WebElement> childLink = parent.findElements(By.xpath(".//a"));
		for(WebElement eachLink : childLink){
			String linkText = eachLink.getText();
			System.out.println(linkText);
			if(linkText.toLowerCase().contains(expectedPage.toLowerCase())){
				System.out.println("in line 57");
				WaitUtils.waitForVisibility(driver,eachLink,10);
				eachLink.click();
				break;
			}
		}
	}

}
