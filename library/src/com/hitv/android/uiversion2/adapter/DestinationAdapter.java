package com.hitv.android.uiversion2.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DestinationAdapter extends GroupAdapter<Content>{
	
	private LayoutInflater inflater;

	public DestinationAdapter(Context context) {
		super(context);
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	protected View createView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		
		if(convertView != null){
			holder = (ViewHolder)convertView.getTag(R.id.aboutcontent);
		}else{
			convertView = inflater.inflate(R.layout.destination_item, parent, false);
			holder = new ViewHolder();
			convertView.setTag(R.id.aboutcontent, holder);
			holder.title = (TextView)convertView.findViewById(R.id.destination_title);
			holder.sugest = (TextView)convertView.findViewById(R.id.destination_sugest);
			holder.image = (ImageView)convertView.findViewById(R.id.destination_image);
			holder.recommend = (TextView)convertView.findViewById(R.id.destination_recommend);
			convertView.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if(listener != null){
						listener.itemFocusChange(v, hasFocus);
					}
					ViewHolder h = (ViewHolder)v.getTag(R.id.aboutcontent);
					if(h != null){
						if(hasFocus){
							h.title.setTextColor(mContext.getResources().getColor(R.color.color_ECECEC));
							h.image.setVisibility(View.GONE);
						}else{
							h.title.setTextColor(mContext.getResources().getColor(R.color.color_777777));
							h.image.setVisibility(View.VISIBLE);
						}
					}
					
				}
			});
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
			Content content = list.get(position-1);
			holder.title.setText(content.getFirstTitle());
			
			if(TextUtils.isEmpty(content.getSuggest())){
				holder.recommend.setText("");
				holder.sugest.setText("");
			}else{
				holder.recommend.setText(mContext.getResources().getString(R.string.recommendedtime));
				holder.sugest.setText(content.getSuggest());
			}

			ImageLoader.getInstance().displayImage(holder.image, ImageLoaderOptions.optionsByDefaultImg, content.getTvThumbnailLocalUrl(), content.getTvThumbnailUrl());

		}
		
		return convertView;
	}

	class ViewHolder{
		public TextView title;
		public TextView sugest;
		public TextView recommend;
		public ImageView image;
	}

}

