//Description:- check response data & response time & date verification for this API ''
//packages
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;
public class openweatherAPI {
    private Response response;
    String cityName = "London";
    String countryName ="GB";
    String description ="overcast clouds";
	 @BeforeTest
	 public void baseURL() {
	     String baseURL = "https://api.openweathermap.org/data/2.5/weather";
	     String apiKey = "c90de70f5c0cbdd5e9ae137e15e11f57"; 
	     File schemaFile = new File("src/test/resources/weather-schema.json");
	      response = RestAssured
	             .given()
	             .baseUri(baseURL)
	             .param("q", cityName)  
	             .param("appid", apiKey)
	             .when()
	             .get();
	           
	             response.then()
	                .assertThat()
	                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
	     }
	 @Test
	 
	 public void StatusCode() {
	     // Assert the status code is either 200 or 201 (OK)
	     assertTrue(response.getStatusCode() == 200  ,
	         "Expected 200 , but received: " + response.getStatusCode());
	
	     }
      @Test
	 
	  public void responseTime() {
      //responseTime should less than 300 ms for example from me 
	     long responseTime = response.getTime(); 
	     assertTrue(responseTime > 300, 
	             "Response time is greater than 300 ms. Actual response time: " + responseTime + " ms");
	     }
      @Test
 	 
 	  public void Value_fields() {
         
    	  response.then()
          .assertThat()
          .body("name",equalTo(cityName));
          
    	  response.then()
          .assertThat()
          .body("sys.country",equalTo(countryName));
    	  
    	  response.then()
          .assertThat()
          .body("weather[0].description",equalTo(description));
 	     }
      
   
  	 
 	

	 }

	   


