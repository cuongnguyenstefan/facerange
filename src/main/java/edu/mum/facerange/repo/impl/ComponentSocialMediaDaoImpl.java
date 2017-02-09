package edu.mum.facerange.repo.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.facerange.model.SocialMedia;
import edu.mum.facerange.repo.ComponentSocialMediaDao;
import edu.mum.facerange.util.DatabaseUtilities;

//@Named("componentSocialMediaDao")
//@ApplicationScoped
public class ComponentSocialMediaDaoImpl implements ComponentSocialMediaDao, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2219647142868242644L;

	private String SELECT_BY_COMPONENTID = "SELECT * FROM socialmedia WHERE componentid = ?";

	private String INSERT = "INSERT into socialmedia(componentid, facebook, instagram, twitter, youtube) values (?, ?, ?, ?, ?)";

	private String DELETE_BY_COMPONENTID = "DELETE FROM socialmedia WHERE componentid = ?";
	
	private String UPDATE = "UPDATE socialmedia SET facebook = ?, instagram = ?, twitter = ?, youtube = ? WHERE socialmediaid = ?";

	private String DELETE_BY_ID = "DELETE FROM socialmedia WHERE socialmediaid = ?";

	private List<SocialMedia> listComponentSocialMediaFromResultSet(ResultSet rs) throws SQLException {
		List<SocialMedia> socialMedias = new ArrayList<SocialMedia>();

		while (rs.next()) {
			SocialMedia sm = new SocialMedia();
			sm.setSocialmediaId(rs.getInt("socialmediaid"));
			sm.setComponentId(rs.getInt("componentid"));
			sm.setFacebookLink(rs.getString("facebook"));
			sm.setInstagramLink(rs.getString("instagram"));
			sm.setTwitterLink(rs.getString("twitter"));
			sm.setYoutubeLink(rs.getString("youtube"));
			socialMedias.add(sm);
		}

		return socialMedias;
	}

	@Override
	public List<SocialMedia> getByComponentId(int id) {
		Connection con = null;
		try {
			con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(SELECT_BY_COMPONENTID);
			prepareStatement.setInt(1, id);
			ResultSet executeQuery = prepareStatement.executeQuery();
			List<SocialMedia> socialMedias = listComponentSocialMediaFromResultSet(executeQuery);
			if (socialMedias.size() > 0) {
				return socialMedias;
			}
		} catch (SQLException e) {
			System.out.println("Error while finding component social media with id - " + id + ": " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean add(SocialMedia socialMedia) {
		Connection con = null;
		try {
			con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(INSERT);
			prepareStatement.setInt(1, socialMedia.getComponentId());
			prepareStatement.setString(2, socialMedia.getFacebookLink());
			prepareStatement.setString(3, socialMedia.getInstagramLink());
			prepareStatement.setString(4, socialMedia.getTwitterLink());
			prepareStatement.setString(5, socialMedia.getYoutubeLink());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while adding component social media: " + e.getMessage());
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean removeByComponentId(int id) {
		Connection con = null;
		try {
			con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(DELETE_BY_COMPONENTID);
			prepareStatement.setInt(1, id);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while removing component social media - " + id + " " + e.getMessage());
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean update(SocialMedia socialMedia) {
		Connection con = null;
		try {
			con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(UPDATE);
			prepareStatement.setString(1, socialMedia.getFacebookLink());
			prepareStatement.setString(2, socialMedia.getInstagramLink());
			prepareStatement.setString(3, socialMedia.getTwitterLink());
			prepareStatement.setString(4, socialMedia.getYoutubeLink());
			prepareStatement.setInt(5, socialMedia.getSocialmediaId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while updating component social media: " + e.getMessage());
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean removeById(int id) {
		Connection con = null;
		try {
			con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(DELETE_BY_ID);
			prepareStatement.setInt(1, id);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while removing component social media - " + id + " " + e.getMessage());
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
