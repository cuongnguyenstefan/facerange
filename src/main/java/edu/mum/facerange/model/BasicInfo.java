package edu.mum.facerange.model;

public class BasicInfo {

	int basicinfoId;
	String from;
	String city;
	int componentId;
	String job;
	public int getBasicinfoId() {
		return basicinfoId;
	}
	public void setBasicinfoId(int basicinfoId) {
		this.basicinfoId = basicinfoId;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getComponentId() {
		return componentId;
	}
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	public boolean empty() {
		return (from == null || from == "") && (city == null || city == "") && (job == null || job == "");
	}
	
}
