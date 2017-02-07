package edu.mum.facerange.model;

import edu.mum.facerange.enumeration.ComponentType;

public class Component {
	private Integer componentId;
	
	private ComponentType componentType;
	
	private Integer userId;

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
