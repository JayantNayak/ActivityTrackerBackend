package com.activitytracker.springboot.service;


import java.util.List;

import com.activitytracker.springboot.model.User;

public interface UserService {
	
	void deleteAllUsers();

	void deleteUserById(Long id);

	List<User> findAllUsers();

	User findById(Long id);

	boolean isUserExist(Long id);

	void saveUser(User user);

	void updateUser(User user);
	
	User isUserExistWithEmail(String email);
	
	void saveAdminUser(User user);
}