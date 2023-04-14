package API_test.Day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

/**
 * Rest Assured is a library that we use to test the REST endpoints. We can add this to the POM.xml
 * and works with BDD as well.
 *
 * There is same HTTP Methods that we use such as GET, POST, PUT, DELETE
 * with the difference that when writting the steps for RestAssure we use methods such as given(), Then().. etc
 *
 * Most of this methods com from Rest-Assured library when we write tests:
 * To make a request we use:
 *  give() -> use to prepare the request
 *  when() and get() -> is used to send the request
 *  then() -> used to verify the request
 *
 * To verify the response:
 *  prettyPrint() -> see the response in json format
 *  prettyPeek() -> dumps all the results including request header, response headers, response body in the terminal logs.
 *  log() -> log (prints) all the response in the terminal log.
 *  asString() -> its used to print in a string format.
 *  contentType() -> When used for POST request we use contentType to verify the response Headers
 *  accept() -> used to verify body response header when we send a GET request.
 *
 *  In order to make a request we need to identify the baseURI
 *  baseURI -> Saves the base URL for all the requests
 *      Example: public static String baseURI = "https://api.octoperf.com";
 *
 *  In order to make requests we also provide the path (endpoint) to the the specific base resource (url)
 *      Example: private String path = "public/users/login";
 *
 *  Therefore the full url is = https://api.octoperf.com/public/users/login
 *
 *  What is an endpoint?
 *      An endpoint is aunique URL that represents an object or collections of objects
 *      for instance https://www.google.com/search?source=cars
 *
 * Request logging --> used after given()
 * - given().log().all() --> logs all request specification details including parameters, headers and body
 * - given().log().params() --> log only the parameters of the request
 * - given().log().body() --> log only the request body
 * - given().log().headers() --> log only the request headers
 * - given().log().cookies() --> log only the request cookies
 * - given().log().method() --> log only the request method
 * - given().log().path() --> log only the request path
 * Response Logging --> used after then()
 * - get(“/x”.then().log().body
 * - get(“/x”.then().log().ifError
 * - get(“/x”.then().log().all()
 * - get(“/x”.then().log().statusLine() --> log only the status line
 * - get(“/x”.then().log().header() --> log only the response headers
 * - get(“/x”.then().log().cookies() --> log only the response cookies
 * - get(“/x”.then().log().ifStatusCodeisEqualTo(200) --> log only if the status code is equal to 200
 *
 *
 */

public class _01_IntroductionToRestAssured {

    /**
     * Send a POST request to login and user one of the methods call prettyPeek()
     * uri: https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12
     */
    @Test
    public void printPrettyPeek(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .prettyPeek();
    }

    /**
     * Send a POST request to login and user one of the methods call prettyPrint()
     * uri: https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12
     */
    @Test
    public void printPrettyPrint(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .prettyPrint();
    }

    /*
     * When verifying the status codes you must pay attention to the following error codes from the response
     * 1xx --> Information
     * 2xx --> success (200 -> ok, Created -> 201, No Content -> 204)
     * 3xx --> redirect
     * 4xx --> client error (400 -> bad request, 401 -> Unauthorized, 403 -> Forbidden, 404 -> not found)
     * 5xx --> Server errors (500 -> Internal Server error, 502 -> bad gateway, 503 -> Service unavailable)
     */

    /**
     * Send a POST Request for log in and validate status code equals = 200
     */
    @Test
    public void verifyStatusCode(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .then()
                .assertThat().statusCode(200);
    }

    /**
     * Validate Json Content type matches = application/json for login Endpoint
     */

    @Test
    public void verifyContentType(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .then().assertThat().contentType(ContentType.JSON);
    }

    /**
     * Validate Content Type and Status code in a unit Test
     */

    @Test
    public void verifyStatusCodeAndContentType(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .then().assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    /**
     * add logs to verify uri for log in endpoint
     */
    @Test
    public void uriLogs(){
        RestAssured.given()
                .log()
                .uri()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .then()
                .assertThat()
                .statusCode(200);
    }

    /**
     * add logs to verify body for log in endpoint
     */
    @Test
    public void bodyLogs(){
        RestAssured.given()
                .log()
                .body()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .then()
                .assertThat()
                .statusCode(200);
    }
    /**
     * add logs to verify all for log in endpoint
     */
    @Test
    public void verifyAllLogs(){
        RestAssured.given()
                .log()
                .all()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .then()
                .assertThat()
                .statusCode(200);
    }

    /**
     * get all the logs for the response body
     */
    @Test
    public void responseBodylogs(){
        RestAssured.given()
                .log()
                .all()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=tla.jiraone@gmail.com&password=test12")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }


}
