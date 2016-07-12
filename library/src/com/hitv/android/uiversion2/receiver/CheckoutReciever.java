package com.hitv.android.uiversion2.receiver;

import com.hitv.android.uiversion2.utils.SPUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CheckoutReciever extends BroadcastReceiver{
	
	public static final String JUSNCHECKIN = "junstcheckin";

	@Override
	public void onReceive(Context context, Intent intent) {
		SPUtils.clear(context);
	}

}
