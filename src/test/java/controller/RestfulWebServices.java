package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestfulWebServices {

    private Gson gson = new Gson();
    private String authToken = "e8dc8a857f3a881cd2616495693a40277e8ed322";

    public RestfulWebServices() {
    }

    public Response getCall(String endPoint, Map<String, String> headers, String authToken) {
        RequestSpecification request = RestAssured.given();
        request.accept("application/json");
        request.header("Content-Type", "application/json", new Object[0]);

        if (null != headers) {
            request.headers(headers);
        }

        if (authToken != null) {
            request.authentication().oauth2(authToken);
        }

        Response response = (Response) request.get(endPoint);
        response.prettyPrint();
        return response;
    }

    public Response postCall(JsonObject jsonBody, String stringBody, String endPoint, Map<String, String> headers, String authToken) {
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();
        request.accept("application/json");
        request.header("Content-Type", "application/json", new Object[0]);
        if ( null != headers) {
            request.headers(headers);
        }

        if (authToken != null) {
            request.authentication().oauth2(authToken);
        }

        if (jsonBody != null) {
            request.body(this.gsonToJson(jsonBody));
        }

        if (stringBody != null) {
            request.body(stringBody);
        }

        Response response = (Response) request.post(endPoint);
        response.prettyPrint();
        return response;
    }

    public Response DeleteCall(JsonObject jsonBody, String stringBody, String endPoint, Map<String, String> headers, String authToken) {
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();
        request.accept("application/json");
        request.header("Content-Type", "application/json", new Object[0]);
        if (!headers.isEmpty()) {
            request.headers(headers);
        }

        if (authToken != null) {
            request.authentication().oauth2(authToken);
        }

        if (jsonBody != null) {
            request.body(this.gsonToJson(jsonBody));
        }

        if (stringBody != null) {
            request.body(stringBody);
        }

        Response response = (Response) request.delete(endPoint, new Object[0]);
        return response;
    }

    public String gsonToJson(JsonObject body) {
        return this.gson.toJson(body);
    }

    private Response waitForApiResponse(String callType, Response response) {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 10000L && response == null) {
            if (System.currentTimeMillis() - startTime == 9999L) {
                System.out.println("Api call has taken more then 10 seconds and may fail");
            }
        }

        return response;
    }

    public String getAuthToken() {
        return authToken;
    }

}
