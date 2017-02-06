package edu.mum.facerange.backingbean.impl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import edu.mum.facerange.backingbean.AuthenticationBean;

@Named("authenticationBean")
@SessionScoped
public class AuthenticationBeanImpl implements AuthenticationBean {
	
	

	@Override
	public String login() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkLogin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkLogged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String signup() {
		// TODO Auto-generated method stub
		return null;
	}

}
