package com.hitv.android.uiversion2.builder;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.hitv.android.uiversion2.adapter.AbstractAdapter;
import com.hitv.android.uiversion2.adapter.DefaultAdapter;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.custom.GalleryViewPager;
import com.hitv.android.uiversion2.custom.SlideScrollView;
import com.hitv.android.uiversion2.interfaces.OnCurrentPageContentChangeListener;
import com.hitv.android.uiversion2.interfaces.OnPagerFocusChangeListener;
import com.hitv.android.uiversion2.interfaces.OnServiceClickListener;


public abstract class Director extends AbstractDirector<Content>{
	
	private AbstractAdapter<Content> mAdapter;

	public Director(Context context, String language) {
		super(context, language);
	}

	@Override
	public void construct(final List<Content> contents, AbstractAdapter<Content> adapter, int index) {
		if(contents == null || contents.size() <= index){
			return;
		}
		
		clearView();
		
		if(titleBuilder != null){
			mAdapter = adapter;
			if (mAdapter == null) {
				mAdapter = new DefaultAdapter(context, language);
			}
			mAdapter.clear();
			mAdapter.setContent(contents);

			pager = new GalleryViewPager(context);
			pager.setAdapter(mAdapter);
			pager.setCurrentItem(5000 * pager.getTruthCount() + index);
			titleBuilder.setTitleIcon(pager);
			setTitleDetail(contents.get(index));
			setContent(contents.get(index));
			pager.setOnCurrentPageContetChangeListener(new OnCurrentPageContentChangeListener() {

				@Override
				public void currentPageContentChange(int arg0) {
					setTitleDetail(contents.get(arg0));
					setContent(contents.get(arg0));
					iPagerChanged(mAdapter, contents, arg0);
				}
			});
			slideLayout.addView(titleBuilder.createView());
		}
		
		if(scrollBuilder != null){
			final SlideScrollView view = scrollBuilder.createView();

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, screenHeight);

			slideLayout.addView(view, params);
			
			if(pager != null){
				pager.setOnPagerFocusChangeListener(new OnPagerFocusChangeListener() {
					
					@Override
					public void onPagerFocusChange(View v, int director, boolean hasFocus) {
						int scrollX = slideLayout.getScrollX();
						int scrollY = slideLayout.getScrollY();
						if (hasFocus) {
							slideLayout.startScroll(scrollX, scrollY, 0, 0 - scrollY,
									1000);
						} else {
							if(director == View.FOCUS_DOWN){
								if(viewMoreListener != null){
									viewMoreListener.viewMore();
								}
								slideLayout.startScroll(scrollX, scrollY, 0,
										(int) view.getY() - scrollY, 1000);
							}
						}
					}
				});
				pager.requestFocus();
			}else if(contents != null && contents.size() > 0){
				setContent(contents.get(0));
			}
		}
	}

	@Override
	public void setService(Content content, OnServiceClickListener listener) {
		scrollBuilder.setService(content, listener, language);
	}
	
	private void setTitleDetail(Content content) {
		float rating = 0;
		try {
			rating = Float.parseFloat(content.getRate());
		} catch (Exception e) {
		}
		titleBuilder.setRating(rating);
		titleBuilder.setSpecail(content.getSpecial());
		titleBuilder.setTitleText(content.getFirstTitle());
	}
	
	private void setContent(Content content) {

		scrollBuilder.clearView();
		setIContent(content);
	}

	private void iPagerChanged(AbstractAdapter<Content> adapter, List<Content> list, int position){
		if(onPagerChanged(list, position)){
			adapter.notifyDataSetChanged();
		}
	}
	
}
