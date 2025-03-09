package tests;

import baseUrl.BaseUrlHerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testDatas.TestDatasHerOkuApp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P14_POST_deSeralization extends BaseUrlHerOkuApp {
    /*
        https://restful-booker.herokuapp.com/booking url’ine
        asagidaki body'ye sahip bir POST request gonderdigimizde
        donen response’un id haric asagidaki gibi oldugunu test edin.
                            Request body
                       {
                            "firstname" : "Ahmet",
                            "lastname" : “Bulut",
                            "totalprice" : 500,
                            "depositpaid" : false,
                            "bookingdates" : {
                                     "checkin" : "2021-06-01",
                                     "checkout" : "2021-06-10"
                                              },
                            "additionalneeds" : "wi-fi"
                        }
                            Response Body // expected data
                        {
                        "bookingid":24,
                        "booking":{
                            "firstname":"Ahmet",
                            "lastname":"Bulut",
                            "totalprice":500,
                            "depositpaid":false,
                            "bookingdates":{
                                "checkin":"2021-06-01",
                                "checkout":"2021-06-10"
                            ,
                            "additionalneeds":"wi-fi"
                        }
         */
    @Test
    public void test(){
        specRestfull.pathParam("pp1","booking");
        Map<String,Object> reqMAPBody= TestDatasHerOkuApp.mapDataOlustur();

        Map<String,Object> expMAPBody=TestDatasHerOkuApp.expDataOlustur();

        Response response=given().spec(specRestfull).contentType(ContentType.JSON).when().body(reqMAPBody).post("/{pp1}");

        assertEquals(TestDatasHerOkuApp.basariliStatusCode,response.getStatusCode());

        Map<String,Object> resMAP=response.as(HashMap.class);

        assertEquals(((Map)expMAPBody.get("booking")).get("firstname"),((Map)resMAP.get("booking")).get("firstname"));
        assertEquals(((Map)expMAPBody.get("booking")).get("lastname"),((Map)resMAP.get("booking")).get("lastname"));
        assertEquals(((Map)expMAPBody.get("booking")).get("totalprice"),((Map)resMAP.get("booking")).get("totalprice"));
        assertEquals(((Map)expMAPBody.get("booking")).get("depositpaid"),((Map)resMAP.get("booking")).get("depositpaid"));
        assertEquals(((Map)expMAPBody.get("booking")).get("additionalneeds"),((Map)resMAP.get("booking")).get("additionalneeds"));
        assertEquals(((Map)((Map)expMAPBody.get("booking")).get("bookingdates")).get("checkin"),((Map)((Map)resMAP.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(((Map)((Map)expMAPBody.get("booking")).get("bookingdates")).get("checkout"),((Map)((Map)resMAP.get("booking")).get("bookingdates")).get("checkout"));
    }

}
