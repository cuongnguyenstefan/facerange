package edu.mum.facerange.repo;

import edu.mum.facerange.model.BasicInfo;

public interface ComponentBasicInfoDao {
	
	public BasicInfo getByComponentId(int id);
	
	public boolean add(BasicInfo basicInfo);
	
	public boolean removeByComponentId(int id);

}
