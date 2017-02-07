package edu.mum.facerange.repo.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.facerange.model.BasicInfo;
import edu.mum.facerange.repo.ComponentBasicInfoDao;
import edu.mum.facerange.util.DatabaseUtilities;

//@Named("componentBasicInfoDao")
//@ApplicationScoped
public class ComponentBasicInfoDaoImpl implements ComponentBasicInfoDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String SELECT_BY_COMPONENTID = "SELECT * FROM basicinfo WHERE componentid = ?";

	private String INSERT = "INSERT into basicinfo(componentid, from, city, job, age) values (?, ?, ?, ?, ?)";

	private String DELETE = "DELETE FROM basicinfo WHERE componentid = ?";

	public List<BasicInfo> getByComponentId(int id) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(SELECT_BY_COMPONENTID);
			prepareStatement.setInt(1, id);
			ResultSet executeQuery = prepareStatement.executeQuery();
			List<BasicInfo> basicInfos = listComponentBasicInfoFromResultSet(executeQuery);
			if (basicInfos.size() > 0) {
				return basicInfos;
			}
		} catch (SQLException e) {
			System.out.println("Error while finding component basic info with id - " + id + ": " + e.getMessage());
		}
		return null;
	}

	public boolean add(BasicInfo basicInfo) {
		try {
			Connection con = DatabaseUtilities.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(INSERT);
			prepareStatement.setInt(1, basicInfo.getComponentId());
			prepareStatement.setString(2, basicInfo.getFrom());
			prepareStatement.setString(3, basicInfo.getCity());
			prepareStatement.setString(4, basicInfo.getJob());
			prepareStatement.setInt(5, basicInfo.getAge());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while adding component basic info: " + e.getMessage());
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
		} catch (SQLException e) {
			System.out.println("Error while removing component basic info - " + id + " " + e.getMessage());
			return false;
		}
		return true;
	}
	
	private List<BasicInfo> listComponentBasicInfoFromResultSet(ResultSet rs) throws SQLException {
		List<BasicInfo> basicInfos = new ArrayList<BasicInfo>();
		
		while (rs.next()) {
			BasicInfo basicInfo = new BasicInfo();
			basicInfo.setAge(rs.getInt("age"));
			basicInfo.setBasicinfoId(rs.getInt("basicinfoid"));
			basicInfo.setCity(rs.getString("city"));
			basicInfo.setComponentId(rs.getInt("componentid"));
			basicInfo.setFrom(rs.getString("from"));
			basicInfo.setJob(rs.getString("job"));
			
			basicInfos.add(basicInfo);
		}
		
		return basicInfos;
	}

}
