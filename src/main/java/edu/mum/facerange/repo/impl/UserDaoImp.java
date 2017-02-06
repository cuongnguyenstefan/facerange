package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.mum.facerange.model.User;
import edu.mum.facerange.repo.UserDao;

public class UserDaoImp implements UserDao {
	
		@Override
		public void addUser(User user) throws SQLException {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection  con=DriverManager.getConnection
	                     ("jdbc:mysql://localhost:3306/facerance_db","root","");
				String  querry="insert into users(fullname,email,gender,password) values('"+user.getFullnane()+"','"+user.getEmail()+"','"+user.getGender()+"','"+user.getPassword()+"')";
				Statement st=con.createStatement();
				st.executeUpdate(querry);

			} catch (ClassNotFoundException e) {
				
				
			}
		}
		public static void main(String arg[]) 
		{
			UserDaoImp doaim=new  UserDaoImp();
			User user=new User();
			user.setEmail("2kkhenricharles@gmail.com");
			user.setFullnane("henricharles");
			user.setGender("mal");
			user.setPassword("12345");
			try{
			doaim.addUser(user);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
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


