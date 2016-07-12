package com.hitv.android.hotel.mvp.views;

import java.util.List;

import android.os.Bundle;

import com.hitv.android.uiversion2.bean.Content;

public interface ITemplateView extends IBaseView{
	
	public void showDialog(boolean flag);

	public void constructView(List<Content> contents, int index);
	
	public void startQrcode(Bundle bundle);
	
	public void openVideoActivity(Bundle bundle);
	
	public void openImageActivity(Bundle bundle);
	
	public void startCarService(Bundle bundle);
	
	public void startMapService(Bundle bundle);

	public void showExplanUp();
	
	public void showExplanDown();
}
