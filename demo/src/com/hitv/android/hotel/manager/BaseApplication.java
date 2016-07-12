package com.hitv.android.hotel.manager;

import android.app.Application;
import android.content.Context;

import com.dmx.android.config.ConfigProperties;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.hotel.utils.Netroid;
import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.statistics.sdk.SDKManager.TERMINAL_TYPE;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.hitv.android.uiversion2.utils.LocationIdResolverUtils;
import com.hitv.android.uiversion2.utils.LruMemoryCash;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.analytics.MobclickAgent;

public class BaseApplication implements
		com.dmx.android.config.ApplicationUtil.ApplicationContinue {
	
	private static Context mContext;

	@Override
	public void onCreate(Application arg0) {
		mContext = arg0.getApplicationContext();
		GlobalParams.LOCATION_ID = LocationIdResolverUtils.getLocationId(arg0);
		GlobalParams.BASEURL = ConfigProperties.getProperty("com.hitv.android.UrlPath");
		initImageLoader(arg0);
		Netroid.init(arg0);
		MobclickAgent.openActivityDurationTrack(false);
		initSDK();
	}
	
	public static Context getContext(){
		return mContext;
	}

	@Override
	public void onTerminate(Application arg0) {
	}

	private void initImageLoader(Context context) {
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.memoryCache(LruMemoryCash.getInstance())
				.memoryCacheSize(2 * 1024 * 1024)  
				.memoryCacheExtraOptions(ImageLoaderOptions.MAX_IMAGE_WIDTH,
						ImageLoaderOptions.MAX_IMAGE_HEIGHT)
				.threadPoolSize(3)
				.threadPriority(Thread.NORM_PRIORITY - 2)  
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheSize(ImageLoaderOptions.MAX_IMAGE_DISK_CACHE_SIZE)
				.diskCacheFileCount(ImageLoaderOptions.MAX_IMAGE_DISK_FILE_COUNT)
				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
        
		ImageLoader.getInstance().init(config);
	}

	private void initSDK(){
    	String appCode = ConfigProperties.getProperty("com.hitv.android.hotel.appCode");
    	String appId = ConfigProperties.getProperty("com.hitv.android.hotel.appId");
    	SDKManager.init(getContext(), "");
		SDKManager.createUnit(TERMINAL_TYPE.TV, appCode, false, appId);
    }
}
