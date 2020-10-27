package insider.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InsiderApplicationFormPage extends Page {
    private static final By APPLICATION_FORM_PART_XPATH = By.xpath(" //div[contains(@class, 'application-form')]" +
            "/h4[text()='Submit your application']/following-sibling::ul");
    private static final By SUBMIT_APPLICATION_BUTTON_XPATH = By.xpath("//button[@type='submit']");

    public WebElement getApplicationFormPartXpath() {
        return driver.findElement(APPLICATION_FORM_PART_XPATH);
    }

    public WebElement getSubmitApplicationButton() {
        return driver.findElement(SUBMIT_APPLICATION_BUTTON_XPATH);
    }

    @Override
    public boolean verify() {
        return getSubmitApplicationButton().isDisplayed() &&
                getApplicationFormPartXpath().isDisplayed();
    }
}
