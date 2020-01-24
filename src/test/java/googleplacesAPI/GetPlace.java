package googleplacesAPI;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBase.BaseClass;

public class GetPlace {

	@Test
	public void getPlace() {
		// https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Xoriant%20Pune&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyB9qQjFNlehW5ZXG4ebIHY1F2dGQUSaFOk
		RestAssured.baseURI = "https://maps.googleapis.com/maps/api";
		RestAssured.basePath = "/place/findplacefromtext/json";

		given().queryParam("input", "Xoriant").queryParam("inputtype", "textquery")
				.queryParam("fields", "photos,formatted_address,name,rating,opening_hours,geometry")
				.queryParam("key", "AIzaSyB9qQjFNlehW5ZXG4ebIHY1F2dGQUSaFOk")
				.when().get();

	}

}
