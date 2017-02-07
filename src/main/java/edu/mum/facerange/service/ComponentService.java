package edu.mum.facerange.service;

import java.util.List;
import java.util.Map;

import edu.mum.facerange.model.Component;

public interface ComponentService {

	// for user to get their list of components - can be used in loading page
	// and edit page
	public Map<String, Object> getComponents(int userId);
	
	// for user to update their components.
	public boolean updateComponents(List<Component> components);

}
