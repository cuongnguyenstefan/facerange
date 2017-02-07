package edu.mum.facerange.backingbean.impl;

import java.util.List;

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
import edu.mum.facerange.repo.ComponentBasicInfoDao;
import edu.mum.facerange.repo.ComponentDao;
import edu.mum.facerange.repo.ComponentImageDao;
import edu.mum.facerange.repo.ComponentSocialMediaDao;
import edu.mum.facerange.util.ComponentUtilities;

@Named("componentBean")
@RequestScoped
public class ComponentBeanImpl implements ComponentBean {

	@Inject
	private AuthenticationBeanImpl authenticationBean;

	@Inject
	private ComponentDao componentDao;

	@Inject
	private ComponentBasicInfoDao basicInfoDao;

	@Inject
	private ComponentImageDao componentImageDao;

	@Inject
	private ComponentSocialMediaDao componentSocialMediaDao;

	private List<Component> components;

	private List<BasicInfo> basicInfos;

	private List<ComponentImage> componentImages;

	private List<SocialMedia> socialMedias;

	public List<Component> getComponents() {
		if (components == null) {
			components = componentDao.byUserId(authenticationBean.getUser().getUserId());
		}
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public List<BasicInfo> getBasicInfos() {
		if (basicInfos == null) {
			Component componentByType = ComponentUtilities.getComponentByType(getComponents(),
					ComponentType.BASIC_INFO);
			if (componentByType != null) {
				basicInfos = basicInfoDao.getByComponentId(componentByType.getComponentId());
			}
		}
		return basicInfos;
	}

	public void setBasicInfos(List<BasicInfo> basicInfos) {
		this.basicInfos = basicInfos;
	}

	public List<ComponentImage> getComponentImages() {
		if (componentImages == null) {
			Component componentByType = ComponentUtilities.getComponentByType(getComponents(),
					ComponentType.COMPONENT_IMAGE);
			if (componentByType != null) {
				componentImages = componentImageDao.getByComponentId(componentByType.getComponentId());
			}
		}
		return componentImages;
	}

	public void setComponentImages(List<ComponentImage> componentImages) {
		this.componentImages = componentImages;
	}

	public List<SocialMedia> getSocialMedias() {
		Component componentByType = ComponentUtilities.getComponentByType(getComponents(),
				ComponentType.COMPONENT_IMAGE);
		if (componentByType != null) {
			socialMedias = componentSocialMediaDao.getByComponentId(componentByType.getComponentId());
		}
		return socialMedias;
	}

	public void setSocialMedias(List<SocialMedia> socialMedias) {
		this.socialMedias = socialMedias;
	}

}
