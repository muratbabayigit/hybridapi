package tests;

import baseUrl.BaseUrlJsonPlace;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testDatas.TestDatasJPH;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P11_GET_TestDataKullanimi extends BaseUrlJsonPlace {
        /*

     https://jsonplaceholder.typicode.com/posts/22 url’ine bir
    GET request yolladigimizda donen response’in
    status kodunun 200 ve
    response body’sinin asagida verilen ile ayni oldugunu test ediniz
      Response body : (expected data)
    {
        “userId”: 3,
        “id”: 22,
        “title”: “dolor sint quo a velit explicabo quia nam”,
        “body”: “eos qui et ipsum ipsam suscipit aut\nsed omnis non odio
        \nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse”
}
 */
        @Test
    public void test(){
            specJsonPlace.pathParams("pp1","posts","pp2",22);

            JSONObject expBody= TestDatasJPH.jphExpDataOlustur();

            Response response=given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");

            JsonPath resJP=response.jsonPath();

            assertEquals(200,response.getStatusCode());
            assertEquals(expBody.get("userId"),resJP.getInt("userId"));
            assertEquals(expBody.get("id"),resJP.getInt("id"));
            assertEquals(expBody.get("title"),resJP.getString("title"));
            assertEquals(expBody.get("body"),resJP.getString("body"));


        }






}
