package com.hitv.android.uiversion2.utils;

import android.util.Log;

public class LogUtils {

	private static boolean isDebug = true;
	
	public static void setDebug(boolean flag){
		isDebug = flag;
	}
	
	public static void Log(String tag, String content){
		if(isDebug){
			Log.i(tag, content);
		}
		
	}
}
