package edu.mum.facerange.repo.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;

import com.mysql.jdbc.Statement;

import edu.mum.facerange.repo.ImageStoreDao;
import edu.mum.facerange.util.DatabaseUtilities;

@ApplicationScoped
public class ImageStoreDaoImpl implements ImageStoreDao {

	private final String INSERT = "INSERT INTO files (file) VALUES (?)";

	private final String SELECT = "SELECT id, file FROM files where id=?";

	@Override
	public int saveImage(InputStream is) throws Exception {
		Connection connection = null;
		try {
			connection = DatabaseUtilities.getConnection();
			connection.setAutoCommit(false);

			PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setBinaryStream(1, is);

			statement.executeUpdate();

			ResultSet generatedKeys = statement.getGeneratedKeys();

			connection.commit();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("Fail to save image: " + e.getMessage());
			throw e;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public byte[] getImage(int id) {
		byte[] imageBytes = null;
		Connection connection = null;
		try {
			connection = DatabaseUtilities.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT);
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				imageBytes = rs.getBytes("file");
			}

			connection.close();
		} catch (SQLException e) {
			System.out.println("Fail to get image id: " + id + " :" + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return imageBytes;
	}

}
