package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
   private WebDriver driver;

   public LoginPage(WebDriver driver){
       this.driver=driver;
   }

   private By signUpLoginBtn=
           By.xpath("//a[text()=' Signup / Login']");

   private By emailInput=
           By.xpath("//input[@data-qa='login-email']");

   private By passwordInput=
           By.xpath("//input[@data-qa='login-password']");

   private By loginBtn=
           By.xpath("//button[@data-qa='login-button']");
   private By logoutButton=
           By.xpath("//a[text()=' Logout']");

   public void clickOnSignUpLoginBtn() {
       driver.findElement(signUpLoginBtn).click();
   }

  public void enterLoginCredentials(String username,String password){
      driver.findElement(emailInput).sendKeys(username);
      driver.findElement(passwordInput).sendKeys(password);
  }

  public void clickOnLoginButton(){
      driver.findElement(loginBtn).click();
  }

  public boolean isLogoutButtonDisplayed(){
      return driver.findElement(logoutButton).isDisplayed();
  }


}
