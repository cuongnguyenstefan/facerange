package edu.mum.facerange.backingbean;

import javax.faces.event.ComponentSystemEvent;

public interface AuthenticationBean {
	
	public void  login();
	
	public void checkLogin(ComponentSystemEvent event);
	
	public void checkLogged(ComponentSystemEvent event);
	
	public String signup();
	
	public String existUsername();
}
