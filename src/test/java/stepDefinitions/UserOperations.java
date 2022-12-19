package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.simple.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;



public class UserOperations {
	
	private String GET_ALL_USERS_URI = "https://reqres.in/api/users";
	private String GET_SINGLE_USER_URI ="https://reqres.in/api/users/3";
	
	private int HTTP_STATUS_CODE_CREATED = 201;
	
	RequestSpecification requestAddNewUser = null;
	Response addNewUserresponse = null;

	RequestSpecification requestGetUserWithId = null;
	Response responseGetUserWithId = null ;
	

	@Given("I want to add a new user with name as {string} and Job as {string}")
	public void i_want_to_add_a_new_user_with_name_as_and_job_as(String name, String job) 
	{		
		RestAssured.baseURI = GET_ALL_USERS_URI;
		
		requestAddNewUser = RestAssured.given();  
		
		String user = getUserJson(name, job).toJSONString();		
		requestAddNewUser.body(user);
	}
	
	@When("i submit user details")
	public void i_submit_user_details() 
	{
		addNewUserresponse = requestAddNewUser.post();
		
		Assert.assertNotNull(addNewUserresponse);
	}

	@Then("user should be added")
	public void user_should_be_added() 
	{
		addNewUserresponse.print();	
		
		ResponseBody responseBodyAddUser = addNewUserresponse.body();

		JsonPath jsonPathAddUser = responseBodyAddUser.jsonPath();

		Assert.assertEquals(HTTP_STATUS_CODE_CREATED,addNewUserresponse.getStatusCode());

	}
	
	@Given("I have a valid userId")
	public void i_have_a_valid_user_id() {
		RestAssured.baseURI = GET_SINGLE_USER_URI;		
		requestGetUserWithId = RestAssured.given();
	}
	@When("I fetch user details")
	public void i_fetch_user_details() {
		responseGetUserWithId = requestGetUserWithId.get();
		
	}
	@Then("user information should be displayed")
	public void user_information_should_be_displayed() {
		responseGetUserWithId.print();	    
	    assertEquals("Emma", responseGetUserWithId.jsonPath().getString("data.first_name"));
	}
	
	
	private JSONObject getUserJson(String name, String job) {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("job", job);
		return json;
	}
	
}
