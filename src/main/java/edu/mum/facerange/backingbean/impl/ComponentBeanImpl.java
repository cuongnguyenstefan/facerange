package edu.mum.facerange.backingbean.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.backingbean.ComponentBean;
import edu.mum.facerange.enumeration.ComponentType;
import edu.mum.facerange.model.BasicInfo;
import edu.mum.facerange.model.Component;
import edu.mum.facerange.model.ComponentImage;
import edu.mum.facerange.model.SocialMedia;
import edu.mum.facerange.service.ComponentService;

@Named("componentBean")
<<<<<<< HEAD
@RequestScoped
public class ComponentBeanImpl implements ComponentBean {
=======
@SessionScoped
public class ComponentBeanImpl implements ComponentBean, Serializable {
>>>>>>> dbe0abf6ac159cfe088f53724a625896925aa760

	private static final long serialVersionUID = 1L;

	@Inject
	AuthenticationBeanImpl authenticationBean;

	@Inject
	ComponentService componentService;/* = new  ComponentServiceImpl();*/

	private List<Component> components;

	private List<BasicInfo> basicInfos;

	private List<ComponentImage> componentImages;

	private List<SocialMedia> socialMedias;

	private Map<String, Object> getMapComponents() {
//		return componentService.getComponents(authenticationBean.getUser().getUserId());
		return componentService.getComponents(9);
	}

	@SuppressWarnings("unchecked")
	public List<Component> getComponents() {
		Map<String, Object> mapComponents = getMapComponents();
		if (mapComponents == null) {
			return null;
		}
		components = (List<Component>) mapComponents.get(ComponentType.COMPONENT.toString());
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	@SuppressWarnings("unchecked")
	public List<BasicInfo> getBasicInfos() {
		// if (basicInfos == null) {
		// Component componentByType =
		// ComponentUtilities.getComponentByType(getComponents(),
		// ComponentType.BASIC_INFO);
		// if (componentByType != null) {
		// basicInfos =
		// basicInfoDao.getByComponentId(componentByType.getComponentId());
		// }
		// }
		Map<String, Object> mapComponents = getMapComponents();
		if (mapComponents == null) {
			return null;
		}
		basicInfos = (List<BasicInfo>) mapComponents.get(ComponentType.BASIC_INFO.toString());
		return basicInfos;
	}

	public void setBasicInfos(List<BasicInfo> basicInfos) {
		this.basicInfos = basicInfos;
	}

	@SuppressWarnings("unchecked")
	public List<ComponentImage> getComponentImages() {
		// if (componentImages == null) {
		// Component componentByType =
		// ComponentUtilities.getComponentByType(getComponents(),
		// ComponentType.COMPONENT_IMAGE);
		// if (componentByType != null) {
		// componentImages =
		// componentImageDao.getByComponentId(componentByType.getComponentId());
		// }
		// }
		Map<String, Object> mapComponents = getMapComponents();
		if (mapComponents == null) {
			return null;
		}
		componentImages = (List<ComponentImage>) mapComponents.get(ComponentType.COMPONENT_IMAGE.toString());
		return componentImages;
	}

	public void setComponentImages(List<ComponentImage> componentImages) {
		this.componentImages = componentImages;
	}

	@SuppressWarnings("unchecked")
	public List<SocialMedia> getSocialMedias() {
		// Component componentByType =
		// ComponentUtilities.getComponentByType(getComponents(),
		// ComponentType.COMPONENT_IMAGE);
		// if (componentByType != null) {
		// socialMedias =
		// componentSocialMediaDao.getByComponentId(componentByType.getComponentId());
		// }
		Map<String, Object> mapComponents = getMapComponents();
		if (mapComponents == null) {
			return null;
		}
		socialMedias = (List<SocialMedia>) mapComponents.get(ComponentType.SOCIAL_MEDIA.toString());
		return socialMedias;
	}

	public void setSocialMedias(List<SocialMedia> socialMedias) {
		this.socialMedias = socialMedias;
	}

}
