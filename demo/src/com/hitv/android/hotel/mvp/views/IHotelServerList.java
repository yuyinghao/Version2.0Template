package com.hitv.android.hotel.mvp.views;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.hitv.android.hotel.bean.Channel;

public interface IHotelServerList extends IBaseView{
	public void updateData(List<Channel> channels);
	public void setBackGround(Drawable drawable);
	public void showProgressDialog(boolean flag);
	public void startOtherApp(String packageName, String activityName, Bundle bundle);
	public void openDetailActivity(Bundle bundle);
	public void openCategoryActivity(Bundle bundle);
	public void openAboutActivity(Bundle bundle);
	public void openMemberActivity(Bundle bundle);
	public void openDiningRoomActivity(Bundle bundle);
}