package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import edu.mum.facerange.model.User;
import edu.mum.facerange.repo.UserDao;
import edu.mum.facerange.util.DatabaseUtilities;
@ApplicationScoped
public class UserDaoImp implements UserDao {
	
	@Override
	public void addUser(User user) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			String querry = "insert into users(fullname,email,gender,password,username,picture) values('" + user.getFullName() + "','"
					+ user.getEmail() + "','" + user.getGender() + "','" + user.getPassword() + "','" + user.getUserName() + "'"
							+ ",'" + user.getPicture() + "')";
			Statement st = con.createStatement();
			st.executeUpdate(querry);

		} catch (SQLException e) {
			System.out.println("Error while adding user: " + e.getMessage());
		}
	}

	
	@Override
	public User signin(String userName, String password) {
		User user=new User();
		try{
		Connection con = DatabaseUtilities.getConnection();
		String query="select * from users where username='"+userName+"' and password='"+password+"'";
		PreparedStatement pr=con.prepareStatement(query);
		ResultSet rs=pr.executeQuery();
		if(rs.next())
		{
			user.setEmail(rs.getString("email"));
			user.setUserName(rs.getString("username"));
			user.setDob(rs.getDate("dob"));
			user.setFullName(rs.getString("fullname"));
			user.setGender(rs.getString("gender"));
			user.setPassword(rs.getString("password"));
			user.setPicture(rs.getInt("picture"));
		}
		else
			user=null;
		
		}
		 catch (SQLException e) {
				System.out.println("Error while adding user: " + e.getMessage());
			}
		
		return user;
	}
	
	public boolean checkAvailable(String value){
		ResultSet rs; 
		boolean aval=false;
		try{
			Connection con = DatabaseUtilities.getConnection();
			String query="select * from users where username='"+value+"'";
			PreparedStatement pr=con.prepareStatement(query);
			 rs=pr.executeQuery();
			 aval=rs.next();
			 
		}
		catch(SQLException e)
		{
			System.out.println("Error while finding user: " + e.getMessage());
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


	
}
