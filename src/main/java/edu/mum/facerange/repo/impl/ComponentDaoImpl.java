package edu.mum.facerange.repo.impl;

import java.util.List;

import edu.mum.facerange.model.Component;

public interface ComponentDaoImpl {
	
	public List<Component> byUserId(int userId);
	
	public boolean addComponent(Component component);
	
	public boolean removeComponent(int componentId);
	
}
