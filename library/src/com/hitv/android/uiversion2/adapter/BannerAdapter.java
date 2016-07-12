package com.hitv.android.uiversion2.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.bean.ContentRecommendPhotos;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BannerAdapter extends AbstractAdapter<Content> {

	private String language;
	
	public BannerAdapter(Context mContext, String language) {
		super(mContext);
		this.language = language;
	}

	@Override
	protected View createView(int position, ViewGroup parent) {
		
		View group = LayoutInflater.from(mContext).inflate(R.layout.banner_layout, null);
		
		ImageView view = (ImageView)group.findViewById(R.id.banner_icon_image);
		
		final Content content = linkedList.get(position);
		
		List<ContentRecommendPhotos> contentRecommendPhotos = content
				.getContentRecommendPhotos();
		if (contentRecommendPhotos != null) {
			boolean isSetImage = false;
			for (ContentRecommendPhotos temp : contentRecommendPhotos) {
				if (language.equals(temp.getContentRecommendPhotoLanguage()) && temp.getDeviceType() == 1) {
					ImageLoader.getInstance().displayImage(view,
							ImageLoaderOptions.optionsByDefaultImg,
							temp.getContentRecommendPhotoLocalUrl(),
							temp.getContentRecommendPhotoUrl());
					
					isSetImage = true;
					break;
				}
			}
			if(!isSetImage){
				view.setBackgroundResource(R.drawable.mrjat);
			}
		} else {
			view.setBackgroundResource(R.drawable.mrjat);
		}

		return group;
	}


}
