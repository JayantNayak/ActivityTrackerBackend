package com.activitytracker.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.actitvitytracker.model.User;
import com.activitytracker.model.service.UserService;
import com.activitytracker.springboot.repository.UserRepository;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(User user){
		saveUser(user);
	}

	public void deleteUserById(Long id){
		userRepository.delete(id);
		
	}

	public void deleteAllUsers(){
		userRepository.deleteAll();
	}

	public List<User> findAllUsers(){
		return (List<User>) userRepository.findAll();
	}

	public boolean isUserExist(Long id) {
		return findById(id) != null;
	}

}