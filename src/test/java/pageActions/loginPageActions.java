package pageActions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import pageObjects.loginPageObjects;

public class loginPageActions extends CommonPageActions{

//    private final WebDriver driver;
//    private final WebDriverWait wait;
//    private final Properties p;
//
//    public loginPageActions() {
//        this.driver = BaseClass.getDriver();
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        this.p = loadProps();
//    }
    loginPageObjects LoginPageObjects=new loginPageObjects(); 
    CommonPageActions commonPageActions= new CommonPageActions();
//
//    private Properties loadProps() {
//        try {
//            Properties props = new Properties();
//            FileReader fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
//            props.load(fr);
//            return props;
//        } catch (IOException e) {
//            throw new RuntimeException("Unable to load config.properties", e);
//        }
//    }

    // ----------- waits/helpers -----------

//    private WebElement visible(By locator) {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
//
//    private WebElement clickable(By locator) {
//        return wait.until(ExpectedConditions.elementToBeClickable(locator));
//    }
//
//    private boolean isPresent(By locator) {
//        try {
//            driver.findElement(locator);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }

    public void waitForHomeReady() {
        // We just ensure header “My Account” toggle is present
    	commonPageActions.visible(LoginPageObjects.MY_ACCOUNT_TOGGLE);
    }

    public void openLoginPage() {
    	commonPageActions.clickable(LoginPageObjects.MY_ACCOUNT_TOGGLE).click();
    	commonPageActions.clickable(LoginPageObjects.LOGIN_LINK).click();
    	
    }

    public void waitForLoginPageReady() {
    	commonPageActions. visible(LoginPageObjects.EMAIL_INPUT);
    	commonPageActions.visible(LoginPageObjects.PASSWORD_INPUT);
    }

    public void enterEmail(String email) {
        WebElement e = commonPageActions.visible(LoginPageObjects.EMAIL_INPUT);
        e.clear();
        e.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement e = visible(LoginPageObjects.PASSWORD_INPUT);
        e.clear();
        e.sendKeys(password);
    }

    public void clickLogin() {
        clickable(LoginPageObjects.LOGIN_BUTTON).click();
    }

    /** Waits for either account breadcrumb (success) or danger alert (error) */
    public void waitForPostLoginLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> {
            return isPresent(LoginPageObjects.ACCOUNT_BREADCRUMB) || isPresent(LoginPageObjects.DANGER_ALERT);
        });
    }

    public boolean isLoginSuccessful() {
        try {
            return visible(LoginPageObjects.ACCOUNT_BREADCRUMB).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public String getLoginErrorMessage() {
        try {
            return visible(LoginPageObjects.DANGER_ALERT).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean logoutIfLoggedIn() {
        try {
            clickable(LoginPageObjects.MY_ACCOUNT_TOGGLE).click();
            // If logout link visible, click it
            if (isPresent(LoginPageObjects.LOGOUT_LINK)) {
                clickable(LoginPageObjects.LOGOUT_LINK).click();
                return true;
            }
        } catch (Exception ignored) {}
        return false;
    }

    public void loginWithConfigCreds() {
        String email = p.getProperty("email");
        String password = p.getProperty("password");
        if (email == null || password == null) {
            throw new RuntimeException("email/password not found in config.properties");
        }
        openLoginPage();
        waitForLoginPageReady();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
}
