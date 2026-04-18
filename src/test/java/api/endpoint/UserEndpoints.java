package api.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserEndpoints {
	
	static Logger logger = LogManager.getLogger(UserEndpoints.class);
	
	public static Response createUser(User payload) {
		
		Response response =RestAssured.
				given().
					accept("application/json").
					contentType("application/json").
					body(payload).
				when()
					.post(Routes.post_url);
		
		logger.info("Create User Response");
		
		return response;

	}
	
	public static Response getUser(String username) {

		Response response =RestAssured.
				given().
					accept("application/json").
					pathParam("username", username).
				when()
					.get(Routes.get_url);
		
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
					.put(Routes.put_url);
		
		logger.info("Update User Response");
		
		return response;

	}
	
	public static Response deleteUser(String username) {

		Response response =RestAssured.
				given().
					accept("application/json").
					pathParam("username", username).
				when()
					.delete(Routes.delete_url);
		
		logger.info("Delete User Response");
		
		return response;

	}
	

}
