package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.mum.facerange.model.Like;
import edu.mum.facerange.repo.LikeDao;
import edu.mum.facerange.util.DatabaseUtilities;

public class LikeDaoImpl implements LikeDao {

	@Override
	public void addLike(Like like) {
		try {
			String sql = "Insert into likes(userid, postid) values (?,?)";
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, like.getUserId());
			prepareStatement.setInt(2, like.getPostId());
			prepareStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error while adding like: " + e.getMessage());
		}
	}

	@Override
	public int count(int postId) {
		int result = 0;
		String sql = "SELECT count(*) as count FROM likes WHERE postid = ?";
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, postId);

			ResultSet rs = prepareStatement.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("Error while counting likes: " + e.getMessage());
		}

		return result;
	}

	@Override
	public void deleteLike(int postId, int userId) {
		String sql = "DELETE FROM likes WHERE postid = ? and userid = ?";

		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, postId);
			prepareStatement.setInt(2, userId);
			prepareStatement.executeUpdate();

			con.close();
		} catch (SQLException e) {
			System.out.println("Error while delete likes: " + e.getMessage());
		}
	}

	@Override
	public boolean liked(int postId, int userId) {
		int result = 0;
		String sql = "SELECT count(*) as count FROM likes WHERE postid = ? and userid = ?";

		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, postId);
			prepareStatement.setInt(2, userId);
			ResultSet rs = prepareStatement.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("Error while counting likes: " + e.getMessage());
		}

		return result > 0;
	}

}
