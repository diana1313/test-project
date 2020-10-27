package insider.ui.pages;

import driver.CustomDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page implements BasePage {
    public static final String BASE_URL = "https://useinsider.com";
    private static final By LOGO_INSIDER_XPATH = By.xpath("//a[@id='logo' and @href='" + BASE_URL + "']");

    protected WebDriver driver = CustomDriverFactory.getDriver();

    @Override
    public void open(String url) {
        driver.get(url);
    }

    public void clickOnElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();

    }

    public void clickOnElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

    }

    public void scrollToPageBottom(By by){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(by));
    }

    public boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

    public void sendKeysIntoField(By element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).sendKeys(text);
    }

    public String getElementText(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText();
    }


    public boolean isElementDisplayed(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }

    public boolean isElementDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    @Override
    public void initElements() {
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean verify() {
        return isElementDisplayed(LOGO_INSIDER_XPATH);
    }


}
