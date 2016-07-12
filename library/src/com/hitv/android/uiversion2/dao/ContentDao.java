package com.hitv.android.uiversion2.dao;

import android.text.TextUtils;

import com.dmx.common.xml.util.XmlUtils;
import com.hitv.android.uiversion2.bean.ContentResponse;
import com.hitv.android.uiversion2.utils.LogUtils;

public class ContentDao {
	
	private static final String TAG = "ContentDao";
	
	public ContentResponse getContent(String value){
		
		ContentResponse response = null;
		
		if(!TextUtils.isEmpty(value)){
			try{
				response = XmlUtils.getObjectbyXml(value, ContentResponse.class);
			}catch(Exception e){
				LogUtils.Log(TAG, e.getMessage());
			}
		}
		
		return response;
		
	}
}
