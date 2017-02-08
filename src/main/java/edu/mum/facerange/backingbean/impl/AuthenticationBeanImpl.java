package edu.mum.facerange.backingbean.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

import edu.mum.facerange.backingbean.AuthenticationBean;
import edu.mum.facerange.model.User;
import edu.mum.facerange.service.AuthenticationService;

@Named("authenticationBean")
@SessionScoped
public class AuthenticationBeanImpl implements AuthenticationBean, Serializable {

	private static final long serialVersionUID = 1L;

	// @Inject
	private AuthenticationService authenticationService;

	private User user;

	private boolean remember;

	@Override
	public String login() {
		User authenticating = authenticationService.authenticating(user);
		if (authenticating.getUserId() != null) {
			return "index";
		}
		user.setPassword("");
		return "authentication/login?faces-redirect=true";
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
		// TODO remove
		user = new User();
		user.setUserId(9);
		user.setUserName("ABC");
		user.setFullnane("DEF");
		user.setPassword("abc");
		if (user == null || user.getPassword() == null || "".equals(user.getPassword())) {
			FacesContext context = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication()
					.getNavigationHandler();
			handler.performNavigation("authentication/login?faces-redirect=true");
		}
	}

	@Override
	public void checkLogged(ComponentSystemEvent event) {
		if (user != null && user.getPassword() != null && !"".equals(user.getPassword())) {
			FacesContext context = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication()
					.getNavigationHandler();
			handler.performNavigation("index?faces-redirect=true");
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
		boolean usernameExist = authenticationService.usernameExist(user.getEmail());
		if (usernameExist) {
			return "Username already exists";
		}
		return "";
	}

	@Override
	public String signup() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

}
