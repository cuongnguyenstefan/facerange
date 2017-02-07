package edu.mum.facerange.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.facerange.model.ComponentImage;
import edu.mum.facerange.repo.ComponentImageDao;
import edu.mum.facerange.util.DatabaseUtilities;

public class ComponentImageDaoImpl implements ComponentImageDao {
	
	private String SELECT_BY_COMPONENTID = "SELECT * FROM componentimage WHERE componentid = ?";
	
	private String INSERT = "INSERT into componentimage(componentid, image1, image2, image3) values (?, ?, ?, ?)";
	
	private String DELETE = "DELETE FROM componentimage WHERE componentid = ?";

	public List<ComponentImage> getByComponentId(int id) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(SELECT_BY_COMPONENTID);
			prepareStatement.setInt(1, id);
			ResultSet executeQuery = prepareStatement.executeQuery();
			List<ComponentImage> listComponentImageFromResultSet = listComponentImageFromResultSet(executeQuery);
			if (listComponentImageFromResultSet.size() > 0) {
				return listComponentImageFromResultSet;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error while finding component image with id - " + id + ": " + e.getMessage());
		}
		return null;
	}
	
	public boolean add(ComponentImage componentImage) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(INSERT);
			prepareStatement.setInt(1, componentImage.getComponentId());
			prepareStatement.setString(2, componentImage.getImage1());
			prepareStatement.setString(3, componentImage.getImage2());
			prepareStatement.setString(4, componentImage.getImage3());
			prepareStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error while adding component image: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean removeByComponentId(int id) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(DELETE);
			prepareStatement.setInt(1, id);
			prepareStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error while removing component image - " + id + " " + e.getMessage());
			return false;
		}
		return true;
	}
	
	private List<ComponentImage> listComponentImageFromResultSet(ResultSet rs) throws SQLException {
		List<ComponentImage> componentImages = new ArrayList<ComponentImage>();
		
		while (rs.next()) {
			ComponentImage componentImage = new ComponentImage();
			componentImage.setComponentId(rs.getInt("componentid"));
			componentImage.setImage1(rs.getString("image1"));
			componentImage.setImage1(rs.getString("image2"));
			componentImage.setImage1(rs.getString("image3"));
			componentImage.setImageId(rs.getInt("imageid"));
			componentImages.add(componentImage);
		}
		
		return componentImages;
	}
}
