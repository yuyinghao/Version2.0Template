package com.hitv.android.uiversion2.activity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.interfaces.IOnServiceConnectComplete;
import com.hitv.android.uiversion2.service.MusicPlayState;
import com.hitv.android.uiversion2.service.ServiceManager;
import com.hitv.android.uiversion2.utils.CheckDelayTimer;
import com.hitv.android.uiversion2.utils.LangugeUtils;
import com.hitv.android.uiversion2.utils.TypefaceUtils;
import com.sinera.android.media.constant.Constant;
import com.sinera.android.media.ui.MediaPlayerActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.sinera.android.media.ui.model.VideoView;
import com.sinera.android.media.ui.model.ProgressSeekBar;

public class VideoPlayerActivity extends MediaPlayerActivity{

	private final String TAG = "MediaPlayerActivity";
	private final static int CHECK_DELAY = 0x0005;
	private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");// 初始化Formatter的转换格式。
	private CheckDelayTimer mCheckDelayTimer;
	private View loadingView;
	private ServiceManager mServiceManager;
	
	private boolean isPullState;
	private boolean isConnectComplete;
	private boolean errorVideo;
	private AssetManager manager;
	private long mExitTime;
	private Map<Integer,String> toasts;
	private TextView loadingMediaName;
	private TextView toast_notice_first_tv;
	private TextView toast_notice_second_tv;
	private ImageView toast_notice_icon;
	private String path;
	private int type;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.media_player);
		
		videoView = (VideoView) findViewById(R.id.video_view);
		mediaProgress = (ProgressSeekBar) findViewById(R.id.seek_progress);
		
		mediaBottom = findViewById(R.id.media_Bottom);
		loadingView = findViewById(R.id.loading);
		loading_normal = findViewById(R.id.loading_normal);
		headerView = findViewById(R.id.header);
		press_notice = findViewById(R.id.press_notice);
		toast_notice_first_tv = (TextView) press_notice.findViewById(R.id.toast_notice_first_tv);
		toast_notice_second_tv = (TextView) press_notice.findViewById(R.id.toast_notice_second_tv);
		toast_notice_icon = (ImageView) press_notice.findViewById(R.id.toast_notice_icon);
		mediaState = (ImageView) findViewById(R.id.media_state);
		loadingMediaName = (TextView)(loadingView.findViewById(R.id.media_name));
		TextView loadingNoticeTv = (TextView)(loadingView.findViewById(R.id.notice_tv));
		TextView commingUpTv = (TextView)(loadingView.findViewById(R.id.comming_up_tv));
		mediaName = (TextView)findViewById(R.id.media_name);
		if("en_US".equalsIgnoreCase(LangugeUtils.getLanguge())){
			loadingMediaName.setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
			loadingNoticeTv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
			commingUpTv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
			mediaName.setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
			 
			toast_notice_first_tv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
			toast_notice_second_tv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
		}else{
			loadingMediaName.setTypeface(TypefaceUtils.getInstance(this).getTypefaceFzltzhjt());
			loadingNoticeTv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceFzltzhjt());
			commingUpTv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceFzltzhjt());
			mediaName.setTypeface(TypefaceUtils.getInstance(this).getTypefaceFzltzhjt());
			mediaName.setPadding(0, 0, 0, 0);
			toast_notice_first_tv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceFzltzhjt());
			toast_notice_second_tv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceFzltzhjt());
		}
		
		currentTime = (TextView) findViewById(R.id.current_Time);
		currentTime.setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
		durationTime = (TextView) findViewById(R.id.duration_Time);
		durationTime.setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
		mediaProgress.getSeekTimeTv().setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
		ad = (ImageView) findViewById(R.id.ad);
		
		manager=getAssets();
		mediaProgress.setOnSeekBarChangeListener(seekBarChangeListener);
		
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		formatterM.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		videoView.setOnCompletionListener(completionListener);
		videoView.setOnErrorListener(errorListener);
		mCheckDelayTimer = new CheckDelayTimer(this);
		if(mServiceManager==null)
			mServiceManager = new ServiceManager(this);
		mServiceManager.setOnServiceConnectComplete(new IOnServiceConnectComplete() {
			
			@Override
			public void OnServiceConnectComplete() {
				if(mServiceManager.getPlayState()==MusicPlayState.MPS_PLAYING){
					isPullState = true;
					mServiceManager.pause();
				}
				isConnectComplete = true;
			}
		});
		mServiceManager.connectService();
		getIntentData();
	}
	
	private void getIntentData() {
		Intent intent=getIntent();
		name=intent.getStringExtra("name");
		path=intent.getStringExtra("path");
		type=intent.getIntExtra("type",0);
	}

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what)
			{
				case CHECK_DELAY:
					checkDelay();
					break;
			}
		}
	};
	
	@Override
	protected void showFirstLoadingView() {
		super.showFirstLoadingView();
		loadingMediaName.setText(" " + name);
		loadingView.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected void closeFirstLoadingView() {
		super.closeFirstLoadingView();
		loadingView.setVisibility(View.INVISIBLE);
		mCheckDelayTimer.setHandler(mHandler, CHECK_DELAY);
		mCheckDelayTimer.startTimer();
	}
	
	public void checkDelay(){
		int pos = getCurPosition();
		currentTime.setText(formatter.format(pos));
		boolean ret = mCheckDelayTimer.isDelay(pos);
		if (ret){
			if(playState != MEDIAPLAYER_STATE.PAUSE){
				loading_normal.setVisibility(View.VISIBLE);
			}
		}else{
			loading_normal.setVisibility(View.GONE);
		}
		mCheckDelayTimer.setPos(pos);
	}
	
	public int getCurPosition()
	{
		if (MEDIAPLAYER_STATE.PLAY == playState || MEDIAPLAYER_STATE.PAUSE == playState)
		{
			return videoView.getCurrentPosition();
		}
		return 0;
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		if (intent != null){
			
		}
		super.onNewIntent(intent);
	}
	
	@SuppressLint("UseSparseArrays")
	@Override
	protected void onStart() {
		super.onStart();
//		url = "file:///android_asset/enjoy_guangzhou.mp4";
//		webUrl = "http://develop.hospitalityinspired.com.cn:8080/vod/res/videos/mercuris/04mercuris_video_angove.mp4";
		if(type==1){
			try {
				fileDescriptor=manager.openFd(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type==0){
			webUrl=path;
		}else{
			url=path;
		}
		List<Integer> points = new ArrayList<Integer>();
		toasts = new HashMap<Integer,String>();
		mediaProgress.invalidateNoticePoint(points);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(isConnectComplete){
			if(mServiceManager.getPlayState()==MusicPlayState.MPS_PLAYING){
				isPullState = true;
				mServiceManager.pause();
			}
		}
		play();
		
//		//数据统计
//		SDKManager.onPageStart("VideoPlayerActivity", "0", "0", "0", "0", "0","0","0","0","open");
//		SDKManager.onPageResume();
//		MobclickAgent.onPageStart("VideoPlayerActivity");
//		MobclickAgent.onResume(this);
	}
	
	
	@Override
	protected void onStop() {
		super.onStop();
		mCheckDelayTimer.stopTimer();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		//数据统计
//		MobclickAgent.onPause(this);
//		MobclickAgent.onPageEnd("VideoPlayerActivity");
//		SDKManager.onPagePause();
//		SDKManager.onPageEnd("VideoPlayerActivity","close");
//		ADImageLoader.getInstance().removeBitmapFromLruCache(adAddress);
		destory();
		finish();
	}
	
	@Override
	protected void onDestroy() {
		if(mServiceManager!=null){
			if(isPullState){
				mServiceManager.play();
			}
			if(isConnectComplete)
				mServiceManager.disconnectService();
		}
		super.onDestroy();
	}
	
	
	private OnErrorListener errorListener = new OnErrorListener() {
		@Override
		public boolean onError(MediaPlayer mp, int what, int extra) {
			errorVideo = true;
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					onStop();
				}
			}, 3000);
			return false;
		}
	};
	
	private OnCompletionListener completionListener = new OnCompletionListener() {

		@Override
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			Log.i(TAG, "###########播放完毕");
//			Intent intent = new Intent(VideoPlayerActivity.this,VideoDetailsActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
//			Bundle bundle = new Bundle();
//			bundle.putSerializable("content",mContent);
//			intent.putExtras(bundle);
//			startActivity(intent);
//			MobclickAgent.onEvent(VideoPlayerActivity.this, "001");
			finish();
		}
	};

	private OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (mediaProgress != null){
				Set<Entry<Integer, String>> toastSet = toasts.entrySet();
				for (Entry<Integer, String> entry : toastSet) {
					if(progress == entry.getKey()){
						if(!Constant.MEDIA_PLAY_ACTION_SPEED.equals(action)){
							restartMediaController();
							mediaProgress.updateNoticePopupWindow(builderNotice(entry.getValue()),progress);
//							MobclickAgent.onEvent(VideoPlayerActivity.this, "006");
						}
					}
				}
			}
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {

		}

	};
	
	
	
	private View builderNotice(String value) {
		View view = View.inflate(this, R.layout.media_notice_popu, null);
		TextView toastNoticeTv = (TextView) view.findViewById(R.id.dialog_seek_toast);
		if("en_US".equalsIgnoreCase(LangugeUtils.getLanguge())){
			toastNoticeTv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceGothamBook());
		}else{
			toastNoticeTv.setTypeface(TypefaceUtils.getInstance(this).getTypefaceFzltzhjt());
		}
		toastNoticeTv.setText(value);
		return view;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		Log.i(TAG, " onKeyUp start " + videoView.isPlaying() + " keyCode="
				+ keyCode + " event=" + event.getKeyCode());

		return super.onKeyUp(keyCode, event);

	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {

		Log.i(TAG, "## onKeyLongPress start " + videoView.isPlaying()
				+ " keyCode=" + keyCode + " event=" + event.getKeyCode());

		return super.onKeyLongPress(keyCode, event);
	}
	
	
	/*
	 * 按键控制
	 */

	@SuppressLint("NewApi")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if(errorVideo){
			return super.onKeyDown(keyCode, event);
		}
		if(videoView.getDuration()<=0){
			if(keyCode == KeyEvent.KEYCODE_BACK){
				finish();
			}else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP || 
					keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_MUTE){
				return super.onKeyDown(keyCode, event);
			}
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {// 退出
			if ((System.currentTimeMillis() - mExitTime) > 3000) {
				setPressBackView();
                mExitTime = System.currentTimeMillis();
                if(handler!=null)
                	handler.postDelayed(backPressRunnable, 3000);
			} else {
				if(handler!=null)
					handler.removeCallbacks(backPressRunnable);
//				MobclickAgent.onEvent(VideoPlayerActivity.this, "003");
                finish();
			}
			return true;
		}else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
//			if(mediaProgress.mPopupQRCodeWindow != null && mediaProgress.mPopupQRCodeWindow.isShowing()
//					&& tvVideoFrame!=null && tvVideoFrame.getApp()!=null){
//				return true;
//			}
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	private void setPressBackView() {
		press_notice.setVisibility(View.VISIBLE);
		toast_notice_first_tv.setText(R.string.toast_press);
		toast_notice_second_tv.setText(R.string.toast_press_back);
		toast_notice_icon.setImageResource(R.drawable.toast_icon);
	}
	
	@Override
	protected void setPressGoOnView() {
		super.setPressGoOnView();
		handler.removeCallbacks(backPressRunnable);
		toast_notice_first_tv.setText(R.string.press);
		toast_notice_second_tv.setText(R.string.press_info);
		toast_notice_icon.setImageResource(R.drawable.ok_btn);
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {// 接收到按键
		Log.i(TAG, " dispatchKeyEvent start   event=" + event.getKeyCode());
		return super.dispatchKeyEvent(event);
	}
	
	@Override
	protected void mediaNowPause(boolean isQRCodePopupWindow) {
		super.mediaNowPause(isQRCodePopupWindow);
		setPressGoOnView();
		loading_normal.setVisibility(View.INVISIBLE);
		if(!isQRCodePopupWindow){
			ad.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	protected void mediaNowResume() {
		super.mediaNowResume();
		ad.setVisibility(View.INVISIBLE);
	}
	@Override
	protected void onUserLeaveHint() {
		sendBroadcast(new Intent("com.hitv.android.video.finish"));
		super.onUserLeaveHint();
	}
}
