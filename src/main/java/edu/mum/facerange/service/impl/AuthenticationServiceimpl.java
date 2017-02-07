package edu.mum.facerange.service.impl;

import javax.inject.Inject;

import edu.mum.facerange.model.User;
import edu.mum.facerange.service.AuthenticationService;
import edu.mum.facerange.repo.*;

public class AuthenticationServiceimpl implements AuthenticationService {

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
	
}
