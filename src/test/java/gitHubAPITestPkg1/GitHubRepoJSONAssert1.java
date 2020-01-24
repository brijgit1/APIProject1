package gitHubAPITestPkg1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testBase.BaseClass;

public class GitHubRepoJSONAssert1 extends BaseClass {

	public GitHubRepoJSONAssert1() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority = 1)
	public void getAllRepositoriesLogParams() throws IOException {
		String actualRespBody=given().auth().preemptive()
		.basic(prop.getProperty("unameGit"), prop.getProperty("pwdGit"))
		.when()
		.get().asString();
		
		String expectedGitHubRepoRespBody= new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"GitHubGetResponseBody.txt")));
		
		//Assertion using JSONAssert
		
		//JSONAssert.assertEquals(expectedGitHubRepoRespBody, actualRespBody, JSONCompareMode.LENIENT);
		
	}


}
