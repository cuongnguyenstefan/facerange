package edu.mum.facerange.repo;

import java.sql.SQLException;
import java.util.List;

import edu.mum.facerange.model.*;

public interface UserDao {
	public void addUser(User user)throws SQLException;
	
	public boolean deleteByEmail(String email);
	public void updateUser(User user);
	public List<User> allUSer();
	public boolean findUserByEmail(String email);
	

}
