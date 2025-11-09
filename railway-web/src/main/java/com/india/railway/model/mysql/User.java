package com.india.railway.model.mysql;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	public User(Long id, String username, String email) {
		this.id = id;
		this.userName = username;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String userName;

	@Column
	private String email;

	@Column
	private String mobileNumber;

	@Column
	private String password;

	@Column
	private String confirmPassword;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	private UserProfile userProfile;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", // Name of the join table
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), // Foreign key in
																						// join table
																						// pointing to
																						// Passenger
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // Foreign
																							// key in
																							// join
	)
	// @NotEmpty(message = "Passenger must have at least one address.")

	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User() {
	}

	public User(String username, String password, UserProfile userProfile) {
		this.userName = username;
		this.password = password;
		this.userProfile = userProfile;
	}

	public User(Long id, String username) {
		this.id = id;
		this.userName = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String moboleno) {
		this.mobileNumber = moboleno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
