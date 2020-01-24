package gitHubAPITestPkg1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import testBase.BaseClass;

public class GitHubAPITest extends BaseClass {

	public GitHubAPITest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority = 1)
	public void getAllRepositories() {
		Response resp=given().auth().preemptive().basic(prop.getProperty("ghUname"), prop.getProperty("ghPwd")).when()
		.get();
		ResponseBody respBody=resp.getBody();
		String respInString=respBody.asString();
		//System.out.println(respInString);
		Assert.assertTrue(respInString.contains("owner"));
		
		JsonPath jp=resp.jsonPath();
		//get list of id keys
		Object obj=jp.get("id");
		//System.out.println(obj);
		Assert.assertTrue(obj.toString().contains("235352540"));
		
		//Get count of total repositories
		List<String> listofRepos=jp.getList("$");
		System.out.println("Total Repos: "+listofRepos.size());
		
		//Print names of all repositories
		List<String> listofReposNames=jp.getList("name");
		for(String i:listofReposNames) {
			System.out.println(i);
		}
		//Assert that first name of Repository in List is -name-
		Assert.assertTrue(listofReposNames.get(0).contains("-name-"));
		
		//Get list of all login ids
		List<String> logins=jp.getList("owner.login");
		System.out.println(logins);
		
		//Get all permissions
				List<String> permiss=jp.getList("owner.permissions");
				System.out.println(permiss.size());
				System.out.println(permiss);
	}

	@Test(priority = 2)
	public void getRepoBody() {
		given().auth().preemptive().basic(prop.getProperty("ghUname"), prop.getProperty("ghPwd")).when().get().then().header("Content-Type",
				"application/json; charset=utf-8");

	}

	@Test(priority = 3)
	public void getRepoBodyString() {
		given().auth().preemptive().basic(prop.getProperty("ghUname"), prop.getProperty("ghPwd")).when().get()
				.then().statusCode(200);
		/*
		 * String respInSTring=resp.asString(); JsonPath jp=new JsonPath(respInSTring);
		 * System.out.println(respInSTring); jp.get("$.[0].id").equals(235352540);
		 */

	}

	@Test(priority = 4)
	public void createARepo() {
		given().auth().preemptive().basic(prop.getProperty("ghUname"), prop.getProperty("ghPwd"))
		.body(Payload.createRepoPayload(name)).when().post()
				.then().statusCode(201);
		System.out.println("Repo created with name: " + name);
	}

	@Test(priority = 5)
	public void deleateARepo() {
		given().contentType(ContentType.JSON)
		.pathParam("owner", prop.getProperty("owner"))
		.pathParam("repo", name).auth().preemptive()
				.basic(prop.getProperty("ghUname"), prop.getProperty("ghPwd")).when().delete(prop.getProperty("delRepoURI")).then()
				.statusCode(204);
		System.out.println("Repo deleted with name: " + name);
	}

}
