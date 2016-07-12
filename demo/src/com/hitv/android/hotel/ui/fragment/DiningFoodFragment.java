package com.hitv.android.hotel.ui.fragment;

import java.util.List;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusSupportFragment;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.hitv.android.hotel.R;
import com.hitv.android.hotel.mvp.presenters.DiningFoodPresenter;
import com.hitv.android.hotel.mvp.views.IDiningFood;
import com.hitv.android.hotel.mvp.views.IDiningRoom;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.hotel.ui.activity.ImageActivity;
import com.hitv.android.hotel.ui.activity.QrCodeActivity;
import com.hitv.android.uiversion2.activity.VideoPlayerActivity;
import com.hitv.android.uiversion2.adapter.BannerAdapter;
import com.hitv.android.uiversion2.adapter.PictureAdapter;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.bean.ContentSocialMedia;
import com.hitv.android.uiversion2.builder.Director;
import com.hitv.android.uiversion2.exceptions.PagerInitException;
import com.hitv.android.uiversion2.interfaces.OnCurrentPageClickListener;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.interfaces.OnServiceClickFixListener;
import com.hitv.android.uiversion2.interfaces.OnViewMoreListener;
import com.umeng.analytics.MobclickAgent;

@RequiresPresenter(DiningFoodPresenter.class)
public class DiningFoodFragment extends NucleusSupportFragment<DiningFoodPresenter> implements IDiningFood{
	
	private Director director;
	private IDiningRoom diningRoom;
	
	private View expandViewDown, expandViewUp;
	
	private Handler handler = new Handler();
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof IDiningRoom){
			diningRoom = (IDiningRoom)activity;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		initView();
		
		handler.post(new Runnable() {
			public void run() {
				getPresenter().createData(getArguments());
			}
		});
		
		return director.getContentView();
	}

	private void initView() {
		
		WindowManager wm = getActivity().getWindowManager();
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		
		director = new Director(getActivity(), GlobalParams.IP_LANAGE) {
			
			@Override
			public void setIContent(final Content content) {
				this.setService(content, new OnServiceClickFixListener() {
					@Override
					public void mapServiceClick(View view) {
						getPresenter().openMapService(director.getPageCurrentItem());
					}
					@Override
					public void carServiceClick(View viwe) {
						getPresenter().openCarService(director.getPageCurrentItem());
					}
					@Override
					public void qrCodeServiceClick(View view,
							ContentSocialMedia value) {
						getPresenter().showQrcode(value);
					}
					@Override
					public void qrCodeServiceClick(View view, int position) {
						getPresenter().showQrcode(content, position);
					}
				});
				this.setAbout(content.getIntro());
				PictureAdapter pictureAdapter = new PictureAdapter(DiningFoodFragment.this.getActivity());
				this.setPictures(pictureAdapter, new OnItemClickListener() {
					
					@Override
					public void onItemClick(View view, int position) {
						getPresenter().initJumpData(content, position);
					}
				});
				getPresenter().setPicture(content, pictureAdapter);
				this.setInformation(content.getPrice2(), content.getBusinessHour(), content.getTelPhone(), content.getAddress());
				this.setTips(content.getRemark());
			}
			
			@Override
			public boolean onPagerChanged(List<Content> list, int position) {
				
				getPresenter().viewOther(position);
				
				return super.onPagerChanged(list, position);
			}
		};
		director.setScreenHeight(metrics.heightPixels - (int)getActivity().getResources().getDimension(R.dimen.dining_topheight) - (int)getActivity().getResources().getDimension(R.dimen.dining_contentpaddingtop));
		director.setOnViewMoreListener(new OnViewMoreListener() {

			@Override
			public void viewMore() {
				getPresenter().viewMore(director.getPageCurrentItem());
			}
		});
		expandViewDown = LayoutInflater.from(getActivity()).inflate(R.layout.tip_pop, null, false);
		expandViewUp = LayoutInflater.from(getActivity()).inflate(R.layout.tip_pop_2, null, false);
	}

	@Override
	public Context getContext() {
		return getActivity();
	}

	@Override
	public void showDialog(boolean flag) {
		if(diningRoom != null){
			diningRoom.showDialog(flag);
		}
	}
	
	public void onDataUpdate(final String path){
		handler.post(new Runnable() {
			public void run() {
				Bundle bundle = new Bundle();
				bundle.putString("path", path);
				getPresenter().createData(bundle);
			}
		});
	}

	@Override
	public void constructView(List<Content> contents, int index) {
		director.construct(contents, new BannerAdapter(getActivity(), GlobalParams.IP_LANAGE), index);
		try {
			director.setTitleClickListener(new OnCurrentPageClickListener() {
				
				@Override
				public void currentPageClick(int position) {
					getPresenter().initJumpData(position, 0);
				}
			});
		} catch (PagerInitException e) {
		}
		if(diningRoom != null){
			diningRoom.requestTop();
		}
	}

	@Override
	public void startQrcode(Bundle bundle) {
		Intent intent = new Intent(getActivity(), QrCodeActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void openVideoActivity(Bundle bundle) {
		Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void openImageActivity(Bundle bundle) {
		Intent intent = new Intent(getActivity(), ImageActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.scale_in, -1);
	}

	@Override
	public void startCarService(Bundle bundle){
		ComponentName com = new ComponentName(
				"com.hitv.android.taxi",
				"com.hitv.android.aataxi.ui.activity.ActivityQRCode");
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setComponent(com);
		
		intent.putExtras(bundle);
		
		try{
			startActivity(intent);
		}catch(Exception e){
			intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.hitv.android.taxi");
			startActivity(intent);
		}
	}
	
	@Override
	public void startMapService(Bundle bundle){
		startActivity("com.hitv.android.map");
	}
	
	private void startActivity(String packageName){
		Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(
				packageName);
		if (intent != null) {
			startActivity(intent);
		} else {
			//TODO 提示没有安装
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("DiningFoodFragment");
		
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("DiningFoodFragment");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if(director != null){
			director.onActivityDestroy();
		}
	}

	@Override
	public void showExplanUp() {
		director.startExpandView(expandViewUp);
	}

	@Override
	public void showExplanDown() {
		director.startExpandView(expandViewDown);
	}
}
