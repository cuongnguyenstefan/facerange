package edu.mum.facerange.repo;

import java.util.List;

import edu.mum.facerange.model.User;

public interface UserDao {
	
	public void addUser(User user);
	public User signin(String userName,String password);

	public boolean deleteByEmail(String email);

	public void updateUser(User user);

	public List<User> allUSer();
	public boolean checkAvailable(String value);
	
	

	public User findUserByEmail(String email);

}
