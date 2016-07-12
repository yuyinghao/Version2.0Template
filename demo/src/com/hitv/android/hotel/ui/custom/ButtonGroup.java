package com.hitv.android.hotel.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Scroller;

import com.hitv.android.hotel.R;
import com.hitv.android.uiversion2.interfaces.OnItemClickListener;
import com.hitv.android.uiversion2.process.FocusProcessor;

public class ButtonGroup extends ViewGroup{

	private int mLeftMargin = 0; //距左边距离
	private int mTopMargin = 0; //距上距离
	private int mItemMargin = 0;//item间距
	private BaseAdapter mAdapter;
	private OnItemClickListener mItemClickListener;
	private int width = 0;
	private View focuseView;
	private Scroller scroller;
	private int scrollTime = 1000;
	
	private int screenWidth;//屏幕的宽度
	
	private SparseArray<View> childViews = new SparseArray<View>();

	public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
		this.mItemClickListener = mItemClickListener;
	}

	public ButtonGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		scroller = new Scroller(context);
		getScreenWidth(context);
		TypedArray custom = getContext().obtainStyledAttributes(attrs,R.styleable.ButtonGroup);
		this.mLeftMargin = (int) custom.getDimension(R.styleable.ButtonGroup_mItemMargin, 0);
		this.mTopMargin = (int) custom.getDimension(R.styleable.ButtonGroup_mTopMargin, 0);
		this.mItemMargin = (int) custom.getDimension(R.styleable.ButtonGroup_mItemMargin, 0);
		custom.recycle();
	}
	
	public ButtonGroup(Context context) {
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
	
	/**
	 * 根据传入的adapter添加view
	 * @param baseAdapter
	 */
	public void setContentAdapter(BaseAdapter baseAdapter) {
		
		if(mAdapter != null){
			mAdapter.unregisterDataSetObserver(observer);
		}
		
		mAdapter = baseAdapter;
		mAdapter.registerDataSetObserver(observer);

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
			view.setFocusable(true);
			view.setClickable(true);

			view.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
	                    invalidate();
	                	viewFocuseScroll(v);
					}
				}
			});
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
			View v = getChildAt(0);
			v.requestFocus();
			focuseView = v;
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
		if(getChildCount() == 0) return;
		
		if(mTopMargin < 0){
			int height = getChildAt(0).getMeasuredHeight();
			mTopMargin = (measureHeight - height) / 2 - getPaddingTop();
		}
		
		width = 0;
		for(int i = 0; i < getChildCount(); i++){
			int childWidth = getChildAt(i).getMeasuredWidth();
			width += (childWidth + mItemMargin);
		}
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
			int nowSet = mLeftMargin;// (mItemMargin + childWidth) * position + childWidth / 2;
			for(int i = 0; i < position; i++){
				nowSet += (mItemMargin + getChildAt(i).getMeasuredWidth());
			}
			nowSet += (getChildAt(position).getMeasuredWidth() / 2);
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
	}
	
	private void clearView(){

		removeAllViews();
		
		childViews.clear();
	}
	
	public void onViewDestroy(){
		mAdapter.unregisterDataSetObserver(observer);
		clearView();
	}
	
	@Override
	public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
		if(focuseView != null){
			return focuseView.requestFocus();
		}
		return false;
	}

	@Override
	public View focusSearch(View focused, int direction) {
		
		if(direction == View.FOCUS_LEFT || direction == View.FOCUS_RIGHT || direction == View.FOCUS_UP){
			if(focuseView != null){
				focuseView.setSelected(false);
			}
			
			View tempView = FocusFinder.getInstance().findNextFocus(this, focused, direction);
			
			if(tempView != null){
				focuseView = tempView;
			}
			
			if(focuseView != null){
				focuseView.setSelected(false);
			}
			return focuseView;
		}
		
		if(focuseView != null){
			focuseView.setSelected(true);
		}
		
		FocusProcessor.focusCache.put(FocusProcessor.OTHER_FOCUSE_VIEW, focused);
		return super.focusSearch(focused, direction);
	}

}
