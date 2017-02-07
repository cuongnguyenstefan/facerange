package edu.mum.facerange.repo.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.facerange.enumeration.ComponentType;
import edu.mum.facerange.model.Component;
import edu.mum.facerange.repo.ComponentDao;
import edu.mum.facerange.util.DatabaseUtilities;

//@Named("componentDao")
//@ApplicationScoped
public class ComponentDaoImpl implements ComponentDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2560542798642885410L;

	private String SELECT_BY_USERID = "SELECT * FROM component WHERE userid = ?";

	private String INSERT = "INSERT into component(componentid, componenttype, userid) values (?, ?, ?)";

	private String DELETE = "DELETE FROM component WHERE componentid = ?";

	public List<Component> byUserId(int userId) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(SELECT_BY_USERID);
			prepareStatement.setInt(1, userId);
			ResultSet executeQuery = prepareStatement.executeQuery();
			List<Component> components = listComponentFromResultSet(executeQuery);
			return components;
		} catch (SQLException e) {
			System.out.println("Error while finding component basic info with id - " + userId + ": " + e.getMessage());
		}
		return null;
	}

	public boolean addComponent(Component component) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(INSERT);
			prepareStatement.setInt(1, component.getComponentId());
			prepareStatement.setString(2, component.getComponentType().toString());
			prepareStatement.setInt(3, component.getUserId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while adding component basic info: " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean removeComponent(int componentId) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(DELETE);
			prepareStatement.setInt(1, componentId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while removing component basic info - " + componentId + " " + e.getMessage());
			return false;
		}
		return true;
	}

	private List<Component> listComponentFromResultSet(ResultSet rs) throws SQLException {
		List<Component> components = new ArrayList<Component>();

		while (rs.next()) {
			Component component = new Component();
			component.setComponentId(rs.getInt("componentid"));
			component.setComponentType(ComponentType.from(rs.getString("componenettype")));
			component.setUserId(rs.getInt("userid"));

			components.add(component);
		}

		return components;
	}
}
