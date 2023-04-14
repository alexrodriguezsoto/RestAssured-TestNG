package Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class differentTypeOfAuthentications {

    @Test // Using api key as part of the request query params
    public void usingAPIKEY() {
        RestAssured.given()
                .log()
                .all()
                .queryParam("t", "Kung Fury")
                .queryParam("apikey", "a9faab96")
                .when()
                .get("http://www.omdbapi.com/")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void BasicAuthentication(){
        RestAssured.given()
                .log()
                .all()
                .auth()
                .basic("tomsmith","SuperSecretPassword!")
                .when()
                .post("http://the-internet.herokuapp.com/authenticate")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200);
    }

    @Test
    public void xmlResponse(){
        RestAssured.get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/")
                .then().log().all()
                .assertThat().statusCode(200)
                .and()
                .contentType(ContentType.XML);
    }

    @Test // Using api key as part of the request query params
    public void usingAPdaIKEY() {
        RestAssured.given()
                .log()
                .all()
                .queryParam("t", "Kung Fury")
                .queryParam("apikey", "a9faab96")
                .when()
                .get("http://www.omdbapi.com/")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void BasicAuthedntication(){
        RestAssured.given()
                .log()
                .all()
                .auth()
                .basic("tomsmith","SuperSecretPassword!")
                .when()
                .post("http://the-internet.herokuapp.com/authenticate")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200);
    }

    @Test
    public void xmlRespdonse(){
        RestAssured.get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/")
                .then().log().all()
                .assertThat().statusCode(200)
                .and()
                .contentType(ContentType.XML);
    }

    @Test // Using api key as part of the request query params
    public void usingAPdIKEY() {
        RestAssured.given()
                .log()
                .all()
                .queryParam("t", "Kung Fury")
                .queryParam("apikey", "a9faab96")
                .when()
                .get("http://www.omdbapi.com/")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void BasicAuthenticaation(){
        RestAssured.given()
                .log()
                .all()
                .auth()
                .basic("tomsmith","SuperSecretPassword!")
                .when()
                .post("http://the-internet.herokuapp.com/authenticate")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200);
    }

    @Test
    public void xmlResponsea(){
        RestAssured.get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/")
                .then().log().all()
                .assertThat().statusCode(200)
                .and()
                .contentType(ContentType.XML);
    }

}
