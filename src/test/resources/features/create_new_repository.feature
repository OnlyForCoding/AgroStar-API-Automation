Feature: Creation of New Repository

  @create_new_repo
  Scenario: Create New Repository
    Given I have endpoint for "create_new_repository"
    And I have request payload for in file "create_new_repository"
    When I change the name of the repository in request payload file "create_new_repository"
    And I create new repository
    Then I see response status is 201
    Then I can see that new repository is created

    @create_duplicate_repo
  Scenario: Create Repository with Name which is already created
    Given I have endpoint for "create_new_repository"
    And I have request payload for in file "create_new_repository"
    And I create new repository
    Then I see that error occurred while creating duplicate repository