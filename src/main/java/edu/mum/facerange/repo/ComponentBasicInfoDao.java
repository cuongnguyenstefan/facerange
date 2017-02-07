package edu.mum.facerange.repo;

import java.util.List;

import edu.mum.facerange.model.BasicInfo;

public interface ComponentBasicInfoDao {
	
	public List<BasicInfo> getByComponentId(int id);
	
	public boolean add(BasicInfo basicInfo);
	
	public boolean removeByComponentId(int id);

}
