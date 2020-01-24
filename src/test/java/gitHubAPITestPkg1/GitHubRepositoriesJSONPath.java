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
import testBase.BaseClass;

public class GitHubRepositoriesJSONPath extends BaseClass {

	public GitHubRepositoriesJSONPath() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority = 1)
	public void getAllRepositories() {
		//Get all repositories having owner as 'brijgit1'
		given().auth().preemptive().basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit")).when()
				.get()
				.then()
				.extract();
				//.jsonPath("owner.findAll{it.description=='This is a test repository created by RestAssured'}");

		// Get total number of Repositories

	}

	@Test(priority = 1)
	public void getTotalCountOfRepositories() {
		Response resp = given().auth().preemptive().basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
				.when().get();
		String respInString = resp.asString();
		System.out.println(respInString);
		Assert.assertTrue(respInString.contains("id"));

		JsonPath jp = resp.jsonPath();
		// Get total number of Repositories
		/*
		 * List<String> listOfRepos=jp.getList("$");
		 * System.out.println(listOfRepos.size());
		 */

		// Print all repositories names
		List<String> nameOfRepos = jp.getList("name");
		System.out.println("Names of Repo using getList(): " + nameOfRepos);

		// Iterate and print all names of Repositories
		for (String i : nameOfRepos) {
			System.out.println(i);
		}

		// Print name of second repository
		System.out.println("Name of Second Repo: " + nameOfRepos.get(1));

		// Print all repositories names using getString
		String names = jp.getString("name");
		System.out.println("Names are: " + names);
		System.out.println("Second repository name: " + jp.getString("name[1]"));

	}

	@Test(priority = 2)
	public void getRepoBody() {
		given().auth().preemptive().basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit")).when().get().then()
				.header("Content-Type", "application/json; charset=utf-8");

	}

	@Test(priority = 3)
	public void getRepoBodyString() {
		String respBody = given().auth().preemptive().basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
				.when().get().getBody().asString();

	}

	@Test(priority = 4)
	public void createARepo() {
		given().auth().preemptive().basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
				.body(Payload.createRepoPayload(name)).when().post().then().statusCode(201);
		System.out.println("Repo created with name: " + name);
	}

	@Test(priority = 5)
	public void deleateARepo() {
		given().contentType(ContentType.JSON).pathParam("owner", "brijgit1").pathParam("repo", name).auth().preemptive()
				.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit")).when()
				.delete(prop.getProperty("deleRepoURI")).then().statusCode(204);
		System.out.println("Repo deleted with name: " + name);
	}

	@Test(priority = 6)
	public void getPlace() {
		Response resp = given().queryParam("input", "Xoriant%20Pune").queryParam("inputtype", "textquery")
				.queryParam("fields", "photos,formatted_address,name,rating,opening_hours,geometry")
				.queryParam("key", "AIzaSyB9qQjFNlehW5ZXG4ebIHY1F2dGQUSaFOk").when()
				.get(prop.getProperty("googlePlaces"));

		String respInStr = resp.getBody().asString();
		JsonPath jp = resp.jsonPath();
		System.out.println(respInStr);

	}

}
