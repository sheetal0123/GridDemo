package appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class AppiumGridTest {

	protected AndroidDriver driver;
	protected WebDriverWait wait;


	@BeforeTest
	protected void createAppiumDriver() throws MalformedURLException, InterruptedException {
		// relative path to apk file
		final File classpathRoot = new File(System.getProperty("user.dir"));
		final File appDir = new File(classpathRoot, "src/test/resources/mobileapps/");
		final File app = new File(appDir, "ApiDemos-debug.apk");

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", ""); // 'chrome' for browser
		caps.setCapability("platform", "ANDROID");
		caps.setCapability("platformVersion", "6.0");
		caps.setCapability("deviceName", "ANDROID");
		caps.setCapability("app", app.getAbsolutePath());

		driver = new AndroidDriver(new URL("http://0.0.0.0:4444/wd/hub"), caps);
		wait = new WebDriverWait(driver, 10);
	}


	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	
	@Test
	public void sampeTest() {

		// click on Accessibility link
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Accessibility")));
		driver.findElement(MobileBy.AccessibilityId("Accessibility")).click();

		// click on 'Accessibility Node Querying' link
		wait.until(
				ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Accessibility Node Querying")));
		driver.findElement(MobileBy.AccessibilityId("Accessibility Node Querying")).click();

		// back
		driver.navigate().back();

		// back
		driver.navigate().back();
	}

}
