package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	static Logger logger = LogManager.getLogger(UserTest.class);
	
	public Faker fake;
	public User userPayload;
	
	@BeforeClass
	public void createDummyUser() {
		
		fake = new Faker();
		
		userPayload = new User();
		userPayload.setId(fake.idNumber().hashCode());
		userPayload.setUsername(fake.name().username());
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().emailAddress());
		userPayload.setPassword(fake.internet().password(5, 10));
		userPayload.setPhone(fake.phoneNumber().cellPhone());
		userPayload.setUserStatus(0);
		
		logger.info("User Data created: " + userPayload.getUsername());
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		
		Response response = UserEndpoints.createUser(userPayload);
		
		logger.info("Response for Post User");
		
		response.then().log().all();		
		
		// Validate the response
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority = 2 ,dependsOnMethods = "testPostUser")
	public void testGetUserByName() {
		
		Response response = UserEndpoints.getUser(userPayload.getUsername());
		
		logger.info("Response for Get User");
		
		response.then().log().all();		
		
		// Validate the response
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 3, dependsOnMethods = "testGetUserByName")
	public void testUpdateUserByName() {
		
		// Update the user details
		userPayload.setFirstName(fake.name().firstName());
		
		Response response = UserEndpoints.updateUser(userPayload, userPayload.getUsername());
		
		logger.info("Response for Update User");
		
		response.then().log().all();		
		
		// Validate the response
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Check User updated Or Not
		
		Response responseAfterUpdate = UserEndpoints.getUser(userPayload.getUsername());
		
		logger.info("Response for Get User After Update");
		
		responseAfterUpdate.then().log().all();
	}
	
	@Test(priority = 4, dependsOnMethods = "testUpdateUserByName")
	public void testDeleteUserByName() {
		
		Response response = UserEndpoints.deleteUser(userPayload.getUsername());
		
		logger.info("Response for Delete User");
		
		response.then().log().all();		
		
		// Validate the response
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
