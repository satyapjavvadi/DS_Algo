package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtils {
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public static void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    public static void scrollUp(WebDriver driver, int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -" + pixels + ")");
    }
    
    public static void scrollToLoadAllTopics(WebDriver driver) {
        // Scroll to bottom
        scrollToBottom(driver);

        // Instead of Thread.sleep, use a small JS wait or WaitUtils
        try {
            Thread.sleep(1000); // quick pause if needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Scroll up slightly to trigger lazy loading
        scrollUp(driver, 100);
    }
    
    public static String getPageInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return document.documentElement.innerText;");
    }
    
    public static void clickElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}

