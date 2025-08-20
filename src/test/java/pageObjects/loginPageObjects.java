package pageObjects;

import org.openqa.selenium.By;

public class loginPageObjects  { 
	
	
    public  final By MY_ACCOUNT_TOGGLE = By.xpath("//a[contains(@href,'route=account/account') and contains(.,'My Account')]");
    public  final By LOGIN_LINK        = By.xpath("//a[contains(@href,'route=account/login')]");
    public  final By LOGOUT_LINK       = By.xpath("//a[contains(@href,'route=account/logout')]");

    // Login page
    public   final By EMAIL_INPUT    = By.xpath("//input[@id='input-email' or @name='email']");
    public   final By PASSWORD_INPUT = By.xpath("//input[@id='input-password' or @name='password']");
    public   final By LOGIN_BUTTON   = By.xpath("//input[@value='Login'] | //button[normalize-space()='Login']");

    // Post-login / account landing
    public   final By ACCOUNT_BREADCRUMB  = By.xpath("//ul[@class='breadcrumb']//a[contains(@href,'route=account/account')]");
    public   final By ACCOUNT_HEADING     = By.xpath("//div[@id='content']//h2 | //div[@id='content']//h1[contains(.,'Account')]");

    // Alerts
    public   final By DANGER_ALERT   = By.xpath("//div[contains(@class,'alert-danger')]");
    public   final By SUCCESS_ALERT  = By.xpath("//div[contains(@class,'alert-success')]");
}
