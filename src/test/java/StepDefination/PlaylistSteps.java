package StepDefination;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;


import POJO.PlayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.DateAndTimeProvider;
import utility.PropReader;
import utility.SpectBuilder;
import utility.DateAndTimeProvider;


public class PlaylistSteps 
{
	
	RequestSpecification reqs;
	Response res;
	static String playlistId;
	
	
	@Given("create playlist api payload")
	public void create_playlist_api_payload() throws IOException 
	{

		//Body of API request
		PlayList reqPlaylist = new PlayList();
		reqPlaylist.setName(PropReader.readPropFile("name")+DateAndTimeProvider.getCurrentDateAndTime());
		reqPlaylist.setDescription(PropReader.readPropFile("description"));
		reqPlaylist.setPublic(false);
		
		
		//pre-requisite using spec-builders
		reqs = given(SpectBuilder.reqSpec())
		.body(reqPlaylist);
		}
		
		
	@When("user calls with POST http request for create playlist")
	public void When_user_calls_with_POST_http_request_for_create_playlist() throws IOException
	{
		String userId = PropReader.readPropFile("user_id");

	    res = reqs
	            .pathParam("userId", userId)
	            .when()
	            .post("users/{userId}/playlists");
				
	}
	
	@Then("API call executed with status code {int}")
	public void API_call_executed_with_status_code(Integer stsCode)
	{
		
		 PlayList playlistResponsobj = res.as(PlayList.class);
		 String NameofPlayList = playlistResponsobj.getName();
		 res.then() 
		 .spec(SpectBuilder.resSpec(stsCode))
		 .extract()
		 .response();
		  playlistId = playlistResponsobj.getId();
		
	}
	
	@Given ("GET playlist api payload") 
	public void GET_playlist_api_payload()throws IOException 
	{
		reqs = given(SpectBuilder.reqSpec())
	    .pathParam("PId", playlistId);
		
		
		
	}
	
	
	@When("user calls with GET http request")
	public void user_calls_with_get_http_request() 
	{
		res = reqs.when()
		.get("playlists/{PId}")	;
	}
	
	
	@Then("API call executes with status code {int}")
	public void api_call_executes_with_status_code(Integer int1) 
	{
	    res.then()
		.spec(SpectBuilder.resSpec(int1));
		 PlayList playlist = res.as(PlayList.class);
		 String playlistIdFromResponse = playlist.getId();
		 Assert.assertEquals(playlistIdFromResponse, playlistId);
		
	}
		
		
	@Given ("update playlist api payload")
	public  void Given_update_playlist_api_payload() throws IOException 
	{
		PlayList reqPlaylist = new PlayList();
		reqPlaylist.setName(PropReader.readPropFile("name")+DateAndTimeProvider.getCurrentDateAndTime());
		reqPlaylist.setDescription("This is the updated playlist created using pojo");
		reqPlaylist.setPublic(false);
		
		reqs = given(SpectBuilder.reqSpec())
		.body(reqPlaylist)
		.pathParam("putPId", playlistId);
	}
		
		
	@When("user calls with PUT http request")
	public void user_calls_with_put_http_request() {
		res = reqs.when()
		.put("playlists/{putPId}");
		
	}

	@Then("API call should executes with status code {int}")
	public void api_call_should_executes_with_status_code(Integer int1) {
		res.then()
		.statusCode(int1);
	}
	}
	
	
	

    