package edu.mum.facerange.backingbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import edu.mum.facerange.model.User;
import edu.mum.facerange.service.AuthenticationService;

@Named("userbean")
@SessionScoped
public class UserSignupBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private UploadedFile profilepic;
	@Inject AuthenticationService auth;
	User user;
	
	public User getUser() {
		return user;
	}

	public void setUser1(User user1) {
		this.user = user1;
	}

	public UploadedFile getProfilepice() {
		return profilepic;
	}

	public void setProfilepic(UploadedFile profilepic) {
		this.profilepic = profilepic;
	}


	
	public void  checkAvailableuser(ValueChangeEvent enven)//to check if user name is avalaible
	{
		
	}
	//saving file
	public void savePicture() throws IOException {
	    String filename = profilepic.getFileName();
	    
	    InputStream input = profilepic.getInputstream();
	    OutputStream output = new FileOutputStream(new File("/userprofileImage", filename));
	    user.setPicture(filename);

	    try {
	        IOUtils.copy(input, output);
	    } finally {
	        IOUtils.closeQuietly(input);
	        IOUtils.closeQuietly(output);
	    }
	}
	public String addUser() throws IOException
	{
		savePicture();
		auth.addUser(this.user);
		return "login1";
		
	}
		
	}
