package edu.mum.facerange.repo.impl;

import edu.mum.facerange.model.SocialMedia;

public interface ComponentSocialMediaImpl {

	public SocialMedia getById(int id);
	
	public boolean add(SocialMedia socialMedia);
	
	public boolean removeById(int id);
	
}
