package edu.mum.facerange.enumeration;

public enum ComponentType {
	
	BASIC_INFO("BASIC_INFO"), COMPONENT_IMAGE("COMPONENT_IMAGE"), SOCIAL_MEDIA("SOCIAL_MEDIA"), COMPONENT("COMPONENT");
	
	private final String val;
	
	private ComponentType(String v) {
		val = v;
	}

	public static ComponentType from(String val) {
		switch (val) {
		case "BASIC_INFO":
			return ComponentType.BASIC_INFO;
		case "COMPONENT_IMAGE":
			return ComponentType.COMPONENT_IMAGE;
		case "SOCIAL_MEDIA":
			return ComponentType.SOCIAL_MEDIA;
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return val;
	}
}
