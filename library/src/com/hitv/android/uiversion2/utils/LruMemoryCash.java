package com.hitv.android.uiversion2.utils;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.nostra13.universalimageloader.cache.memory.MemoryCache;

public class LruMemoryCash implements MemoryCache {

	private LruCache<String, WeakReference<Bitmap>> lruCache;
	private Map<String, Integer> map;

	private LruMemoryCash() {
		map = new HashMap<String, Integer>();
		int MAXMEMONRY = (int) (Runtime.getRuntime().maxMemory() / 1024);
		lruCache = new LruCache<String, WeakReference<Bitmap>>(MAXMEMONRY / 8) {

			@Override
			protected int sizeOf(String key, WeakReference<Bitmap> bitmap) {
				
				return map.get(key);
			}

			@Override
			protected void entryRemoved(boolean evicted, String key,
					WeakReference<Bitmap> oldValue, WeakReference<Bitmap> newValue) {
				if(evicted){
					map.remove(key);
				}
				//LogUtils.Log("LruMemoryCash", "!!!!!!!!!!!!!!>"+evicted + " : "+key + " : " + (oldValue.getRowBytes() * oldValue.getHeight() / 1024));
				/*if(oldValue != null){
					if(!oldValue.isRecycled()){
						oldValue.recycle();
						oldValue = null;
						System.gc();
					}
				}*/
			}

		};
	}

	private static LruMemoryCash instance;

	public static LruMemoryCash getInstance() {

		if (instance == null) {
			synchronized (LruMemoryCash.class) {
				if (instance == null) {
					instance = new LruMemoryCash();
				}
			}
		}

		return instance;
	}

	@Override
	public void clear() {
		lruCache.evictAll();
		map.clear();
	}

	@Override
	public Bitmap get(String arg0) {
		
		Bitmap result = null;
		WeakReference<Bitmap> reference = lruCache.get(arg0);
		if (reference != null) {
			result = reference.get();
		}
		return result;
		
	}

	@Override
	public Collection<String> keys() {
		synchronized (lruCache) {
			return new HashSet<String>(lruCache.snapshot().keySet());
		}
	}

	@Override
	public boolean put(String arg0, Bitmap arg1) {
		map.put(arg0, arg1.getRowBytes() * arg1.getHeight() / 1024);
		lruCache.put(arg0, new WeakReference<Bitmap>(arg1));
		return true;
	}

	@Override
	public Bitmap remove(String arg0) {
		Reference<Bitmap> bmpRef = lruCache.remove(arg0);
		map.remove(arg0);
		return bmpRef == null ? null : bmpRef.get();
	}

}
