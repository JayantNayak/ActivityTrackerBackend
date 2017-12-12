package com.activitytracker.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitytracker.springboot.model.Shooting;
import com.activitytracker.springboot.model.User;
import com.activitytracker.springboot.repository.ShootingRepository;



@Service("shootingService")
@Transactional
public class ShootingServiceImpl implements ShootingActivityService{

	@Autowired
	private ShootingRepository shootingRepository;
	
	@Autowired
	UserService userService;

	@Override
	public void saveActivity(Shooting activity) {
		User parentUser = userService.findById(activity.getUserId());
		activity.setParentUser(parentUser);
		shootingRepository.save(activity);
		
		
	}

	@Override
	public void updateActivity(Shooting activity) {
		saveActivity(activity);
		
	}

	@Override
	public void deleteActivityById(Long id) {
		shootingRepository.delete(id);
		List<Integer> a;
		
	}

	@Override
	public void deleteAllActivities() {
		shootingRepository.deleteAll();
		
	}

	@Override
	public List<Shooting> findAllActivities() {
		return  shootingRepository.findAll();
		
	}

	@Override
	public boolean isActivityExist(Long id) {
		return findById(id) != null;
	}

	@Override
	public Shooting findById(Long id) {
		return shootingRepository.findOne(id);
	}
	
	
	@Override
	public List<Shooting> getActivitiesForUserFromDate(long userid, String startDate) {
		
		return null;
	
	}

	@Override
	public List<Shooting> getActivitiesForUser(long userid) {

		return shootingRepository.getActivitiesForUser(userid);
		
	}

}
