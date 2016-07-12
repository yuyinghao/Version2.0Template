package com.hitv.android.hotel.dao;

import android.text.TextUtils;

import com.dmx.common.xml.util.XmlUtils;
import com.hitv.android.hotel.bean.ChildChannelResponse;

public class ChannelDao {
	
	public ChildChannelResponse getChannelList(String response){
		ChildChannelResponse bean = null;
		
		if(!TextUtils.isEmpty(response)){
			try{
				bean = XmlUtils.getObjectbyXml(response, ChildChannelResponse.class);
			}catch(Exception e){
			}
		}
		
		return bean;
	}
	
}
