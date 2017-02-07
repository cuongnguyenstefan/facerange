package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.facerange.model.User;
import edu.mum.facerange.repo.UserDao;
import edu.mum.facerange.util.DatabaseUtilities;

public class UserDaoImp implements UserDao {
	
	private static String INSERT_STATEMENT = "Insert into users(fullname,email,gender,password) values(?,?,?,?)";
	
	private static String FIND_BY_EMAIL_STATEMENT = "SELECT * FROM users WHERE email = ?";

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
		} catch (SQLException e) {
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
	public User findUserByEmail(String email) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(FIND_BY_EMAIL_STATEMENT);
			prepareStatement.setString(1, email);
			ResultSet rs = prepareStatement.executeQuery();
			List<User> listUserFromResultSet = listUserFromResultSet(rs);
			if (listUserFromResultSet.size() > 0) {
				return listUserFromResultSet.get(0);
			}
		} catch (SQLException e) {
			System.out.println("Error while adding user: " + e.getMessage());
		}
		return null;
	}
	
	private List<User> listUserFromResultSet(ResultSet rs) throws SQLException {
		List<User> users = new ArrayList<User>();
		
		while (rs.next()) {
			User user = new User();
			user.setEmail(rs.getString("email"));
			user.setFullnane(rs.getString("fullname"));
			user.setGender(rs.getString("gender"));
			user.setPassword(rs.getString("password"));
			user.setUserId(rs.getInt("userid"));
			users.add(user);
		}
		
		return users;
	}
}
