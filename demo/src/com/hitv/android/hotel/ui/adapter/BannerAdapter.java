package com.hitv.android.hotel.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.adapter.AbstractAdapter;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.bean.ContentBackground;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BannerAdapter extends AbstractAdapter<Content> {

	public BannerAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	protected View createView(int position, ViewGroup parent) {
		
		ImageView view = new ImageView(mContext);
		
		final Content content = linkedList.get(position);
		
		List<ContentBackground> contentBackgrounds = content.getContentBackgrounds();
		
		if(contentBackgrounds != null && contentBackgrounds.size() > 0){
			ContentBackground background = contentBackgrounds.get(0);
			ImageLoader.getInstance().displayImage(view,
					ImageLoaderOptions.optionsByDefaultImg,
					background.getBackgroundLocalUrl(), background.getBackgroundUrl());
		}else{
			view.setBackgroundResource(R.drawable.mrjat);
		}

		return view;
	}


}
