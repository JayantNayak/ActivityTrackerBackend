package com.activitytracker.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activitytracker.springboot.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

}
