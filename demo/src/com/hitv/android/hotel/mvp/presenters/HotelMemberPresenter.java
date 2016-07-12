package com.hitv.android.hotel.mvp.presenters;

import android.os.Bundle;

import com.hitv.android.hotel.mvp.views.IHotelMember;
import com.hitv.android.hotel.params.GlobalParams;

public class HotelMemberPresenter extends TemplatePresenter<IHotelMember>{
	
	public HotelMemberPresenter(){
		super();
	}

	@Override
	protected String getContentPath(Bundle bundle) {
		String channelId = bundle.getString("channelId");
		return model.getChannelContentUrl(GlobalParams.BASEURL, GlobalParams.LOCATION_ID, channelId, GlobalParams.IP_LANAGE);
	}

}
