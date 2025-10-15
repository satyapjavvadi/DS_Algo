package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

	WebDriver driver;
	@FindBy(linkText = "Get Started")
	WebElement getStartedBtn;

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
	
	@FindBy(xpath = "//a[text()='Stack']")
	WebElement stackOption;
	
	@FindBy(xpath = "//div[@role='alert']")
	WebElement ErrMsg;
	
	@FindBy(xpath = "//a[text()='Arrays']")
	WebElement arrayOption;
	
	@FindBy(xpath = "//a[text()='Queue']")
	WebElement queueOption;
	
	@FindBy(xpath = "//a[text()='Tree']")
	WebElement treeOption;
	
	@FindBy(xpath = "//a[text()='Graph']")
	WebElement graphOption;
	
	@FindBy(xpath = "//a[@href='array']")
	WebElement arrayGetStarted;
	
	@FindBy(xpath = "//a[@href='stack']")
	WebElement stackGetStarted;
	
	@FindBy(xpath = "//a[@href='queue']")
	WebElement queueGetStarted;
	
	@FindBy(xpath = "//a[@href='tree']")
	WebElement treeGetStarted;
	
	@FindBy(xpath = "//a[@href='linked-list']")
	WebElement linkedListGetStarted;
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void launchUrl(String baseURL) {
		driver.get(baseURL);
	}

	public void clickGetStarted() {
		getStartedBtn.click();
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

	public void clickDataStructureDropdown() {
		dataStructureDropdown.click();
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
	
	public void clickArrayOption() {
		dataStructureDropdown.click();
		arrayOption.click();
	}
	
	public void clickQueueOption() {
		dataStructureDropdown.click();
		queueOption.click();
	}
	
	public void clickTreeOption() {
		dataStructureDropdown.click();
		treeOption.click();
	}	
	
	public void clickGraphOption() {
		dataStructureDropdown.click();
		graphOption.click();
	}
	public void clickArrayGetStarted() {
		
		arrayGetStarted.click();
	}
	
	public void clickStackGetStarted() {
		
		stackGetStarted.click();
	}
	public void clickQueueGetStarted() {
		
		queueGetStarted.click();
	}
	
	public void clickTreeGetStarted() {
		
		treeGetStarted.click();
	}
	
	public void clickLinkedListGetStarted() {
		
		linkedListGetStarted.click();
	}
	


	
	public void clickHeadings(String Heading) {
		getStartedBtn.click();
		switch(Heading) {
		case "Array":
			arrayGetStarted.click();
			break;
		case "Stack":
			stackGetStarted.click();
			break;
		case "Queue":
			queueGetStarted.click();
			break;
		case "Tree":
			treeGetStarted.click();
			break;
		case "Linked List":
			linkedListGetStarted.click();
			break;
		default:
			System.out.println("No matching heading found");
			
			
			
		}
	}
}