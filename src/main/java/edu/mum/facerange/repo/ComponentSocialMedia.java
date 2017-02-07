package edu.mum.facerange.repo;

import edu.mum.facerange.model.SocialMedia;

public interface ComponentSocialMedia {

	public SocialMedia getById(int id);
	
	public boolean add(SocialMedia socialMedia);
	
	public boolean removeById(int id);
	
}
