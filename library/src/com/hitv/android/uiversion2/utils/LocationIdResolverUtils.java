package com.hitv.android.uiversion2.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class LocationIdResolverUtils {
	
	public static String getLocationId(Context context) {
		Uri uri = Uri
				.parse("content://com.hitv.android.initinstall.provider.ConfigContentProvider/config");
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = null;
		try{
			cursor = resolver.query(uri, new String[] { "sn", "locationId",
					"roomNumber", "themeurl", "logourl", "backgroudurl",
					"cityName", "weatherSnapSource", "locationLanguage" }, null,
					null, null);
		}catch(Exception e){
		}
		String locationId = "";
		if (cursor != null && cursor.moveToFirst()) {
			locationId = cursor.getString(cursor.getColumnIndex("locationId"));
			cursor.close();
		}
		return locationId;
	}
	
	public static String getInfo(Context context){
		Uri uri = Uri
				.parse("content://com.hitv.android.initinstall.provider.ConfigContentProvider/config");
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = null;
		try{
			cursor = resolver.query(uri, new String[] { "sn", "locationId",
					"roomNumber", "themeurl", "logourl", "backgroudurl",
					"cityName", "weatherSnapSource", "locationLanguage" }, null,
					null, null);
		}catch(Exception e){
		}
		String info = "";
		if (cursor != null && cursor.moveToFirst()) {
			info = "&locationId=" + cursor.getString(cursor.getColumnIndex("locationId")) + "&roomNumber=" + cursor.getString(cursor.getColumnIndex("roomNumber"));
			cursor.close();
		}
		return info;
	}

}
