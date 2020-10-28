Feature: E2E testing of Insider "Apply for Job" Process

  Scenario: Verification of possibility to apply for a job through Insider
    Given I open Insider Home Page
    Then I check Insider Home page is opened
    When I select "Career" button in navigation bar on Insider Home page
    Then I check following blocks are displayed on Insider Career page:
      | Culture         |
      | Locations       |
      | Teams           |
      | Jobs            |
      | Life at Insider |
    When I scroll to Career Opportunities by selecting "Jobs" block on Insider Career page
    Then I check Job List items are displayed
    When I filter jobs by "location" - "Istanbul, Turkey" in Career Opportunities on Insider Career page
    When I filter jobs by "team" - "Quality Assurance" in Career Opportunities on Insider Career page
    Then I check all the Job List items for "location" contains "Istanbul,Turkey" in Career Opportunities on Insider Career page
    Then I check all the Job List items for "team" contains "QualityAssurance" in Career Opportunities on Insider Career page
    When I click on "2" Job List item in Career Opportunities on Insider Career page
    Then I check that correct Job Position page is opened and contains required data
    When I click on 'Apply for this Job' button on Job Position page
    Then I check Insider Application Form page is opened

  Scenario: Fail Verification of blocks on Insider Career page
    Given I open Insider Home Page
    Then I check Insider Home page is opened
    When I select "Career" button in navigation bar on Insider Home page
    Then I check following blocks are displayed on Insider Career page:
      | Culture         |
      | Locations       |
      | Teams           |
      | Process         |
      | Life at Insider |