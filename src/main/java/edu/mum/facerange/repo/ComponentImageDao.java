package edu.mum.facerange.repo;

import java.util.List;

import edu.mum.facerange.model.ComponentImage;

public interface ComponentImageDao {

	public List<ComponentImage> getByComponentId(int id);
	
	public boolean add(ComponentImage componentImage);
	
	public boolean removeByComponentId(int id);
	
	public boolean removeById(int id);
	
	public boolean update(ComponentImage componentImage);
	
}
