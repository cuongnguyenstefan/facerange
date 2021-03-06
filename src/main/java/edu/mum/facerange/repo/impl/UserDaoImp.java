package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import edu.mum.facerange.model.User;
import edu.mum.facerange.repo.UserDao;
import edu.mum.facerange.util.DatabaseUtilities;

@ApplicationScoped
public class UserDaoImp implements UserDao {

	@Override
	public void addUser(User user) {
		Connection con = null;
		try {
			con = DatabaseUtilities.getConnection();
			String querry = "insert into users(fullname,email,gender,password,username,picture) values('"
					+ user.getFullName() + "','" + user.getEmail() + "','" + user.getGender() + "','"
					+ user.getPassword() + "','" + user.getUserName() + "'" + ",'" + user.getPicture() + "')";
			Statement st = con.createStatement();
			st.executeUpdate(querry);

		} catch (SQLException e) {
			System.out.println("Error while adding user: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User signin(String userName, String password) {
		User user = null;
		Connection con = null;
		try {
			con = DatabaseUtilities.getConnection();
			String query = "select * from users where username = ? and password= ?";
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, userName);
			pr.setString(2, password);
			
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userid"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setDob(rs.getDate("dob"));
				user.setFullName(rs.getString("fullname"));
				user.setGender(rs.getString("gender"));
				user.setPassword(rs.getString("password"));
				user.setPicture(rs.getInt("picture"));
			} 


		} catch (SQLException e) {
			System.out.println("Error while adding user: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	public boolean checkAvailable(String value) {
		ResultSet rs;
		boolean aval = false;
		Connection con = null;
		try {
			con = DatabaseUtilities.getConnection();
			String query = "select * from users where username='" + value + "'";
			PreparedStatement pr = con.prepareStatement(query);
			rs = pr.executeQuery();
			aval = rs.next();

		} catch (SQLException e) {
			System.out.println("Error while finding user: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aval;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int userId) {
		Connection con = null;
		User user = null;
		try {
			con = DatabaseUtilities.getConnection();
			String query = "select * from users where userid = ?";
			PreparedStatement pr = con.prepareStatement(query);
			pr.setInt(1, userId);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(userId);
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setDob(rs.getDate("dob"));
				user.setFullName(rs.getString("fullname"));
				user.setGender(rs.getString("gender"));
				user.setPassword(rs.getString("password"));
				user.setPicture(rs.getInt("picture"));
			}
			

		} catch (SQLException e) {
			System.out.println("Error while adding user: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	@Override
	public List<User> searchUser(String name) {
		String search = "SELECT * FROM users WHERE fullname LIKE ?";
		
		Connection con = null;
		try {
			con = DatabaseUtilities.getConnection();
			PreparedStatement pr = con.prepareStatement(search);
			pr.setString(1, "%" + name + "%");
			ResultSet rs = pr.executeQuery();
			return listUserFromResultSet(rs);
		} catch (SQLException e) {
			System.out.println("Error while adding user: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private List<User> listUserFromResultSet(ResultSet rs) throws SQLException {
		List<User> users = new ArrayList<User>();

		while (rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt("userid"));
			user.setEmail(rs.getString("email"));
			user.setUserName(rs.getString("username"));
			user.setDob(rs.getDate("dob"));
			user.setFullName(rs.getString("fullname"));
			user.setGender(rs.getString("gender"));
			user.setPassword(rs.getString("password"));
			user.setPicture(rs.getInt("picture"));

			users.add(user);
		}

		return users;
	}

}
