package com.activitytracker.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Exercise extends BaseSportsActivity{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	protected Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
