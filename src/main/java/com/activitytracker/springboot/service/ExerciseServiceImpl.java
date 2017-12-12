package com.activitytracker.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitytracker.springboot.model.Exercise;
import com.activitytracker.springboot.model.User;
import com.activitytracker.springboot.model.Yoga;
import com.activitytracker.springboot.repository.ExerciseRepository;



@Service("exerciseService")
@Transactional
public class ExerciseServiceImpl implements ExerciseActivityService{

	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	UserService userService;

	@Override
	public void saveActivity(Exercise activity) {
		User parentUser = userService.findById(activity.getUserId());
		activity.setParentUser(parentUser);
		exerciseRepository.save(activity);
		
		
	}

	@Override
	public void updateActivity(Exercise activity) {
		saveActivity(activity);
		
	}

	@Override
	public void deleteActivityById(Long id) {
		exerciseRepository.delete(id);
		List<Integer> a;
		
	}

	@Override
	public void deleteAllActivities() {
		exerciseRepository.deleteAll();
		
	}

	@Override
	public List<Exercise> findAllActivities() {
		return  exerciseRepository.findAll();
		
	}

	@Override
	public boolean isActivityExist(Long id) {
		// TODO Auto-generated method stub
		return findById(id) != null;
	}

	@Override
	public Exercise findById(Long id) {
		return exerciseRepository.findOne(id);
	}
	
	
	@Override
	public List<Exercise> getActivitiesForUserFromDate(long userid, String startDate) {
		
		return null;
		
	}

	@Override
	public List<Exercise> getActivitiesForUser(long userid) {

		return exerciseRepository.getActivitiesForUser(userid);
		
	}

}
