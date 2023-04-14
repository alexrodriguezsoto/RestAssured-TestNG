# RestAssured, JAVA, TestNG Unit Tests

Simple Make a request Using RestAssured Library 

## pom.xml should have dependencies

RestAssured [Dependency](https://mvnrepository.com/artifact/io.rest-assured/rest-assured)

TestNG [Dependency](https://mvnrepository.com/artifact/org.testng/testng)



## Simple Example 

```java
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class MyAPITest {
    @Test
    public void testGetUser() {
        RestAssured.baseURI = "https://myapi.com";
        
        Response response = given()
                .when()
                .get("/users/12345")
                .then()
                .extract()
                .response();

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getString("username"), "johndoe");
        assertEquals(response.jsonPath().getInt("age"), 35);
    }
}
```

## Also Visit 

For more content documentation visit my Medium page and dont forget to subscribe

click [here](https://medium.com/@alex_rodriguez_soto)

