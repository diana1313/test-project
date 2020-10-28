package uisteps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import driver.CustomDriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hooks {
    private WebDriver driver;

    @Before(order = 1)
    public void setupDriver() {
        driver = CustomDriverFactory.getDriver();
    }

    @Before(order = 2)
    public void prepareDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        takeAScreenShot();
        CustomDriverFactory.quit();
    }

    public void takeAScreenShot() {
        Allure.addAttachment("Screenshot",
                new ByteArrayInputStream(((TakesScreenshot)
                driver).getScreenshotAs(OutputType.BYTES)));
    }

}
