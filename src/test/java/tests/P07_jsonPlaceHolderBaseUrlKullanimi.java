package tests;

import baseUrl.BaseUrlJsonPlace;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P07_jsonPlaceHolderBaseUrlKullanimi extends BaseUrlJsonPlace {
    /*
    https://jsonplaceholder.typicode.com/posts/44 endpointine
    bir GET request gonderdigimizde donen response’un
        status code’unun 200 oldugunu ve
        “title” degerinin “optio dolor molestias sit”
    oldugunu test edin

     */
    @Test
    public void test(){
      specJsonPlace.pathParams("pp1","posts","pp2","44");

        Response response=given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");

        response.then().assertThat().statusCode(200).body("title", Matchers.equalTo("optio dolor molestias sit"));
    }
}
