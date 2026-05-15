package pages;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.ElementUtil;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private ElementUtil elementUtil;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("timeout")))
        );
        elementUtil=new ElementUtil(DriverFactory.getDriver());
    }

    private final By signUpLoginBtn =
            By.xpath("//a[normalize-space()='Signup / Login']");

    private final By emailInput =
            By.xpath("//input[@data-qa='login-email']");

    private final By passwordInput =
            By.xpath("//input[@data-qa='login-password']");

    private final By loginBtn =
            By.xpath("//button[@data-qa='login-button']");

    private final By logoutButton =
            By.xpath("//a[normalize-space()='Logout']");

    public void clickOnSignUpLoginBtn() {
        elementUtil.doClick(signUpLoginBtn);
    }

    public void enterLoginCredentials(String username, String password) {
        elementUtil.doSendKeys(emailInput,username);
        elementUtil.doSendKeys(passwordInput,password);
    }

    public void clickOnLoginButton() {
       elementUtil.doClick(loginBtn);
    }

    public boolean isLogoutButtonDisplayed() {
       return elementUtil.doIsDisplayed(logoutButton);
    }
}
