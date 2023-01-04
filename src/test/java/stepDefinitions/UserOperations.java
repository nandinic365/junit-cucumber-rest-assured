package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class UserOperations {
	
	private String GET_ALL_USERS_URI = "https://reqres.in/api/users";
	private String GET_SINGLE_USER_URI ="https://reqres.in/api/users/3";
	private String UPDATE_USER = "https://reqres.in/api/users/4";
	private String DELETE_USER="https://reqres.in/api/users/2";
	
	private int HTTP_STATUS_CODE_CREATED = 201;
	private int HTTP_STATUS_CODE_DELETED = 204;
	
	private static Response response;

	private static final String BASE_URL = "https://lms-admin-rest-service.herokuapp.com";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "password";
	private static int programId = 0;
	
	RequestSpecification requestAddNewUser = null;
	RequestSpecification updateNewUserrequest = null;
	RequestSpecification deleteNewUserrequest = null;
	Response addNewUserresponse = null;
	

	RequestSpecification requestGetUserWithId = null;
	Response responseGetUserWithId = null ;
	Response responseUpdateUserWithId = null;
	Response updateNewUserresponse = null;
	Response deleteUserresponse = null;
	

	@Given("I want to add a new user with name as {string} and Job as {string}")
	public void i_want_to_add_a_new_user_with_name_as_and_job_as1(String name, String job) 
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
		
	@Given("I have a valid user")
    public void i_have_a_valid_user() {
        RestAssured.baseURI = GET_SINGLE_USER_URI;
        requestGetUserWithId = RestAssured.given();
        RestAssured.baseURI = UPDATE_USER;
        updateNewUserrequest = RestAssured.given();
    }
	
	@When("I update the user name as {string} and Job as {string}")
    public void i_update_the_user_name_as_and_job_as(String name, String job) {
        String user = getUserJson(name, job).toJSONString();
        updateNewUserrequest.body(user);
        updateNewUserresponse = updateNewUserrequest.post();
    }
	
    @Then("the user details should be updated")
    public void the_user_details_should_be_updated() {
        updateNewUserresponse.print();
        ResponseBody responseBodyAddUser = updateNewUserresponse.body();
        JsonPath jsonPathAddUser = responseBodyAddUser.jsonPath();
    }    

@Given("I have a valid userId as {string}")
public void i_have_a_valid_userId_as(String string) {
    // Write code here that turns the phrase above into concrete actions
    RestAssured.baseURI = DELETE_USER;	
    deleteNewUserrequest = RestAssured.given();  
}

@When("I invoke the delete operation")
public void i_invoke_the_delete_operation() {
    // Write code here that turns the phrase above into concrete actions
	deleteUserresponse = deleteNewUserrequest.delete();
}

@Then("the user details should be deleted")
public void the_user_details_should_be_deleted() {
	//deleteUserresponse
	Assert.assertEquals(HTTP_STATUS_CODE_DELETED,deleteUserresponse.getStatusCode());
}
}
