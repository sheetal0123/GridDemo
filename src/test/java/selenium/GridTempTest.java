package selenium;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GridTempTest {
    
    @Test
    public void test() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.WINDOWS);
        WebDriver driver =  new RemoteWebDriver(new URL("http://192.168.1.131:4444/wd/hub"), desiredCapabilities);
        driver.get("https://in.yahoo.com/?p=us");
        driver.findElement(By.id("UHSearchBox")).sendKeys("Hello Yahoo");
        Thread.sleep(10000);
        System.out.println("Test2:"+Thread.currentThread().getId());
        driver.quit();
        
    }
    
    
}
