package com.activitytracker.springboot.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER_ROLE")
public class UserRole implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "ROLE", nullable = false)
	private String role;

	// @OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	@JoinColumn(name = "parent_user_id", unique = true, referencedColumnName = "ID", nullable = false)
	private User parentUser;

	public Long getId() {
		return id;
	}

	// not required to expose the parent user
	// uncomment only when developing
	/*
	 * public User getParentUser() { return parentUser; }
	 */

	public String getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	/*
	 * @NotEmpty
	 * 
	 * @OneToOne(cascade = CascadeType.ALL) private Long userId;
	 */

	public void setRole(String role) {
		this.role = role;
	}

	public UserRole() {
	}

	public UserRole(String role, User parentUser) {
		this.role = role;
		this.parentUser = parentUser;
	}

}
