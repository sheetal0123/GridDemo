package selenium;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


/**
 * #How To Run
 * Right click and run "demo_lambada.xml"
 * 
 * To get username/accesskey
 * Create account with Lambada Test and go to Profile > Account Settings
 * https://accounts.lambdatest.com/security
 * 
 * To check Test Run Result:
 * https://automation.lambdatest.com/build
 */
public class SampleLambdaTest {

    public String username = "sheetal.singh8";  //TODO 1
    public String accesskey = "";      //TODO 2
    public RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub"; 
    boolean status = false;

    @BeforeTest
    @Parameters(value={"browser","version","platform"})
    public void setUp(String browser, String version, String platform){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version", version);
        capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "Prod Bug Build");
        capabilities.setCapability("name", "Sample Wiki Test");
        capabilities.setCapability("geoLocation", "JP"); //Geo Location
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testWikiPage(){
        try{
            driver.get("https://en.wikipedia.org/wiki/Wikipedia");
            driver.manage().window().maximize();

            System.out.println("### "+driver.getCurrentUrl());
            driver.findElement(By.name("search")).sendKeys("Oldest Scriptures");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"searchform\"]/div/button")).click();
            Thread.sleep(2000);
            System.out.println("### "+driver.getCurrentUrl());
            driver.get("https://www.google.com/");
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    

    @AfterTest
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
    
}
