package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverFactory {

    // ThreadLocal driver
    private static final ThreadLocal<WebDriver> tlDriver =
            new ThreadLocal<>();


    // Initialize Driver
    public static void initDriver() {

        tlDriver.set(new ChromeDriver());

        getDriver().manage().window().maximize();

        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }


    // Get Driver
    public static WebDriver getDriver() {

        return tlDriver.get();
    }


    // Quit Driver
    public static void quitDriver() {

        if (getDriver() != null) {

            getDriver().quit();

            tlDriver.remove();
        }
    }
}