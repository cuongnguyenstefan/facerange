package edu.mum.facerange.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import edu.mum.facerange.enumeration.ComponentType;
import edu.mum.facerange.enumeration.Service;
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
	private ComponentDao componentDao/* = new ComponentDaoImpl() */;

	@Inject
	private ComponentBasicInfoDao componentBasicInfoDao/*
														 * = new
														 * ComponentBasicInfoDaoImpl
														 * ()
														 */;

	@Inject
	private ComponentImageDao componentImageDao/*
												 * = new ComponentImageDaoImpl()
												 */;

	@Inject
	private ComponentSocialMediaDao componentSocialMediaDao/*
															 * = new
															 * ComponentSocialMediaDaoImpl
															 * ()
															 */;

	@SuppressWarnings("unchecked")
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
					Object object = map.get(ComponentType.COMPONENT_IMAGE.toString());
					if (object != null) {
						List<ComponentImage> cis = (List<ComponentImage>) object;
						byComponentId.addAll(cis);
					}
					map.put(ComponentType.COMPONENT_IMAGE.toString(), byComponentId);
				}
				break;
			case BASIC_INFO:
				List<BasicInfo> byComponentId2 = componentBasicInfoDao.getByComponentId(c.getComponentId());
				if (byComponentId2 != null) {
					// Object object =
					// map.get(ComponentType.BASIC_INFO.toString());
					// if (object != null) {
					// List<BasicInfo> cis = (List<BasicInfo>) object;
					// byComponentId2.addAll(cis);
					// }
					map.put(ComponentType.BASIC_INFO.toString(), byComponentId2);
				}
				break;
			case SOCIAL_MEDIA:
				List<SocialMedia> byComponentId3 = componentSocialMediaDao.getByComponentId(c.getComponentId());
				if (byComponentId3 != null) {
					// Object object =
					// map.get(ComponentType.SOCIAL_MEDIA.toString());
					// if (object != null) {
					// List<SocialMedia> cis = (List<SocialMedia>) object;
					// byComponentId3.addAll(cis);
					// }
					map.put(ComponentType.SOCIAL_MEDIA.toString(), byComponentId3);
				}
				break;
			default:
				break;
			}
		}
		return map;
	}

	@Override
	public int saveComponent(Component component, Service service) {
		if (Service.CREATE.equals(service)) {
			return componentDao.addComponent(component);
		} else if (Service.DELETE.equals(service)) {
			componentDao.removeComponent(component.getComponentId());
			return component.getComponentId();
		} else if (Service.UPDATE.equals(service)) {
			// not supported
		}
		return component.getComponentId();
	}

	@Override
	public boolean saveBasicInfo(BasicInfo basicInfo, Service service) {
		if (Service.CREATE.equals(service)) {
			componentBasicInfoDao.add(basicInfo);
			return true;
		} else if (Service.DELETE.equals(service)) {
			componentBasicInfoDao.removeById(basicInfo.getBasicinfoId());
			return true;
		} else if (Service.UPDATE.equals(service)) {
			componentBasicInfoDao.update(basicInfo);
			return true;
		}
		return false;
	}

	@Override
	public boolean saveImageComponent(ComponentImage componentImage, Service service) {
		if (Service.CREATE.equals(service)) {
			componentImageDao.add(componentImage);
			return true;
		} else if (Service.DELETE.equals(service)) {
			componentImageDao.removeById(componentImage.getImageId());
			return true;
		} else if (Service.UPDATE.equals(service)) {
			componentImageDao.update(componentImage);
			return true;
		}
		return false;
	}

	@Override
	public boolean saveSocialMedia(SocialMedia socialMedia, Service service) {
		if (Service.CREATE.equals(service)) {
			componentSocialMediaDao.add(socialMedia);
			return true;
		} else if (Service.DELETE.equals(service)) {
			componentSocialMediaDao.removeById(socialMedia.getSocialmediaId());
			return true;
		} else if (Service.UPDATE.equals(service)) {
			componentSocialMediaDao.update(socialMedia);
			return true;
		}
		return false;
	}

}
