package edu.mum.facerange.service;

import java.util.Map;

import edu.mum.facerange.enumeration.Service;
import edu.mum.facerange.model.BasicInfo;
import edu.mum.facerange.model.Component;
import edu.mum.facerange.model.ComponentImage;
import edu.mum.facerange.model.SocialMedia;

public interface ComponentService {

	// for user to get their list of components - can be used in loading page
	// and edit page
	public Map<String, Object> getComponents(int userId);
	
	public int saveComponent(Component component, Service service);
	
	public boolean saveBasicInfo(BasicInfo basicInfo, Service service);
	
	public boolean saveImageComponent(ComponentImage componentImage, Service service);
	
	public boolean saveSocialMedia(SocialMedia socialMedia, Service service);

}
