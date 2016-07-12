package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

public class PictureBean implements Serializable{
	
	private static final long serialVersionUID = 8907537731087532941L;
	
	private int type;
	private String photoUrl;
	private String photoLocalUrl;
	private String videoUrl;
	private String videoLocalUrl;
	
	public PictureBean(ContentPhotos photos){
		type = 1;
		photoUrl = photos.getPhotoUrl();
		photoLocalUrl = photos.getPhotoLocalUrl();
	}
	
	public PictureBean(ContentVideo video){
		type = 2;
		photoUrl = video.getTvVideoThumbnailUrl();
		photoLocalUrl = video.getTvVideoThumbnailLocalUrl();
		videoUrl = video.getTvVideoUrl();
		videoLocalUrl = video.getTvVideoLocalUrl();
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getPhotoLocalUrl() {
		return photoLocalUrl;
	}
	public void setPhotoLocalUrl(String photoLocalUrl) {
		this.photoLocalUrl = photoLocalUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getVideoLocalUrl() {
		return videoLocalUrl;
	}
	public void setVideoLocalUrl(String videoLocalUrl) {
		this.videoLocalUrl = videoLocalUrl;
	}
	
}
