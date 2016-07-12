package com.hitv.android.uiversion2.builder;


import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.adapter.PictureBroweAdapter;
import com.hitv.android.uiversion2.bean.Content;
import com.hitv.android.uiversion2.custom.ImageViewPager;
import com.hitv.android.uiversion2.utils.TypefaceUtils;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureBrowseBuilder implements OnPageChangeListener {
	private FrameLayout pictureBrowse;
	private ImageViewPager mViewPager;
	private PictureBroweAdapter adapter;
	private FragmentActivity mContext;
	private TextView pageNumber;
	private int totalPage;
	private ImageView left;
	private ImageView right;
	
	public PictureBrowseBuilder(FragmentActivity mContext){
		this.mContext=mContext;
		pictureBrowse = (FrameLayout) LayoutInflater.from(mContext).inflate(R.layout.picture_browse, null);
		initedView();
	}
	
	private void initedView() {
		mViewPager = (ImageViewPager) pictureBrowse.findViewById(R.id.picture_browse_img);
		pageNumber = (TextView) pictureBrowse.findViewById(R.id.picture_browse_page_number);
		left = (ImageView) pictureBrowse.findViewById(R.id.picture_browse_dir_left);
		right = (ImageView) pictureBrowse.findViewById(R.id.picture_browse_dir_right);
		pageNumber.setTypeface(TypefaceUtils.getInstance(mContext).getTypefaceGothamBook());
		adapter=new PictureBroweAdapter(mContext.getSupportFragmentManager());
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);
	}

	public void setData(Content content,int postion){
		 adapter.setData(content.getContentPhotos());
		 mViewPager.setCurrentItem(postion, false);
		 totalPage=content.getContentPhotos().size();
		 if(totalPage==1){
			 left.setVisibility(View.INVISIBLE);
			 right.setVisibility(View.INVISIBLE);
		 }else if(postion==0){
			 left.setVisibility(View.INVISIBLE);
			 right.setVisibility(View.VISIBLE);
		 }else if(postion==totalPage-1){
			 left.setVisibility(View.VISIBLE);
			 right.setVisibility(View.INVISIBLE);
		 }else{
			 left.setVisibility(View.VISIBLE);
			 right.setVisibility(View.VISIBLE);
		 }
		 pageNumber.setText((postion+1)+" / "+totalPage);
	}
	
	public ViewGroup getView(){
		return pictureBrowse;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		 pageNumber.setText((arg0+1)+" / "+totalPage);
		 if(arg0==0){
			 left.setVisibility(View.INVISIBLE);
			 right.setVisibility(View.VISIBLE);
		 }else if(arg0==totalPage-1){
			 left.setVisibility(View.VISIBLE);
			 right.setVisibility(View.INVISIBLE);
		 }else{
			 left.setVisibility(View.VISIBLE);
			 right.setVisibility(View.VISIBLE);
		 }
	}
	
	public void setDuration(int duration){
		mViewPager.setDuration(duration);
	}
	
}	
