package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.LoginPage;
import pages.SignupPage;

import java.time.Instant;

public class SignupSteps {
    private SignupPage signupPage;
    private LoginPage loginPage;

    private SignupPage getSignupPage() {
        if (signupPage == null) {
            signupPage = new SignupPage(DriverFactory.getDriver());
        }
        return signupPage;
    }

    private LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(DriverFactory.getDriver());
        }
        return loginPage;
    }

    @Given("user is on login page")
    public void user_is_on_login_page() {
        getLoginPage();
        getSignupPage();
        System.out.println("user is on login page");
    }

    @And("user enters name {string} and email {string}")
    public void user_enters_name_and_email(String name, String email) {
        String uniqueEmail = buildUniqueEmail(email);
        System.out.println("Using signup email: " + uniqueEmail);
        getSignupPage().signUpDetails(name, uniqueEmail);
    }

    @And("user clicks on signup button")
    public void user_clicks_on_signup_button() {
        getSignupPage().clickOnSignUpButton();
    }

    @Then("user navigates to signup page")
    public void user_navigates_to_signup_page() {
        Assert.assertTrue(
                DriverFactory.getDriver().getCurrentUrl().contains("signup"),
                "Not navigated to signup page"
        );
    }

    private String buildUniqueEmail(String email) {
        if (email == null || !email.contains("@")) {
            return email;
        }

        String[] emailParts = email.split("@", 2);
        return emailParts[0] + "+" + Instant.now().toEpochMilli() + "@" + emailParts[1];
    }
}
