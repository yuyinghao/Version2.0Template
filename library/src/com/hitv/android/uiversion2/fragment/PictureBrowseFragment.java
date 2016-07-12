package com.hitv.android.uiversion2.fragment;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.bean.ContentPhotos;
import com.hitv.android.uiversion2.utils.ImageLoaderOptions;
import com.hitv.android.uiversion2.utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

@SuppressLint("ValidFragment")
public class PictureBrowseFragment extends Fragment implements ImageLoadingListener {
	private ContentPhotos photo;
	private ImageView image;
	private ProgressBar bar;
	public PictureBrowseFragment(ContentPhotos photo){
		this.photo=photo;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.item_picture_browse, container, false);
		image=(ImageView) view.findViewById(R.id.item_picture_browse_img);
		bar=(ProgressBar) view.findViewById(R.id.item_picture_browse_bar);
		ImageLoaderUtils.getInstance().imageLoader(photo.getPhotoLocalUrl(), photo.getPhotoUrl(), image, ImageLoaderOptions.optionsByNullImg, this);
		return view;
	}

	@Override
	public void onLoadingCancelled(String arg0, View arg1) {
		bar.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
		bar.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
		bar.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onLoadingStarted(String arg0, View arg1) {
		bar.setVisibility(View.VISIBLE);
	}
	
}
