package stepDefinations;

import base.BaseClass;
import pageActions.loginPageActions;

import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginStepDefinations {

    private loginPageActions login = new loginPageActions();

    @Given("the user is on the OpenCart homepage")
    public void the_user_is_on_the_open_cart_homepage() {
        // Hooks already opens appURL and maximizes
        login.waitForHomeReady();
    }

    @When("the user navigates to the Login page")
    public void the_user_navigates_to_the_login_page() {
        login.openLoginPage();
        login.waitForLoginPageReady();
    }

    @When("the user logs in with email {string} and password {string}")
    public void the_user_logs_in_with_email_and_password(String email, String password) {
        login.enterEmail(email);
        login.enterPassword(password);
        login.clickLogin();
        login.waitForPostLoginLoad();
    }

    @When("the user logs in with valid credentials from config")
    public void the_user_logs_in_with_valid_credentials_from_config() {
        login.loginWithConfigCreds();
        login.waitForPostLoginLoad();
    }

    @Then("the user should see they are logged in")
    public void the_user_should_see_they_are_logged_in() {
        Assert.assertTrue(login.isLoginSuccessful(), "Expected login success but account indicator not found.");
    }

    @Then("the user should see a login error")
    public void the_user_should_see_a_login_error() {
        String err = login.getLoginErrorMessage();
        Assert.assertTrue(err != null && err.toLowerCase().contains("warning"), "Expected warning alert on invalid login. Actual: " + err);
    }

    @When("the user logs out if already logged in")
    public void the_user_logs_out_if_already_logged_in() {
        login.logoutIfLoggedIn();
    }
}
