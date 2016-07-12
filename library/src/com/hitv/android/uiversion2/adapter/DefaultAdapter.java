package com.hitv.android.uiversion2.adapter;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.bean.ContentRecommendPhotos;
import com.hitv.android.uiversion2.custom.TypeTextView;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.hitv.android.uiversion2.utils.SPUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DefaultAdapter extends AbstractAdapter<Content> {

	private String language;

	public DefaultAdapter(Context mContext, String language) {
		super(mContext);
		this.language = language;
	}

	@Override
	protected View createView(int position, ViewGroup parent) {
		
		View groupView = LayoutInflater.from(mContext).inflate(R.layout.title_icon, null);
		final View heartView = groupView.findViewById(R.id.title_icon_heart);
		
		groupView.setClickable(true);
		
		ImageView view = (ImageView)groupView.findViewById(R.id.title_icon_image);
		final TypeTextView textView = (TypeTextView)groupView.findViewById(R.id.title_icon_heatindex);
		final Content content = linkedList.get(position);
		
		groupView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean flag = (Boolean)SPUtils.get(mContext, content.getContentId()+"1", false);
				int heart = 0;
				try{
					heart = Integer.parseInt((String)SPUtils.get(mContext, content.getContentId(), ""));
				}catch(Exception e){
					heart = -1;
				}
				
				ScaleAnimation animation = new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
				animation.setDuration(200);
				heartView.startAnimation(animation);
				
				if(heart != -1){
					if(flag){
						heart--;
					}else{
						heart++;
					}
					SPUtils.put(mContext, content.getContentId(), heart+"");
					SPUtils.put(mContext, content.getContentId()+"1", !flag);
					
					textView.setText(heart+"");
				}
				
			}
		});
		
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
		
		String heat = (String)SPUtils.get(mContext, content.getContentId(), "");
		
		if(TextUtils.isEmpty(heat)){
			if(TextUtils.isEmpty(content.getHeatIndex())){
				heat = getRandom(600, 1000);
			}else{
				heat = content.getHeatIndex();
			}
			SPUtils.put(mContext, content.getContentId(), heat);
		}
		
		textView.setText(heat);

		return groupView;
	}

	public static String getRandom(int min, int max) {
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return String.valueOf(s);
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.setPrimaryItem(container, position, object);
	}
}
