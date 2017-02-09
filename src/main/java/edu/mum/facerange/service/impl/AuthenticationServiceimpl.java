package edu.mum.facerange.service.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.model.User;
import edu.mum.facerange.repo.UserDao;
import edu.mum.facerange.service.AuthenticationService;
@Named("authenticationService")
@ApplicationScoped
public class AuthenticationServiceimpl implements AuthenticationService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject 
	UserDao userDao;
	@Override
	public User authenticating(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public boolean usernameExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void  addUser(User user) {
		
		userDao.addUser(user);
	}



	@Override
	public User signin(String userName, String password) {
		return userDao.signin(userName, password);
	}



	@Override
	public boolean checkAvailable(String value) {
		return userDao.checkAvailable(value);
	}
	
}
