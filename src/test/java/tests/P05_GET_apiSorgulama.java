package tests;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P05_GET_apiSorgulama {
/*
    https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde donen Response’un,
 	status code’unun 200,
	ve content type’inin application/json,
	ve response body’sindeki
		"firstname“in,"Susan",
	ve "lastname“in, "Jackson",
	ve "totalprice“in,612,
	ve "depositpaid“in,false,
	ve "additionalneeds“in,"Breakfast"
oldugunu test edin
 */

    @Test
    public void test01(){
        String url="https://restful-booker.herokuapp.com/booking/10";


        Response response=given().when().get(url);
        response.then().assertThat().statusCode(200).contentType("application/json")
                                    .body("firstname", equalTo("Jim"),"lastname",equalTo("Ericsson"),
                                            "totalprice",equalTo(122),"depositpaid",equalTo(true),
                                            "additionalneeds",equalTo("Breakfast"));


    }

    //https://reqres.in
}
