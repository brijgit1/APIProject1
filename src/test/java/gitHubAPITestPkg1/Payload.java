package gitHubAPITestPkg1;

import java.util.Random;

import org.testng.annotations.Test;

public class Payload {
	
public static String createRepoPayload(String name) {
	String str="{\r\n" + 
			"  \"name\": \"$name\",\r\n" + 
			"  \"description\": \"This is a test repository created by RestAssured\"\r\n" + 
			"}";
	
	return str.replace("$name", name);
}


}
