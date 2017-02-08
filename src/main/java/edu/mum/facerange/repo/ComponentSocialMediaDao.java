package edu.mum.facerange.repo;

import java.util.List;

import edu.mum.facerange.model.SocialMedia;

public interface ComponentSocialMediaDao {

	public List<SocialMedia> getByComponentId(int id);

	public boolean add(SocialMedia socialMedia);

	public boolean removeByComponentId(int id);

	public boolean update(SocialMedia socialMedia);

	public boolean removeById(int id);

}
