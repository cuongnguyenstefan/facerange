package edu.mum.facerange.service;

public interface ComponentService {

	// for user to get their list of components - can be used in loading page
	// and edit page
	public boolean getComponents();

	// for user to update their components.
	public boolean updateComponents();

}
