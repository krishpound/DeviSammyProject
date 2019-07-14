package com.krishdevi.calif;

public class VisionObject {
	
	private String imgid;
	private String imgpath;
	private String imgname;
	private String imgmodified;
	private long imgsize;
	private String message;
	private String label;
	private float score;
	private int labelcount;
	private String visionrecord;
	
	public VisionObject(){
		this.imgid="";
		this.imgpath="";
		this.imgname="";
		this.imgmodified="";
		this.imgsize=0;
		this.label="";
		this.score=0.0f;
		this.message="";
		this.labelcount=0;
		this.visionrecord="";
	}
	public void setImgid(String s) {
		imgid = s;
	}
	public String getImgid() {
		return imgid;
	}
	public void setImgpath(String s) {
		imgpath = s;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgname(String s) {
		imgname = s;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgmodified(String s) {
		imgmodified = s;
	}
	public String getImgmodified() {
		return imgmodified;
	}
	public void setImgsize(long l) {
		imgsize = l;
	}
	public long getImgsize() {
		return imgsize;
	}
	public void setLabel(String s) {
		label = s;
	}
	public String getLabel() {
		return label;
	}
	public void setScore(Float f) {
		score = f;
	}
	public Float getScore() {
		return score;
	}
	public void setLabelcount(int i) {
		labelcount = i;
	}
	public int getLabelcount() {
		return labelcount;
	}
	public void setMessage(String s) {
		message = s;
	}
	public String getMessage() {
		return message;
	}
	public void setVisionrecord(String s) {
		visionrecord = s;
	}
	public String getVisionrecord() {
		return this.label + "\t\t" + this.score;
	}
}
