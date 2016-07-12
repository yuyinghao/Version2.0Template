package com.hitv.android.hotel.mvp.views;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.hitv.android.hotel.bean.Category;

public interface IHotelCategoryList extends IBaseView{
	public void updateData(List<Category> categorys);
	public void setBackGround(Drawable drawable);
	public void showProgressDialog(boolean flag);
	public void openCategoryActivity(Bundle bundle);
	public void openServerDetailActivit(Bundle bundle);

}
