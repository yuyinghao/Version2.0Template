package com.hitv.android.uiversion2.utils;

import java.util.Locale;

public class LangugeUtils {
	public static String getLanguge(){
		return initLanguage();
	}
	private static String initLanguage() {
    	Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        if (language.equals("en")) {
            return "en_US";
        } else {
        	String country = locale.getCountry();
        	if(country.equals("TW")){
        		return "zh_TW";
        	}else{
        		return "zh_CN";
        	}
        }
        
    }
}
