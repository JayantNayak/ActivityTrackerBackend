package com.activitytracker.springboot.service;

import java.util.List;

import com.activitytracker.springboot.model.Yoga;
import com.activitytracker.springboot.util.ApiPath;

public interface YogaActivityService {

	// The path where we expect the VideoSvc to live
	public static final String YOGA_SVC_PATH = ApiPath.USER_API+"/activity/yoga";
	public static final String YOGA_ADMIN_SVC_PATH = ApiPath.ADMIN_API+"/activity/yoga";

	void deleteActivityById(Long id);

	void deleteAllActivities();

	boolean isActivityExist(Long id);

	
	List<Yoga> findAllActivities();

	Yoga findById(Long id);

	List<Yoga> getActivitiesForUserFromDate(long userid, String startDate);
	
	List<Yoga> getActivitiesForUser(long userid);

	void saveActivity(Yoga activity);

	void updateActivity(Yoga activity);

}