package com.activitytracker.springboot;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activitytracker.springboot.model.Shooting;
import com.activitytracker.springboot.model.User;
import com.activitytracker.springboot.model.Yoga;
import com.activitytracker.springboot.service.UserService;
import com.activitytracker.springboot.service.YogaActivityService;
import com.activitytracker.springboot.util.ApiPath;
import com.activitytracker.springboot.util.CustomErrorType;

@RestController
//@RequestMapping("/api")
public class ActivityYogaController {
	
	public static final Logger logger = LoggerFactory.getLogger(ActivityYogaController.class);

	@Autowired
	private YogaActivityService yogaService;
	//yogaService = new YogaServiceImpl();
	@Autowired
	private UserService userService;
		
	
	@RequestMapping(value = "/api/activity/singleyoga")
	public ResponseEntity<?> createSingleYoga() {

		
		//LocalDate date1 =  LocalDate.of(2017,Month.OCTOBER,6);
		Long date1=System.currentTimeMillis();
		//LocalDate date = new LocalDate(2017,10,10);
		//Duration dr = new Duration((long) 12345);
		Long dr =  12345L;
		int rate = 5;
		String comment = "good went well";
		Long userID = 1L;
		Yoga act = new Yoga(date1 ,dr , rate,  comment,userID);
		yogaService.saveActivity(act);
		//return new ResponseEntity<Yoga>(yogaService.findById(act.getId()), HttpStatus.OK);
		return new ResponseEntity<Yoga>(act, HttpStatus.OK);
		
	}

		// -------------------Retrieve All Yoga activities For all users---------------------------------------------

		@RequestMapping(value = YogaActivityService.YOGA_ADMIN_SVC_PATH, method = RequestMethod.GET)
		public ResponseEntity<List<Yoga>> listAllYogaActivities() {
			
			List<Yoga> activities = yogaService.findAllActivities();
			/*if (activities.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
				
			}*/
			return new ResponseEntity<List<Yoga>>(activities, HttpStatus.OK);
		}
		
		// --- Retrieve all yoga activities for a user-----
		
				@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH, method = RequestMethod.GET,headers = { "Authorization" })
				public ResponseEntity<List<Yoga>> getAllYogaForUser(@RequestHeader("Authorization") String authorization) {
					
					
					logger.info("getAllYogaForUser");
					
					String logErrorMsg =  "Unable to retrieve yoga activities invalid credentials.";
					ApiPath apiUtil = new ApiPath();
					User userExist = apiUtil.getUserForCredential(authorization,userService);
					if ( userExist==null) {
						logger.error(logErrorMsg);
						return new ResponseEntity(new CustomErrorType(logErrorMsg),HttpStatus.CONFLICT);
					}
					
					return new ResponseEntity<List<Yoga>>(yogaService.getActivitiesForUser(userExist.getId()),HttpStatus.OK);
				}

		// -------------------Create a Yoga Activity-------------------------------------------

		@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH, method = RequestMethod.POST,headers = { "Authorization" })
		public ResponseEntity<?> createUser(@RequestBody Yoga activity, @RequestHeader("Authorization") String authorization) {
			logger.info("Creating Activity Yoga : {}", activity);
			
			String logErrorMsg =  "Unable to create yoga activity invalid credentials.";
			ApiPath apiUtil = new ApiPath();
			User userExist = apiUtil.getUserForCredential(authorization,userService);
			if ( userExist==null) {
				logger.error(logErrorMsg);
				return new ResponseEntity(new CustomErrorType(logErrorMsg),HttpStatus.CONFLICT);
			}
			activity.setUserId(userExist.getId());
			yogaService.saveActivity(activity);

			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		
	
		// ------------------- Delete All Yoga-----------------------------

		@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH, method = RequestMethod.DELETE)
		public ResponseEntity<Yoga> deleteAllYogaActivities() {
			logger.info("Deleting All Yoga");

			yogaService.deleteAllActivities();
			return new ResponseEntity<Yoga>(HttpStatus.NO_CONTENT);
		}
		
		
		
		///---------------------Delete A Yoga ---------------------------------
		@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH +"/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteYoga(@PathVariable("id") long id) {
			logger.info("Fetching & Deleting yoga with id {}", id);

			Yoga yoga = yogaService.findById(id);
			if (yoga == null) {
				logger.error("Unable to delete. yoga with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("Unable to delete. Yoga with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			yogaService.deleteActivityById(id);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		
		//------------retrieve by date
		
	/*	@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH, method = RequestMethod.GET,headers = { "Authorization" })
		public ResponseEntity<List<Yoga>> getYogaForUser(@PathVariable("userId") long userId,
			       @RequestParam(value="startdate", required=true) String startDate) {
			
			return new ResponseEntity<List<Yoga>>(yogaService.getActivitiesForUserFromDate(userId,startDate),HttpStatus.OK);
		}*/

		
	
	 @RequestMapping("/display")
	    public String display() {
	     return "hii helllo";
	    }
	 
	 @RequestMapping("/time")
	    public LocalDateTime displayTime() {
	   
	     return LocalDateTime.now();
	    }

	 
	 @RequestMapping("/YogaActivity")
	    public Yoga displayYogaActivity() {
	     return  new Yoga();
	    }
	
	 @RequestMapping("/ShootingActivity")
	    public Shooting displayShhotingActivity() {
	     return  new Shooting();
	    }
}
