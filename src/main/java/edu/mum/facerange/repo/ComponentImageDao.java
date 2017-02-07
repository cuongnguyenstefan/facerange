package edu.mum.facerange.repo;

import edu.mum.facerange.model.ComponentImage;

public interface ComponentImageDao {

	public ComponentImage getByComponentId(int id);
	
	public boolean add(ComponentImage componentImage);
	
	public boolean removeByComponentId(int id);
}
