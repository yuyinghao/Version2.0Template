package com.hitv.android.hotel.ui.activity;

import java.util.List;

import nucleus.factory.RequiresPresenter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.hitv.android.hotel.R;
import com.hitv.android.hotel.bean.Category;
import com.hitv.android.hotel.mvp.presenters.DiningRoomPresenter;
import com.hitv.android.hotel.mvp.views.IDiningRoom;
import com.hitv.android.hotel.ui.adapter.ButtonAdapter;
import com.hitv.android.hotel.ui.custom.ButtonGroup;
import com.hitv.android.hotel.ui.fragment.DiningFoodFragment;
import com.hitv.android.statistics.sdk.SDKManager;
import com.hitv.android.uiversion2.dialog.CustomProgressBarDialog;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.process.FocusProcessor;
import com.umeng.analytics.MobclickAgent;

@RequiresPresenter(DiningRoomPresenter.class)
public class DiningRoomActivity extends BaseActivity<DiningRoomPresenter> implements IDiningRoom{
	
	private CustomProgressBarDialog dialog;
	
	private ButtonGroup buttonGroup;
	
	private DiningFoodFragment foodFragment;
	
	private ButtonAdapter adapter;
	
	private FrameLayout frameLayout;
	
	private View topBar;
	
	private View tempView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inroom_dining_layout);
		initView();
		handler.post(new Runnable() {
			public void run() {
				getPresenter().createData(getIntent().getExtras());
			}
		});
	}

	private void initView() {
		dialog = new CustomProgressBarDialog(this);
		
		topBar = findViewById(R.id.inroom_dining_bar);
		
		frameLayout = (FrameLayout)findViewById(R.id.inroom_dining_content);
		
		buttonGroup = (ButtonGroup)findViewById(R.id.inroom_dining_top);
		
		adapter = new ButtonAdapter(this);
		buttonGroup.setContentAdapter(adapter);
		
		foodFragment = new DiningFoodFragment();
		
		buttonGroup.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(View view, int position) {
				getPresenter().updateData(position);
			}
		});
	}

	@Override
	public Context getContext() {
		return this;
	}

	@Override
	public void showDialog(boolean flag) {
		if(flag){
			dialog.show();
		}else{
			dialog.dismiss();
		}
	}

	@Override
	public void updateTopButton(List<Category> categorys) {
		if(categorys.size() < 2){
			topBar.setVisibility(View.GONE);
			frameLayout.setPadding(0, 0, 0, 0);
		}else{
			topBar.setVisibility(View.VISIBLE);
			frameLayout.setPadding(0, (int)getResources().getDimension(R.dimen.dining_contentpaddingtop), 0, 0);
		}
		adapter.addContents(categorys);
	}

	@Override
	public void onDataComplete(String path) {
		if(!foodFragment.isAdded()){
			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			Bundle bundle = new Bundle();
			bundle.putString("path", path);
			foodFragment.setArguments(bundle);
			transaction.replace(R.id.inroom_dining_content, foodFragment);
			transaction.commit();
		}else{
			foodFragment.onDataUpdate(path);
		}
	}

	@Override
	public void setContentData(String path) {
		foodFragment.onDataUpdate(path);
	}

	@Override
	public void requestTop() {
		buttonGroup.requestFocus();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		tempView = FocusProcessor.focusCache.remove(FocusProcessor.OTHER_FOCUSE_VIEW);
		
		MobclickAgent.onPause(this);
		
		SDKManager.onPagePause();
		SDKManager.onPageEnd("0", "close");
	}

	@Override
	protected void onResume() {
		super.onResume();
		FocusProcessor.focusCache.put(FocusProcessor.OTHER_FOCUSE_VIEW, tempView);
		
		MobclickAgent.onResume(this);
		
		SDKManager.onPageStart("0", "0", "0", "0", "0", "0", "0", "0", "0", "open");
		SDKManager.onPageResume();
	}

}
