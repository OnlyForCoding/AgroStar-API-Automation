Feature: Star Repo

  Scenario: Star Repo and Verify
    Given I have endpoint for "create_new_repository"
    And I have request payload for in file "create_new_repository"
    When I change the name of the repository in request payload file "create_new_repository"
    And I create new repository
    Then I see response status is 201
    Then I can see that new repository is created
    When I star the recently created repository
    Then I see that repo is starred
    And I delete the recently created repo
