package edu.mum.facerange.backingbean.impl;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.backingbean.AuthenticationBean;
import edu.mum.facerange.model.User;
import edu.mum.facerange.service.AuthenticationService;

@Named("authenticationBean")
@SessionScoped
public class AuthenticationBeanImpl implements AuthenticationBean {

	@Inject
	private AuthenticationService authenticationService;

	private User user;

	@Override
	public String login() {
		boolean authenticated = authenticationService.authenticating(user);
		if (authenticated) {
			return "index";
		}
		user.setPassword("");
		return "authentication/login?faces-redirect=true";
	}

	@Override
	public String signup() {
		boolean signedup = authenticationService.signup(user);
		if (signedup) {
			return "index";
		}
		user.setPassword("");
		return "authentication/signup?faces-redirect=true";
	}

	@Override
	public void checkLogin(ComponentSystemEvent event) {
		if (user.getPassword() == null || "".equals(user.getPassword())) {
			FacesContext context = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication()
					.getNavigationHandler();
			handler.performNavigation("authentication/login");
		}
	}

	@Override
	public void checkLogged(ComponentSystemEvent event) {
		if (user.getPassword() != null && !"".equals(user.getPassword())) {
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
		boolean usernameExist = authenticationService.usernameExist(user.getEmail());
		if (usernameExist) {
			return "Username already exists";
		}
		return "";
	}

}
