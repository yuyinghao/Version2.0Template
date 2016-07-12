package com.hitv.android.uiversion2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.bean.PictureBean;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PictureAdapter extends GroupAdapter<PictureBean> {

	public PictureAdapter(Context context) {
		super(context);
	}

	@Override
	protected View createView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.picture_item, parent, false);
			convertView.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (listener != null) {
						listener.itemFocusChange(v, hasFocus);
					}
				}
			});
			holder = new ViewHolder();
			convertView.setTag(R.id.aboutcontent, holder);
			holder.image = (ImageView)convertView.findViewById(R.id.picture_item_image);
			holder.play = (ImageView)convertView.findViewById(R.id.picture_item_play);
		}else{
			holder = (ViewHolder)convertView.getTag(R.id.aboutcontent);
		}
		
		if(getItemViewType(position) == 0){
			if(convertView.getVisibility() != View.INVISIBLE){
				convertView.setVisibility(View.INVISIBLE);
			}
			convertView.setFocusable(false);
			LayoutParams params = new LayoutParams((int)mContext.getResources().getDimension(R.dimen.itemwidth), LayoutParams.WRAP_CONTENT);
			convertView.setLayoutParams(params);
		}else{
			if(convertView.getVisibility() != View.VISIBLE){
				convertView.setVisibility(View.VISIBLE);
			}
			convertView.setFocusable(true);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			convertView.setLayoutParams(params);
			if(list.get(position-1).getType() == 2){
				holder.play.setVisibility(View.VISIBLE);
			}else{
				holder.play.setVisibility(View.GONE);
			}
			ImageLoader.getInstance().displayImage(holder.image,
					ImageLoaderOptions.optionsByDefaultImg,
					list.get(position-1).getPhotoLocalUrl(),
					list.get(position-1).getPhotoUrl());
		}

		return convertView;
	}
	
	class ViewHolder{
		ImageView image;
		ImageView play;
	}
}
