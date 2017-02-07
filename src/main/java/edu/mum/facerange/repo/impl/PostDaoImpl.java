package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error while adding posting: " + e.getMessage());
		}
	}

	@Override
	public List<Post> getUserPosts(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(int postId) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
