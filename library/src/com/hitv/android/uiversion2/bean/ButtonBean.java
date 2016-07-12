package com.hitv.android.uiversion2.bean;

import android.view.View.OnClickListener;

public class ButtonBean {
	
	private String name;
	private int resource;
	private OnClickListener listener;
	
	public ButtonBean(String name, OnClickListener listener) {
		super();
		this.name = name;
		this.listener = listener;
	}
	
	public ButtonBean(String name, int resource, OnClickListener listener) {
		super();
		this.name = name;
		this.resource = resource;
		this.listener = listener;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getResource() {
		return resource;
	}
	public void setResource(int resource) {
		this.resource = resource;
	}
	public OnClickListener getListener() {
		return listener;
	}
	public void setListener(OnClickListener listener) {
		this.listener = listener;
	}
	
	

}
