package com.activitytracker.springboot;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.activitytracker.springboot.exposedmodel.UserClient;
import com.activitytracker.springboot.model.User;
import com.activitytracker.springboot.service.UserService;
import com.activitytracker.springboot.util.ApiPath;
import com.activitytracker.springboot.util.CustomErrorType;

@RestController

public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work
	
	
	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = ApiPath.ADMIN_API+"/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value =ApiPath.ADMIN_API+"/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
		
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = ApiPath.USER_API+"/createUser", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);
		User userExist = userService.isUserExistWithEmail(user.getEmailId());
		if ( userExist!=null) {
			logger.error("Unable to create. A User with emailID {} already exist", user.getEmailId());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with emailId " + 
			user.getEmailId() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = ApiPath.ADMIN_API+"/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		currentUser.setFirstname(user.getFirstname());
		currentUser.setLastname(user.getLastname());
		currentUser.setEmailId(user.getEmailId());
		currentUser.setPassword(user.getPassword());
		currentUser.setAssociatedSports(user.getAssociatedSports());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = ApiPath.ADMIN_API+"/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = ApiPath.ADMIN_API+"/user", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	// ---------------------------Return Userid ---------------------

	
	@RequestMapping(value=ApiPath.USER_API+"/userlogin",method = RequestMethod.GET, headers = { "Authorization" })
	public ResponseEntity<?>  checkEmailId(@RequestHeader("Authorization") String authorization){
		User userExist = null;
		 if (authorization != null && authorization.startsWith("Basic")) {
		        // Authorization: Basic base64credentials
		        String base64Credentials = authorization.substring("Basic".length()).trim();
		        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
		                Charset.forName("UTF-8"));
		        // credentials = username:password
		        final String[] values = credentials.split(":",2);
		        String emailId = values[0];
		        userExist = userService.isUserExistWithEmail(emailId);
		if(userExist==null){
			logger.error("EmailId " + emailId +" not registered.");
			return new ResponseEntity(new CustomErrorType("EmailId " + emailId +" not registered."),
					HttpStatus.NOT_FOUND);
			}
		
		 }
		
		 //userExist.set
		 return new ResponseEntity<UserClient>(new UserClient(userExist), HttpStatus.OK);
	}
	
	
	//--------------------------------create a one time first admin user --------------------
	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = ApiPath.USER_API+"/createAnOneTimeAdminUser")
	public ResponseEntity<?> createAnOneTimeAdminUser() {
		User adminUser = new User("Jayant","Nayak","jayant@gmail.com","Admin123");
		
		logger.info("Creating User : {}", adminUser);
		User userExist = userService.isUserExistWithEmail(adminUser.getEmailId());
		if ( userExist != null) {
			logger.error("Unable to create. A User with emailID {} already exist", adminUser.getEmailId());
			return new ResponseEntity(new CustomErrorType("Unable to create admin User with emailId " + 
					adminUser.getEmailId() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveAdminUser(adminUser);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
}
