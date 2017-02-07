package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import edu.mum.facerange.model.User;
import edu.mum.facerange.repo.UserDao;
import edu.mum.facerange.util.DatabaseUtilities;

public class UserDaoImp implements UserDao {
	
	private static String INSERT_STATEMENT = "Insert into users(fullname,email,gender,password) values(?,?,?,?)";

	@Override
	public void addUser(User user) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(INSERT_STATEMENT);
			prepareStatement.setString(1, user.getFullnane());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getGender());
			prepareStatement.setString(4, user.getPassword());
			prepareStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error while adding user: " + e.getMessage());
		}
	}

	public static void main(String arg[]) {
		UserDaoImp doaim = new UserDaoImp();
		User user = new User();
		user.setEmail("2kkhenricharles@gmail.com");
		user.setFullnane("henricharles");
		user.setGender("mal");
		user.setPassword("12345");
		doaim.addUser(user);
		System.out.println("sucess created");

	}

	@Override
	public boolean deleteByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> allUSer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}
}
