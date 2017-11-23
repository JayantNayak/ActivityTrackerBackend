package com.activitytracker.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.activitytracker.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 @Query("select u from User u where u.emailId = :emailid")
	 List<User> getUsersWithEmail(@Param("emailid") String emailid);

}