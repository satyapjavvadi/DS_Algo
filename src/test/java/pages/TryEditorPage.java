package pages;

import DriverManager.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExcelReader;
import utils.TestContext;
import utils.WaitUtils;

public class TryEditorPage {

    private WaitUtils wait;
    private WebDriver driver;

    @FindBy(xpath = "//button")
    private WebElement run_button;

    @FindBy(xpath = "//span[@role='presentation']//span")
    private WebElement codeArea;
    
    @FindBy(css = ".CodeMirror")
    private WebElement codeEditor;


    public TryEditorPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(this.driver, this);
        wait = new WaitUtils();
    }


    public void runCode(String scenarioType) {
        TestContext.testData = ExcelReader.getTestData(scenarioType);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions act = new Actions(driver);
        act.moveToElement(codeArea).click();
        js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue('');");
        js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);", TestContext.testData.get("code"));
        run_button.click();
    }

    public String getConsoleOutput(){
        wait.waitForPageLoad();

        String result = wait.waitForCodeMirrorOutput("output", 120);
        System.out.println("Submission result: " + result);
        return result;
    }
    
    public boolean isEditorVisible() {
        try {
            return codeEditor.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    
}
