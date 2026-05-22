package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;

    private LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(DriverFactory.getDriver());
        }
        return loginPage;
    }

    @Given("user navigates to login page")
    public void user_navigates_to_login_page() {
        System.out.println("User is on the login page");
    }

    @When("user clicks on signup login button")
    public void user_clicks_on_signup_login_button() {

        getLoginPage().clickOnSignUpLoginBtn();
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        getLoginPage().enterLoginCredentials(username, password);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {

        getLoginPage().clickOnLoginButton();
    }

    @Then("user should see logout button")
    public void user_should_see_logout_button() {
        Assert.assertTrue(getLoginPage().isLogoutButtonDisplayed(), "logout button is not displayed");
    }
}
