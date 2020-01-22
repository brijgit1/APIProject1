package gitHubAPITestPkg1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GETAllReposOfaUser {
	
@Test(enabled=false)
public void getRepos() {
	RestAssured.baseURI="https://api.github.com/user";
	given().auth().preemptive().basic("brijgit1", "Pidaura1*")
	.when()
	.get("/repos").then().log().all();
	
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

@Test
public void getRespUsingCURL() {
	given()
	.when()
	.get("https://api.github.com/users/octocat/orgs")
	.then()
	.log().all();
}

}
