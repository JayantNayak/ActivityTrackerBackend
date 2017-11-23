package com.activitytracker.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activitytracker.springboot.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}