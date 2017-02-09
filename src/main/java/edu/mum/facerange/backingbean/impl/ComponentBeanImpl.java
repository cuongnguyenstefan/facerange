package edu.mum.facerange.backingbean.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import edu.mum.facerange.backingbean.ComponentBean;
import edu.mum.facerange.enumeration.ComponentType;
import edu.mum.facerange.model.BasicInfo;
import edu.mum.facerange.model.Component;
import edu.mum.facerange.model.ComponentImage;
import edu.mum.facerange.model.SocialMedia;
import edu.mum.facerange.model.User;
import edu.mum.facerange.service.ComponentService;
import edu.mum.facerange.util.ConvertUtils;

@Named("componentBean")
@RequestScoped
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

	
	private Map<String, Object> getMapComponents() {
		return componentService.getComponents(getUserId());
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

	public User getLoggedUser() {
		return authenticationBean.getUser();
	}

	public int getUserId() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String parameter = req.getParameter("userid");
		Integer userId = getLoggedUser().getUserId();
		int parseInt = ConvertUtils.parseInt(parameter, userId);
		return parseInt;
	}
}
