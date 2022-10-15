package restAssuredPractice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Autontication {
	
	public void basicAuth() {
		
		RestAssured.baseURI=("https://postman-echo.com/");
		
		given().log().all().headers("Content-Type","application/json").auth().preemptive().basic("postman","password").
		when().get("/basic-auth").
		then().log().all().assertThat().statusCode(200);
		
	}
}
