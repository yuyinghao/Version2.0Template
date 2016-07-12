package com.hitv.android.uiversion2.builder;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.adapter.GroupAdapter;
import com.hitv.android.uiversion2.bean.ButtonBean;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.bean.ContentPartner;
import com.hitv.android.uiversion2.bean.ContentSocialMedia;
import com.hitv.android.uiversion2.custom.FlowLayout;
import com.hitv.android.uiversion2.custom.HorizontalList;
import com.hitv.android.uiversion2.custom.RoundedImageView;
import com.hitv.android.uiversion2.custom.TypeButton;
import com.hitv.android.uiversion2.custom.TypeTextView;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.interfaces.OnServiceClickFixListener;
import com.hitv.android.uiversion2.interfaces.OnServiceClickListener;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ScrollCustomBuilder extends ScrollBuilder{

	protected int CONTENTWIDTH;
	protected float COMPOUNDDRAWABLEPADDING;
	protected float SERVICEBUTTONMARGINRIGHT;
	protected float CONTENTMARGINBOTTOM;
	protected float MARGINLEFT;
	
	public ScrollCustomBuilder(Context context) {
		super(context);
		CONTENTWIDTH = (int)context.getResources().getDimension(R.dimen.CONTENTWIDTH);
		COMPOUNDDRAWABLEPADDING = context.getResources().getDimension(R.dimen.COMPOUNDDRAWABLEPADDING);
		SERVICEBUTTONMARGINRIGHT = context.getResources().getDimension(R.dimen.SERVICEBUTTONMARGINRIGHT);
		CONTENTMARGINBOTTOM = context.getResources().getDimension(R.dimen.CONTENTMARGINBOTTOM);
		MARGINLEFT = context.getResources().getDimension(R.dimen.MARGINLEFT);
	}

	@Override
	public void setService(Content content, final OnServiceClickListener listener, String language) {

		String displayType = content.getDisplayType();
		String distance = content.getDistance();
		String distanceUnit = content.getDistanceUnit();
		List<ContentSocialMedia> contentSocialMedias = content.getContentSocialMedias();
		
		FlowLayout serviceLayout = new FlowLayout(context);
		
		String qrcode = "";
		if(TextUtils.isEmpty(language)){
			qrcode = content.getQrCtaEnText();
		}else if(language.equals("en_US")){
			qrcode = content.getQrCtaEnText();
		}else if(language.equals("zh_TW")){
			qrcode = content.getQrCtaTwText();
		}else if(language.equals("zh_CN")){
			qrcode = content.getQrCtaZhText();
		}
		if(!TextUtils.isEmpty(qrcode)){
			String [] qrcodes = qrcode.split("@@");
			if(qrcodes != null){
				for(int i = 0; i < qrcodes.length; i++){
					final int temp = i;
					addService(serviceLayout, qrcodes[i], R.drawable.qr_code, new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							if (listener instanceof OnServiceClickFixListener) {
								OnServiceClickFixListener newName = (OnServiceClickFixListener) listener;
								newName.qrCodeServiceClick(v, temp);
							}
						}
					});
				}
			}
		}
		

		boolean displayMap = false;
		float distanceValue = 0;
		String[] types = displayType.split(",");
		for(String type : types){
			int temp = 0;
			try{
				temp = Integer.parseInt(type);
			}catch(Exception e){
			}
			
			if(temp == 3){
				displayMap = true;
				break;
			}
		}
		try{
			distanceValue = Float.parseFloat(distance);
		}catch(Exception e){
		}
		if((distanceValue > 1000.0f && distanceUnit.equalsIgnoreCase("3")) || (distanceValue > 1.0f && distanceUnit.equalsIgnoreCase("2"))){
			addCarService(serviceLayout, listener);
		}
		if(displayMap){
			addMapService(serviceLayout, distanceValue, distanceUnit, listener);
		}
		
		for(ContentSocialMedia socialMedia : contentSocialMedias){
			addQrcodeService(serviceLayout, socialMedia, listener, language);
		}
		
		if(serviceLayout.getChildCount() > 0){
			addView(serviceLayout, getBuilderLayoutParams(CONTENTWIDTH, LinearLayout.LayoutParams.WRAP_CONTENT));
		}
		
	}
	
	protected void addQrcodeService(ViewGroup layout, final ContentSocialMedia socialMedia, final OnServiceClickListener listener, String language){
		String name = "";
		if(language.equals("en_US")){
			name = socialMedia.getScoialMediaTvEName();
		}else if(language.equals("zh_TW")){
			name = socialMedia.getScoialMediaTvCName();
		}else if(language.equals("zh_CN")){
			name = socialMedia.getScoialMediaTvCName();
		}
		addService(layout, name, R.drawable.qr_code, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.qrCodeServiceClick(v, socialMedia);
			}
		});
	}
	
	protected void addCarService(ViewGroup layout, final OnServiceClickListener listener){
		addService(layout, context.getResources().getString(R.string.carservice), R.drawable.carservice, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.carServiceClick(v);
			}
		});
	}
	
	protected void addMapService(ViewGroup layout, float distance, String distanceUnit, final OnServiceClickListener listener){
		String distanceInfo = getDistanceUnit(distance, distanceUnit);
		addService(layout, distanceInfo, R.drawable.juli, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.mapServiceClick(v);
			}
		});
	}
	
	@Override
	public void setButtons(ButtonBean... beans) {
		
		FlowLayout serviceLayout = new FlowLayout(context);
		
		for(ButtonBean bean : beans){
			addService(serviceLayout, bean.getName(), bean.getResource(), bean.getListener());
		}
		
		if(serviceLayout.getChildCount() > 0){
			addView(serviceLayout, getBuilderLayoutParams(CONTENTWIDTH, LinearLayout.LayoutParams.WRAP_CONTENT));
		}
	}
	
	protected void addService(ViewGroup layout, String name, int source, OnClickListener listener){
		TypeButton button = new TypeButton(context);
		button.setType(1);
		button.setGravity(Gravity.CENTER);
		button.setPadding((int)context.getResources().getDimension(R.dimen.buttongpadding), 0, (int)context.getResources().getDimension(R.dimen.buttongpadding), 0);
		
		if(source != 0){
			Drawable drawable = context.getResources().getDrawable(source);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
			button.setCompoundDrawables(drawable, null, null, null);
			button.setCompoundDrawablePadding((int)COMPOUNDDRAWABLEPADDING);
		}
		
		button.setText(name);
		button.setTextColor(context.getResources().getColor(R.color.color_ECECEC));
		button.setTextSize((int)context.getResources().getDimension(R.dimen.buttontextsize));
		button.setBackgroundResource(R.drawable.service_bg);
		button.setOnClickListener(listener);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int)context.getResources().getDimension(R.dimen.buttonheight));
		params.setMargins(0, 0, (int)SERVICEBUTTONMARGINRIGHT, (int)SERVICEBUTTONMARGINRIGHT);
		layout.addView(button, params);
	}

	@Override
	public void setAbout(String content) {
		setStringInform(context.getResources().getString(R.string.about), content);
	}

	@Override
	public void setInformation(String promotion, String openTime,
			String telephone, String address) {
		LinearLayout layout = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.information_layout, null);
		
		boolean flag = false;
		if(!TextUtils.isEmpty(promotion)){
			flag = true;
			setInformVisible(layout, R.id.information_promotiontitle, R.id.information_promotionvalue, promotion);
		}
		if(!TextUtils.isEmpty(openTime)){
			flag = true;
			setInformVisible(layout, R.id.information_openinghourstitle, R.id.information_openinghoursvalue, openTime);
		}
		if(!TextUtils.isEmpty(telephone)){
			flag = true;
			setInformVisible(layout, R.id.information_telephonetitle, R.id.information_telephonevalue, telephone);
		}
		if(!TextUtils.isEmpty(address)){
			flag = true;
			setInformVisible(layout, R.id.information_addresstitle, R.id.information_addressvalue, address);
		}
		
		if(flag){
			addView(layout, getBuilderLayoutParams(CONTENTWIDTH, LinearLayout.LayoutParams.WRAP_CONTENT));
		}
	}
	
	protected void setInformVisible(View parent, int sourceTitle, int sourceValue, String value){
		TypeTextView titleText = (TypeTextView)parent.findViewById(sourceTitle);
		TypeTextView valueText = (TypeTextView)parent.findViewById(sourceValue);
		titleText.setVisibility(View.VISIBLE);
		valueText.setText(Html.fromHtml(value));
		valueText.setVisibility(View.VISIBLE);
	}

	@Override
	public void setTips(String tips) {
		setStringInform(context.getResources().getString(R.string.tips), tips);
	}

	@Override
	public void setStringInform(String title, String content) {
		
		if(TextUtils.isEmpty(title) || TextUtils.isEmpty(content))
			return;
		
		LinearLayout stringInformLayout = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.stringinform_layout, null);
		
		TypeTextView aboutTitleText = (TypeTextView)stringInformLayout.findViewById(R.id.abouttitle);
		TypeTextView aboutContentText = (TypeTextView)stringInformLayout.findViewById(R.id.aboutcontent);
		aboutTitleText.setText(title);
		aboutContentText.setText(Html.fromHtml(content));
		
		addView(stringInformLayout, getBuilderLayoutParams(CONTENTWIDTH, LinearLayout.LayoutParams.WRAP_CONTENT));
	}

	@Override
	public void setPartners(List<ContentPartner> partners) {
		
		if(partners == null || partners.size() == 0)
			return;
		
		LinearLayout partnerLayout = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.partners_layout, null);
		
		TypeTextView detailText = (TypeTextView)partnerLayout.findViewById(R.id.partners_detail);
		FlowLayout iconLayout = (FlowLayout)partnerLayout.findViewById(R.id.partners_icons);
		
		detailText.setText(partners.get(0).getPartnerIntro());
		
		for(ContentPartner partner : partners){
			RoundedImageView icon = new RoundedImageView(context);
			icon.setCornerRadius(5.0f);
			icon.setScaleType(ScaleType.FIT_XY);
			int size = (int)context.getResources().getDimension(R.dimen.partnersize);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
			params.setMargins(0, 0, (int)context.getResources().getDimension(R.dimen.partnermargin), (int)SERVICEBUTTONMARGINRIGHT);
			iconLayout.addView(icon, params);
			
			ImageLoader.getInstance().displayImage(icon, ImageLoaderOptions.optionsByNullImg, partner.getTvPartnerLocalUrl(), partner.getTvPartnerUrl());
		}
		
		addView(partnerLayout, getBuilderLayoutParams(CONTENTWIDTH, LinearLayout.LayoutParams.WRAP_CONTENT));
		
	}
	
	@Override
	public void setDestinations(GroupAdapter adapter, OnItemClickListener listener) {
		setHorizontalList(context.getResources().getString(R.string.destinations), adapter, listener);
	}

	@Override
	public void setPictures(GroupAdapter adapter, OnItemClickListener listener) {
		setHorizontalList(context.getResources().getString(R.string.pictures), adapter, listener);
	}
	
	@Override
	public void setHorizontalList(String title, final GroupAdapter adapter, final OnItemClickListener listener){
		final LinearLayout layout = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.horizotal_list, null);
		addView(layout, getBuilderLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0, 0, 0, (int)CONTENTMARGINBOTTOM));
		TextView titleText = (TextView)layout.findViewById(R.id.horizontal_title);
		final TextView numText = (TextView)layout.findViewById(R.id.horizontal_num);
		titleText.setText(title);
		
		HorizontalList horizaontal = (HorizontalList)layout.findViewById(R.id.horizontal_list);
		horizaontal.setAdapter(adapter);
		horizaontal.setOnItemClickListener(listener);
		
		/*CustomHomeItemLayout horizaontal = new CustomHomeItemLayout(context);
		layout.addView(horizaontal);
		homeLayouts.add(horizaontal);
		
		horizaontal.setContentAdapter(adapter);
		horizaontal.setOnItemClickListener(listener);*/
		
		if(adapter.getCount() > 2){
			numText.setText((adapter.getCount()-2) + "");
			if(layout.getVisibility() != View.VISIBLE){
				layout.setVisibility(View.VISIBLE);
			}
		}
		
		adapter.registerDataSetObserver(new DataSetObserver() {
			@Override
			public void onChanged() {
				super.onChanged();
				numText.setText((adapter.getCount() - 2) + "");
				if(adapter.getCount() > 2){
					if(layout.getVisibility() != View.VISIBLE){
						layout.setVisibility(View.VISIBLE);
					}
				}else{
					if(layout.getVisibility() != View.GONE){
						layout.setVisibility(View.GONE);
					}
				}
				
			}
		});
	}
	
	protected LinearLayout.LayoutParams getBuilderLayoutParams(int width, int height){
		
		return getBuilderLayoutParams(width, height, (int)MARGINLEFT, 0, 0, (int)CONTENTMARGINBOTTOM);
	}
	
	protected LinearLayout.LayoutParams getBuilderLayoutParams(int width, int height, int marginLeft, int marginTop, int marginRight, int marginBottom){
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
		
		params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
		
		return params;
	}
	
	protected String getDistanceUnit(float distance, String distanceUnit){
		
		StringBuilder unit = new StringBuilder();
		
		if(distanceUnit.equals("1")){
			int temp = (int)distance;
			unit.append(temp).append(context.getResources().getString(R.string.louceng));
		}else if(distanceUnit.equals("2")){
			unit.append(distance).append(context.getResources().getString(R.string.gongli));
		}else if(distanceUnit.equals("3")){
			unit.append(distance).append(context.getResources().getString(R.string.mi));
		}
		
		return unit.toString();
	}

}
