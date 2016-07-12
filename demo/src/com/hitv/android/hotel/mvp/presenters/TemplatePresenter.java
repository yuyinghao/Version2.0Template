package com.hitv.android.hotel.mvp.presenters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nucleus.presenter.Presenter;
import android.os.Bundle;

import com.hitv.android.hotel.mvp.model.ContentModel;
import com.hitv.android.hotel.mvp.model.IContentModel;
import com.hitv.android.hotel.mvp.model.IContentModel.OnGetContentResponselListener;
import com.hitv.android.hotel.mvp.views.ITemplateView;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.hotel.ui.activity.ImageActivity;
import com.hitv.android.hotel.ui.activity.QrCodeActivity;
import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.uiversion2.adapter.PictureAdapter;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.bean.ContentPhotos;
import com.hitv.android.uiversion2.bean.ContentResponse;
import com.hitv.android.uiversion2.bean.ContentSocialMedia;
import com.hitv.android.uiversion2.bean.ContentVideo;
import com.hitv.android.uiversion2.bean.PictureBean;
import com.umeng.analytics.MobclickAgent;

public abstract class TemplatePresenter<p extends ITemplateView> extends
		Presenter<p> {

	protected IContentModel model;

	protected List<Content> contents;

	private boolean startUp = false;
	private boolean startDown = false;

	public TemplatePresenter() {
		model = new ContentModel();
	}

	public void createData(Bundle bundle) {
		if (bundle == null)
			return;
		getView().showDialog(true);
		String path = getContentPath(bundle);
		
		model.getContentResponse(path, new OnGetContentResponselListener() {

			@Override
			public void getNet(ContentResponse response) {
				dealWithData(response);
			}

			@Override
			public void getCache(ContentResponse response) {
				dealWithData(response);
			}
		});
	}

	protected abstract String getContentPath(Bundle bundle);

	protected void dealWithData(ContentResponse response) {
		if (getView() == null)
			return;
		getView().showDialog(false);
		if (response != null) {
			contents = response.getContents();
			if (contents != null) {
				getView().constructView(contents, 0);
				if(!startDown){
					getView().showExplanDown();
					startDown = true;
				}
			}
		} else {
			// TODO 提示获取数据失败
		}
	}

	public void openCarService(int position) {
		
		if(contents == null || contents.size() <= position) return;
		
		Content temp = contents.get(position);
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("contentId", temp.getContentId());
		map.put("firstTitle", temp.getFirstTitle());
		MobclickAgent.onEvent(getView().getContext(), "openCarservice", map);
		
		SDKManager.onEvent("openCarservice", map);
		
		Bundle bundle = new Bundle();
		bundle.putInt("type", 1);
		bundle.putString("endLongitude", temp.getLongitude());
		bundle.putString("endLatitude", temp.getLatitude());
		
		getView().startCarService(bundle);
	}

	public void openMapService(int position) {
		
		if(contents == null || contents.size() <= position) return;
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("contentId", contents.get(position).getContentId());
		map.put("firstTitle", contents.get(position).getFirstTitle());
		MobclickAgent.onEvent(getView().getContext(), "openMapService", map);
		
		SDKManager.onEvent("openMapService", map);
		
		Bundle bundle = new Bundle();
		getView().startMapService(bundle);
	}

	public void showQrcode(ContentSocialMedia media) {
		showQrcode(media.getQrCodeUrl(), null, null);
	}

	public void showQrcode(Content content, int position) {

		String value = "";
		String logo = "";
		String detail = "";
		String[] values = new String[] {};
		String[] logos = new String[] {};
		String[] details = new String[] {};
		if (GlobalParams.IP_LANAGE.equals("en_US")) {
			values = content.getQrEnCode().split("@@");
			details = content.getQrEnText().split("@@");
			logos = content.getQrEnLogoUrl().split("@@");
		} else if (GlobalParams.IP_LANAGE.equals("zh_TW")) {
			values = content.getQrTwCode().split("@@");
			details = content.getQrTwText().split("@@");
			logos = content.getQrTwLogoUrl().split("@@");
		} else if (GlobalParams.IP_LANAGE.equals("zh_CN")) {
			values = content.getQrCode().split("@@");
			details = content.getQrZhText().split("@@");
			logos = content.getQrLogoUrl().split("@@");
		}
		if (values.length > position) {
			value = values[position];
		}
		if (logos.length > position) {
			logo = logos[position];
		}
		if (details.length > position) {
			detail = details[position];
		}
		showQrcode(value, logo, detail);

	}

	public void showQrcode(String content, String logo, String detail) {
		Bundle bundle = new Bundle();
		bundle.putString(QrCodeActivity.QRCONTENT, content);
		bundle.putString(QrCodeActivity.QRLOGO, logo);
		bundle.putString(QrCodeActivity.DETAIL, detail);
		getView().startQrcode(bundle);
	}

	private ArrayList<PictureBean> beans;

	public void setPicture(Content content, PictureAdapter adapter) {
		beans = new ArrayList<PictureBean>();
		List<ContentVideo> videos = content.getContentVideos();
		for (ContentVideo video : videos) {
			if (video.getDeviceType() == 1) {
				beans.add(new PictureBean(video));
			}

		}
		List<ContentPhotos> photos = content.getContentPhotos();
		for (ContentPhotos photo : photos) {
			if(photo.getDeviceType() == 1){
				beans.add(new PictureBean(photo));
			}
			
		}
		adapter.addContents(beans);
	}
	
	public void initJumpData(int position, int index){
		if(contents != null && contents.size() > position){
			initJumpData(contents.get(position), index);
		}
	}

	public void initJumpData(Content content, int position) {
		if (beans != null && beans.size() > position) {
			int posi = 0;
			for (PictureBean bean : beans) {
				if (bean.getType() == 2) {
					posi++;
				} else {
					break;
				}
			}
			PictureBean bean = beans.get(position);
			Bundle bundle = new Bundle();
			switch (bean.getType()) {
			case 1:
				bundle.putSerializable(ImageActivity.CONTENT, content);
				bundle.putInt(ImageActivity.POSITION, position - posi);
				
				HashMap<String,String> pictureMap = new HashMap<String,String>();
				pictureMap.put("imageUrl", bean.getPhotoUrl());
				pictureMap.put("firstTitle", content.getFirstTitle());
				MobclickAgent.onEvent(getView().getContext(), "openPicture", pictureMap);
				
				SDKManager.onEvent("openPicture", pictureMap);
				
				getView().openImageActivity(bundle);
				break;
			case 2:
				bundle.putString("name", "");
				bundle.putString("path", bean.getVideoUrl());
				bundle.putInt("type", 0);
				
				HashMap<String,String> videoMap = new HashMap<String,String>();
				videoMap.put("videoUrl", bean.getVideoUrl());
				videoMap.put("firstTitle", content.getFirstTitle());
				MobclickAgent.onEvent(getView().getContext(), "openVideo", videoMap);
				
				SDKManager.onEvent("openVideo", videoMap);

				getView().openVideoActivity(bundle);
				break;
			}
		}
	}

	public void viewMore(int position) {

		if (contents != null && contents.size() > position) {
			Content temp = contents.get(position);
			if (temp != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("contentId", temp.getContentId());
				map.put("firstTitle", temp.getFirstTitle());
				MobclickAgent.onEvent(getView().getContext(), "downseemore",
						map);

				SDKManager.onEvent("downseemore", map);
			}
		}
		if(!startUp && getView() != null){
			getView().showExplanUp();
			startUp = true;
		}
	}
	
	public void viewOther(int position){
		if (contents != null && contents.size() > position) {
			Content temp = contents.get(position);
			if (temp != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("contentId", temp.getContentId());
				map.put("firstTitle", temp.getFirstTitle());
				MobclickAgent.onEvent(getView().getContext(), "nextforother",
						map);

				SDKManager.onEvent("nextforother", map);
			}
		}
	}
}
