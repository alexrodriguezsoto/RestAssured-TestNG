package Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class paramethers {

    /**
     * Send a request to log in using the url as parameters for your username and password
     */
    @Test
    public void fullURLwithQueryParams(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    /**
     * Log in using Map as query params
     */

    @Test
    public void logInWithMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username","tla.jiraone@gmail.com");
        map.put("password", "test12");

        RestAssured.given()
                .log()
                .all()
                .queryParams(map)
                .when()
                .post("https://api.octoperf.com/public/users/login")
                .then()
                .statusCode(200);
    }

    /**
     * Log in using query parameters and verify status code
     */
    @Test
    public void logInwithQueryParams(){
        RestAssured.given()
                .log()
                .all()
                .queryParams("username", "tla.jiraone@gmail.com")
                .queryParams("password", "test12")
                .when()
                .post("https://api.octoperf.com/public/users/login")
                .then()
                .log().body()
                .statusCode(200);
    }

    /**
     * Log in using multiple params
     */
    /**
     * Log in using query parameters and verify status code
     */
    @Test
    public void logInwithMultipleQueryParams(){
        RestAssured.baseURI = "https://api.octoperf.com";
        String path = "/public/users/login";
        RestAssured.given()
                .log()
                .all()
                .queryParams("username", "tla.jiraone@gmail.com", "password","test12")
                .when()
                .post(path)
                .then()
                .log().body()
                .statusCode(200);
    }
}
