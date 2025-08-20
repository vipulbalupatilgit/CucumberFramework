package pageActions;


import pageObjects.loginPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class loginPageActions extends loginPageObjects{

	loginPageObjects login= new loginPageObjects();
    private WebDriver driver;

   

    // ------------ Actions ------------

    public void openLoginPage() {
        driver.findElement(login.MY_ACCOUNT_TOGGLE).click();
        driver.findElement(login.LOGIN_LINK).click();
    }

    public void enterEmail(String email) {
        WebElement emailBox = driver.findElement(login.EMAIL_INPUT);
        emailBox.clear();
        emailBox.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement pwdBox = driver.findElement(login.PASSWORD_INPUT);
        pwdBox.clear();
        pwdBox.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(login.LOGIN_BUTTON).click();
    }

    public boolean isLoginSuccessful() {
        try {
            return driver.findElement(login.ACCOUNT_BREADCRUMB).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(login.DANGER_ALERT).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void logoutIfLoggedIn() {
        try {
            driver.findElement(login.MY_ACCOUNT_TOGGLE).click();
            driver.findElement(login.LOGOUT_LINK).click();
        } catch (Exception e) {
            // ignore if not logged in
        }
    }

    // High-level reusable method
    public boolean login(String email, String password) {
        openLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        return isLoginSuccessful();
    }
}
