package com.hitv.android.hotel.utils;

import java.io.File;
import org.apache.http.protocol.HTTP;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import com.duowan.mobile.netroid.Network;
import com.duowan.mobile.netroid.RequestQueue;
import com.duowan.mobile.netroid.cache.DiskCache;
import com.duowan.mobile.netroid.stack.HttpClientStack;
import com.duowan.mobile.netroid.stack.HttpStack;
import com.duowan.mobile.netroid.stack.HurlStack;
import com.duowan.mobile.netroid.toolbox.BasicNetwork;

public class Netroid {

	private static RequestQueue mQueue;
	private static Netroid netroid;
	
	public static Netroid init(Context context){
		if(netroid==null)
			netroid = new Netroid(context);
		return netroid;
	}
	
	private Netroid(Context context){
		int poolSize = RequestQueue.DEFAULT_NETWORK_THREAD_POOL_SIZE;
		HttpStack stack;
		String userAgent = "netroid/0";
		try {
			String packageName = context.getPackageName();
			PackageInfo info = context.getPackageManager().getPackageInfo(
					packageName, 0);
			userAgent = packageName + "/" + info.versionCode;
		} catch (NameNotFoundException e) {
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			stack = new HurlStack(userAgent, null);
		} else {
			stack = new HttpClientStack(userAgent);
		}

		Network network = new BasicNetwork(stack, HTTP.UTF_8);
		mQueue = new RequestQueue(network, poolSize,
				new DiskCache(new File(context.getCacheDir(),
						Const.HTTP_DISK_CACHE_DIR_NAME),
						Const.HTTP_DISK_CACHE_SIZE));
		mQueue.start();

	}
	
	public static RequestQueue newRequestQueue() {
		return mQueue;
	}

	public class Const {
		// http parameters
		public static final int HTTP_MEMORY_CACHE_SIZE = 2 * 1024 * 1024; // 2MB
		public static final int HTTP_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
		public static final String HTTP_DISK_CACHE_DIR_NAME = "netroid";
		public static final String USER_AGENT = "netroid.cn";
	}
}
