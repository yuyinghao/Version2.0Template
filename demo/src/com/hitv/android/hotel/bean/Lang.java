package com.hitv.android.hotel.bean;

import org.json.JSONException;
import org.json.JSONObject;


public class Lang {
	private String i18n;
	private String title;
	private String summary;
	
	public Lang(JSONObject object){
		try {
			i18n = object.getString("i18n");
			title = object.getString("title");
			summary = object.getString("summary");
		} catch (JSONException e) {
		}
		
	}

	public String getI18n() {
		return i18n;
	}

	public void setI18n(String i18n) {
		this.i18n = i18n;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
