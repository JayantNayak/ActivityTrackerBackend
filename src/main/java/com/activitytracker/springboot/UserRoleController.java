package com.activitytracker.springboot;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.activitytracker.springboot.model.User;
import com.activitytracker.springboot.model.UserRole;
import com.activitytracker.springboot.service.UserRoleService;
import com.activitytracker.springboot.util.ApiPath;
import com.activitytracker.springboot.util.CustomErrorType;

@RestController
@RequestMapping(ApiPath.ADMIN_API)
public class UserRoleController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	@Autowired
	UserRoleService authentication; 
	
	public static final String USER_ROLE_SVC_PATH = "/userRole";
	
	// -------------------Retrieve All UserRoles---------------------------------------------

	@RequestMapping(value = USER_ROLE_SVC_PATH, method = RequestMethod.GET)
	public ResponseEntity<List<UserRole>> listAllUsers() {
		List<UserRole> usersRoles = authentication.findAllUserRole();
		/*if (usersRoles.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}*/
		return new ResponseEntity<List<UserRole>>(usersRoles, HttpStatus.OK);
	}
	
	// -------------------Create a User Role-------------------------------------------

		@RequestMapping(value = USER_ROLE_SVC_PATH, method = RequestMethod.POST)
		public ResponseEntity<?> createUser(@RequestBody UserRole userRole, UriComponentsBuilder ucBuilder) {
			logger.info("Creating UserRole : {}", userRole);

			/*if (userService.isUserExistWithEmail(user.getEmailId())) {
				logger.error("Unable to create. A User with emailID {} already exist", user.getEmailId());
				return new ResponseEntity(new CustomErrorType("Unable to create. A User with emailId " + 
				user.getEmailId() + " already exist."),HttpStatus.CONFLICT);
			}*/
			authentication.saveUserRole(userRole);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/admin"+USER_ROLE_SVC_PATH+"/{id}").buildAndExpand(userRole.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		
	
		// ------------------- Delete a User-----------------------------------------

		@RequestMapping(value = USER_ROLE_SVC_PATH +"/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
			logger.info("Fetching & Deleting UserRole with id {}", id);

			UserRole userRole = authentication.findById(id);
			if (userRole == null) {
				logger.error("Unable to delete. userRole with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("Unable to delete. userRole with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			authentication.deleteUserRoleById(id);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}

		// ------------------- Delete All Users-----------------------------

		@RequestMapping(value = USER_ROLE_SVC_PATH, method = RequestMethod.DELETE)
		public ResponseEntity<User> deleteAllUsers() {
			logger.info("Deleting All User Role");

			authentication.deleteAllUserRole();
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
	
	
}
