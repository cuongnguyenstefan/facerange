package edu.mum.facerange.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import edu.mum.facerange.model.User;
import edu.mum.facerange.repo.UserDao;
import edu.mum.facerange.service.UserService;

@ApplicationScoped
public class UserServiceImpl implements UserService {

	@Inject
	UserDao userRepo;
	
	@Override
	public User getUser(int userId) {
		return userRepo.getUser(userId);
	}

	@Override
	public List<User> searchUser(String name) {
		return userRepo.searchUser(name);
	}

}
