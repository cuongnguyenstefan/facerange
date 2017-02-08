package edu.mum.facerange.service;

import edu.mum.facerange.model.User;


public interface AuthenticationService {

	public User authenticating(User user);
	
	public void addUser(User user);
	public User signin(String userName,String password);
	
	public boolean usernameExist(String username);

	public boolean checkAvailable(String value);
	
	
}
