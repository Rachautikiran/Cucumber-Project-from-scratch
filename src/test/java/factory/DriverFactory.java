package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("Driver is not initialized. Please call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}