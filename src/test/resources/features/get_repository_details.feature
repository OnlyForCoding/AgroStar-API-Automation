@Repo_Details
Feature: Repository Details

  @get_repo_details
  Scenario: Get repository details
    Given I have endpoint for "get_repo_details"
    When I ask for repository details of "Agro-India68"
    Then I see response status is 200
    And I see the details of requested repository "Agro-India68"

  @get_details_for_missing_repo
  Scenario: Get repository details which not present
    Given I have endpoint for "get_repo_details"
    When I ask for repository details of "Agro-India6"
    Then I see response status is 404
    And I see the error message as "Not Found"