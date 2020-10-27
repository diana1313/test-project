package insider.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InsiderJobPositionPage extends Page {
    private static final By DESCRIPTION_BASE_PART_XPATH = By.xpath("(//div[@class='section page-centered'])[1]");
    private static final By BOTTOM_APPLY_BUTTON_XPATH = By.xpath("//div[@data-qa='btn-apply-bottom']/a[text()='Apply for this job']");

    public WebElement getDescriptionBasePartXpath() {
        return driver.findElement(DESCRIPTION_BASE_PART_XPATH);
    }

    public WebElement getBottomApplyButton() {
      scrollToPageBottom(BOTTOM_APPLY_BUTTON_XPATH);
        return driver.findElement(BOTTOM_APPLY_BUTTON_XPATH);
    }

    public void applyForThisJob() {

        clickOnElement(getBottomApplyButton());
    }

    @Override
    public boolean verify() {
        return getDescriptionBasePartXpath().isDisplayed() &&
                getBottomApplyButton().isDisplayed();
    }

    public boolean verifyCorrectJobLinkIsOpened(String expectedUrl) {
        return driver.getCurrentUrl().equalsIgnoreCase(expectedUrl);
    }
}
