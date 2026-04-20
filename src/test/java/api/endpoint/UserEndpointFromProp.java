package api.endpoint;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.payload.User;
import api.utilities.ReadPropertyFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserEndpointFromProp {
	
static Logger logger = LogManager.getLogger(UserEndpoints.class);

static ReadPropertyFile readPropertyFile;
	
	public static Response createUser(User payload) throws IOException {
		
		readPropertyFile = new ReadPropertyFile();
		
		Response response =RestAssured.
				given().
					accept("application/json").
					contentType("application/json").
					body(payload).
				when()
					.post(readPropertyFile.postUrlForTest());
		
		logger.info("Create User Response");
		
		return response;

	}
	
	public static Response getUser(String username) {

		Response response =RestAssured.
				given().
					accept("application/json").
					pathParam("username", username).
				when()
					.get(readPropertyFile.getUrlForTest());
		
		logger.info("Get User Response");
		
		return response;

	}
	
	public static Response updateUser(User payload, String username) {
		
		Response response =RestAssured.
				given().
					accept("application/json").
					contentType("application/json").
					pathParam("username", username).
					body(payload).
				when()
					.put(readPropertyFile.putUrlForTest());
		
		logger.info("Update User Response");
		
		return response;

	}
	
	public static Response deleteUser(String username) {

		Response response =RestAssured.
				given().
					accept("application/json").
					pathParam("username", username).
				when()
					.delete(readPropertyFile.deleteUrlForTest());
		
		logger.info("Delete User Response");
		
		return response;

	}
	
	
}

