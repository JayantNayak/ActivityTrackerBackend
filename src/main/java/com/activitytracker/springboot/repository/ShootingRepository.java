package com.activitytracker.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activitytracker.springboot.model.Shooting;

public interface ShootingRepository extends JpaRepository<Shooting, Long> {

}