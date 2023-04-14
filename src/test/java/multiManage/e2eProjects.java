package multiManage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;

public class e2eProjects {

    Map <String, Object > map;
    Response response;

    /**
     * This project is internal for the user where they can create multiple projects
     * based on their need
     * we test api endpoints to validate Project creation based on their profile workspace
     */

    // Step 1 Send a request to log in endpoint and save the Token
    @BeforeTest
    public String token(){
        RestAssured.baseURI = "https://api.octoperf.com";
        String path = "/public/users/login";

        Map<String, Object> map = new HashMap<>();
        map.put("username","tla.jiraone@gmail.com");
        map.put("password", "test12");

        return RestAssured.given()
                .queryParams(map)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(path)
                .then()
                .statusCode(SC_OK)
                .extract()
                .body()
                .jsonPath()
                .get("token");
    }

    // Step 2 Get all the workspaces from /member-of endpoint and select default
    @Test
    public void getMemberDefault(){
        // Make a request to endpoing and save response in Response Interface add Assertion for name = Default
        // Using Header -> given().header("Authorization", token()).....
        String path = "/workspaces/member-of";

        response = RestAssured.given()
                .header("Authorization", token())
                .when()
                .get(path)
                .then()
                .extract()
                .response();

        System.out.println(response.prettyPrint());
        System.out.println(response.jsonPath().getString("[6].name"));
        Assert.assertEquals(response.jsonPath().getString("[6].name"),"Default");
        // Add Assertion for Description, id and UserID
        Assert.assertEquals(response.jsonPath().getString("[6].id"),"nTGRTYQBeIYl0G_YNKQF");
        Assert.assertEquals(response.jsonPath().getString("[6].userId"),"1kt3tHgB6T29TqnSCje3");

        map = new HashMap<>();
        // id = nTGRTYQBeIYl0G_YNKQF
        // userId = 1kt3tHgB6T29TqnSCje3
        map.put("id",response.jsonPath().getString("[6].id"));
        map.put("userId",response.jsonPath().getString("[6].userId"));
    }

    @Test(dependsOnMethods = "getMemberDefault")
    public void createProject(){
        String path = "/design/projects";
        System.out.println(map.get("id"));
        System.out.println(map.get("userId"));

        String jsonPayload = "{\"id\":\"\",\"created\":\"2021-03-11T06:15:20.845Z\",\"lastModified\":\"2021-03-11T06:15:20.845Z\",\"userId\":\"" + map.get("userId") + "\",\"workspaceId\":\"" + map.get("id") + "\",\"name\":\"testing22\",\"description\":\"testing\",\"type\":\"DESIGN\",\"tags\":[]}";

        response = RestAssured.given()
//                .log().all()
                .header("Authorization", token())
                .header("Content-type", "application/json")
                .and()
                .body(jsonPayload)
                .when()
                .post(path)
                .then()
//                .log().all()
                .extract()
                .response();

        System.out.println(response.prettyPrint());
        System.out.println(response.jsonPath().getString("name"));
        Assert.assertEquals(response.jsonPath().getString("name"),"testing22");
        Assert.assertEquals(response.jsonPath().getString("description"),"testing");

        // add a map with variables id call it projectId
        map.put("projectId", response.jsonPath().getString("id"));
    }

    @Test(dependsOnMethods = {"getMemberDefault","createProject"})
    public void updateProject(){
        String updatePayload = "{\"created\":1615443320845,\"description\":\"testing\",\"id\":\"" + map.get("projectId") + "\",\"lastModified\":1629860121757,\"name\":\"testing Soto\",\"tags\":[],\"type\":\"DESIGN\",\"userId\":\""+map.get("userId")+"\",\"workspaceId\":\""+map.get("id")+"\"}";
        String path = "/design/projects/"+map.get("projectId");

        response = RestAssured.given()
                .header("authorization", token())
                .header("Content-type", "application/json")
                .and()
                .body(updatePayload)
                .when()
                .put(path)
                .then()
                .extract()
                .response();

        System.out.println(response.prettyPrint());
    }

    @Test (dependsOnMethods = {"getMemberDefault","createProject","updateProject"})
    public void deleteProject(){
        String deletePath = "/design/projects/"+ map.get("projectId");
        response = RestAssured.given()
                .log().all()
                .header("Authorization", token())
                .when()
                .delete(deletePath)
                .then()
                .extract()
                .response();
        Assert.assertEquals(response.statusCode(), 204);
    }
}



