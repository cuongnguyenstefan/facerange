package edu.mum.facerange.service;

public interface PageService {
	
	// load information of a page
	public boolean load();
	
	// post a new status
	public boolean post();
	
	// when user like something
	public boolean like();
	
	// add comment on a status
	public boolean comment();
	
	// give in post id, last comment id
	public boolean getNewComments();

}
