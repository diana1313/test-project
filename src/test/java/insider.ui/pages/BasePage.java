package insider.ui.pages;

import org.openqa.selenium.By;

public interface BasePage {
    void open(String url);
    void clickOnElement(By by);
    void sendKeysIntoField(By by, String text);
    String getElementText(By by);
    boolean isElementPresent(By by);
    boolean isElementDisplayed(By by);
    void initElements();
    boolean verify();
}
