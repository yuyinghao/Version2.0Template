package com.hitv.android.hotel.ui.activity;

import java.util.Locale;

import nucleus.presenter.Presenter;
import nucleus.view.NucleusFragmentActivity;
import android.os.Handler;

import com.hitv.android.hotel.params.GlobalParams;

@SuppressWarnings("rawtypes")
public class BaseActivity<P extends Presenter> extends NucleusFragmentActivity<P>{
	
	protected static Handler handler = new Handler();
	
	@Override
	protected void onResume() {
		super.onResume();
		initLanguage();
	}
	
	private void initLanguage() {
    	Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        if (language.equals("en")) {
            GlobalParams.IP_LANAGE = "en_US";
        } else {
        	String country = locale.getCountry();
        	if(country.equals("TW")){
        		GlobalParams.IP_LANAGE = "zh_TW";
        	}else{
        		GlobalParams.IP_LANAGE = "zh_CN";
        	}
        }
    }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacksAndMessages(null);
	}

}