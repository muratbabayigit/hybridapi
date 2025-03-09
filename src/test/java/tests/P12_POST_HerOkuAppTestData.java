package tests;

import baseUrl.BaseUrlHerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testDatas.TestDatasHerOkuApp;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P12_POST_HerOkuAppTestData extends BaseUrlHerOkuApp {
    /*
           https://restful-booker.herokuapp.com/booking url’ine asagidaki body’ye sahip
            bir POST request gonderdigimizde donen response’un
            id haric asagidaki gibi oldugunu test edin.

            Request body
                   {
                       "firstname" : "Ahmet",
                       "lastname" : "Bulut",
                       "totalprice" : 500,
                       “depositpaid" : false,
                       "bookingdates" : {
                           "checkin" : "2021-06-01",
                           "checkout" : "2021-06-10"
                       },
                       "additionalneeds" : "wi-fi"
                   }
            Response Body
             {
                "bookingid": 24,
                "booking":   {
                       "firstname" : "Ahmet",
                       "lastname" : "Bulut",
                       "totalprice" : 500,
                       “depositpaid" : false,
                       "bookingdates" : {
                           "checkin" : "2021-06-01",
                           "checkout" : "2021-06-10"
                       },
                       "additionalneeds" : "wi-fi"
                   }
            }
             */
    @Test
    public void test2(){
        specRestfull.pathParam("pp1","booking");
        JSONObject reqBody= TestDatasHerOkuApp.reqBodyOlustur();

        //JSONObject expData=TestDatasHerOkuApp.expBodyOlustur();
        JSONObject expBody=new JSONObject();
        expBody.put("bookingid",129);
        expBody.put("booking",reqBody);

        Response response=given().spec(specRestfull).contentType(ContentType.JSON).when().body(reqBody.toString()).post("/{pp1}");

        JsonPath resJP=response.jsonPath();
        assertEquals(expBody.getJSONObject("booking").get("firstname"),resJP.get("booking.firstname"));
        assertEquals(expBody.getJSONObject("booking").get("lastname"),resJP.get("booking.lastname"));
        assertEquals(expBody.getJSONObject("booking").get("totalprice"),resJP.get("booking.totalprice"));
        assertEquals(expBody.getJSONObject("booking").get("depositpaid"),resJP.get("booking.depositpaid"));
        assertEquals(expBody.getJSONObject("booking").get("additionalneeds"),resJP.get("booking.additionalneeds"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),resJP.get("booking.bookingdates.checkin"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),resJP.get("booking.bookingdates.checkout"));

    }
}
