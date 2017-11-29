package com.activitytracker.springboot.exposedmodel;

import java.util.List;

import com.activitytracker.springboot.model.User;

public class UserClient {

	
	private Long id;

	private String firstname;

	private String lastname;

	private String emailId;

	private List<Integer> associatedSports ;
	
	
	public  UserClient(User user){
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.emailId = user.getEmailId();
		this.id=user.getId();
		this.associatedSports = user.getAssociatedSports();
	}


	public List<Integer> getAssociatedSports() {
		return associatedSports;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getFirstname() {
		return firstname;
	}

	public Long getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setAssociatedSports(List<Integer> associatedSports) {
		this.associatedSports = associatedSports;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", emailId=" + emailId + ",firstname=" + firstname + ",lastname=" + lastname + "]";
	}
	


}
