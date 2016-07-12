package com.hitv.android.uiversion2.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolController {
	private static final int SDK_THREAD_COUNT = 5;

	private static ThreadPoolController mController = new ThreadPoolController();

	private ExecutorService mService = null;

	private ThreadPoolController() {
		mService = Executors.newFixedThreadPool(SDK_THREAD_COUNT);
	}

	public static ThreadPoolController getInstance() {
		return mController;
	}

	public void fetchData(Runnable runnable) {
		mService.execute(runnable);
	}
	
}
