package edu.mum.facerange.util;

import java.util.List;

import edu.mum.facerange.enumeration.ComponentType;
import edu.mum.facerange.model.Component;

public class ComponentUtilities {

	private ComponentUtilities() {
		
	}
	
	public static Component getComponentByType(List<Component> components, ComponentType componentType) {
		if (components != null) {
			for (Component c : components) {
				if (componentType.equals(c.getComponentType())) {
					return c;
				}
			}
		}
		return null;
	}
}
