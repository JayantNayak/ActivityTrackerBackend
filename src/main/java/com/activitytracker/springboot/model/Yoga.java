package com.activitytracker.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

//import org.joda.time.LocalDate;

@Entity
@Table(name = "ACTIVITY_YOGA")
public class Yoga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DURATION", nullable = false)

	private Long duration;

	@Column(name = "DATE", nullable = false)
	private Long date;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "RATING", nullable = false)
	private int rating;

	@Column(name = "USERID", nullable = false)
	private long userid;

	@ManyToOne
	@JoinColumn(name = "PARENT_USER_ID", referencedColumnName = "ID", nullable = false, updatable = false)
	private User parentUser;

	public long getUserId() {
		return userid;
	}

	public void setUserId(long userid) {
		this.userid = userid;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Yoga() {

	}

	public Yoga(Long date, Long duration, int rating, String comment, Long userid) {

		this.date = date;
		this.duration = duration;
		this.rating = rating;
		this.comment = comment;
		this.userid = userid;
	}

	public Yoga(Long date, Long duration, int rating, Long userid) {

		this.date = date;
		this.duration = duration;
		this.rating = rating;
		this.userid = userid;
	}
}
