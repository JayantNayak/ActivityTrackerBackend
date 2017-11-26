package com.activitytracker.springboot.service;

import java.util.List;

import com.activitytracker.springboot.model.UserRole;

public interface  UserRoleService {
	void deleteAllUserRole();

	void deleteUserRoleById(Long id);

	List<UserRole> findAllUserRole();

	UserRole findById(Long id);

	boolean isUserRoleExist(Long id);

	void saveUserRole(UserRole userRole);

	void updateUserRole(UserRole userRole);
	

}
