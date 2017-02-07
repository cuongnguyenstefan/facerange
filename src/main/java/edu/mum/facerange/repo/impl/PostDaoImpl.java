package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.mum.facerange.model.Post;
import edu.mum.facerange.repo.PostDao;
import edu.mum.facerange.util.DatabaseUtilities;

public class PostDaoImpl implements PostDao {

	@Override
	public void addPost(Post post) {
		try {
			String sql = "Insert into posts(userid, post) values (?,?)";
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, post.getUserId());
			prepareStatement.setString(2, post.getPost());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while adding posting: " + e.getMessage());
		}
	}

	@Override
	public List<Post> getUserPosts(int userId) {
		List<Post> results = new ArrayList<>();
		String sql = "SELECT postid, userid, post FROM posts WHERE userid = ? ORDER BY datecreated DESC";
	    try {
	    	Connection con = DatabaseUtilities.getConnection();
	    	PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, userId);
			
	        ResultSet rs = prepareStatement.executeQuery();
	        
	        while (rs.next()) {
	        	Post post = new Post();	        	
	        	post.setPostId(rs.getInt("postid"));
	        	post.setUserId(rs.getInt("userid"));
	        	post.setPost(rs.getString("post"));
	        	
	            results.add(post);
	        }
	    } catch (SQLException e ) {
	    	System.out.println("Error while get postings: " + e.getMessage());
	    } 

		return results;
	}

	@Override
	public void deletePost(int postId) {
		try {
			String sql = "delete from posts where postid = ?";
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, postId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while deleting posting: " + e.getMessage());
		}
	}

	@Override
	public void updatePost(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post getPost(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPosts(int offset, int size, int userId) {
	    List<Post> results = new ArrayList<>();
		String sql = "SELECT postid, userid, post FROM posts WHERE userid = ? ORDER BY datecreated desc LIMIT ? OFFSET ?";
	    try {
	    	Connection con = DatabaseUtilities.getConnection();
	    	PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, userId);
			prepareStatement.setInt(2, size);
			prepareStatement.setInt(3, offset);
			
	        ResultSet rs = prepareStatement.executeQuery();
	        
	        while (rs.next()) {
	        	Post post = new Post();	        	
	        	post.setPostId(rs.getInt("postid"));
	        	post.setUserId(rs.getInt("userid"));
	        	post.setPost(rs.getString("post"));
	        	
	            results.add(post);
	        }
	    } catch (SQLException e ) {
	    	System.out.println("Error while get postings: " + e.getMessage());
	    } 

		return results;
	}

}
