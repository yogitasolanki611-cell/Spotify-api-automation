package Authmanager;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.PropReader;

public class TokenGenerartion 
{

	static	String access_token;
	static Instant expiry_time;
	public static String getToken() throws IOException
	{
		if(access_token == null || Instant.now().isAfter(expiry_time) )
		{
			System.out.println("Renewing the token!!!");
			
			Response resp = renewAcessToken();
			
			JsonPath jp = resp.jsonPath();
			
			access_token = jp.getString("access_token");
			
			int expiryTimeInSeconds = jp.getInt("expires_in");
			
			expiry_time	= Instant.now().plusSeconds(expiryTimeInSeconds-300);
		
		}
		
		else
		{
			System.out.println("Token is good to use and need not be generated");
		}
		
		return access_token;
		
		
	}
	
		
	
	public static Response renewAcessToken() throws IOException
	{
		
		HashMap<String,String > param = new HashMap<String,String >();

		param.put("grant_type", "refresh_token");
		param.put("refresh_token", PropReader.readPropFile("refresh_token"));
		param.put("client_id", PropReader.readPropFile("client_id"));
		param.put("client_secret", PropReader.readPropFile("client_secret"));
		
		baseURI = "https://accounts.spotify.com";
		
		Response resp = given()
				
		.header("Content-Type", "application/x-www-form-urlencoded")
		
		.formParams(param)
		
		.log().all()
		
		.when()
		
		.post("/api/token")
		
		.then()
		
		.log().all()
		
		.extract()
		
		.response();
		
		if(resp.statusCode()!=200)
		{
			throw new RuntimeException("Token generation API call failed ..");
		}
		
		
		return resp;
					

	}
	
	public static void main(String[] args) throws IOException {
		renewAcessToken();
	}
	
	
	
}
