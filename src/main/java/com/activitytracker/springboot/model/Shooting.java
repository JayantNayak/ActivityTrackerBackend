package com.activitytracker.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVITY_SHOOTING")
public class Shooting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TOTALSCORE", nullable = false)
	private float totalscore;

	@Column(name = "TOTALSHOTS", nullable = false)
	private int totalshots;

	@Column(name = "DATE", nullable = false)
	private Long date;

	@Column(name = "USERID", nullable = false)
	private long userid;
	
	public long getUserId() {
		return userid;
	}

	public void setUserId(long userid) {
		this.userid = userid;
	}
	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "COMMENT")
	private String comment;

	@ManyToOne
	@JoinColumn(name = "PARENT_USER_ID", referencedColumnName = "ID", nullable = false, updatable = false)
	private User parentUser;

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getTotalscore() {
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

	public Shooting() {

	}

	public Shooting(float totalscore, int totalshots, Long date, Long userid) {
		this.totalscore = totalscore;
		this.totalshots = totalshots;
		this.date = date;
		this.userid= userid;

	}

	public Shooting(float totalscore, int totalshots, Long date, String comment, Long userid) {
		this.totalscore = totalscore;
		this.totalshots = totalshots;
		this.date = date;
		this.comment = comment;
		this.userid= userid;

	}
}
