package com.activitytracker.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitytracker.springboot.model.Yoga;
import com.activitytracker.springboot.repository.YogaRepository;



@Service("yogaService")
@Transactional
public class YogaServiceImpl implements YogaActivityService{

	@Autowired
	private YogaRepository yogaRepository;

	@Override
	public void saveActivity(Yoga activity) {
		yogaRepository.save(activity);
		
	}

	@Override
	public void updateActivity(Yoga activity) {
		saveActivity(activity);
		
	}

	@Override
	public void deleteActivityById(Long id) {
		yogaRepository.delete(id);
		List<Integer> a;
		
	}

	@Override
	public void deleteAllActivities() {
		yogaRepository.deleteAll();
		
	}

	@Override
	public List<Yoga> findAllActivities() {
		return  yogaRepository.findAll();
		
	}

	@Override
	public boolean isActivityExist(Long id) {
		// TODO Auto-generated method stub
		return findById(id) != null;
	}

	@Override
	public Yoga findById(Long id) {
		return yogaRepository.findOne(id);
	}
	
	
	@Override
	public List<Yoga> getActivitiesForUserFromDate(long userid, String startDate) {
		return yogaRepository.getActivitiesForUserFromDate(userid);
	}

}
