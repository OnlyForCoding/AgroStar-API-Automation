package controller;

import com.jayway.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class AgroStarPage {

    private RestfulWebServices restfulWebServices;
    private ReadJsonFile readJsonFile = new ReadJsonFile();
    private String endpoint = "https://api.github.com/";
    private String requestPayloadFilePath = "src/test/resources/request_payload/";
    private String requestPayloadInString;
    private String nameOfRepository;
    private String currentFileName;
    private Response response;

    public AgroStarPage(RestfulWebServices restfulWebServices) {
        this.restfulWebServices = restfulWebServices;
    }

    public String getNameOfRepository() {
        return nameOfRepository;
    }

    public void setNameOfRepository(String nameOfRepository) {
        this.nameOfRepository = nameOfRepository;
    }

    public String getEndPoint(String feature) {
        String f = feature.toLowerCase();
        switch (f) {
            case "create_new_repository":
                endpoint = endpoint + "user/repos";
                break;
            case "get_repo_details":
                endpoint = endpoint + "repos/OnlyForCoding/";
                break;
        }

        return endpoint;
    }

    public String getRequestPayload(String fileName) {
        requestPayloadInString = readJsonFile.getJsonContentInString(requestPayloadFilePath + fileName);
        currentFileName = requestPayloadFilePath + fileName;
        return requestPayloadInString;
    }

    public void createNewRepository() {
        response = restfulWebServices.postCall(null, requestPayloadInString, endpoint, null, restfulWebServices.getAuthToken());
        setNameOfRepository(response.jsonPath().get("name"));
    }

    public void changeNameOfRepository(String currentFileName) {
        JSONObject jsonObject = readJsonFile.readJson(requestPayloadFilePath + currentFileName);
        setNameOfRepository("AgroStar");
        String newName = getNameOfRepository() + (int) (Math.random() * 1000);
        jsonObject.put("name", newName);
        setNameOfRepository(newName);
        requestPayloadInString = readJsonFile.getJsonContentInString(jsonObject);
    }

    public void assertThatStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code is not matching");
    }

    public void assertThatIsNewRepositoryCreated() {
        System.out.println("Name of repository is : " + nameOfRepository);
        Assert.assertEquals(response.jsonPath().get("name"), nameOfRepository, "Repository is not created with given name");
    }

    public void assertThatDuplicateRepo() {
        Assert.assertEquals(response.jsonPath().get("errors[0].message"), "name already exists on this account", "Duplicate repository is created");
    }

    public void getDetailsOfRepository(String nameOfRepository){
        endpoint = endpoint + nameOfRepository;
        response = restfulWebServices.getCall(endpoint, null, restfulWebServices.getAuthToken());
        response.prettyPrint();
    }

    public void assertDetailsOfRepository(String repository){
        Assert.assertEquals(response.jsonPath().get("name"), repository, "Repository name is not what I am expecting");
    }

    public void assertErrorMessageForMissingRepository(String errorMessage){
        Assert.assertEquals(response.jsonPath().get("message"), errorMessage, "Expected error message is not getting");
    }

    public void deleteRecentRepo(){
        if (null == getNameOfRepository()){
            throw new RuntimeException("Please run the post call to create the repository");
        }
        endpoint = "https://api.github.com/repos/OnlyForCoding/"+getNameOfRepository();
        restfulWebServices.deleteCall(null, null, endpoint,null, restfulWebServices.getAuthToken());
    }

    public void assertThatIsRecentCreatedRepoDeleted(){
        if (null == getNameOfRepository()){
            throw new RuntimeException("Please run the post call to create the repository");
        }
        endpoint = "https://api.github.com/repos/OnlyForCoding/"+getNameOfRepository();
        response = restfulWebServices.getCall(endpoint,null,restfulWebServices.getAuthToken());
        assertErrorMessageForMissingRepository("Not Found");
    }

    public void starTheRecentlyCreatedRepo(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Length","0");
        endpoint = "https://api.github.com/user/starred/OnlyForCoding/"+getNameOfRepository();
        restfulWebServices.putCall(null, null,endpoint,null,restfulWebServices.getAuthToken());
    }

    public void assertThatIsRepoStarred(){
        endpoint = "https://api.github.com/repos/OnlyForCoding/"+getNameOfRepository();
        response = restfulWebServices.getCall(endpoint,null,restfulWebServices.getAuthToken());
        //Assert.assertEquals(response.jsonPath().get("stargazers_count"), "1","Repo is not starred");
        Assert.assertEquals(response.jsonPath().get("stargazers_count").toString(), "1","Repo is not starred");
    }
}
