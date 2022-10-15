package restAssuredPractice;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


public class TokanizationAndAuthorization {
	
	@Test
	public String tokanGeneration() {
		
		RestAssured.baseURI = "https://bookstore.toolsqa.com/";
		
		String r = given().log().all().header("Content-Type","application/json").body("{\r\n"
				+ "\"userName\": \"VELOCITYS\"\r\n"
				+ "\"password\": \"Velocity@1234567890\"\r\n"
				+ "}").
		when().post("/Account/v1/GenerateToken").
		then().log().all().assertThat().statusCode(201).body("status", equalTo("Success")).extract().response().asString();
		
		JsonPath j= new JsonPath(r);
		String t = j.getString("token");
		
		System.out.println(t);
		return t;

	}
	
	@Test
	public void recordCreation() {
		
        RestAssured.baseURI = "https://bookstore.toolsqa.com/";
		
		TokanizationAndAuthorization ta = new TokanizationAndAuthorization();
//		System.out.println(TA.generateToken());
		
		given().log().all().header("Content-Type","application/json").header("Authorization","Barrer "+ta.tokanGeneration()).body("{\r\n"
				+ "  \"userId\": \"2848814e-3558-4151-bbe1-0f5f5b29b1da\",\r\n"
				+ "  \"collectionOfIsbns\": [\r\n"
				+ "    {\r\n"
				+ "      \"isbn\": \"9781449331818\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}").
		when().post("/Account/va/Books").
		then().log().all().assertThat().statusCode(201).body("Books",notNullValue()).extract().response().asString();
		
		
		System.out.println("------------------------------");
		

	}
}
