package edu.mum.facerange.repo;

import edu.mum.facerange.model.SocialMedia;

public interface ComponentSocialMedia {

	public SocialMedia getByComponentId(int id);
	
	public boolean add(SocialMedia socialMedia);
	
	public boolean removeByComponentId(int id);
	
}
