package edu.mum.facerange.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtilities {

	private DatabaseUtilities() {
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(Configuration.DATABASE_URL, Configuration.DATABASE_USERNAME,
				Configuration.DATABASE_PASSWORD);
		return con;
	}

}
