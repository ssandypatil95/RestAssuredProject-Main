package restAssuredPractice;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
// import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Request1 {
	
	@Test
	public void getrequest() {
		
		RestAssured.baseURI = "https://reqres.in/";
		
		String GR=given().log().all().header("Content-Type","application/json").
		when().get("api/users?page=2").
		then().log().all().assertThat().statusCode(200).
		       body("page",equalTo(2)).extract().response().asString();
		
		System.out.println(GR);
		
		JsonPath j=new JsonPath(GR);
//		String n = j.getString("total");
		String n = j.getString("data[1].id");
		System.out.println(n);
		
		Assert.assertEquals(n, "8");
	}
//-----------------------------------------------------------------------------------	
	
	   @Test
	  public void postrequest() {
		  
		  RestAssured.baseURI="https://reqres.in/";
		  
		  String PR = given().log().all().header("Content-Type","application/json").body("{\r\n"
		  		+ "    \"name\": \"morpheus\",\r\n"
		  		+ "    \"job\": \"leader\"\r\n"
		  		+ "}").
		  when().post("/api/users").
		  then().log().all().assertThat().statusCode(201).
		         body("name", equalTo("morpheus")).extract().response().asString();
		  
		  System.out.println(PR);
	  }
//-----------------------------------------------------------------------------------	  
	  
	  @Test
	  public void putrequest() {
		  
		  RestAssured.baseURI="https://reqres.in/";
		  
		  String putR = given().log().all().header("Content-Type","application/json").body("{\r\n"
		  		+ "    \"name\": \"morpheus\",\r\n"
		  		+ "    \"job\": \"zion resident\"\r\n"
		  		+ "}").
		  when().put("/api/users/2").
		  then().log().all().assertThat().statusCode(200).
		         body("job",equalTo("zion resident")).extract().response().asString();
		  
		  System.out.println(putR);
		   }
//--------------------------------------------------------------------------------------
	  
	  @Test
	   public void patchrequest() {
		   
		  String patchR = RestAssured.baseURI="https://reqres.in/";
		   
		   given().log().all().header("Content-Type","application/json").body("{\r\n"
		   		+ "    \"name\": \"morpheus\",\r\n"
		   		+ "    \"job\": \"zion resident\"\r\n"
		   		+ "}").
		   when().patch("/api/users/2").
		   then().log().all().assertThat().statusCode(200).
		          body("job", equalTo("zion resident")).extract().response().asString();
		   
		   System.out.println(patchR);
	   }
//----------------------------------------------------------------------------------------
	  @Test
	  public void deleterequest() {
		  
		  String DR = RestAssured.baseURI="https://reqres.in/";
		  
		  given().log().all().header("Content-Type","application/json").
		  when().delete("/api/users/2").
		  then().log().all().assertThat().statusCode(204).extract().response().asString();
		  
		  System.out.println(DR);
		  
	  }
	  
}
