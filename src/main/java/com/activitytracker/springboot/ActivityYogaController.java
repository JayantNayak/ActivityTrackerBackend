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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.actitvitytracker.model.Shooting;
import com.actitvitytracker.model.Yoga;
import com.activitytracker.model.service.YogaActivityService;
import com.activitytracker.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class ActivityYogaController {
	
	public static final Logger logger = LoggerFactory.getLogger(ActivityYogaController.class);

	@Autowired
	private YogaActivityService yogaService;
	//yogaService = new YogaServiceImpl();
	
		
	
	@RequestMapping(value = "/activity/singleyoga")
	public ResponseEntity<?> createSingleYoga() {

		
		//LocalDate date1 =  LocalDate.of(2017,Month.OCTOBER,6);
		LocalDateTime date1= LocalDateTime.now();
		//LocalDate date = new LocalDate(2017,10,10);
		//Duration dr = new Duration((long) 12345);
		Long dr =  12345L;
		int rate = 5;
		String comment = "good went well";
		Yoga act = new Yoga(date1 ,dr , rate,  comment);
		//yogaService.saveActivity(act);
		//return new ResponseEntity<Yoga>(yogaService.findById(act.getId()), HttpStatus.OK);
		return new ResponseEntity<Yoga>(act, HttpStatus.OK);
		
	}

		// -------------------Retrieve All Yoga activities---------------------------------------------

		@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH, method = RequestMethod.GET)
		public ResponseEntity<List<Yoga>> listAllYogaActivities() {
			
			List<Yoga> activities = yogaService.findAllActivities();
			/*if (activities.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
				
			}*/
			return new ResponseEntity<List<Yoga>>(activities, HttpStatus.OK);
		}

		// -------------------Create a Yoga Activity-------------------------------------------

		@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH, method = RequestMethod.POST)
		public ResponseEntity<?> createUser(@RequestBody Yoga activity, UriComponentsBuilder ucBuilder) {
			logger.info("Creating Activity Yoga : {}", activity);

			if (yogaService.isActivityExist(activity.getId())) {
				logger.error("Unable to create. A  yoga activity with username {} already exist", activity.getId());
				return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
						activity.getId() + " already exist."),HttpStatus.CONFLICT);
			}
			yogaService.saveActivity(activity);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/activity/{id}").buildAndExpand(activity.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		
	
		// ------------------- Delete All Yoga-----------------------------

		@RequestMapping(value = YogaActivityService.YOGA_SVC_PATH, method = RequestMethod.DELETE)
		public ResponseEntity<Yoga> deleteAllYogaActivities() {
			logger.info("Deleting All Yoga");

			yogaService.deleteAllActivities();
			return new ResponseEntity<Yoga>(HttpStatus.NO_CONTENT);
		}
	
	// -- Retrieve yoga activities for a particular user-----
		
		@RequestMapping(value="/activity/useryoga/{userId}")
		public ResponseEntity<List<Yoga>> getYogaForUser(@PathVariable("userId") long userId,
			       @RequestParam(value="startdate", required=true) String startDate) {
			
			return new ResponseEntity<List<Yoga>>(yogaService.getActivitiesForUserFromDate(userId,startDate),HttpStatus.OK);
		}
	
		
		
		
		
	
	
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
