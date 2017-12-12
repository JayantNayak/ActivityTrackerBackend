package com.activitytracker.springboot.service;

import java.util.List;

import com.activitytracker.springboot.model.Shooting;
import com.activitytracker.springboot.util.ApiPath;

public interface ShootingActivityService {

	public static final String SHOOTING_SVC_PATH = ApiPath.USER_API+"/activity/shooting";
	public static final String SHOOTING_ADMIN_SVC_PATH = ApiPath.ADMIN_API+"/activity/shooting";

	void deleteActivityById(Long id);

	void deleteAllActivities();

	boolean isActivityExist(Long id);

	
	List<Shooting> findAllActivities();

	Shooting findById(Long id);

	List<Shooting> getActivitiesForUserFromDate(long userid, String startDate);
	
	List<Shooting> getActivitiesForUser(long userid);

	void saveActivity(Shooting activity);

	void updateActivity(Shooting activity);

}