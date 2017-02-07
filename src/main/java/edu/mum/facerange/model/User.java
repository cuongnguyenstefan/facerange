package edu.mum.facerange.model;

import java.util.Date;

public class User {
	private Integer userId;
	private String gender;
	private String fullnane;
	private String email;
	private String password;
	private Date dob;
	private String picture;
	private String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

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
