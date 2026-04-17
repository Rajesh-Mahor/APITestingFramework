package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.endpoint.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestWithExcelData {

	public User userPayload;

	@Test(priority = 1, dataProvider = "AllUserData", dataProviderClass = api.utilities.DataProvider.class)
	public void testPostUser(String userID, String UserName, String FirstName, String LastName, String Email,
			String Password, String Phone) {

		userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		userPayload.setUserStatus(0);

		System.out.println("User Data created: " + userPayload.getUsername());

		Response response = UserEndpoints.createUser(userPayload);

		System.out.println("Response for Post User");

		response.then().log().all();

		// Validate the response
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2, dependsOnMethods = "testPostUser")
	public void testGetUserByName() {

		Response response = UserEndpoints.getUser(userPayload.getUsername());

		System.out.println("Response for Get User");

		response.then().log().all();

		// Validate the response
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 3, dependsOnMethods = "testGetUserByName", dataProvider = "UserNameData", dataProviderClass = api.utilities.DataProvider.class)
	public void testUpdateUserByName(String UserNameData) {

		// Update the user details
		userPayload.setFirstName(UserNameData);

		Response response = UserEndpoints.updateUser(userPayload, userPayload.getUsername());

		System.out.println("Response for Update User");

		response.then().log().all();

		// Validate the response
		Assert.assertEquals(response.getStatusCode(), 200);

		// Check User updated Or Not

		Response responseAfterUpdate = UserEndpoints.getUser(userPayload.getUsername());

		System.out.println("Response for Get User After Update");

		responseAfterUpdate.then().log().all();
	}

	@Test(priority = 4, dependsOnMethods = "testUpdateUserByName")
	public void testDeleteUserByName() {

		Response response = UserEndpoints.deleteUser(userPayload.getUsername());

		System.out.println("Response for Delete User");

		response.then().log().all();

		// Validate the response
		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
