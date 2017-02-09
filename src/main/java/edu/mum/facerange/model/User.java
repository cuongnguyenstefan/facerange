package edu.mum.facerange.model;





import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 225705509219450653L;
	private Integer userId;
	private String gender;
	private String fullName;
	private String email;
	private String password;
	private Date dob;
	private int picture;
	private String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPicture() {
		return picture;
	}

	public void setPicture(int picture) {
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

	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
