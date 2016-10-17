package selenium;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * To test Selenium Grid functionality with Mac/Windows machines.
 * Simple plain selenium web class, run this class using testng.xml create different mozilla,
 * chrome node on window and mac machines
 */
public class SeleniumGridTest {
	WebDriver driver = null;
	private StringBuffer verificationErrors = new StringBuffer();

	@Parameters({ "platform", "browser" })
	@BeforeTest(alwaysRun = true)
	public void setup(String platform, String browser) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();

		
		// Platforms
		if (platform.equalsIgnoreCase("Windows"))
			caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);

		if (platform.equalsIgnoreCase("MAC"))
			caps.setPlatform(org.openqa.selenium.Platform.MAC);

		
		// Browsers
		if (browser.equalsIgnoreCase("IE"))
			caps = DesiredCapabilities.internetExplorer();

		if (browser.equalsIgnoreCase("Firefox"))
			caps = DesiredCapabilities.firefox();

		if (browser.equalsIgnoreCase("Chrome"))
			caps = DesiredCapabilities.chrome();

//		if (browser.equalsIgnoreCase("Safari"))
//			caps = DesiredCapabilities.safari();

		
		//create a remote web driver and pass appropriate capability
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
	}
	
	
	@Test(description = "Xebia Labs")
	public void test1() throws MalformedURLException, InterruptedException {
		driver.navigate().to("https://xebialabs.com/");
		driver.findElement(By.id("search")).sendKeys("Hello Xebia");
		//Thread.sleep(5000);
		System.out.println("Test1:"+Thread.currentThread().getId());
		driver.close();
	}

	
	//@Test(description = "Yahoo home page")
	public void test2() throws MalformedURLException, InterruptedException {
		driver.navigate().to("https://in.yahoo.com/?p=us");
		driver.findElement(By.id("UHSearchBox")).sendKeys("Hello Yahoo");
		Thread.sleep(10000);
		System.out.println("Test2:"+Thread.currentThread().getId());
		driver.close();
	}
	
	
	//@Test(description = "Amazon home page")
	public void test3() throws MalformedURLException, InterruptedException {
		driver.navigate().to("http://www.amazon.in");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Hello Amazon");
		Thread.sleep(10000);
		System.out.println("Test3:"+Thread.currentThread().getId());
		driver.close();
	}
	
	
	
	

	@AfterTest
	public void afterTest() {
		// Close the browser
		driver.quit();

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}

	}
}
