package testBase;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseClass2 {
	public static String name;
	public Properties prop;

	public BaseClass2() throws IOException {
		FileInputStream fis=new FileInputStream("./src/main/resources/configuration/config.properties");
		prop=new Properties();
		prop.load(fis);
		RestAssured.baseURI = "https://api.github.com";
		RestAssured.basePath = "/user/repos";
		name = "Repo" + System.currentTimeMillis();
	}

}
