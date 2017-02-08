package edu.mum.facerange.backingbean;

import edu.mum.facerange.model.ComponentImage;

public interface ComponentBean {
	
	public String addComponent();
	
	public String editComponent();
	
	public String removeImage(ComponentImage componentImage, int num);

}
