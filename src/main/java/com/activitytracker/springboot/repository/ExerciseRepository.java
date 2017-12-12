package com.activitytracker.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.activitytracker.springboot.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
	
	@Query("select e from Exercise e  where e.userid = :userid order by date asc")
	 List<Exercise> getActivitiesForUser(@Param("userid") long userid);
}