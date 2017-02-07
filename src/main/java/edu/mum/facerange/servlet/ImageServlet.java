package edu.mum.facerange.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.facerange.util.DatabaseUtilities;

@WebServlet(name="image", value="/image")
public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Image bytes
			String id = req.getParameter("id");
			byte[] imageBytes = null;

			// Connect to the database
			Connection connection = DatabaseUtilities.getConnection();
			// Create the statement
			// This query is specific to MySQL, it returns only one row (using
			// 'LIMIT 1') - the last uploaded file
			PreparedStatement statement = connection.prepareStatement("SELECT id, file FROM files where id=?");

			statement.setInt(1, Integer.parseInt(id));
			
			ResultSet rs = statement.executeQuery();

			while (rs.next()) { // This will run only once
				imageBytes = rs.getBytes("file");
			}

			// Close the connection
			connection.close();

			resp.getOutputStream().write(imageBytes);
			resp.getOutputStream().close();

		} catch (Exception e) {
			// Display error message
			resp.getWriter().write(e.getMessage());
			resp.getWriter().close();
		}
	}
}
