import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;

@Listeners(ExtentListener.class)
public class TargetClass {
	WebDriver driver;
	public TargetClass() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\labuser\\Downloads\\geckodriver-v0.35.0-win32\\geckodriver.exe");
    	driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
    
    public void navigate(String url) {
        driver.get(url);
    }

    public void click(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void enterText(String locator, String value) {
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }

    public String getText(String locator) {
        return driver.findElement(By.xpath(locator)).getText();
    }
    
    public void verifyText(String locator, String value) {
    	String actual = getText(locator);
    	String expected = value;
    	assertEquals(actual, expected);
    }
    
    
    public void quit() {
    	driver.quit();
    }

}
