package com.hitv.android.hotel.mvp.presenters;

import android.os.Bundle;

import com.hitv.android.hotel.mvp.views.IHotelServerDetail;

public class HotelServerDetailPresenter extends TemplatePresenter<IHotelServerDetail>{
	
	public HotelServerDetailPresenter(){
		super();
	}

	@Override
	protected String getContentPath(Bundle bundle) {
		return bundle.getString("path", "");
	}
}
