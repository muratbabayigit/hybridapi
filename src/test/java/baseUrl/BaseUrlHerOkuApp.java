package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class BaseUrlHerOkuApp {


        protected RequestSpecification specRestfull;
        @BeforeEach
        public void setup(){
            specRestfull=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

        }
    }

