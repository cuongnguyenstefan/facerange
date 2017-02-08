package edu.mum.facerange.repo;

import java.io.InputStream;

public interface ImageStoreDao {
	
	public int saveImage(InputStream is) throws Exception;
	
	public byte[] getImage(int id);

}
