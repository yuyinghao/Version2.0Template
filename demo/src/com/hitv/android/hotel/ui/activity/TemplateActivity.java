package com.hitv.android.hotel.ui.activity;

import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.hitv.android.hotel.mvp.presenters.TemplatePresenter;
import com.hitv.android.hotel.mvp.views.ITemplateView;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.activity.VideoPlayerActivity;
import com.hitv.android.uiversion2.adapter.PictureAdapter;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.bean.ContentSocialMedia;
import com.hitv.android.uiversion2.builder.Director;
import com.hitv.android.uiversion2.dialog.CustomProgressBarDialog;
import com.hitv.android.uiversion2.dialog.ReminderDialog;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.interfaces.OnServiceClickFixListener;
import com.hitv.android.uiversion2.interfaces.OnViewMoreListener;

public abstract class TemplateActivity<p extends TemplatePresenter> extends
		BaseActivity<p> implements ITemplateView {

	protected CustomProgressBarDialog dialog;

	protected ReminderDialog reminderDialog;

	protected Director director;

	private View expandViewDown, expandViewUp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		setContentView(director.getContentView());
	}

	protected void initView() {
		getWindow().getDecorView()
				.setBackgroundResource(R.drawable.launcher_bg);
		dialog = new CustomProgressBarDialog(this);
		reminderDialog = new ReminderDialog(this);
		director = new Director(this, GlobalParams.IP_LANAGE) {
			@Override
			public void setIContent(final Content content) {
				this.setService(content, new OnServiceClickFixListener() {

					@Override
					public void mapServiceClick(View view) {
						getPresenter().openMapService(
								director.getPageCurrentItem());
					}

					@Override
					public void carServiceClick(View viwe) {
						getPresenter().openCarService(
								director.getPageCurrentItem());
					}

					@Override
					public void qrCodeServiceClick(View view, ContentSocialMedia value) {
						getPresenter().showQrcode(value);
					}

					@Override
					public void qrCodeServiceClick(View view, int position) {
						getPresenter().showQrcode(content, position);
					}
				});
				this.setAbout(content.getIntro());
				this.setInformation(content.getPrice2(),
						content.getBusinessHour(), content.getTelPhone(),
						content.getAddress());

				PictureAdapter pictureAdapter = new PictureAdapter(
						TemplateActivity.this);
				setPictures(pictureAdapter, new OnItemClickListener() {

					@Override
					public void onItemClick(View view, int position) {
						getPresenter().initJumpData(content, position);
					}
				});
				getPresenter().setPicture(content, pictureAdapter);

				this.setTips(content.getRemark());
			}
			
			@Override
			public boolean onPagerChanged(List<Content> list, int position) {
				getPresenter().viewOther(position);
				return super.onPagerChanged(list, position);
			}
		};

		director.setOnViewMoreListener(new OnViewMoreListener() {

			@Override
			public void viewMore() {
				getPresenter().viewMore(director.getPageCurrentItem());
			}
		});
		expandViewDown = LayoutInflater.from(this).inflate(R.layout.tip_pop, null, false);
		expandViewUp = LayoutInflater.from(this).inflate(R.layout.tip_pop_2, null, false);
	}

	@Override
	public void showDialog(boolean flag) {
		if (flag) {
			dialog.show();
		} else {
			dialog.dismiss();
		}
	}

	@Override
	public Context getContext() {
		return this;
	}

	@Override
	public void startQrcode(Bundle bundle) {
		Intent intent = new Intent(this, QrCodeActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void openVideoActivity(Bundle bundle) {
		Intent intent = new Intent(this, VideoPlayerActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void openImageActivity(Bundle bundle) {
		Intent intent = new Intent(this, ImageActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		overridePendingTransition(R.anim.scale_in, -1);
	}

	@Override
	public void startCarService(Bundle bundle) {
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
			intent = getPackageManager().getLaunchIntentForPackage("com.hitv.android.taxi");
			startActivity(intent);
		}
	}

	@Override
	public void startMapService(Bundle bundle) {
		startActivity("com.hitv.android.map", bundle);
	}

	private void startActivity(String packageName, Bundle bundle) {
		Intent intent = getPackageManager().getLaunchIntentForPackage(
				packageName);
		if (intent != null) {
			intent.putExtras(bundle);
			startActivity(intent);
		} else {
			// TODO 提示没有安装
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
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
