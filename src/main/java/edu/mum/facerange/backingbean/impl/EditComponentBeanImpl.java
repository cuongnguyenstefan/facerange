package edu.mum.facerange.backingbean.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.mum.facerange.backingbean.EditComponentBean;
import edu.mum.facerange.enumeration.ComponentType;
import edu.mum.facerange.enumeration.Service;
import edu.mum.facerange.model.BasicInfo;
import edu.mum.facerange.model.Component;
import edu.mum.facerange.model.ComponentImage;
import edu.mum.facerange.model.SocialMedia;
import edu.mum.facerange.repo.ImageStoreDao;
import edu.mum.facerange.service.ComponentService;

@Named("editComponentBean")
@RequestScoped
public class EditComponentBeanImpl implements EditComponentBean, Serializable {

	private Component component;

	private BasicInfo basicInfo = new BasicInfo();

	private ComponentImage componentImage = new ComponentImage();

	private SocialMedia socialMedia = new SocialMedia();

	private static final long serialVersionUID = 1L;

	@Inject
	private AuthenticationBeanImpl authenticationBean;

	@Inject
	private ComponentService componentService;

	@Inject
	private ImageStoreDao imageStoreDao;

	private List<ComponentImage> componentImages;

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public BasicInfo getBasicInfo() {
		if (basicInfo == null || basicInfo.getBasicinfoId() == 0) {
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
		if (socialMedia == null || socialMedia.getSocialmediaId() == null) {
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

	@Override
	public String addComponent() {
		int saveComponent = componentService.saveComponent(component, Service.CREATE);
		if (saveComponent > 0) {
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
			if (basicInfo.getBasicinfoId() != 0) {
				componentService.saveBasicInfo(basicInfo, Service.UPDATE);
			} else {
				Component comp = new Component();
				comp.setComponentType(ComponentType.BASIC_INFO);
				comp.setUserId(authenticationBean.getUser().getUserId());
				int saveComponent = componentService.saveComponent(comp, Service.CREATE);
				basicInfo.setComponentId(saveComponent);
				componentService.saveBasicInfo(basicInfo, Service.CREATE);
			}
		}
		if (componentImage != null) {
			try {
				boolean changed = false;
				if (componentImage.getFile1() != null && !"".equals(componentImage.getFile1().getFileName())) {
					int saveImage = imageStoreDao.saveImage(componentImage.getFile1().getInputstream());
					componentImage.setImage1(saveImage + "");
					changed = true;
				}
				if (componentImage.getFile2() != null && !"".equals(componentImage.getFile2().getFileName())) {
					int saveImage = imageStoreDao.saveImage(componentImage.getFile2().getInputstream());
					componentImage.setImage2(saveImage + "");
					changed = true;
				}
				if (componentImage.getFile3() != null && !"".equals(componentImage.getFile3().getFileName())) {
					int saveImage = imageStoreDao.saveImage(componentImage.getFile3().getInputstream());
					componentImage.setImage3(saveImage + "");
					changed = true;
				}
				if (changed) {
					Component comp = new Component();
					comp.setComponentType(ComponentType.COMPONENT_IMAGE);
					comp.setUserId(authenticationBean.getUser().getUserId());
					int saveComponent = componentService.saveComponent(comp, Service.CREATE);
					componentImage.setComponentId(saveComponent);
					componentService.saveImageComponent(componentImage, Service.CREATE);
				}
			} catch (Exception e) {
				System.out.println("Exception trying to save images " + e.getMessage());
			}
		}
		if (socialMedia != null) {
			if (socialMedia.getSocialmediaId() != null) {
				componentService.saveSocialMedia(socialMedia, Service.UPDATE);
			} else {
				Component comp = new Component();
				comp.setComponentType(ComponentType.SOCIAL_MEDIA);
				comp.setUserId(authenticationBean.getUser().getUserId());
				int saveComponent = componentService.saveComponent(comp, Service.CREATE);
				socialMedia.setComponentId(saveComponent);
				componentService.saveSocialMedia(socialMedia, Service.CREATE);
			}
		}
		
		if (componentImages != null) {
			for (ComponentImage ci : componentImages) {
				try {
					boolean changed = false;
					if (ci.getFile1() != null && !"".equals(ci.getFile1().getFileName())) {
						int saveImage = imageStoreDao.saveImage(ci.getFile1().getInputstream());
						ci.setImage1(saveImage + "");
						changed = true;
					}
					if (ci.getFile2() != null && !"".equals(ci.getFile2().getFileName())) {
						int saveImage = imageStoreDao.saveImage(ci.getFile2().getInputstream());
						ci.setImage2(saveImage + "");
						changed = true;
					}
					if (ci.getFile3() != null && !"".equals(ci.getFile3().getFileName())) {
						int saveImage = imageStoreDao.saveImage(ci.getFile3().getInputstream());
						ci.setImage3(saveImage + "");
						changed = true;
					}
					if (changed) {
						componentService.saveImageComponent(ci, Service.UPDATE);
					}
				} catch (Exception e) {
					System.out.println("Exception trying to save images " + e.getMessage());
				}
			}
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

	@SuppressWarnings("unchecked")
	public List<BasicInfo> getBasicInfos() {
		Map<String, Object> mapComponents = getMapComponents();
		if (mapComponents == null) {
			return null;
		}
		return (List<BasicInfo>) mapComponents.get(ComponentType.BASIC_INFO.toString());
	}

	private Map<String, Object> getMapComponents() {
		return componentService.getComponents(authenticationBean.getUser().getUserId());
	}

	@SuppressWarnings("unchecked")
	public List<SocialMedia> getSocialMedias() {
		Map<String, Object> mapComponents = getMapComponents();
		if (mapComponents == null) {
			return null;
		}
		return (List<SocialMedia>) mapComponents.get(ComponentType.SOCIAL_MEDIA.toString());
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

}
