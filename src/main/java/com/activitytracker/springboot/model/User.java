package com.activitytracker.springboot.model;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="APP_USER")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="FIRST_NAME", nullable=false)
	private String firstname;
	
	@NotEmpty
	@Column(name="LAST_NAME", nullable=false)
	private String lastname;
	
	
	@NotEmpty
	@Column(name="EMAILID", nullable=false, unique=true)
	private String emailId;

	@NotEmpty
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	@Column(name="Associated_Sports")
	@ElementCollection(targetClass=Integer.class)
	private List<Integer> associatedSports ;
	
	
	@Column(name="ENABLED", nullable=false)
	private int enabled=1;

	@OneToOne(mappedBy = "parentUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    UserRole userRole;
	
	@OneToMany(mappedBy = "parentUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Yoga> yogaActivities;
	
	/*  public void addChild(Child child) {
	        child.setParent(this);
	        children.add(child);
	    }

	    public void removeChild(Child child) {
	        children.remove(child);
	        if (child != null) {
	            child.setParent(null);
	        }
	    }*/

	

	/*public  UserRole getUserRole() {
		return userRole;
	}

	public  void setUserRole(UserRole userRole) {
		userRole.setParentUser(this);
		this.userRole = userRole;
	}
*/
	
	
	
	
	public  User(){}

	public  User(String firstname,String lastname,String emailId,String password){
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailId = emailId;
		this.password = password;
	}

	public  User(String firstname,String lastname,String emailId,String password, List<Integer>associatedSports){
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailId = emailId;
		this.password = password;
		this.associatedSports = associatedSports;
		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if(id.equals(user.id) && emailId.equals(user.emailId) && firstname.equals(user.firstname)
				&& lastname.equals(user.lastname) && password.equals(user.password)){
			return true;
		}
		return false;
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

	public String getPassword() {
		return password;
	}
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}


	@Override
	public int hashCode() {
		int result;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (emailId != null ? emailId.hashCode() : 0);
		result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		return result;
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

	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", emailId=" + emailId + ",firstname=" + firstname + ",lastname=" + lastname + "]";
	}


}
