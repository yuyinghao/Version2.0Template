package com.hitv.android.hotel.mvp.presenters;

import java.util.HashMap;
import java.util.List;

import nucleus.presenter.Presenter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hitv.android.hotel.bean.Background;
import com.hitv.android.hotel.bean.Channel;
import com.hitv.android.hotel.bean.ChildChannelResponse;
import com.hitv.android.hotel.bean.MainBackground;
import com.hitv.android.hotel.mvp.model.HotelServerListModel;
import com.hitv.android.hotel.mvp.model.IHotelServerListModel;
import com.hitv.android.hotel.mvp.model.IHotelServerListModel.OnGetChildChannelListener;
import com.hitv.android.hotel.mvp.views.IHotelServerList;
import com.hitv.android.hotel.params.GlobalParams;
import com.hitv.android.hotel.utils.ReflectUtil;
import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.hitv.android.uiversion2.utils.LocationIdResolverUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

public class HotelServerListPresenter extends Presenter<IHotelServerList> {

	private IHotelServerListModel model;

	private String channelId = "0";

	private List<Channel> channels;

	public HotelServerListPresenter() {
		model = new HotelServerListModel();
	}

	public void createData(Bundle bundle) {
		if (bundle == null)
			return;
		getView().showProgressDialog(true);
		channelId = bundle.getString("mainChannelId", "");
		GlobalParams.HOTEL_NAME = model.getHotelName(
				bundle.getString("langs", ""), GlobalParams.IP_LANAGE);
		
		AnalyticsConfig.setChannel(channelId + "_" + LocationIdResolverUtils.getLocationId(getView().getContext()));

		model.getChildChannelResponse(GlobalParams.BASEURL,
				GlobalParams.LOCATION_ID, channelId, GlobalParams.IP_LANAGE,
				new OnGetChildChannelListener() {

					@Override
					public void getNet(ChildChannelResponse response) {
						dealWithData(response);
					}

					@Override
					public void getCache(ChildChannelResponse response) {
						dealWithData(response);
					}
				});
	}

	public void cancelData() {
		model.cancelResponse(GlobalParams.BASEURL, GlobalParams.LOCATION_ID,
				channelId, GlobalParams.IP_LANAGE);
	}

	public void openChild(int position) {

		if(channels != null && channels.size() > position){
			Channel channel = channels.get(position);

			openChildView(channel);
		}
		
	}

	private void openChildView(Channel channel) {
		if(channel == null) return;

		String tempLateType = channel.getTemplateType();
		if (TextUtils.isEmpty(tempLateType))
			return;

		String[] tempSplit = tempLateType.split("@@");
		int type = -1;
		try {
			type = Integer.parseInt(tempSplit[0]);
		} catch (Exception e) {
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("channelId", channel.getChannelId()+"");
		map.put("channelName", channel.getChannelName());
		Bundle bundle = new Bundle();
		switch (type) {
		case 0:
			bundle.putString("channelId", channel.getChannelId() + "");
			getView().openMemberActivity(bundle);
			break;
		case 1:
			bundle.putString("channelId", channel.getChannelId() + "");
			getView().openAboutActivity(bundle);
			break;
		case 2:
			int openType = channel.getOpenType();
			if (openType == 3) {
				String path = model.getCategoryUrl(
						GlobalParams.BASEURL, GlobalParams.LOCATION_ID,
						channel.getChannelId() + "", GlobalParams.IP_LANAGE);
				bundle.putString("path", path);
				map.put("path", path);
				getView().openCategoryActivity(bundle);
			} else if (openType == 4) {
				String path = model.getContentUrl(
						GlobalParams.BASEURL, GlobalParams.LOCATION_ID,
						channel.getChannelId() + "", GlobalParams.IP_LANAGE);
				bundle.putString("path", path);
				map.put("path", path);
				getView().openDetailActivity(bundle);
			}

			break;
		case 3:
			String path = model.getCategoryUrl(GlobalParams.BASEURL,
					GlobalParams.LOCATION_ID, channel.getChannelId() + "",
					GlobalParams.IP_LANAGE);
			bundle.putString("path", path);
			map.put("path", path);
			getView().openDiningRoomActivity(bundle);
			break;
		case 4:
			map.put("tempLateType", tempLateType);
			openOtherApp(tempSplit, channel);
			break;
		}
		MobclickAgent.onEvent(getView().getContext(), "openChannel", map);
		
		SDKManager.onEvent("openChannel", map);
	}

	private void openOtherApp(String[] tempSplit, Channel channel) {
		String packageName = "";
		String activityName = "";
		Bundle bundle = new Bundle();
		if (tempSplit.length > 1) {
			String appInfo = tempSplit[1];
			String[] appInfoSplit = appInfo.split(":");
			if (appInfoSplit.length > 1) {
				String[] packageInfo = appInfoSplit[0].split("-");
				if (packageInfo.length > 1) {
					packageName = packageInfo[0];
					activityName = packageInfo[1];
				}
				String[] params = appInfoSplit[1].split(",");
				for (String param : params) {
					String[] keyValue = param.split("=");
					if (keyValue.length > 1) {
						String value = keyValue[1];
						if (value.charAt(0) == '$') {
							Object o = ReflectUtil.invokeGet(channel,
									value.substring(1));
							String paramValue = "";
							if (o instanceof Integer) {
								paramValue = String.valueOf((Integer) o);
							} else if (o instanceof String) {
								paramValue = (String) o;
							}
							bundle.putString(keyValue[0], paramValue);
						} else {
							bundle.putString(keyValue[0], value);
						}
					}
				}
			}
		}

		List<Background> backgrounds = channel.getBackgrounds();
		if (backgrounds != null && backgrounds.size() > 0) {
			bundle.putString("hotel_channel_background", backgrounds.get(0)
					.getBackgroundUrl());
			bundle.putString("hotel_channel_loacalbackground",
					backgrounds.get(0).getBackgroundLocalUrl());
		}

		if (!TextUtils.isEmpty(packageName) && !TextUtils.isEmpty(activityName)) {
			getView().startOtherApp(packageName, activityName, bundle);
		}
	}

	private void dealWithData(ChildChannelResponse response) {

		if (getView() == null)
			return;

		getView().showProgressDialog(false);
		if (response != null) {
			List<MainBackground> backgrounds = response.getMainBackgrounds();
			if (backgrounds != null && backgrounds.size() > 0) {
				MainBackground mainBackground = backgrounds.get(0);
				ImageLoader.getInstance().loadImage(
						new ImageSize(1920, 1080),
						ImageLoaderOptions.optionsByNullImg,
						new ImageLoadingListener() {
							@Override
							public void onLoadingStarted(String arg0, View arg1) {
							}

							@Override
							public void onLoadingFailed(String arg0, View arg1,
									FailReason arg2) {
							}

							@Override
							public void onLoadingComplete(String arg0,
									View arg1, Bitmap arg2) {
								if (getView() != null) {
									Drawable drawable = new BitmapDrawable(
											getView().getContext()
													.getResources(), arg2);
									getView().setBackGround(drawable);
								}
							}

							@Override
							public void onLoadingCancelled(String arg0,
									View arg1) {
							}
						}, mainBackground.getBackgroundLocalUrl(),
						mainBackground.getBackgroundUrl());
			}

			channels = response.getChannels();
			if (channels != null) {
				getView().updateData(channels);
			}
		} else {
			// TODO 提示获取数据失败
		}
	}

}