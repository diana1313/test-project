Feature: Pet Store API
  Verification of CRUD operations


  Scenario: Create Pet in Store
    When I create pet with name parameter "pitbull"
    Then I check created pet exists and has expected name
    When I delete current pet
    Then I check current pet doesn't exist


  Scenario: Update Pet in Store
    When I create pet with name parameter "pitbull"
    Then I check created pet exists and has expected name
    When I update current pet with new name parameter "stafford"
    Then I check updated pet exists and has expected name
    When I delete current pet
    Then I check current pet doesn't exist

  #Fails cause pet still exist ith this ID
  Scenario: Delete Pet in Store
    When I create pet with name parameter "pitbull"
    When I delete current pet
    Then I check current pet doesn't exist