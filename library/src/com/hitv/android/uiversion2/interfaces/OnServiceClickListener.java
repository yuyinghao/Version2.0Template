package com.hitv.android.uiversion2.interfaces;

import com.hitv.android.uiversion2.bean.ContentSocialMedia;

import android.view.View;

public interface OnServiceClickListener {
	public void carServiceClick(View viwe);
	public void mapServiceClick(View view);
	public void qrCodeServiceClick(View view, ContentSocialMedia value);
}
