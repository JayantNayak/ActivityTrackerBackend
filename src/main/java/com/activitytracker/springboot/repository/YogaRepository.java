package com.activitytracker.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.activitytracker.springboot.model.Yoga;

public interface YogaRepository extends JpaRepository<Yoga, Long> {

	/* @Query("select y from Yoga y where y.userId = :userid")
	 List<Yoga> getActivitiesForUserFromDate(@Param("userid") long userid);*/

	 //List<Yoga> getActivitiesForUserFromDate(@Param("userid") long userid, @Param("startdate")String startdate);
	
	
	
	//@Query("select y from Yoga y where y.userid = :userid")
	@Query("select y from Yoga y  where y.userid = :userid order by date asc")
	 List<Yoga> getActivitiesForUser(@Param("userid") long userid);
	 
}
