package edu.mum.facerange.backingbean.impl;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import edu.mum.facerange.backingbean.AuthenticationBean;
import edu.mum.facerange.model.User;
import edu.mum.facerange.repo.ImageStoreDao;
import edu.mum.facerange.service.AuthenticationService;

@Named("authenticationBean")
@SessionScoped
public class AuthenticationBeanImpl implements AuthenticationBean, Serializable {

	private static final long serialVersionUID = 1L;
	private UploadedFile profilepic;
	@Inject
	private AuthenticationService auth;
	private String userName;
	private String password;

	public UploadedFile getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(UploadedFile profilepic) {
		this.profilepic = profilepic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Inject
	private ImageStoreDao imageStoreDao;

	private User user = new User();

	@Override
	public void login() {
		
		user = auth.signin(this.userName, this.password);
		String url;
		if (user != null) {
			System.out.println(user.getEmail());
			url="index.jsf";//replace by index 
		}
		
		else
		{
			password="";
			url="login.jsf";
		}
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	// @Override
	// public String signup() {
	// boolean signedup = authenticationService.(user);
	// if (signedup) {
	// return "index";
	// }
	// user.setPassword("");
	// return "authentication/signup?faces-redirect=true";
	// }

	@Override
	public void checkLogin(ComponentSystemEvent event) {
		if (user == null || user.getPassword() == null || "".equals(user.getPassword())) {
			FacesContext context = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication()
					.getNavigationHandler();
			handler.performNavigation("login");
		}
	}

	@Override
	public void checkLogged(ComponentSystemEvent event) {
		if (user != null && user.getPassword() != null && !"".equals(user.getPassword())) {
			FacesContext context = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication()
					.getNavigationHandler();
			handler.performNavigation("index");
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String existUsername() {
		boolean usernameExist = auth.usernameExist(userName);
		if (usernameExist) {
			return "Username already exists";
		}
		return "";
	}

	@Override
	public String signup() {
		try {
			if (profilepic != null && !"".equals(profilepic.getFileName())) {
				//add the 
				int saveImage = imageStoreDao.saveImage(profilepic.getInputstream());
				//this.user.setPicture(saveImage);
				user.setPicture(saveImage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		auth.addUser(this.user);
		return "login?faces-redirect=true";
	}
	
	@PostConstruct
	public void init() {
	    user = new User();
	}

	public String logout() {
		this.user = null;
		return "login?faces-redirect=true";
	}
}
