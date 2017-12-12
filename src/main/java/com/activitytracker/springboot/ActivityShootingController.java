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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.activitytracker.springboot.model.Shooting;
import com.activitytracker.springboot.model.User;
import com.activitytracker.springboot.service.ShootingActivityService;
import com.activitytracker.springboot.service.UserService;
import com.activitytracker.springboot.util.ApiPath;
import com.activitytracker.springboot.util.CustomErrorType;

@RestController
public class ActivityShootingController {
	
	public static final Logger logger = LoggerFactory.getLogger(ActivityShootingController.class);

	@Autowired
	private ShootingActivityService shootingService;
	@Autowired
	private UserService userService;
		


		// -------------------Retrieve All Shooting activities For all users---------------------------------------------

		@RequestMapping(value = ShootingActivityService.SHOOTING_ADMIN_SVC_PATH, method = RequestMethod.GET)
		public ResponseEntity<List<Shooting>> listAllShootingActivities() {
			
			List<Shooting> activities = shootingService.findAllActivities();
			/*if (activities.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
				
			}*/
			return new ResponseEntity<List<Shooting>>(activities, HttpStatus.OK);
		}
		
		// --- Retrieve all Shooting activities for a user-----
		
				@RequestMapping(value = ShootingActivityService.SHOOTING_SVC_PATH, method = RequestMethod.GET,headers = { "Authorization" })
				public ResponseEntity<List<Shooting>> getAllShootingForUser(@RequestHeader("Authorization") String authorization) {
					
					
					logger.info("getAllShootingForUser");
					
					String logErrorMsg =  "Unable to retrieve Shooting activities invalid credentials.";
					ApiPath apiUtil = new ApiPath();
					User userExist = apiUtil.getUserForCredential(authorization,userService);
					if ( userExist==null) {
						logger.error(logErrorMsg);
						return new ResponseEntity(new CustomErrorType(logErrorMsg),HttpStatus.CONFLICT);
					}
					
					return new ResponseEntity<List<Shooting>>(shootingService.getActivitiesForUser(userExist.getId()),HttpStatus.OK);
				}

		// -------------------Create a Shooting Activity-------------------------------------------

		@RequestMapping(value = ShootingActivityService.SHOOTING_SVC_PATH, method = RequestMethod.POST,headers = { "Authorization" })
		public ResponseEntity<?> createUser(@RequestBody Shooting activity, @RequestHeader("Authorization") String authorization) {
			logger.info("Creating Activity Shooting : {}", activity);
			
			String logErrorMsg =  "Unable to create Shooting activity invalid credentials.";
			ApiPath apiUtil = new ApiPath();
			User userExist = apiUtil.getUserForCredential(authorization,userService);
			if ( userExist==null) {
				logger.error(logErrorMsg);
				return new ResponseEntity(new CustomErrorType(logErrorMsg),HttpStatus.CONFLICT);
			}
			activity.setUserId(userExist.getId());
			shootingService.saveActivity(activity);

			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		
	
		// ------------------- Delete All Shooting-----------------------------

		@RequestMapping(value = ShootingActivityService.SHOOTING_ADMIN_SVC_PATH, method = RequestMethod.DELETE)
		public ResponseEntity<Shooting> deleteAllShootingActivities() {
			logger.info("Deleting All Shooting");

			shootingService.deleteAllActivities();
			return new ResponseEntity<Shooting>(HttpStatus.NO_CONTENT);
		}
		
		
		
		///---------------------Delete A Shooting ---------------------------------
		@RequestMapping(value = ShootingActivityService.SHOOTING_SVC_PATH +"/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteShooting(@PathVariable("id") long id) {
			logger.info("Fetching & Deleting Shooting with id {}", id);

			Shooting shooting = shootingService.findById(id);
			if (shooting == null) {
				logger.error("Unable to delete. shooting with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("Unable to delete. shooting with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			shootingService.deleteActivityById(id);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		
		//------------retrieve by date
		
	/*	@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH, method = RequestMethod.GET,headers = { "Authorization" })
		public ResponseEntity<List<Yoga>> getYogaForUser(@PathVariable("userId") long userId,
			       @RequestParam(value="startdate", required=true) String startDate) {
			
			return new ResponseEntity<List<Yoga>>(yogaService.getActivitiesForUserFromDate(userId,startDate),HttpStatus.OK);
		}*/


}
