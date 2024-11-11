package automation.example.com.support.instances;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Devices {
    private static final String APPIUM_SERVER = "http://127.0.0.1:4723";
    private static final DesiredCapabilities capabilities = new DesiredCapabilities();

    public static AndroidDriver getInstanceAndroid() throws MalformedURLException {
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:app", new File("app/SauceLabs.apk").getAbsolutePath());
        capabilities.setCapability("appium:noReset", "false");
        capabilities.setCapability("appium:appWaitActivity", "com.swaglabsmobileapp.*");
        return new AndroidDriver(new URL(APPIUM_SERVER), capabilities);
    }

    public static IOSDriver getInstanceIOS() throws MalformedURLException {
        capabilities.setCapability("appium:platformName", "iOS");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:deviceName", "iPhone 11");
        capabilities.setCapability("appium:app", new File("app/SauceLabs.app").getAbsolutePath());
        capabilities.setCapability("appium:noReset", "true");
        return new IOSDriver(new URL(APPIUM_SERVER), capabilities);
    }
}