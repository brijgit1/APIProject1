package testBase;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseClass {
	public static String name;

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://api.github.com";
		RestAssured.basePath = "/user/repos";
		name = "Repo" + System.currentTimeMillis();
	}

}
