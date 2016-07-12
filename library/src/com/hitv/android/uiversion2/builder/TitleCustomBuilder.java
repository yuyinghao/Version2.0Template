package com.hitv.android.uiversion2.builder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.custom.FlowLayout;
import com.hitv.android.uiversion2.custom.GalleryViewPager;
import com.hitv.android.uiversion2.custom.TypeTextView;
import com.hitv.android.uiversion2.interfaces.OnCurrentPageTextChangeListener;

public class TitleCustomBuilder extends TitleBuilder{
	
	public TitleCustomBuilder(Context context) {
		super(context);
	}

	@Override
	public void setTitleIcon(View view) {
		if(view != null){
			RelativeLayout iconLayout = (RelativeLayout)titleLayout.findViewById(R.id.titleicon);
			android.widget.RelativeLayout.LayoutParams layoutParams = new android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.MATCH_PARENT, android.widget.RelativeLayout.LayoutParams.MATCH_PARENT);
			int margin = (int)context.getResources().getDimension(R.dimen.titleiconmargin);
			layoutParams.setMargins(margin, 0, margin, 0);
			layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			
			iconLayout.addView(view, layoutParams);
			checkIsViewPager(view);

		}
		
	}

	private void checkIsViewPager(View view) {
		if(view instanceof ViewPager){
			GalleryViewPager pager = (GalleryViewPager)view;
			TextView totalpageText = (TextView)titleLayout.findViewById(R.id.pagetotalpage);
			final TextView currentpageText = (TextView)titleLayout.findViewById(R.id.pagecurrentpage);
			int totalValue = pager.getTruthCount();

			if(totalValue < 2){
				titleLayout.findViewById(R.id.title_lefticon).setVisibility(View.GONE);
				titleLayout.findViewById(R.id.title_righticon).setVisibility(View.GONE);
			}
			totalpageText.setText(totalValue+"");
			try{
				currentpageText.setText( (pager.getCurrentItem()%totalValue+1) + "");
			}catch(Exception e){
				currentpageText.setText("1");
			}
			
			pager.setOnCurrentPageTextChangeListener(new OnCurrentPageTextChangeListener() {
				
				@Override
				public void currentPageTextChange(int page) {
					int value = page+1;
					currentpageText.setText(value+"");
				}
			});
			
		}else{
			TextView seprateText = (TextView)titleLayout.findViewById(R.id.pageseprate);
			seprateText.setVisibility(View.GONE);
		}
	}

	@Override
	public void setTitleText(String text) {
		TextView titleText = (TextView)titleLayout.findViewById(R.id.titletext);
		titleText.setText(text);
	}

	@Override
	public void setRating(float value) {
		
		if(value <= 0){
			return;
		}
		
		LinearLayout ratingLayout = (LinearLayout)titleLayout.findViewById(R.id.ratinglayout);
		ratingLayout.removeAllViews();
		int rating = (int)value;
		for(int i = 0; i < rating && i < 5; i++){
			addRating(ratingLayout, R.drawable.star_full, i);
		}
		
		if(value - rating > 0){
			addRating(ratingLayout, R.drawable.star_half, rating);
			rating++;
		}
		
		for(int i = rating; i < 5; i++){
			addRating(ratingLayout, R.drawable.star_empty, i);
		}
		
	}
	
	private void addRating(LinearLayout layout, int source, int index){
		ImageView v = new ImageView(context);
		v.setImageResource(source);

		if(index != 0){
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.setMargins((int)context.getResources().getDimension(R.dimen.ratingmargin), 0, 0, 0);
			v.setLayoutParams(layoutParams);
		}
		
		layout.addView(v);
	}

	@Override
	public void setSpecail(String specail) {
		FlowLayout labelLayout = (FlowLayout)titleLayout.findViewById(R.id.specaillayout);
		labelLayout.removeAllViews();
		if(!TextUtils.isEmpty(specail)){
			String [] labels = specail.split(",");
			if(labels.length > 0){
				for(String label : labels){
					addTextView(labelLayout, label);
				}
			}
		}
	}
	
	private void addTextView(ViewGroup layout, String label){
		TypeTextView labelText = new TypeTextView(context);
		labelText.setTextSize((int)context.getResources().getDimension(R.dimen.labeltextsize));
		labelText.setTextColor(context.getResources().getColor(R.color.color_777777));
		labelText.setBackgroundResource(R.drawable.special_bg);
		labelText.setGravity(Gravity.CENTER);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 0, (int)context.getResources().getDimension(R.dimen.labelmargin), (int)context.getResources().getDimension(R.dimen.labelmargin));
		labelText.setLayoutParams(layoutParams);
		labelText.setText(label);
		layout.addView(labelText);
	}
	
}