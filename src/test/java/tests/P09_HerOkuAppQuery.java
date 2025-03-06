package tests;

import baseUrl.BaseUrlHerOkuApp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P09_HerOkuAppQuery extends BaseUrlHerOkuApp {
    /*

            https://restful-booker.herokuapp.com/booking endpointine
            gerekli Query parametrelerini yazarak
                “firstname” degeri “Susan”
                ve “lastname” degeri “Jones” olan
            rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
            donen response’un
                status code’unun 200 oldugunu
                ve “Susan Jones” ismine sahip en az bir booking oldugunu test edin
         */
    @Test
    public void test(){
        specRestfull.pathParam("pp1","booking").queryParams("firstname","Susan","lastname","Jones");

        Response response=given().spec(specRestfull).when().get("/{pp1}");

        response.then().assertThat().statusCode(200).body("size()", Matchers.greaterThanOrEqualTo(1));
    }
}
