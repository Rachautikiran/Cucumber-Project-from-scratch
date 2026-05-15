package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class SignupPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("timeout")))
        );
    }

    private final By nameInput =
            By.xpath("//input[@data-qa='signup-name']");

    private final By emailInput =
            By.xpath("//input[@data-qa='signup-email']");

    private final By signUpButton =
            By.xpath("//button[@data-qa='signup-button']");

    public void signUpDetails(String name, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    public void clickOnSignUpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
    }

    public String verifyCurrentURL() {
        return driver.getCurrentUrl();
    }
}
