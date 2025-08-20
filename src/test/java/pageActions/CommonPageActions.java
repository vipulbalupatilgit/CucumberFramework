package pageActions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import pageObjects.loginPageObjects;

public class CommonPageActions {
	
	public final WebDriver driver;
	public final WebDriverWait wait;
	public final Properties p;
	    
	    public CommonPageActions() {
	        this.driver = BaseClass.getDriver();
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        this.p = loadProps();
	        loginPageObjects LoginPageObjects=new loginPageObjects();
	        
	    }
	    public Properties loadProps() {
	        try {
	            Properties props = new Properties();
	            FileReader fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
	            props.load(fr);
	            return props;
	        } catch (IOException e) {
	            throw new RuntimeException("Unable to load config.properties", e);
	        }
	    }
	 public WebElement visible(By locator) {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	 public WebElement clickable(By locator) {
	        return wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }

	 public boolean isPresent(By locator) {
	        try {
	            driver.findElement(locator);
	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }

}
