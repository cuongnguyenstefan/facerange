package edu.mum.facerange.backingbean.impl;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import edu.mum.facerange.backingbean.ImageStoreBean;
import edu.mum.facerange.repo.ImageStoreDao;

@Named("imageStoreBean")
@RequestScoped
public class ImageStoreBeanImpl implements ImageStoreBean {

	@Inject
	private ImageStoreDao imageStoreDao;

	private UploadedFile file;

	public Integer storeImage() {

		try {
			int saveImage = imageStoreDao.saveImage(file.getInputstream());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload success",
					file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return saveImage;
		} catch (Exception e) {
			FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload error", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, errorMsg);
		}

		return 0;

	}

	// Getter method
	public UploadedFile getFile() {
		return file;
	}

	// Setter method
	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
