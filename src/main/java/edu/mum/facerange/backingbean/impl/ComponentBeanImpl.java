package edu.mum.facerange.backingbean.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.backingbean.ComponentBean;
import edu.mum.facerange.enumeration.ComponentType;
import edu.mum.facerange.enumeration.Service;
import edu.mum.facerange.model.BasicInfo;
import edu.mum.facerange.model.Component;
import edu.mum.facerange.model.ComponentImage;
import edu.mum.facerange.model.SocialMedia;
import edu.mum.facerange.service.ComponentService;

@Named("componentBean")
@SessionScoped
public class ComponentBeanImpl implements ComponentBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AuthenticationBeanImpl authenticationBean;

	@Inject
	private ComponentService componentService;

	private List<Component> components;

	private List<BasicInfo> basicInfos;

	private List<ComponentImage> componentImages;

	private List<SocialMedia> socialMedias;
	
	private Component component;
	
	private BasicInfo basicInfo;
	
	private ComponentImage componentImage;
	
	private SocialMedia socialMedia;

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public BasicInfo getBasicInfo() {
		if (basicInfo == null) {
			List<BasicInfo> basicInfos2 = getBasicInfos();
			if (basicInfos2 != null && basicInfos2.size() > 0) {
				basicInfo = basicInfos2.get(0);
			}
		}
		return basicInfo;
	}

	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}

	public ComponentImage getComponentImage() {
		return componentImage;
	}

	public void setComponentImage(ComponentImage componentImage) {
		this.componentImage = componentImage;
	}

	public SocialMedia getSocialMedia() {
		if (socialMedia == null) {
			List<SocialMedia> socialMedias2 = getSocialMedias();
			if (socialMedias2 != null && socialMedias2.size() > 0) {
				socialMedia = socialMedias2.get(0);
			}
		}
		return socialMedia;
	}

	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

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

	@Override
	public String addComponent() {
		boolean saveComponent = componentService.saveComponent(component, Service.CREATE);
		if (saveComponent) {
			if (basicInfo != null) {
				componentService.saveBasicInfo(basicInfo, Service.CREATE);
				basicInfo = null;
			}
			if (componentImage != null) {
				componentService.saveImageComponent(componentImage, Service.CREATE);
				componentImage = null;
			}
			if (socialMedia != null) {
				componentService.saveSocialMedia(socialMedia, Service.CREATE);
				socialMedia = null;
			}
		}
		return "index?faces-redirect=true";
	}

	@Override
	public String editComponent() {
		if (basicInfo != null) {
			componentService.saveBasicInfo(basicInfo, Service.UPDATE);
			basicInfo = null;
		}
		if (componentImage != null) {
			componentService.saveImageComponent(componentImage, Service.UPDATE);
			componentImage = null;
		}
		if (socialMedia != null) {
			componentService.saveSocialMedia(socialMedia, Service.UPDATE);
			socialMedia = null;
		}
		return "index?faces-redirect=true";
	}

	@Override
	public String removeImage(ComponentImage componentImage, int num) {
		if (num == 1) {
			componentImage.setImage1(null);
		} else if (num == 2) {
			componentImage.setImage2(null);
		} else if (num == 3) {
			componentImage.setImage3(null);
		}
		componentService.saveImageComponent(componentImage, Service.UPDATE);
		return "editprofile?faces-redirect=true";
	}

}
