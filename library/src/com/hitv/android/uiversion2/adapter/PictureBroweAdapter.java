package com.hitv.android.uiversion2.adapter;

import java.util.ArrayList;
import java.util.List;

import com.hitv.android.uiversion2.bean.ContentPhotos;
import com.hitv.android.uiversion2.fragment.PictureBrowseFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PictureBroweAdapter extends FragmentPagerAdapter {
	private List<ContentPhotos> photos = new ArrayList<ContentPhotos>();
	
	public void setData(List<ContentPhotos> photos){
		this.photos = photos;
		notifyDataSetChanged();
	}
	
	public PictureBroweAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		return new PictureBrowseFragment(photos.get(arg0));
	}

	@Override
	public int getCount() {
		return photos.size();
	}

}
