package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverManager.DriverFactory;
import utils.WaitUtils;

public class HomePage {

	private WebDriver driver;

	@FindBy(linkText = "NumpyNinja")
	WebElement companyName;

	@FindBy(xpath = "//a[text()=' Register ']")
	WebElement registerLink;

	@FindBy(xpath = "//a[text()='Sign in']")
	WebElement logInLink;

	@FindBy(xpath = "//a[text()='Data Structures']")
	WebElement dataStructureDropdown;

	// all options in data structure dropdown
	@FindBy(xpath = "//a[@class='dropdown-item']")
	List<WebElement> dataStructureOptions;

	@FindBy(tagName = "h4")
	WebElement headingTitle;

	@FindBy(xpath = "//a[text()='Stack']")
	WebElement stackOption;

	@FindBy(xpath = "//div[@role='alert']")
	WebElement ErrMsg;

	@FindBy(xpath = "//div[@class='card-body d-flex flex-column']")
	private List<WebElement> parentCard;

	@FindBy(css = "div.alert[role='alert']")
	private WebElement loginAlert;

	@FindBy(xpath = "//a[@href='/login']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[text()=' ValidUser ']")
	WebElement loggedInUser;

	@FindBy(xpath = "//a[text()='Sign out']")
	WebElement signOutLink;

	@FindBy(xpath = "//ul")
	private WebElement parent;

	@FindBy(css = "div.alert[role='alert']")
	private WebElement alert;

	public HomePage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void launchUrl(String baseURL) {
		driver.get(baseURL);
	}

	public boolean getHomePage() {
		return driver.getCurrentUrl().contains("/home");
	}

	public String getLoginSuccessMessage() {
		return WaitUtils.getVisibleText(driver, loginAlert, 0);
	}

	public void clickSignInButton() {
		loginButton.click();
	}

	public String getCompanyName() {
		return companyName.getText();
	}

	public String getRegisterLink() {
		return registerLink.getText();

	}

	public String getLogInLink() {
		return logInLink.getText();
	}

	public String getLoggedInUser() {
		return loggedInUser.getText();
	}

	public String getSignOutLink() {
		return signOutLink.getText();
	}

	public void clickDataStructureDropdown() {
		dataStructureDropdown.click();
	}
	
	public String getRightCornerLink(String linkText) {
		if (linkText.equalsIgnoreCase("Sign out")) {
			System.out.println("Link Text is: " + signOutLink.getText());
			return signOutLink.getText();
		} 
		else if (linkText.equalsIgnoreCase("Validuser")) {
			System.out.println("Link Text is: " + loggedInUser.getText());
			return loggedInUser.getText();
		} 
		else {
			return "Invalid Link Text";
		}
	}

	public List<String> getDataStructureOptionsText() {
		List<WebElement> options = dataStructureOptions;
		System.out.println("dropdown options are:" + options.size());

		for (WebElement option : options) {
			System.out.println(option.getText());
		}
		return options.stream().map(WebElement::getText).collect(Collectors.toList());

	}

	public void clickStackOption() {
		dataStructureDropdown.click();
		stackOption.click();

	}

	public String getErrMsg() {
		return ErrMsg.getText();
	}

	public void selectOption(String option) {
		dataStructureDropdown.click();
		for (WebElement opt : dataStructureOptions) {
			if (opt.getText().equals(option)) {
				opt.click();
				break;
			}
		}
	}

	public String getHeadingTitle() {
		return headingTitle.getText();
	}

	public List<String> getTitles(String string2) {
		List<String> title = new ArrayList<>();
		for (WebElement child : parentCard) {
			List<WebElement> grandChild = child.findElements(By.xpath(".//h5"));

			for (WebElement element : grandChild) {
				title.add(element.getText());
				System.out.println("Titles are: " + title);
			}
		}
		return title;
	}

	public String getLinkName(String linkText) {
		if (linkText.equalsIgnoreCase("Register")) {
			System.out.println("Link Text is: " + registerLink.getText());
			return registerLink.getText();	
		} else if (linkText.equalsIgnoreCase("Sign in")) {
			System.out.println("Link Text is: " + logInLink.getText());
			return logInLink.getText();
		} else {
			return "Invalid Link Text";
		}
	}
	public String getPageHeading(String OptionName) {

		switch (OptionName) {
		case "Graph":
			headingTitle.getText();
			break;
		case "Array":
			headingTitle.getText();
			break;
		case "Linked List":
			headingTitle.getText();
			break;
		case "Stack":
			headingTitle.getText();
			break;
		case "Queue":
			headingTitle.getText();
			break;
		case "Tree":
			headingTitle.getText();
			break;

		default:
			System.out.println("Invalid Heading");
			break;
		}
		System.out.println("Page Heading is: " + headingTitle.getText());
		return headingTitle.getText();
	
	}

	public void clickTitlePage(String ExpectedTitle) {
		String topicHeading;
		for (WebElement child : parentCard) {
			List<WebElement> grandChild = child.findElements(By.xpath(".//h5"));//do i need to create web element

			for (WebElement element : grandChild) {
				topicHeading = element.getText();
				if (topicHeading.equalsIgnoreCase(ExpectedTitle)) {
					element.findElement(By.xpath("..//a")).click();
					return;
				}
			}
		}
	}

    public void navigatetoPages(String pageInfo) {
		WaitUtils.waitForVisibility(driver,parent,10);
		List<WebElement> childLink = parent.findElements(By.xpath(".//a"));
		for(WebElement eachLink : childLink){
			String linkText = eachLink.getText();
			System.out.println(linkText);
			if(linkText.toLowerCase().contains(pageInfo.toLowerCase())){
				System.out.println("in line 57");
				WaitUtils.waitForVisibility(driver,eachLink,10);
				eachLink.click();
				break;
			}
		}
	}

	public String getAlertMessage() {
		return alert.getText().trim();
	}

	public void clickGetStarted(String cardTitle) {
		System.out.println(cardTitle);
		for (WebElement child : parentCard) {
			List<WebElement> grandChild = child.findElements(By.xpath(".//h5"));

			for (WebElement element : grandChild) {
				element.getText();
				System.out.println("in home " +element.getText());
				if(element.getText().equalsIgnoreCase(cardTitle)){
					child.findElement(By.xpath(".//a")).click();
					return;
				}

			}
		}
	}
}
