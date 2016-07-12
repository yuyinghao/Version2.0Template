package com.hitv.android.uiversion2.custom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.adapter.GroupAdapter;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.interfaces.OnItemFocusChangeListener;

public class CustomHomeItemLayout extends ViewGroup {
	
	private int mLeftMargin = 0; //距左边距离
	private int mTopMargin = 0; //距上距离
	private int mItemMargin = 0;//item间距
	private GroupAdapter mAdapter;
	private OnItemClickListener mItemClickListener;
	private int width = 0;
	private View firstView;
	private Scroller scroller;
	private int mAnimationTime = 200;
	private int childWidth;
	private int scrollTime = 1000;
	
	private int screenWidth;//屏幕的宽度
	
	private SparseArray<View> childViews = new SparseArray<View>();

	public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
		this.mItemClickListener = mItemClickListener;
	}

	public CustomHomeItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		scroller = new Scroller(context);
		getScreenWidth(context);
		TypedArray custom = getContext().obtainStyledAttributes(attrs,R.styleable.CustomHomeItemLayout);
		this.mLeftMargin = (int) custom.getDimension(R.styleable.CustomHomeItemLayout_left_scale_space, 0);
		this.mTopMargin = (int) custom.getDimension(R.styleable.CustomHomeItemLayout_top_scale_space, 0);
		this.mItemMargin = (int) custom.getDimension(R.styleable.CustomHomeItemLayout_item_margin, 0);
		custom.recycle();
	}
	
	public CustomHomeItemLayout(Context context) {
		super(context);
		scroller = new Scroller(context);
		getScreenWidth(context);
	}
	
	/**
	 * 获得屏幕宽度
	 */
	private void getScreenWidth(Context context){
		WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		screenWidth = outMetrics.widthPixels;
	}
	
	public View getFirstFocusView(){
		return firstView;
	}
	
	/**
	 * 根据传入的adapter添加view
	 * @param baseAdapter
	 */
	public void setContentAdapter(GroupAdapter baseAdapter) {
		
		if(mAdapter != null){
			mAdapter.unregisterDataSetObserver(observer);
		}
		
		mAdapter = baseAdapter;
		mAdapter.registerDataSetObserver(observer);
		
		mAdapter.setListener(new OnItemFocusChangeListener() {
			
			@Override
			public void itemFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
                    invalidate();
                    v.bringToFront();
                    changeToView(v, 1f, 1.125f, mAnimationTime);
                	viewFocuseScroll(v);
				}else{
					changeToView(v, 1.125f, 1f, mAnimationTime);
				}
			}
		});
		
		getAdapterView();
		
	}
	
	private DataSetObserver observer = new DataSetObserver() {

		@Override
		public void onChanged() {
			super.onChanged();
			getAdapterView();
		}
		
	};
	
	private void getAdapterView(){
		clearView();
		
		for (int i = 0; i < mAdapter.getCount(); i++) {
			final int position = i;
			View view = mAdapter.getView(i, null, this);
			if(i == 0){
				firstView = view;
			}
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(mItemClickListener != null){
						mItemClickListener.onItemClick(v, position);
					}
				}
			});
			childViews.append(i, view);
			addView(view);
			view.setTag(i);
		}
		if(getChildCount() > 0){
			getChildAt(0).requestFocus();
		}
		
		
	}
	
	/**
	 * 测量view的实际大小
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measureHeight = measureHeight(heightMeasureSpec);
		// 计算自定义的ViewGroup中所有子控件的大小
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		// 设置自定义的控件MyViewGroup的大小
		if(getChildCount() == 0){
			return;
		}
		childWidth = getChildAt(0).getMeasuredWidth();
		width = (childWidth + mItemMargin)*getChildCount() + mLeftMargin + mItemMargin/2;
		int height = getChildAt(0).getMeasuredHeight();
		setMeasuredDimension(width, measureHeight);
	}
	
	private int measureHeight(int pHeightMeasureSpec) {
		int result = 0;

		int heightMode = MeasureSpec.getMode(pHeightMeasureSpec);
		int heightSize = MeasureSpec.getSize(pHeightMeasureSpec);

		switch (heightMode) {
		case MeasureSpec.AT_MOST:
		case MeasureSpec.EXACTLY:
			result = heightSize;
			break;
		}
		return result;
	}
	
	/**
	 * 子view的布局显示
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int XPostion = getPaddingLeft() + mLeftMargin;
		int YPostion = getPaddingTop() + mTopMargin;
		
		int contentCount = getChildCount();
		for (int i = 0; i < contentCount; i++) {
			View child = childViews.get(i);
			child.layout(XPostion, YPostion, child.getMeasuredWidth()
					+ XPostion, child.getMeasuredHeight() + YPostion);// 左上右下
			//Log.i("aaaaa", "============"+child.getTag()+"==>xposition : "+XPostion + " yposition : "+YPostion + " : "+child.getMeasuredWidth() + " : "+child.getMeasuredHeight());
			XPostion = XPostion + child.getMeasuredWidth() + mItemMargin;
		}
	}
	
	public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        if (!scroller.isFinished())
        	scroller.abortAnimation();
        scroller.startScroll(startX, startY, dx, dy, duration);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
    
	private void viewFocuseScroll(View v) {
		
		if(screenWidth < width){
			
			int position = (Integer) v.getTag();		
			int sX = getScrollX();
			int sY = getScrollY();
			
			//想要在的位置
			int wantSet = (screenWidth - mLeftMargin)/2 + mLeftMargin;
			//本身的位置
			int nowSet = mLeftMargin + (mItemMargin + childWidth) * position + childWidth / 2;
			//本身的位置向右 width - nowSet
			
			if(nowSet < wantSet){
				startScroll(sX, sY, 0 - sX, 0, scrollTime);
			}else if((width - nowSet) < (screenWidth - mLeftMargin)/2){
				int offset = width - screenWidth;
				startScroll(sX, sY, offset - sX, 0, scrollTime);
			}else{
				int offset = nowSet - wantSet;
				startScroll(sX, sY, offset - sX, 0, scrollTime);
			}
		}

		
		/*int mWight = v.getMeasuredWidth();
		
		if(position < 3){
			startScroll(sX, sY, 0 - sX, 0, 1000);
		}else if(position >= getChildCount() - 3){
			int offset = width - 1920;
			startScroll(sX, sY, offset - sX, 0, 1000);
		}else{
			//offset = 本身的位置 - 想要在的位置
			int offset = (mLeftMargin + (mItemMargin + mWight) * position + mWight/2) - (1920 / 2);
			startScroll(sX, sY, offset - sX, 0, 1000);
		}*/
	}
	
	private void changeToView(final View view, float beginS, float endS,
			long duration) {
		view.setLayerType(View.LAYER_TYPE_HARDWARE, null);// 开启物理加速
		AnimatorSet set = new AnimatorSet();
		set.playTogether(ObjectAnimator.ofFloat(view, "rotationX", 0, 0),
				ObjectAnimator.ofFloat(view, "rotationY", 0, 0),
				ObjectAnimator.ofFloat(view, "translationX", 0, 0),
				ObjectAnimator.ofFloat(view, "translationY", 0, 0),
				ObjectAnimator.ofFloat(view, "scaleX", beginS, endS),
				ObjectAnimator.ofFloat(view, "scaleY", beginS, endS),
				ObjectAnimator.ofFloat(view, "alpha", 1, 1));
		set.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}
		});
		set.setDuration(duration).start();
	}
	
	private void clearView(){

		removeAllViews();
		
		/*int count = childViews.size();
		for(int i = 0; i < count; i++){
			ImageLoaderOptions.recycleBitmap(childViews.get(childViews.keyAt(i)));
		}*/
		
		childViews.clear();
	}
	
	public void onViewDestroy(){
		mAdapter.unregisterDataSetObserver(observer);
		clearView();
	}

	@Override
	public View focusSearch(View focused, int direction) {
		
		int position = (Integer)focused.getTag();
		
		if(position == 0 && direction == View.FOCUS_LEFT){
			return null;
		}else if(position == getChildCount() - 1 && direction == View.FOCUS_RIGHT){
			return null;
		}
		
		return super.focusSearch(focused, direction);
	}
}
