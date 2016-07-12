package com.hitv.android.uiversion2.builder;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.adapter.AbstractAdapter;
import com.hitv.android.uiversion2.adapter.GroupAdapter;
import com.hitv.android.uiversion2.bean.ButtonBean;
import com.hitv.android.uiversion2.bean.ContentPartner;
import com.hitv.android.uiversion2.custom.ExpandView;
import com.hitv.android.uiversion2.custom.GalleryViewPager;
import com.hitv.android.uiversion2.custom.SlideLayout;
import com.hitv.android.uiversion2.custom.SlideLayout.MyDispatchKeyListener;
import com.hitv.android.uiversion2.exceptions.PagerInitException;
import com.hitv.android.uiversion2.interfaces.OnCurrentPageClickListener;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.interfaces.OnServiceClickListener;
import com.hitv.android.uiversion2.interfaces.OnViewMoreListener;

public abstract class AbstractDirector<T> implements IDirector<T>{
	
	protected SlideLayout slideLayout;
	protected ScrollBuilder scrollBuilder;
	protected TitleBuilder titleBuilder;

	protected GalleryViewPager pager;
	
	protected ExpandView expandView;

	protected Context context;

	protected String language;
	
	protected int screenHeight;
	
	protected OnViewMoreListener viewMoreListener;
	
	public AbstractDirector(Context context, String language){
		this.context = context;
		this.language = language;
		slideLayout = new SlideLayout(context);
		slideLayout.setBackClickListener(new MyDispatchKeyListener() {
			
			@Override
			public boolean onBackKeyClick() {
				if(pager != null && !pager.isFocused()){
					scrollBuilder.scrollToTop();
					pager.requestFocus();
					return true;
				}
				
				return false;
			}
		});

		scrollBuilder = new ScrollCustomBuilder(context);
		titleBuilder = new TitleCustomBuilder(context);
		
		if(context instanceof Activity){
			ViewGroup decorView = (ViewGroup)((Activity) context).getWindow().getDecorView();
			expandView = new ExpandView(context);
			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int)context.getResources().getDimension(R.dimen.tip_pop_width), (int)context.getResources().getDimension(R.dimen.tip_pop_height));
			params.gravity = Gravity.RIGHT | Gravity.BOTTOM;
			params.setMargins(0, 0, (int)context.getResources().getDimension(R.dimen.tip_pop_margin), (int)context.getResources().getDimension(R.dimen.tip_pop_margin));
			
			decorView.addView(expandView, params);
		}
		
		WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metrics);
		screenHeight = metrics.heightPixels;
	}
	
	public void startExpandView(View view){
		if(expandView != null){
			expandView.bringToFront();
			expandView.setView(view);
			expandView.initedAnimation();
			expandView.start(1);
		}
	}
	
	@Override
	public void setScreenHeight(int screenHeight){
		this.screenHeight = screenHeight;
	}

	@Override
	public void setScrollBuilder(ScrollBuilder scrollBuilder) {
		this.scrollBuilder = scrollBuilder;
	}

	@Override
	public void setTitleBuilder(TitleBuilder titleBuilder) {
		this.titleBuilder = titleBuilder;
	}
	
	@Override
	public void setTitleClickListener(final OnCurrentPageClickListener listener) throws PagerInitException {
		if(pager != null){
			pager.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(listener != null){
						listener.currentPageClick(pager.getCurrentItem() % pager.getTruthCount());
					}
				}
			});
		}else{
			throw new PagerInitException("GalleryViewPager 没有初始化，请先调用 construct方法");
		}
	}
	
	@Override
	public void setOnViewMoreListener(OnViewMoreListener listener){
		this.viewMoreListener = listener;
	}

	@Override
	public SlideLayout getContentView() {
		return slideLayout;
	}

	@Override
	public abstract void construct(List<T> contents, AbstractAdapter<T> adapter, int index);

	@Override
	public int getPageCurrentItem() {
		return pager != null ? pager.getCurrentItem() % pager.getTruthCount() : 0;
	}

	@Override
	public abstract void setService(T content, OnServiceClickListener listener);

	@Override
	public void setAbout(String content) {
		scrollBuilder.setAbout(content);
	}

	@Override
	public void setInformation(String promotion, String openTime, String telephone, String address) {
		scrollBuilder.setInformation(promotion, openTime, telephone, address);
	}

	@Override
	public void setTips(String tips) {
		scrollBuilder.setTips(tips);
	}

	@Override
	public void setStringInform(String title, String content) {
		scrollBuilder.setStringInform(title, content);
	}

	@Override
	public void setPartners(List<ContentPartner> partners) {
		scrollBuilder.setPartners(partners);
	}

	@Override
	public void setDestinations(GroupAdapter adapter, OnItemClickListener listener) {
		scrollBuilder.setDestinations(adapter, listener);
	}

	@Override
	public void setPictures(GroupAdapter adapter, OnItemClickListener listener) {
		scrollBuilder.setPictures(adapter, listener);
	}

	@Override
	public void setHorizontalList(String title, GroupAdapter adapter, OnItemClickListener listener) {
		scrollBuilder.setHorizontalList(title, adapter, listener);
	}

	@Override
	public void setButtons(ButtonBean... beans) {
		scrollBuilder.setButtons(beans);
	}

	@Override
	public void clearView() {
		slideLayout.removeAllViews();

		if(titleBuilder != null){
			titleBuilder.clearView();
		}
		
		if(scrollBuilder != null){
			scrollBuilder.clearView();
		}
		
		if(pager != null){
			pager.onViewDestroy();
			pager = null;
		}
		
		System.gc();
	}

	@Override
	public void onActivityDestroy() {
		slideLayout.removeAllViews();
		if(titleBuilder != null){
			titleBuilder.recycleView();
			titleBuilder = null;
		}
		
		if(scrollBuilder != null){
			scrollBuilder.recycleView();
			scrollBuilder = null;
		}
		
		if(pager != null){
			pager.onViewDestroy();
			pager = null;
		}
		
		if(expandView != null){
			expandView.clearAnimations();
		}
		
		System.gc();
	}

	@Override
	public abstract void setIContent(T content);

	@Override
	public boolean onPagerChanged(List<T> list, int position) {
		return false;
	}

}
