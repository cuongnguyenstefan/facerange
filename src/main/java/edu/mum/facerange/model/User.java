package edu.mum.facerange.model;

import java.util.Date;

public class User {
	private Integer userId;
	private String gender;
	private String fullnane;
	private String email;
	private String password;
	private Date dob;

	public Integer getUserId() {
		return userId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFullnane() {
		return fullnane;
	}

	public void setFullnane(String fullnane) {
		this.fullnane = fullnane;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
