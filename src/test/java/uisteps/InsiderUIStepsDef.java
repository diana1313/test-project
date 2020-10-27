package uisteps;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import insider.ui.other.JobLink;
import insider.ui.pages.InsiderApplicationFormPage;
import insider.ui.pages.InsiderCareerPage;
import insider.ui.pages.InsiderHomePage;
import insider.ui.pages.InsiderJobPositionPage;
import insider.ui.pages.Page;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class InsiderUIStepsDef {
    private JobLink jobLink;

    public InsiderUIStepsDef(JobLink jobLink) {
        this.jobLink = jobLink;
    }

    @Given("^I open Insider Home Page$")
    public void iOpenInsiderHomePage() {
        InsiderHomePage homePage = new InsiderHomePage();
        homePage.open(Page.BASE_URL);
    }

    @Then("^I check Insider Home page is opened$")
    public void iCheckInsiderHomePageIsOpened() {
        InsiderHomePage homePage = new InsiderHomePage();
        boolean isPageOpened = homePage.verify();
        Assert.assertTrue(isPageOpened, "Insider Home page was expected to be opened");
    }

    @When("^I select \"?([^\"]*)\"? button in navigation bar on Insider Home page$")
    public void iSelectButtonInNavigationBarOnInsiderHomePage(String buttonName) {
        InsiderHomePage homePage = new InsiderHomePage();
        homePage.clickOnNavMenuButtonWithName(buttonName);
    }

    @Then("^I check following blocks are displayed on Insider Career page:$")
    public void iCheckFollowingBlocksAreDisplayedOnInsiderCareerPage(List<String> blocks) {
        InsiderCareerPage careerPage = new InsiderCareerPage();
        for (String block : blocks) {
            Assert.assertTrue(careerPage.isButtonWithNameDisplayed(block),
                    String.format("Block with name '%s' is not displayed on Insider Career page.", block));
        }
    }

    @When("^I scroll to Career Opportunities by selecting \"?([^\"]*)\"? block on Insider Career page$")
    public void iScrollToCareerOpportunitiesBySelectingBlockOnInsiderCareerPage(String block) {
        new InsiderCareerPage().clickOnButtonWithName(block);
    }

    @Then("^I check Job List items are displayed$")
    public void iCheckJobListItemsAreDisplayed() {
        boolean isJobListExists = !new InsiderCareerPage().getJobListItems().isEmpty();
        Assert.assertTrue(isJobListExists, "Job List was expected to be present");
    }

    @When("^I filter jobs by \"?(location|team)\"? - \"?([^\"]*)\"? in Career Opportunities on Insider Career page$")
    public void iFilterJobsInCareerOpportunitiesOnInsiderCareerPage(String filterType, String filterValue) {
        new InsiderCareerPage().selectOptionInFilter(filterType, filterValue);

    }

    @Then("^I check all the Job List items for \"?(location|team)\"? contains \"?([^\"]*)\"? " +
            "in Career Opportunities on Insider Career page$")
    public void iCheckAllTheJobListItemsContainsInCareerOpportunitiesOnInsiderCareerPage(String valueType, String expectedValue) {
        InsiderCareerPage careerPage = new InsiderCareerPage();
        for (int position = 0; position < careerPage.getJobListItems().size() - 1; position++) {
            String actualValue = careerPage.getAttribute(position, valueType);
            Assert.assertEquals(actualValue, expectedValue,
                    String.format("Job list item '%s' was expected to have value '%s'. Actual value is '%s'.",
                            valueType, expectedValue, actualValue));
        }
    }

    @When("^I click on \"?([^\"]*)\"? Job List item in Career Opportunities on Insider Career page$")
    public void iClickOnJobListItemInCareerOpportunitiesOnInsiderCareerPage(int number) {
        InsiderCareerPage careerPage = new InsiderCareerPage();
        List<WebElement> jobListItems = careerPage.getJobListItems();
        if (number > jobListItems.size()) {
            throw new IllegalArgumentException(String.format(
                    "Job List is not having item with %s number. It's size - %s", number, jobListItems.size()));
        } else {
            WebElement jobListItem = jobListItems.get(number - 1);
            jobLink.setLink(jobListItem.getAttribute("href"));
            jobListItem.click();
        }
    }

    @Then("^I check that correct Job Position page is opened and contains required data$")
    public void iCheckThatCorrectJobPositionPageIsOpenedAndContainsRequiredData() {
        InsiderJobPositionPage jobPositionPage = new InsiderJobPositionPage();
        boolean result = jobPositionPage.verifyCorrectJobLinkIsOpened(jobLink.getLink())
                && jobPositionPage.verify();
        Assert.assertTrue(result, "Opened Job Position page does not match to expected");
    }

    @When("^I click on 'Apply for this Job' button on Job Position page$")
    public void iClickOnApplyForThisJobButtonOnJobPositionPage() {
        new InsiderJobPositionPage().applyForThisJob();
    }

    @Then("^I check Insider Application Form page is opened$")
    public void iCheckApplicationFormPageIsOpened() {
        Assert.assertTrue(new InsiderApplicationFormPage().verify(),
                "Insider Application Form page was expected to be opened");

    }
}
