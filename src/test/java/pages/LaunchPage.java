package pages;

import java.util.ArrayList;
import java.util.List;

import DriverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.JSUtils;

public class LaunchPage {

   private WebDriver driver;

    // Locators
    @FindBy(xpath = "//*[text()='Preparing for the Interviews']")
    private WebElement interviewText;

    @FindBy(linkText = "Get Started")
    private WebElement getStartedButton;

    @FindBy(xpath = "//*[contains(text(),'Copyright@NumpyNinja2021')]")
    private WebElement copyrightInfo;

    @FindBy(tagName = "button")
    private List<WebElement> buttons;

    // Constructor
    public LaunchPage(WebDriver driver ) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Browser checks
    public void browserIsOpen() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized");
        }
    }

    public boolean doesPageContainText(String expectedText) {
        return JSUtils.getPageInnerText(driver).contains(expectedText);
    }

    public void clickGetStartedButton() {
        getStartedButton.click();
    }

    public int getButtonCount() {
        return buttons.size();
    }

    public List<String> getButtonText() {
        List<String> buttonTexts = new ArrayList<>();
        for (WebElement button : buttons) {
            buttonTexts.add(button.getText());
        }
        return buttonTexts;
    }

    public boolean doesPageContainButtonWithText(String expectedButtonText) {
        return getButtonText().contains(expectedButtonText);
    }

    public void clickButtonByText(String buttonText) {
        for (WebElement button : buttons) {
            if (button.getText().equalsIgnoreCase(buttonText)) {
                button.click();
                break;
            }
        }
    }

    // URL
    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }

    public boolean isButtonEnabled(String buttonText) {
        for (WebElement button : buttons) {
            if (button.getText().equalsIgnoreCase(buttonText)) {
                return button.isEnabled();
            }
        }
        return false;
    }
}
