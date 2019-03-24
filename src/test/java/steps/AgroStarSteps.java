package steps;

import controller.AgroStarPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AgroStarSteps {

    private AgroStarPage agroStarPage;

    public AgroStarSteps(AgroStarPage agroStarPage) {
        this.agroStarPage = agroStarPage;
    }

    @Given("^I have endpoint for \"([^\"]*)\"$")
    public void i_have_endpoint_for(String feature) {
        agroStarPage.getEndPoint(feature);
    }

    @When("^I create new repository$")
    public void i_create_new_repository() {
        agroStarPage.createNewRepository();
    }

    @Then("^I can see that new repository is created$")
    public void i_can_see_that_new_repository_is_created() {
        agroStarPage.assertThatIsNewRepositoryCreated();
    }

    @And("^I have request payload for in file \"([^\"]*)\"$")
    public void iHaveRequestPayloadForInFile(String fileName) {
        agroStarPage.getRequestPayload(fileName);
    }


    @Then("^I see response status is (\\d+)$")
    public void iSeeResponseStatusIs(int statusCode) {
        agroStarPage.assertThatStatusCode(statusCode);

    }

    @When("^I change the name of the repository in request payload file \"([^\"]*)\"$")
    public void iChangeTheNameOfTheRepositoryInRequestPayloadFile(String name) {
        agroStarPage.changeNameOfRepository(name);
    }

    @Then("^I see that error occurred while creating duplicate repository$")
    public void iSeeThatErrorOccurredWhileCreatingDuplicateRepository() {
        agroStarPage.assertThatDuplicateRepo();
    }

    @When("^I ask for repository details of \"([^\"]*)\"$")
    public void iAskForRepositoryDetailsOf(String repository) {
        agroStarPage.getDetailsOfRepository(repository);
    }

    @And("^I see the details of requested repository \"([^\"]*)\"$")
    public void iSeeTheDetailsOfRequestedRepository(String repository) {
        agroStarPage.assertDetailsOfRepository(repository);
    }

    @And("^I see the error message as \"([^\"]*)\"$")
    public void iSeeTheErrorMessageAs(String errorMsg) {
        agroStarPage.assertErrorMessageForMissingRepository(errorMsg);
    }
}
