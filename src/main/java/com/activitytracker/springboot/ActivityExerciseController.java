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

import com.activitytracker.springboot.model.Exercise;
import com.activitytracker.springboot.model.User;
import com.activitytracker.springboot.service.ExerciseActivityService;
import com.activitytracker.springboot.service.UserService;
import com.activitytracker.springboot.util.ApiPath;
import com.activitytracker.springboot.util.CustomErrorType;

@RestController
public class ActivityExerciseController {
	
	public static final Logger logger = LoggerFactory.getLogger(ActivityExerciseController.class);

	@Autowired
	private ExerciseActivityService exerciseService;
	@Autowired
	private UserService userService;
		
	

		// -------------------Retrieve All Yoga activities For all users---------------------------------------------

		@RequestMapping(value = ExerciseActivityService.EXERCISE_ADMIN_SVC_PATH, method = RequestMethod.GET)
		public ResponseEntity<List<Exercise>> listAllExerciseActivities() {
			
			List<Exercise> activities = exerciseService.findAllActivities();
			/*if (activities.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
				
			}*/
			return new ResponseEntity<List<Exercise>>(activities, HttpStatus.OK);
		}
		
		// --- Retrieve all Exercise activities for a user-----
		
				@RequestMapping(value = ExerciseActivityService.EXERCISE_SVC_PATH, method = RequestMethod.GET,headers = { "Authorization" })
				public ResponseEntity<List<Exercise>> getAllExerciseForUser(@RequestHeader("Authorization") String authorization) {
					
					
					logger.info("getAllExerciseForUser");
					
					String logErrorMsg =  "Unable to retrieve Exercise activities invalid credentials.";
					ApiPath apiUtil = new ApiPath();
					User userExist = apiUtil.getUserForCredential(authorization,userService);
					if ( userExist==null) {
						logger.error(logErrorMsg);
						return new ResponseEntity(new CustomErrorType(logErrorMsg),HttpStatus.CONFLICT);
					}
					
					return new ResponseEntity<List<Exercise>>(exerciseService.getActivitiesForUser(userExist.getId()),HttpStatus.OK);
				}

		// -------------------Create a Exercise Activity-------------------------------------------

		@RequestMapping(value = ExerciseActivityService.EXERCISE_SVC_PATH, method = RequestMethod.POST,headers = { "Authorization" })
		public ResponseEntity<?> createUser(@RequestBody Exercise activity, @RequestHeader("Authorization") String authorization) {
			logger.info("Creating Activity Exercise : {}", activity);
			
			String logErrorMsg =  "Unable to create Exercise activity invalid credentials.";
			ApiPath apiUtil = new ApiPath();
			User userExist = apiUtil.getUserForCredential(authorization,userService);
			if ( userExist==null) {
				logger.error(logErrorMsg);
				return new ResponseEntity(new CustomErrorType(logErrorMsg),HttpStatus.CONFLICT);
			}
			activity.setUserId(userExist.getId());
			exerciseService.saveActivity(activity);

			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		
	
		// ------------------- Delete All Exercise-----------------------------

		@RequestMapping(value = ExerciseActivityService.EXERCISE_ADMIN_SVC_PATH, method = RequestMethod.DELETE)
		public ResponseEntity<Exercise> deleteAllExerciseActivities() {
			logger.info("Deleting All Exercise");

			exerciseService.deleteAllActivities();
			return new ResponseEntity<Exercise>(HttpStatus.NO_CONTENT);
		}
		
		
		
		///---------------------Delete A Exercise ---------------------------------
		@RequestMapping(value = ExerciseActivityService.EXERCISE_SVC_PATH +"/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteExercise(@PathVariable("id") long id) {
			logger.info("Fetching & Deleting Exercise with id {}", id);

			Exercise exercise = exerciseService.findById(id);
			if (exercise == null) {
				logger.error("Unable to delete. Exercise with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("Unable to delete. Exercise with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			exerciseService.deleteActivityById(id);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		
		//------------retrieve by date
		
	/*	@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH, method = RequestMethod.GET,headers = { "Authorization" })
		public ResponseEntity<List<Yoga>> getYogaForUser(@PathVariable("userId") long userId,
			       @RequestParam(value="startdate", required=true) String startDate) {
			
			return new ResponseEntity<List<Yoga>>(yogaService.getActivitiesForUserFromDate(userId,startDate),HttpStatus.OK);
		}*/

}
