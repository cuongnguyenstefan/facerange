package edu.mum.facerange.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtilities {

	private DatabaseUtilities() {
	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(Configuration.DATABASE_URL, Configuration.DATABASE_USERNAME,
					Configuration.DATABASE_PASSWORD);
			return con;
		} catch (ClassNotFoundException e) {
			System.out.println("Database.getConnection() Error -->" + e.getMessage());
			return null;
		}
	}

}
