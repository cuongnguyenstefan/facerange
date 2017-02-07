package edu.mum.facerange.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import edu.mum.facerange.enumeration.ComponentType;
import edu.mum.facerange.model.BasicInfo;
import edu.mum.facerange.model.Component;
import edu.mum.facerange.model.ComponentImage;
import edu.mum.facerange.model.SocialMedia;
import edu.mum.facerange.repo.ComponentBasicInfoDao;
import edu.mum.facerange.repo.ComponentDao;
import edu.mum.facerange.repo.ComponentImageDao;
import edu.mum.facerange.repo.ComponentSocialMediaDao;
import edu.mum.facerange.service.ComponentService;

@ApplicationScoped
public class ComponentServiceImpl implements ComponentService {
	
	@Inject
	private ComponentDao componentDao/* = new  ComponentDaoImpl()*/;
	
	@Inject
	private ComponentBasicInfoDao componentBasicInfoDao/* = new ComponentBasicInfoDaoImpl()*/;
	
	@Inject
	private ComponentImageDao componentImageDao/* = new ComponentImageDaoImpl()*/;
	
	@Inject
	private ComponentSocialMediaDao componentSocialMediaDao/* = new ComponentSocialMediaDaoImpl()*/;

	@Override
	public boolean updateComponents(List<Component> components) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getComponents(int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Component> components = componentDao.byUserId(userId);
		if (components == null) {
			return null;
		}
		map.put(ComponentType.COMPONENT.toString(), components);
		for (Component c : components) {
			switch (c.getComponentType()) {
			case COMPONENT_IMAGE:
				List<ComponentImage> byComponentId = componentImageDao.getByComponentId(c.getComponentId());
				if (byComponentId != null) {
					map.put(ComponentType.COMPONENT_IMAGE.toString(), byComponentId);
				}
				break;
			case BASIC_INFO:
				List<BasicInfo> byComponentId2 = componentBasicInfoDao.getByComponentId(c.getComponentId());
				if (byComponentId2 != null) {
					map.put(ComponentType.BASIC_INFO.toString(), byComponentId2);
				}
				break;
			case SOCIAL_MEDIA:
				List<SocialMedia> byComponentId3 = componentSocialMediaDao.getByComponentId(c.getComponentId());
				if (byComponentId3 != null) {
					map.put(ComponentType.SOCIAL_MEDIA.toString(), byComponentId3);
				}
				break;
			default:
				break;
			}
		}
		return map;
	}
	
	

}
