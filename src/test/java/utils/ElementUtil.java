package utils;

import DriverManager.DriverFactory;
import org.openqa.selenium.WebDriver;

public class ElementUtil {



    public static String getCurrentURL() {
        return DriverFactory.getDriver().getCurrentUrl();
    }
}
