package com.activitytracker.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitytracker.springboot.model.UserRole;
import com.activitytracker.springboot.repository.UserRoleRepository;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public void deleteAllUserRole() {
		userRoleRepository.deleteAll();
		
	}

	@Override
	public void deleteUserRoleById(Long id) {
		userRoleRepository.delete(id);
	}

	@Override
	public List<UserRole> findAllUserRole() {
		return userRoleRepository.findAll();
	}

	@Override
	public UserRole findById(Long id) {
		return userRoleRepository.findOne(id);
	}

	@Override
	public boolean isUserRoleExist(Long id) {
		return  findById(id) != null;
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		userRoleRepository.save(userRole);
	}

	@Override
	public void updateUserRole(UserRole userRole) {
		saveUserRole(userRole);
		
	}

}
