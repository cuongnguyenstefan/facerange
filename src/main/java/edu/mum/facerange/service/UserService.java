package edu.mum.facerange.service;

import java.util.List;

import edu.mum.facerange.model.User;

public interface UserService {
	public User getUser(int userId);
	
	public List<User> searchUser(String name);
}
