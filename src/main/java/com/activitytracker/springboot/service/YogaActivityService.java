package com.activitytracker.springboot.service;

import java.util.List;

import com.activitytracker.springboot.model.Yoga;

public interface YogaActivityService {

	// The path where we expect the VideoSvc to live
	public static final String YOGA_SVC_PATH = "/api/activity/yoga";

	void deleteActivityById(Long id);

	void deleteAllActivities();

	boolean isActivityExist(Long id);

	
	List<Yoga> findAllActivities();

	Yoga findById(Long id);

	List<Yoga> getActivitiesForUserFromDate(long userid, String startDate);

	void saveActivity(Yoga activity);

	void updateActivity(Yoga activity);

}