package com.hitv.android.hotel.utils;

import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.duowan.mobile.netroid.DefaultRetryPolicy;
import com.duowan.mobile.netroid.Listener;
import com.duowan.mobile.netroid.NetroidError;
import com.duowan.mobile.netroid.NetroidLog;
import com.duowan.mobile.netroid.request.StringRequest;
import com.hitv.android.hotel.manager.BaseApplication;

public class NetworkUtils {

	public static NetworkUtils networkUtils = new NetworkUtils(
			BaseApplication.getContext());

	private NetworkUtils(Context context) {
	}

	public static NetworkUtils getInstance() {
		return networkUtils;
	}
	
	public void getConfig(String url, int method, final String mTag, int mTimes, final Listener<String> listener){
		Log.i("NetworkUtils", "-------------->url : "+url);
		StringRequest request = new StringRequest(method, url, new Listener<String>() {
			long startTimeMs;
		    int retryCount;
		    
		    @Override
		    public void onPreExecute() {
		        startTimeMs = SystemClock.elapsedRealtime();
		        if(listener != null){
		        	listener.onPreExecute();
		        }
		    }
		    
		    @Override
		    public void onSuccess(String response) {
		    	if(listener != null){
		    		listener.onSuccess(response);
		    	}
		    }

		    @Override
		    public void onError(NetroidError error) {
		    	error.printStackTrace();
		    	if(listener != null){
		    		listener.onError(error);
		    	}
		    }
		    
		    @Override
		    public void onCacheSuccess(String response) {
		    	super.onCacheSuccess(response);
		    	if(listener != null){
		    		listener.onCacheSuccess(response);
		    	}
		    }
		    
		    @Override
		    public void onRetry() {
		    	if(listener != null){
		    		listener.onRetry();
		    	}
		    	long executedTime = SystemClock.elapsedRealtime() - startTimeMs;
		        if (++retryCount > 5 || executedTime > 30000) {
		            NetroidLog.e("retryCount : " + retryCount + " executedTime : " + executedTime);
		            Netroid.newRequestQueue().cancelAll(mTag);
		        }
		        
		    }
		    
		    @Override
		    public void onNetworking() {
		    	super.onNetworking();
		    	if(listener != null){
		    		listener.onNetworking();
		    	}
		    }
		});

		// 设置请求Header
		request.setRetryPolicy(new DefaultRetryPolicy(10*1000, 3, 1f));
		request.addHeader("Accept-Encoding", "gzip, deflate");
		request.setTag(mTag);
		request.setCacheExpireTime(TimeUnit.SECONDS, mTimes);
		Netroid.newRequestQueue().add(request);
	}
	
	public void onActivityStop(String mTag){
		Netroid.newRequestQueue().cancelAll(mTag);
	}
}
