package com.hitv.android.uiversion2.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.custom.RoundedDrawable;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * ImageLoaderOptions.java - ImageLoader配置参数
 */
public class ImageLoaderOptions {

    public static final int MAX_IMAGE_WIDTH = 1920; // 图片最大宽度480像素
    public static final int MAX_IMAGE_HEIGHT = 1080; // 图片最大高度480像素
    public static final int MAX_IMAGE_MEMORY_CACHE_SIZE = 2 * 1024 * 1024; // 内存缓存图片2MB
    public static final int MAX_IMAGE_DISK_CACHE_SIZE = 50 * 1024 * 1024; // SD卡缓存图片50MB
    public static final int MAX_IMAGE_DISK_FILE_COUNT = 200; // SD卡缓存图片最多为200个

    public static DisplayImageOptions optionsByNullImg =
            new DisplayImageOptions.Builder().showImageOnLoading(null) // 设置图片下载期间显示的图片
                    .showImageForEmptyUri(null) // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(null) // 设置图片加载或解码过程中发生错误显示的图片
                    .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                    .bitmapConfig(Bitmap.Config.RGB_565) // default
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .showDiskCacheOnLoading(true)
                    .build(); // 创建配置过得DisplayImageOption对象
    
    public static DisplayImageOptions optionsByDefaultImg =
            new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.mrjat) // 设置图片下载期间显示的图片
                    .showImageForEmptyUri(R.drawable.mrjat) // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.drawable.mrjat) // 设置图片加载或解码过程中发生错误显示的图片
                    .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                    .bitmapConfig(Bitmap.Config.RGB_565) // default
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .showDiskCacheOnLoading(true)
                    .build(); // 创建配置过得DisplayImageOption对象
    
    public static void recycleBitmap(Bitmap bitmap){
    	if(bitmap != null){
			if(!bitmap.isRecycled()){
				bitmap.recycle();
				bitmap = null;
				System.gc();
			}
		}
    }
    
    private static void recycleBitmap(View view){
    	if(view instanceof ViewGroup){
    		int count = ((ViewGroup) view).getChildCount();
    		for(int i = 0; i < count; i++){
    			View child = ((ViewGroup) view).getChildAt(i);
    			recycleBitmap(child);
    		}
    	}else{
    		if(view instanceof ImageView){
    			Drawable drawa = ((ImageView) view).getDrawable();
    			if(drawa != null){
    				if(drawa instanceof BitmapDrawable){
    					Bitmap bitmap = ((BitmapDrawable) drawa).getBitmap();
    					((ImageView) view).setImageDrawable(null);
    					if(bitmap != null){
    						if(!bitmap.isRecycled()){
    							ImageLoader.getInstance().cancelDisplayTask((ImageView)view);
    							bitmap.recycle();
    							bitmap = null;
    							System.gc();
    						}
    					}
    				}else if(drawa instanceof RoundedDrawable){
    					Bitmap bitmap = ((RoundedDrawable) drawa).getSourceBitmap();
    					((ImageView) view).setImageDrawable(null);
    					if(bitmap != null){
    						if(!bitmap.isRecycled()){
    	    					ImageLoader.getInstance().cancelDisplayTask((ImageView)view);
    							bitmap.recycle();
    							bitmap = null;
    							System.gc();
    						}
    					}
    					
    				}
    			}
    		}
    	}
    }
    

}
