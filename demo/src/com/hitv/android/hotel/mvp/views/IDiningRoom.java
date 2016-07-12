package com.hitv.android.hotel.mvp.views;

import java.util.List;

import com.hitv.android.hotel.bean.Category;

public interface IDiningRoom extends IBaseView{
	public void showDialog(boolean flag);
	public void updateTopButton(List<Category> categorys);
	public void onDataComplete(String path);
	public void setContentData(String path);
	public void requestTop();
}