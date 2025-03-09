package tests;

import baseUrl.BaseUrlHerOkuApp;
import baseUrl.BaseUrlJsonPlace;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testDatas.TestDatasJPH;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P13_PUT_deSeralization extends BaseUrlJsonPlace {
 /*
            https://jsonplaceholder.typicode.com/posts/70 url'ine
            asagidaki body’e sahip bir PUT request yolladigimizda
            donen response’in response body’sinin
            asagida verilen ile ayni oldugunu test ediniz
            Request Body
            {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
            }

            Expected Data :
            {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
            }

     */
    @Test
    public void test01(){
        specJsonPlace.pathParams("pp1","posts","pp2","70");
        Map<String, Object> reqMapBody=TestDatasJPH.jphMapDataOlustur();

        Map<String,Object> expMapBody=TestDatasJPH.jphMapDataOlusturPli("Ahmet","Merhaba",10.0,70.0);

        Response response=given().contentType(ContentType.JSON).spec(specJsonPlace).when().body(reqMapBody).put("/{pp1}/{pp2}");

        Map<String,Object> resMAP=response.as(HashMap.class);
        assertEquals(expMapBody.get("title"),resMAP.get("title"));
        assertEquals(expMapBody.get("body"),resMAP.get("body"));
        assertEquals(expMapBody.get("userId"),resMAP.get("userId"));
        assertEquals(expMapBody.get("id"),resMAP.get("id"));




    }
}
