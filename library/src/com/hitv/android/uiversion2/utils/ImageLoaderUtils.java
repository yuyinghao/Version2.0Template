package com.hitv.android.uiversion2.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ImageLoaderUtils {
	private static ImageLoaderUtils imageLoaderUtils;

	public static ImageLoaderUtils getInstance() {
		if (imageLoaderUtils == null) {
			imageLoaderUtils = new ImageLoaderUtils();
		}
		return imageLoaderUtils;
	}

	public void imageLoader(final String localUrl, final String url,final ImageView imageView, final DisplayImageOptions options,ImageLoadingListener listener) {
		try {
			if(!StringUtils.isEmpty(localUrl)&&!StringUtils.isEmpty(url)){
				loadingImage(localUrl,url,imageView,options,listener);
			}else if(!StringUtils.isEmpty(url)&&StringUtils.isEmpty(localUrl)){
				loadingImage(url,imageView,options,listener);
			}else if(StringUtils.isEmpty(url)&&!StringUtils.isEmpty(localUrl)){
				loadingImage(localUrl,imageView,options,listener);
			}else{
				if(listener!=null)
					listener.onLoadingFailed(null, null, null);
			}
		} catch (Exception e) {
			if(listener!=null)
				listener.onLoadingFailed(null, null, null);
			e.printStackTrace();
		}
		
	}
	private static void loadingImage(String localUrl,final String url,final ImageView imageView,final DisplayImageOptions options,final ImageLoadingListener listenr){
		ImageLoader.getInstance().displayImage(imageView, options, new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String arg0, View arg1) {
			}
			
			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				if(listenr!=null){
					listenr.onLoadingFailed(arg0, arg1, arg2);
				}
			}
			
			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
				if(listenr!=null){
					listenr.onLoadingComplete(arg0, arg1, arg2);
				}
			}
			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
			}
		},localUrl,url);
	}
	private static void loadingImage(final String url,final ImageView imageView,final DisplayImageOptions options,final ImageLoadingListener listenr){
		ImageLoader.getInstance().displayImage(imageView, options, new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String arg0, View arg1) {
			}
			
			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				if(listenr!=null){
					listenr.onLoadingFailed(arg0, arg1, arg2);
				}
			}
			
			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
				if(listenr!=null){
					listenr.onLoadingComplete(arg0, arg1, arg2);
				}
			}
			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
			}
		},url);
	}
}
