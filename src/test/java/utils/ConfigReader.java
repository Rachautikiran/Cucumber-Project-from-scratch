package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static void initProperties() {

        properties = new Properties();

        try {
            FileInputStream file = new FileInputStream(
                    "src/test/resources/config/config.properties"
            );

            properties.load(file);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {

        if (properties == null) {
            initProperties();
        }

        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Property not found in config.properties: " + key);
        }

        return value;
    }
}