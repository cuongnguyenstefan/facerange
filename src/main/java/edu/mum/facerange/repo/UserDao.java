package edu.mum.facerange.repo;

import java.util.List;

import edu.mum.facerange.model.User;

public interface UserDao {
	
	public void addUser(User user);
	public User signin(String userName,String password);

	public boolean deleteByEmail(String email);

	public void updateUser(User user);
	public User getUser(int userId);
	public List<User> allUSer();
	public boolean checkAvailable(String value);
	
	public List<User> searchUser(String name);

	public User findUserByEmail(String email);

}
