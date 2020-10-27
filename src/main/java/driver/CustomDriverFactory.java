package driver;

import helpers.PropertiesReader;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManagerException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class CustomDriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public static void initializeDriver() {
        DriverManagerType driverType = DriverManagerType.valueOf(PropertiesReader.getPropertyValue("webdriver.driver"));
        ChromeDriverManager.getInstance(driverType).setup();
        if (driverType.toString().equals("Google Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (driverType.toString().equals("Mozilla Firefox")) {
            driver = new FirefoxDriver();
//            FirefoxProfile ffprofile = new FirefoxProfile();
//            ffprofile.setPreference("dom.webnotifications.enabled", false);
//            driver = new FirefoxDriver(new FirefoxOptions().setProfile(ffprofile));
        } else {
            throw new WebDriverManagerException(String.format(
                    "Driver type '%s' defined in properties is not supported", driverType.toString()));
        }
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
