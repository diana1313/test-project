package uisteps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import driver.CustomDriverFactory;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before(order = 1)
    public void setupDriver() {
        CustomDriverFactory.initializeDriver();
    }

    @Before(order = 2)
    public void prepareDriver() {
        CustomDriverFactory.getDriver().manage().window().maximize();
        CustomDriverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        CustomDriverFactory.quit();
    }

}
