import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.net.MalformedURLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SampleTest {

    private AndroidDriver driver;
    private MobileObjects mobileObjects;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
        mobileObjects = new MobileObjects(driver);
    }


    @Test
    public void changeTest() {
        mobileObjects.input.isDisplayed();
        mobileObjects.input.click();
        mobileObjects.input.sendKeys(" ");
        mobileObjects.change.isDisplayed();
        mobileObjects.change.click();
        mobileObjects.textToBeChanged.isDisplayed();
        Assertions.assertEquals("Hello UiAutomator!", mobileObjects.textToBeChanged.getText());
    }
    @Test
    public void activityTest() {
        WebDriverWait wait = new WebDriverWait(driver,5);
        mobileObjects.input.isDisplayed();
        mobileObjects.input.click();
        mobileObjects.input.sendKeys("Netology");
        wait.until(ExpectedConditions.textToBePresentInElement(mobileObjects.input,"Netology"));
        mobileObjects.activity.click();
        mobileObjects.textToBeActivity.isDisplayed();
        Assertions.assertEquals("Netology", mobileObjects.textToBeActivity.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
