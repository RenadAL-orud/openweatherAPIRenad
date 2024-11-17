//Description:- check response data & response time & date verification for this API ''
//packages
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bsh.TargetError;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.equalTo;
import java.io.File;
import java.util.logging.ErrorManager;
/**
 * 
 */
public class openweatherAPI {
    private Response response;
    public   String cityName = "";
    public  String  countryName ="";
     public String  description ="";
     public int  id =0;
     public String  main ="";
     public String icon ="";
     public float lon=0;
     public float lat= 0;
     public String base="";
     public float temp= 0;
     public float  feels_like=0;
     public float  temp_min=0;
     public float  temp_max=0;
     public int  pressure=0;
     public int  humidity=0;
     public int  sea_level=0;
     public int  grnd_level=0;
     public int  visibility=0;
     public float speed=0;
     public int deg=0;
     public int all=0;
     public int dt=0;
     public  int timezone=0;
     public int idF= 0;
     public String name ="";
     public int cod=0;
     public int type=0;
     public int idsys=0;
     public int sunrise=0;
     public int sunset=0;
	 public void baseURL() {
	     String baseURL = "https://api.openweathermap.org/data/2.5/weather";
	     String apiKey = "c90de70f5c0cbdd5e9ae137e15e11f57"; 
	   
	      response = RestAssured
	             .given()
	             .baseUri(baseURL)
	             .param("q", "London")  
	             .param("appid", apiKey)
	             .when()
	             .get();
	 }
	            
	      public void response_value() {
	             JsonPath jsonPath = response.jsonPath();
	              lon =jsonPath.getFloat("coord.lon");
	              lat=jsonPath.getFloat("coord.lat");
	             // System.out.print(lon);
	    		 description =jsonPath.getString("weather[0].description");
	    		 countryName =jsonPath.getString("sys.country");
	    		 cityName=jsonPath.getString("name");
	    		 id=jsonPath.getInt("weather[0].id");
	    		 main=jsonPath.getString("weather[0].main");
                 icon =jsonPath.getString("weather[0].icon");
                 temp=jsonPath.getFloat("main.temp");
                 feels_like=jsonPath.getFloat("main.feels_like");
                 temp_min=jsonPath.getFloat("main.temp_min");
                 temp_max=jsonPath.getFloat("main.temp_max");
                 pressure=jsonPath.getInt("main.pressure");
                 humidity=jsonPath.getInt("main.humidity");
                 sea_level=jsonPath.getInt("main.sea_level");
                 grnd_level=jsonPath.getInt("main.grnd_level");
                 visibility=jsonPath.getInt("visibility");
                 speed=jsonPath.getFloat("wind.speed");
                 deg =jsonPath.getInt("wind.deg");
                 all=jsonPath.getInt("clouds.all");
                 dt=jsonPath.getInt("dt");
                 timezone=jsonPath.getInt("timezone");
                 idF= jsonPath.getInt("id");
                 name=jsonPath.getString("name");
                 cod=  jsonPath.getInt("cod");
                 type=jsonPath.getInt("sys.type");
                 idsys=jsonPath.getInt("sys.id");
                 sunrise=jsonPath.getInt("sys.sunrise");
                 sunset=jsonPath.getInt("sys.sunset");
                

        
        
               
               
              
                 
	     }
	
	 public void schemaFile() {
		  File schemaFile = new File("src/schema/schema.json");
		  try {
		  response.then()
          .assertThat()
          .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
		  System.out.print(" the  validation  Schema  working \n ");
		  }
		  catch (Exception e) {
			// TODO: handle exception
			  System.out.print(" the  validation  Schema not  working \n  "+e.getMessage());
		}
	 }
	
	 public void StatusCode() {
		 try {
	     // Assert the status code is either 200 or 201 (OK)
	     assertTrue(response.getStatusCode() == 200  ,
	         "Expected 200 , but received: " + response.getStatusCode());
	     System.out.print(" StatusCode  is 200  \n ");
		 }
		 catch (Exception e) {
			// TODO: handle exception
		     System.out.print(" StatusCode  is not  200 \n  ");
		}
	     }
	  public void responseTime() {
		  try {
      //responseTime should less than 300 ms for example from me 
	     long responseTime = response.getTime(); 
	     assertTrue(responseTime > 300, 
	             "Response time is greater than 300 ms. Actual response time: " + responseTime + " ms");
	     System.out.print(" Response time is greater than 300 ms. Actual response time \n  ");
	     }
		  catch (Exception e) {
			// TODO: handle exception
			  System.out.print(" Response time is less than 300 ms. Actual response time: \n ");
		}
	  }
	//response data check 
	   public void coord() {
		   try {
		   response.then()
	          .assertThat()
	          .body("coord.lon",equalTo(lon));
	    	  System.out.print(" the lon is  "+lon+"\n");
		   
	   }
	   catch (Exception e) {
				// TODO: handle exception
	        	  System.out.print(" the lon is not correct   "+"\n");
	        	  System.out.print(lon);
			}
		   try {
			   response.then()
		          .assertThat()
		          .body("coord.lat",equalTo(lat));
		    	  System.out.print(" the lat is  "+lat+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the lat is not correct   "+"\n");
				}
		   
		   
}
	   public void  base()
	   
	   {
		   try {
			   response.then()
		          .assertThat()
		          .body("base",equalTo(base));
		    	  System.out.print(" the base is  "+base+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the base is not correct   "+"\n");
				}
		   
	   }
	   public void  main()
	   {
		   try {
			   response.then()
		          .assertThat()
		          .body("main.temp",equalTo(temp));
		    	  System.out.print(" the temp is  "+temp+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the temp is not correct   "+"\n");
				} 
		 
		   try {
			   response.then()
		          .assertThat()
		          .body("main.feels_like",equalTo(feels_like));
		    	  System.out.print(" the feels_like is  "+feels_like+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the feels_like is not correct   "+"\n");
				} 
		  
		   try {
			   response.then()
		          .assertThat()
		          .body("main.temp_min",equalTo(temp_min));
		    	  System.out.print(" the temp is  "+temp_min+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the temp_min is not correct   "+"\n");
				}
		   
		   try {
			   response.then()
		          .assertThat()
		          .body("main.temp_max",equalTo(temp_max));
		    	  System.out.print(" the temp is  "+temp_max+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the temp_max is not correct   "+"\n");
				}
		   try {
			   response.then()
		          .assertThat()
		          .body("main.pressure",equalTo(pressure));
		    	  System.out.print(" the pressure is  "+pressure+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the pressure is not correct   "+"\n");
				} 
		   try {
			   response.then()
		          .assertThat()
		          .body("main.humidity",equalTo(humidity));
		    	  System.out.print(" the humidity is  "+humidity+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the humidity is not correct   "+"\n");
				}
		   try {
			   response.then()
		          .assertThat()
		          .body("main.sea_level",equalTo(sea_level));
		    	  System.out.print(" the sea-level is  "+sea_level+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the humidity is not correct   "+"\n");
				}
		   
		   try {
			   response.then()
		          .assertThat()
		          .body("main.grnd_level",equalTo(grnd_level));
		    	  System.out.print(" the grnd_level is  "+grnd_level+"\n");
			   
		   }
		   catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the grnd_level is not correct   "+"\n");
				}
		  
	   }
	   public void visibility() {
		   try {
		    	  response.then()
		          .assertThat()
		          .body("visibility",equalTo(visibility));
		    	  System.out.print(" the visibility is  "+visibility+"\n");
		 	  }
		         catch (Exception e) {
					// TODO: handle exception
		        	  System.out.print(" the visibility is not correct   "+"\n");
				} 
	   }
	   
	   
   public void wind() {

	   try {
	    	  response.then()
	          .assertThat()
	          .body("wind.speed",equalTo(speed));
	    	  System.out.print(" the speed is  "+speed+"\n");
	 	  }
	         catch (Exception e) {
				// TODO: handle exception
	        	  System.out.print(" the speed is not correct   "+"\n"+e.getMessage());
			}
	   try {
	    	  response.then()
	          .assertThat()
	          .body("wind.deg",equalTo(deg));
	    	  System.out.print(" the deg is  "+deg+"\n");
	 	  }
	         catch (Exception e) {
				// TODO: handle exception
	        	  System.out.print(" the deg is not correct   "+"\n"+e.getMessage());
			}
		   
	   }
 	public void clouds(){
 		 try {
 	    	  response.then()
 	          .assertThat()
 	          .body("clouds.all",equalTo(all));
 	    	  System.out.print(" the all is  "+all+"\n");
 	 	  }
 	         catch (Exception e) {
 				// TODO: handle exception
 	        	  System.out.print(" the all is not correct   "+"\n");
 			}
 	}
 	public void dt(){
 		 try {
	    	  response.then()
	          .assertThat()
	          .body("dt",equalTo(dt));
	    	  System.out.print(" the dt is  "+all+"\n");
	 	  }
	         catch (Exception e) {
				// TODO: handle exception
	        	  System.out.print(" the dt is not correct   "+"\n");
			}
 	}
 	public void timezone() {
        try {
      	  response.then()
            .assertThat()
            .body("timezone",equalTo(timezone));
      	  System.out.print(" the timezone is  "+timezone+"\n");
   	  }
           catch (Exception e) {
  			// TODO: handle exception
          	  System.out.print(" the timezone is not correct   "+"\n");
  		}
 	}
 	public void idF() {
        try {
      	  response.then()
            .assertThat()
            .body("id",equalTo(idF));
      	  System.out.print(" the id is  "+idF+"\n");
   	  }
           catch (Exception e) {
  			// TODO: handle exception
          	  System.out.print(" the id is not correct   "+"\n");
  		}
 	}
	public void name() {
        try {
      	  response.then()
            .assertThat()
            .body("name",equalTo(name));
      	  System.out.print(" the name is  "+name+"\n");
   	  }
           catch (Exception e) {
  			// TODO: handle exception
          	  System.out.print(" the name is not correct   "+"\n");
  		}
 	}
	public void cod() {
        try {
      	  response.then()
            .assertThat()
            .body("cod",equalTo(cod));
      	  System.out.print(" the cod is  "+cod+"\n");
   	  }
           catch (Exception e) {
  			// TODO: handle exception
          	  System.out.print(" the cod is not correct   "+"\n");
  		}
 	}

	  public void sys() {
		  try {
	    	  response.then()
	          .assertThat()
	          .body("sys.type",equalTo(type));
	    	  System.out.print(" the countryName is  "+type+"\n");
		  }
			  catch (Exception e) {
				// TODO: handle exception
				  System.out.print(" the type is not correct   "+"\n");
			}
		  try {
	    	  response.then()
	          .assertThat()
	          .body("sys.id",equalTo(idsys));
	    	  System.out.print(" the id is  "+idsys+"\n");
		  }
			  catch (Exception e) {
				// TODO: handle exception
				  System.out.print(" the id is not correct   "+"\n");
			}
		  try {
    	  response.then()
          .assertThat()
          .body("sys.country",equalTo(countryName));
    	  System.out.print(" the countryName is  "+countryName+"\n");
	  }
		  catch (Exception e) {
			// TODO: handle exception
			  System.out.print(" the countryName is not correct   "+"\n");
		}
		  try {
	    	  response.then()
	          .assertThat()
	          .body("sys.sunrise",equalTo(sunrise));
	    	  System.out.print(" the sunrise is  "+sunrise+"\n");
		  }
			  catch (Exception e) {
				// TODO: handle exception
				  System.out.print(" the sunrise is not correct   "+"\n");
			}
		  try {
	    	  response.then()
	          .assertThat()
	          .body("sys.sunset",equalTo(sunset));
	    	  System.out.print(" the sunset is  "+sunset+"\n");
		  }
			  catch (Exception e) {
				// TODO: handle exception
				  System.out.print(" the sunset is not correct   "+"\n");
			}
	  }
    	
	  public void weather() {
		  try {
    	  response.then()
          .assertThat()
          .body("weather[0].description",equalTo(description));
    	  System.out.print("the description is "+description+"\n");
		  }
		  catch (Exception e) {
			// TODO: handle exception
			 System.out.print("the description is not correct "+"\n");
			  
		}
		  try {
	    	  response.then()
	          .assertThat()
	          .body("weather[0].id",equalTo(id));
	    	  System.out.print("the id is "+id+"\n");
			  }
			  catch (Exception e) {
				// TODO: handle exception
				 System.out.print("the id is not correct "+"\n");
				  
			}
		  
		  try {
	    	  response.then()
	          .assertThat()
	          .body("weather[0].main",equalTo(main));
	    	  System.out.print("the main is "+main+"\n");
			  }
			  catch (Exception e) {
				// TODO: handle exception
				 System.out.print("the main is not correct "+"\n");
				  
			}
		  try {
	    	  response.then()
	          .assertThat()
	          .body("weather[0].icon",equalTo(icon));
	    	  System.out.print("the icon is "+icon+"\n");
			  }
			  catch (Exception e) {
				// TODO: handle exception
				 System.out.print("the icon is not correct "+"\n");
				  
			} 
		  
 	     }
 	  
	  
 	  @Test
 	    public void testGetAndUseDescription() {
 		 baseURL();
 		 response_value();
 		 schemaFile();
 		 StatusCode();
 		 responseTime() ;
 		 coord();
 		 main();
 		 visibility();
 		 wind();
 		 clouds();
 		 dt();
 		 timezone();
 		 idF();
 		 name();
 		 cod();
 		 sys();
 	     weather();

 	      
 	    }
      
   
  	 
// 	

	 }

	   


