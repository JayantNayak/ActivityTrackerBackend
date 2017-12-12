package com.activitytracker.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.activitytracker.springboot.model.Shooting;

public interface ShootingRepository extends JpaRepository<Shooting, Long> {
	@Query("select s from Shooting s  where s.userid = :userid order by date asc")
	 List<Shooting> getActivitiesForUser(@Param("userid") long userid);
}