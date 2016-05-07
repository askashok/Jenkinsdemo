package Project;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Mobile {

    static String Appium_Node_Path="C:/Program Files (x86)/nodejs/node.exe";
    static String Appium_JS_Path="C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";
    static AppiumDriverLocalService service;
    static String service_url;
    static AndroidDriver<MobileElement> driver;

public static void appiumStart() throws Exception{
        service = AppiumDriverLocalService
        		.buildService(new AppiumServiceBuilder()
        		.usingPort(4723)
        		.usingDriverExecutable(new File(Appium_Node_Path))
        		.withAppiumJS(new File(Appium_JS_Path)));
        service.start();
        Thread.sleep(25000);
        service_url = service.getUrl().toString();
    }

 @BeforeTest
 public void setUp() throws Exception {
  // Start appium server.
  appiumStart();
  DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setCapability("deviceName", "J668A1ZR5B101972");
  capabilities.setCapability("browserName", "Android");
  capabilities.setCapability("platformVersion", "5.1");
  capabilities.setCapability("platformName", "Android");
  capabilities.setCapability("app", "http://192.168.1.184:9090/jenkins/job/Project1_TObject/lastSuccessfulBuild/artifact/app/build/outputs/apk/app-debug.apk");
  capabilities.setCapability("appPackage","com.example.android.testing.unittesting.BasicSample");
  capabilities.setCapability("appActivity","com.example.android.testing.unittesting.BasicSample.MainActivity");
  driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 
 }
@Test
 public void ScrollToTab() {
	
	 driver.findElement(By.name("Enter your name")).sendKeys("ashok");
	 driver.hideKeyboard();
     driver.findElement(By.name("Enter your Email")).sendKeys("ashok.blisslogix@gmail.com");
     driver.findElement(By.name("Save")).click();
 }
 @AfterTest
 public static void appiumStop() throws Exception{
	 driver.quit();
	 service.stop();
}
}