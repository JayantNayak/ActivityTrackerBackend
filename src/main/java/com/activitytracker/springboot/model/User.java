package com.activitytracker.springboot.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="APP_USER")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	
	@Column(name="FIRST_NAME", nullable=false)
	private String firstname;
	
	
	@Column(name="LAST_NAME", nullable=false)
	private String lastname;
	
	
	
	@Column(name="EMAILID", nullable=false)
	private String emailId;

	
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	@Column(name="Associated_Sports")
	@ElementCollection(targetClass=Integer.class)
	private List<Integer> associatedSports ;

	

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
