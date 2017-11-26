package com.activitytracker.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitytracker.springboot.model.User;
import com.activitytracker.springboot.model.UserRole;
import com.activitytracker.springboot.repository.UserRepository;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleService userRoleService;

	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public void saveUser(User user) {
		userRepository.save(user);
		/* create corresponding authentication in userrole*/
		userRoleService.saveUserRole(new UserRole("USER",user));
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

	@Override
	public boolean isUserExistWithEmail(String email) {
		// TODO Auto-generated method stub
		List<User> usersWithSameEmail = userRepository.getUsersWithEmail(email);
		if(usersWithSameEmail.size()>0)
		{
			return true;
		}
		return false;
	}

}
