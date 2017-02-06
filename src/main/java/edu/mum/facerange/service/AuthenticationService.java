package edu.mum.facerange.service;

import edu.mum.facerange.model.User;

public interface AuthenticationService {

	public User authenticating(User user);
	
	public boolean signup(User user);
	
	public boolean usernameExist(String username);
	
}
