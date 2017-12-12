package com.activitytracker.springboot.service;

import java.util.List;

import com.activitytracker.springboot.model.Exercise;
import com.activitytracker.springboot.util.ApiPath;

public interface ExerciseActivityService {

	public static final String EXERCISE_SVC_PATH = ApiPath.USER_API+"/activity/exercise";
	public static final String EXERCISE_ADMIN_SVC_PATH = ApiPath.ADMIN_API+"/activity/exercise";

	void deleteActivityById(Long id);

	void deleteAllActivities();

	boolean isActivityExist(Long id);

	
	List<Exercise> findAllActivities();

	Exercise findById(Long id);

	List<Exercise> getActivitiesForUserFromDate(long userid, String startDate);
	
	List<Exercise> getActivitiesForUser(long userid);

	void saveActivity(Exercise activity);

	void updateActivity(Exercise activity);

}