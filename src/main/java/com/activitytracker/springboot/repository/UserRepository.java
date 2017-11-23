package com.activitytracker.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activitytracker.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}