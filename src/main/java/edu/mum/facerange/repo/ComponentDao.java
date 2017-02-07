package edu.mum.facerange.repo;

import java.util.List;

import edu.mum.facerange.model.Component;

public interface ComponentDao {
	
	public List<Component> byUserId(int userId);
	
	public boolean addComponent(Component component);
	
	public boolean removeComponent(int componentId);
	
}
