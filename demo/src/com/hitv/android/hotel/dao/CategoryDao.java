package com.hitv.android.hotel.dao;

import android.text.TextUtils;

import com.dmx.common.xml.util.XmlUtils;
import com.hitv.android.hotel.bean.CategoryResponse;

public class CategoryDao {
	public CategoryResponse getCategoryList(String response){
		CategoryResponse bean = null;
		
		if(!TextUtils.isEmpty(response)){
			try{
				bean = XmlUtils.getObjectbyXml(response, CategoryResponse.class);
			}catch(Exception e){
			}
		}
		
		return bean;
	}
}
