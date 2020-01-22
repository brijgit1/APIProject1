package gitHubAPITestPkg1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateARepo {
	
@Test
public void createRepos() {
	RestAssured.baseURI="https://api.github.com/user";
	given().auth().preemptive().basic("brijgit1", "Pidaura1*")
	.and()
	.headers("Content-Type", "application/json")
	.and()
	//.body(Payload.createRepoPayload())
	.when()
	.post("/repos").then().statusCode(201);
	
}

@Test(enabled=false)
public void getReposVerifyStatusCode() {
	RestAssured.baseURI="https://api.github.com/user";
	given().auth().preemptive().basic("brijgit1", "Pidaura1*")
	.queryParam("visibility", "all")
	.when()
	.get("/repos").then().statusCode(200);
}

@Test(enabled=false)
public void getReposVerifyStatusLine() {
	RestAssured.baseURI="https://api.github.com/user";
	given().auth().preemptive().basic("brijgit1", "Pidaura1*")
	.queryParam("visibility", "all")
	.when()
	.get("/repos").then().statusLine("HTTP/1.1 200 OK");
}

}
