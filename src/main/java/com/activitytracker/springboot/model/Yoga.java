package com.activitytracker.springboot.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.joda.time.LocalDate;

@Entity
@Table(name = "ACTIVITY_YOGA")
public class Yoga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DURATION", nullable = false)
	// @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDuration")
	// @Type(type="org.joda.time.contrib.hibernate.PersistentDuration")
	// @Type(type="org.joda.time.Duration")
	private Long duration;

	@Column(name = "DATETIME", nullable = false)
	// @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private Long datetime;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "RATING", nullable = false)
	private int rating;

	@Column(name = "USERID", nullable = false)
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Long getDateTime() {
		return datetime;
	}

	public void setDateTime(Long datetime) {
		this.datetime = datetime;
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

	public Yoga(Long datetime, Long duration, int rating, String comment) {

		this.datetime = datetime;
		this.duration = duration;
		this.rating = rating;
		this.comment = comment;
	}

	public Yoga(Long datetime, Long duration, int rating) {

		this.datetime = datetime;
		this.duration = duration;
		this.rating = rating;
	}
}
