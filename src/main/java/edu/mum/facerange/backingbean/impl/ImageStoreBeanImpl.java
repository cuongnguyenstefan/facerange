package edu.mum.facerange.backingbean.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.mysql.jdbc.Statement;

import edu.mum.facerange.backingbean.ImageStoreBean;
import edu.mum.facerange.util.DatabaseUtilities;

@Named("imageStoreBean")
@RequestScoped
public class ImageStoreBeanImpl implements ImageStoreBean {
	private UploadedFile file;

	// Store file in the database
	public Integer storeImage() {
		// Create connection
		try {
			// Load driver
			Connection connection = DatabaseUtilities.getConnection();
			// Set autocommit to false to manage it by hand
			connection.setAutoCommit(false);

			// Create the statement object
			PreparedStatement statement = connection.prepareStatement("INSERT INTO files (file) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			// Set file data
			statement.setBinaryStream(1, file.getInputstream());

			// Insert data to the database
			statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			// Commit & close
			connection.commit(); // when autocommit=false
			connection.close();

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload success",
					file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);

			if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
			
		} catch (Exception e) {
			e.printStackTrace();

			// Add error message
			FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload error", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, errorMsg);
		}
		
		return 0;

	}

	// Getter method
	public UploadedFile getFile() {
		return file;
	}

	// Setter method
	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
