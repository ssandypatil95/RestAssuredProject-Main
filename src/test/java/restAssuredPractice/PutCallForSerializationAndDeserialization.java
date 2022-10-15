package restAssuredPractice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PutCallForSerializationAndDeserialization {
	
	@Test
	public void putRequest() {
		
		POJORequestBody ReqBody=new POJORequestBody();
		ReqBody.setName("morpheus");
		ReqBody.setJob("zion resident");
	
	 RestAssured.baseURI = "https://reqres.in/";
	  
	     POJORequestBody Res = given().log().all().header("Content-Type","application/json").body("ReqBody").expect().defaultParser(Parser.JSON).
	  when().put("/api/users/2").
	  then().log().all().assertThat().statusCode(200).
	         body("job",equalTo("zion resident")).extract().response().as(POJORequestBody.class);
	  
	  System.out.println(Res.getJob());
	
	}
}
