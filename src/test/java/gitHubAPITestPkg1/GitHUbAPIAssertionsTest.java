package gitHubAPITestPkg1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBase.BaseClass;

public class GitHUbAPIAssertionsTest extends BaseClass {

	@Test(priority = 1)
	public void getAllRepositories() {
		String respBody=given().auth().preemptive().basic("brijgit1", "Pidaura1*").when()
		.get().getBody().asString();
		String str1=respBody.replace("[", "");
		String str2=str1.replace("]", "");
		System.out.println(str2);
		JSONObject jo=new JSONObject(str2);

	}

	@Test(priority = 2)
	public void getRepoBody() {
		given().auth().preemptive().basic("brijgit1", "Pidaura1*").when().get().then().header("Content-Type",
				"application/json; charset=utf-8");

	}

	@Test(priority = 3)
	public void getRepoBodyString() {
		String respBody = given().auth().preemptive().basic("brijgit1", "Pidaura1*").when().get().getBody().asString();

	}

	@Test(priority = 4)
	public void createARepo() {
		given().auth().preemptive().basic("brijgit1", "Pidaura1*").body(Payload.createRepoPayload(name)).when().post()
				.then().statusCode(201);
		System.out.println("Repo created with name: " + name);
	}

	@Test(priority = 5)
	public void deleateARepo() {
		given().contentType(ContentType.JSON).pathParam("owner", "brijgit1").pathParam("repo", name).auth().preemptive()
				.basic("brijgit1", "Pidaura1*").when().delete("https://api.github.com/repos/{owner}/{repo}").then()
				.statusCode(204);
		System.out.println("Repo deleted with name: " + name);
	}

}
