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

public class GitHubRepoLoggings extends BaseClass {

	public GitHubRepoLoggings() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority = 1)
	public void getAllRepositoriesLogParams() {
		given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.log()
		.parameters()
		.when()
		.get();

	}
	
	@Test(priority = 2)
	public void getAllRepositoriesLogHeaders() {
		given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.log()
		.headers()
		.when()
		.get().then().statusCode(200);

	}
	
	@Test(priority = 3)
	public void getAllRepositoriesLogRequestBody() {
		given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.log()
		.body()
		.when()
		.get().then().statusCode(200);
	}
	
	@Test(priority = 4)
	public void getAllRepositoriesLogHeadersifFails() {
		given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.log()
		.ifValidationFails()
		.when()
		.get().then().statusCode(201);//As validation here will fail hence request will get printed

	}
	
	@Test(priority = 4)
	public void getAllRepositoriesLogHeadersifFails1() {
		given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.log()
		.ifValidationFails()
		.when()
		.get().then().statusCode(200);//As validation here will PASS hence request will NOT get printed

	}
	
	@Test(priority = 5)
	public void getAllRepositoriesLogResponseHeaders() {
		given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.when()
		.get()
		.then()
		.log()
		.headers();
		//It will print the response
	}
	
	@Test(priority = 5)
	public void getAllRepositoriesLogResponseBody() {
		given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.when()
		.get()
		.then()
		.log()
		.body();
		//It will print the response
	}
	
	@Test(priority = 6)
	public void getAllRepositoriesLogResponseBodyInFailure() {
		given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.when()
		.get()
		.then()
		.log()
		.ifValidationFails()
		.assertThat().statusCode(20011);//It will log Response Body
		//It will print the response
	}
	
	@Test(priority = 7)
	public void getAllRepositoriesLogResponseBodyInFailure1() {
		given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.when()
		.get()
		.then()
		.log()
		.ifValidationFails()
		.assertThat().statusCode(200);//It will log Response Body
		//It will print the response
	}


}
