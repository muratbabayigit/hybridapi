package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P06_JsonPathKullanimi {
 /*
            https://restful-booker.herokuapp.com/booking url’ine
            asagidaki body'ye sahip bir POST request gonderdigimizde

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

                     donen Response’un,
             status code’unun 200,
             content type’inin applicatio/json,
             response body’sindeki
                    "firstname“in,"Ahmet",
                    ve "lastname“in, "Bulut",
                    ve "totalprice“in,500,
                    ve "depositpaid“in,false,
                    ve "checkin" tarihinin 2021-06-01
                    ve "checkout" tarihinin 2021-06-10
                    ve "additionalneeds“in,"wi-fi"

              oldugunu test edin

  */
@Test
    public void test(){
    String url="https://restful-booker.herokuapp.com/booking";
    JSONObject innerData=new JSONObject();
    innerData.put( "checkin" , "2025-06-01");
    innerData.put( "checkout" , "2025-06-10");
    JSONObject reqBody=new JSONObject();
    reqBody.put("firstname","Murat");
    reqBody.put("lastname","Yiğit");
    reqBody.put("totalprice",500);
    reqBody.put("depositpaid",true);
    reqBody.put("additionalneeds","wi-fi");
    reqBody.put("bookingdates",innerData);

    Response response=given().contentType(ContentType.JSON).when().body(reqBody.toString()).post(url);
    //response.prettyPrint();


  // response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8")
  //         .body("booking.firstname", equalTo("Murat"),
  //                 "booking.lastname",equalTo("Yiğit"),
  //                 "booking.totalprice",equalTo(500),
  //                 "booking.depositpaid",equalTo(true),
  //                 "booking.bookingdates.checkin",equalTo("2025-06-01"),
  //                 "booking.bookingdates.checkout",equalTo("2025-06-10"));

    JsonPath resJP=response.jsonPath();

    assertEquals(200,response.getStatusCode());
    assertEquals("application/json; charset=utf-8",response.getContentType());
    assertEquals("Murat",resJP.get("booking.firstname"));
    assertEquals("Yiğit",resJP.get("booking.lastname"));
    assertEquals(true,resJP.get("booking.depositpaid"));
    assertEquals("wi-fi",resJP.get("booking.additionalneeds"));
    assertEquals("2025-06-10",resJP.get("booking.bookingdates.checkout"));
    int resTot=resJP.get("booking.totalprice");
    assertEquals(500,resTot );


}
}
