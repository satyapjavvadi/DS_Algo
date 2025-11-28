package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {
        prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + File.separator + "src" 
                                + File.separator + "test" + File.separator + "resources" 
                                + File.separator + "config.properties");
        try (FileInputStream fis = new FileInputStream(propFile)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    // Get value by key
    public static String getProperty(String key) {
        String value = prop.getProperty(key);
        if (value != null) return value;
        else throw new RuntimeException("Property '" + key + "' not found in config.properties");
    }

    // Optional: get the whole Properties object
    public static Properties getProperties() {
        return prop;
    }
}
