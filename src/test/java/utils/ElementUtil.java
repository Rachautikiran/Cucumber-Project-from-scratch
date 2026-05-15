package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtil {

    private WebDriver driver;
    private WaitUtil waitUtil;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
    }

    public WebElement getElement(By locator) {
        return waitUtil.waitForElementVisible(locator);
    }

    public void doClick(By locator) {
        waitUtil.waitForElementClickable(locator).click();
    }

    public void doSendKeys(By locator, String value) {
        WebElement element = waitUtil.waitForElementVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    public String doGetText(By locator) {
        return waitUtil.waitForElementVisible(locator).getText();
    }

    public boolean doIsDisplayed(By locator) {
        return waitUtil.waitForElementVisible(locator).isDisplayed();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}