package com.activitytracker.springboot.service;

public interface BaseActivityService {
	void deleteActivityById(Long id);

	void deleteAllActivities();

	boolean isActivityExist(Long id);
}