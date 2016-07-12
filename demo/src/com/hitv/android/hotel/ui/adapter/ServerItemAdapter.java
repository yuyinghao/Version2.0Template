package com.hitv.android.hotel.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hitv.android.hotel.R;
import com.hitv.android.hotel.bean.Channel;
import com.hitv.android.uiversion2.adapter.GroupAdapter;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ServerItemAdapter extends GroupAdapter<Channel> {

	public ServerItemAdapter(Context context) {
		super(context);
	}

	@Override
	protected View createView(int position, View convertView, ViewGroup parent) {

		View view = LayoutInflater.from(mContext).inflate( R.layout.server_item_layout, parent, false);

		final TextView textView = (TextView) view.findViewById(R.id.server_item_text);
		ImageView imageView = (ImageView) view.findViewById(R.id.server_item_icon);

		Channel channel = list.get(position);
		textView.setText(channel.getChannelName());
		
		ImageLoader.getInstance().displayImage(imageView, ImageLoaderOptions.optionsByNullImg, channel.getTvThumbnailLocalUrl(), channel.getTvThumbnailUrl());
		
		int numContent = 1;
		if(channel.getOpenType() == 3){
			numContent = channel.getHasRelCategory();
		}else if(channel.getOpenType() == 4){
			numContent = channel.getHasRelContent();
		}
		if(numContent > 0){
			textView.setAlpha(1.0f);
			imageView.setAlpha(1.0f);
			view.setFocusable(true);
			view.setClickable(true);
		}else{
			textView.setAlpha(0.7f);
			imageView.setAlpha(0.3f);
			view.setFocusable(false);
			view.setClickable(false);
		}
		
		view.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
				if(hasFocus){
					textView.setTextColor(mContext.getResources().getColor(R.color.color_ECECEC));
				}else{
					textView.setTextColor(mContext.getResources().getColor(R.color.color_777777));
				}
				
				if(listener != null){
					listener.itemFocusChange(v, hasFocus);
				}
			}
		});

		return view;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

}
