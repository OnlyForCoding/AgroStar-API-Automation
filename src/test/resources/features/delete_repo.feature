Feature: Delete Repo

  Scenario: Delete the created repo
    Given I have endpoint for "create_new_repository"
    And I have request payload for in file "create_new_repository"
    When I change the name of the repository in request payload file "create_new_repository"
    And I create new repository
    When I delete the recently created repo
    Then I see that repo is deleted