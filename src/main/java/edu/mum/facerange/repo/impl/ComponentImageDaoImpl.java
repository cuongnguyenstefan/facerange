package edu.mum.facerange.repo.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.mum.facerange.model.ComponentImage;
import edu.mum.facerange.repo.ComponentImageDao;
import edu.mum.facerange.util.DatabaseUtilities;

//@Named("componentImageDao")
//@ApplicationScoped
public class ComponentImageDaoImpl implements ComponentImageDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064116145219498479L;

	private String SELECT_BY_COMPONENTID = "SELECT * FROM componentimage WHERE componentid = ?";

	private String INSERT = "INSERT into componentimage(componentid, image1, image2, image3) values (?, ?, ?, ?)";

	private String DELETE_BY_COMPONENTID = "DELETE FROM componentimage WHERE componentid = ?";

	private String UPDATE = "UPDATE componentimage SET image1 = ?, image2 = ?, image3 = ? WHERE imageid = ?";

	private String DELETE_BY_ID = "DELETE FROM componentimage WHERE imageid = ?";

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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			System.out.println("Error while adding component image: " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean removeByComponentId(int id) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(DELETE_BY_COMPONENTID);
			prepareStatement.setInt(1, id);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
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
			componentImage.setImage2(rs.getString("image2"));
			componentImage.setImage3(rs.getString("image3"));
			componentImage.setImageId(rs.getInt("imageid"));
			componentImages.add(componentImage);
		}

		return componentImages;
	}

	@Override
	public boolean removeById(int id) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(DELETE_BY_ID);
			prepareStatement.setInt(1, id);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while removing component image - " + id + " " + e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean update(ComponentImage componentImage) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(UPDATE);
			if (componentImage.getImage1() != null) {
				prepareStatement.setString(1, componentImage.getImage1());
			} else {
				prepareStatement.setNull(1, Types.INTEGER);
			}
			if (componentImage.getImage2() != null) {
				prepareStatement.setString(2, componentImage.getImage2());
			} else {
				prepareStatement.setNull(2, Types.INTEGER);
			}
			if (componentImage.getImage3() != null) {
				prepareStatement.setString(3, componentImage.getImage3());
			} else {
				prepareStatement.setNull(3, Types.INTEGER);
			}
			prepareStatement.setInt(4, componentImage.getImageId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while updating component image: " + e.getMessage());
			return false;
		}
		return true;
	}
}
