package com.activitytracker.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actitvitytracker.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}