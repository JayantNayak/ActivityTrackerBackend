package com.activitytracker.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Shooting extends BaseSportsActivity {
	private int totalscore;
	private int totalshots;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	protected Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public int getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(int totalscore) {
		this.totalscore = totalscore;
	}
	public int getTotalshots() {
		return totalshots;
	}
	public void setTotalshots(int totalshots) {
		this.totalshots = totalshots;
	}
	public Shooting(){
		
	}
}
