package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import edu.mum.facerange.model.Comment;
import edu.mum.facerange.model.Post;
import edu.mum.facerange.repo.CommentDao;
import edu.mum.facerange.util.DatabaseUtilities;

public class CommentDaoImpl implements CommentDao {

	@Override
	public void addComment(Comment coment) {
		try {
			String sql = "Insert into comments(userid, postid, comment) values (?,?,?)";
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, coment.getUserId());
			prepareStatement.setInt(2, coment.getPostId());
			prepareStatement.setString(3, coment.getComment());
			prepareStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error while adding comment: " + e.getMessage());
		}
	}

	@Override
	public List<Comment> getPostComments(int postId) {
		List<Comment> results = new ArrayList<>();
		String sql = "SELECT commentid, postid, userid, comment FROM comments WHERE postid = ?";
		try {
			Connection con = DatabaseUtilities.getConnection();
	    	PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, postId);
			
	        ResultSet rs = prepareStatement.executeQuery();
	        
	        while (rs.next()) {
	        	Comment comment = new Comment();
	        	comment.setCommentId(rs.getInt("commentid"));
	        	comment.setPostId(rs.getInt("postid"));
	        	comment.setUserId(rs.getInt("userid"));
	        	comment.setComment(rs.getString("comment"));
	        	
	            results.add(comment);
	        }
	        
	        con.close();
	    } catch (SQLException e ) {
	    	System.out.println("Error while get comments: " + e.getMessage());
	    }
		
		return results;
	}

	@Override
	public void deleteComment(int commentId) {
		try {
			String sql = "delete from comments where commentid = ?";
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, commentId);
			prepareStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error while deleting comment: " + e.getMessage());
		}
	}

	@Override
	public void updatecomment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post getComment(int commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getLastComments(int postId, int lastCommentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getComments(int offset, int size, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(int postId) {
		int result = 0;
		String sql = "SELECT count(*) as count FROM comments WHERE postid = ?";
		try {
			Connection con = DatabaseUtilities.getConnection();
	    	PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, postId);
			
	        ResultSet rs = prepareStatement.executeQuery();
	        
	        if (rs.next()) {        	
	        	result = rs.getInt(1);
	        }
	        
	        con.close();
	    } catch (SQLException e ) {
	    	System.out.println("Error while counting comments: " + e.getMessage());
	    }
		
		return result;
	}

}
