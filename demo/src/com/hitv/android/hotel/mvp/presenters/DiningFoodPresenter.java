package com.hitv.android.hotel.mvp.presenters;

import android.os.Bundle;

import com.hitv.android.hotel.mvp.views.IDiningFood;

public class DiningFoodPresenter extends TemplatePresenter<IDiningFood>{
	
	public DiningFoodPresenter(){
		super();
	}

	@Override
	protected String getContentPath(Bundle bundle) {
		return bundle.getString("path", "");
	}

	
}
