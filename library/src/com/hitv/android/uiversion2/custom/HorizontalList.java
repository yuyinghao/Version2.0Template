package com.hitv.android.uiversion2.custom;

import java.util.LinkedList;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Scroller;

import com.hitv.android.uiversion2.adapter.GroupAdapter;
import com.hitv.android.uiversion2.interfaces.OnItemFocusChangeListener;

public class HorizontalList extends AdapterView<Adapter> {

	// 新添加的所有子视图在当前最当前最后一个子视图后添加的布局模型
	private static final int LAYOUT_MODE_BELOW = 0;
	// 与LAYOUT_MODE_BELOW相反方向添加的布局模型
	private static final int LAYOUT_MODE_ABOVE = 1;

	private Adapter mAdapter;
	// 当前显示最后一个Item在Adapter中位置
	private int mLastItemPosition = -1;
	// 当前显示第一个Item在Adapter中位置
	private int mFirstItemPosition;

	// 当前顶部第一个item
	private int mListLeft;
	private int mListOldLeft;
	private int mListLeftOffset;
	// 触摸Down事件时进行记录
	private int mListLeftStart;
	
	//是否正在移动
	private boolean scrolling;
	private Scroller scroller;
	
	private com.hitv.android.uiversion2.interfaces.OnItemClickListener mItemClickListener;
	
	//private int mItemMargin = 20;

	//存放复用的View
	private final LinkedList<View> mCachedItemViews = new LinkedList<View>();

	public HorizontalList(Context context) {
		super(context);
		init(context);
	}

	public HorizontalList(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public HorizontalList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context){
		setChildrenDrawingOrderEnabled(true);
		scroller = new Scroller(context);
	}

	@Override
	public Adapter getAdapter() {
		return mAdapter;
	}
	
	public void setOnItemClickListener(com.hitv.android.uiversion2.interfaces.OnItemClickListener mItemClickListener) {
		this.mItemClickListener = mItemClickListener;
	}

	@Override
	public void setAdapter(Adapter adapter) {
		
		if(mAdapter != null){
			mAdapter.unregisterDataSetObserver(dataSetObserver);
			if(mAdapter instanceof GroupAdapter){
				((GroupAdapter) mAdapter).setListener(null);
			}
		}
		
		mAdapter = adapter;
		mAdapter.registerDataSetObserver(dataSetObserver);
		
		if(mAdapter instanceof GroupAdapter){
			((GroupAdapter) mAdapter).setListener(new OnItemFocusChangeListener() {
				
				@Override
				public void itemFocusChange(View v, boolean flag) {
					if (flag) {
						changeToView(v, 1.0f, 1.1f, 200);

						if (currentFocuseView != null) {
							measureScrollDistance(v);
						}

						currentFocuseView = v;
					} else {
						changeToView(v, 1.1f, 1.0f, 200);
					}
				}
			});
		}
		
		initLayout();
	}
	
	private void initLayout(){
		removeAllViewsInLayout();
		requestLayout();
	}
	
	private DataSetObserver dataSetObserver = new DataSetObserver() {

		@Override
		public void onChanged() {
			super.onChanged();
			initLayout();
		}

		@Override
		public void onInvalidated() {
			super.onInvalidated();
			initLayout();
		}
		
	};

	@Override
	public View getSelectedView() {
		return null;
	}

	@Override
	public void setSelection(int position) {
	}
	
	/*@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if(getChildCount() != 0){
			View view = getChildAt(0);
			view.measure(widthMeasureSpec, heightMeasureSpec);
			
			setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), view.getMeasuredHeight());
		}
	}*/
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		if (mAdapter == null) {
			return;
		}

		if (getChildCount() == 0) {
			mLastItemPosition = -1;
			
			fillListRight(mListLeft, 0);
		} else {
			final int offset = mListLeft + mListLeftOffset - getChildAt(0).getLeft();
			
			removeNonVisibleViews(offset);
			fillList(offset);
		}
		positioinItems();

		invalidate();

	}

	private void fillList(final int offset) {
		final int rightEdge = getChildAt(getChildCount() - 1).getRight();// + mItemMargin;
		fillListRight(rightEdge, offset);

		final int leftEdge = getChildAt(0).getLeft();
		fillListLeft(leftEdge, offset);
	}

	private void fillListLeft(int leftEdge, int offset) {
		while (leftEdge + offset > 0 && mFirstItemPosition > 0) {
			
			
			mFirstItemPosition--;

			View newLeftChild = mAdapter.getView(mFirstItemPosition,
					getCachedView(), this);
			newLeftChild.setTag(mFirstItemPosition);
			addAndMeasureChild(newLeftChild, LAYOUT_MODE_ABOVE);
			int childWidth = newLeftChild.getMeasuredWidth();// + mItemMargin;
			leftEdge -= childWidth;

			mListLeftOffset -= childWidth;
		}
	}

	private void fillListRight(int rightEdge, int offset) {
		
		while (rightEdge + offset < getWidth()
				&& mLastItemPosition < mAdapter.getCount() - 1) {
			mLastItemPosition++;
			View newRightChild = mAdapter.getView(mLastItemPosition,
					getCachedView(), this);
			newRightChild.setTag(mLastItemPosition);
			addAndMeasureChild(newRightChild, LAYOUT_MODE_BELOW);
			rightEdge += (newRightChild.getMeasuredWidth());// + mItemMargin);
		}
	}

	private View currentFocuseView;

	private void addAndMeasureChild(View child, int layoutMode) {

		LayoutParams params = child.getLayoutParams();
		if (params == null) {
			params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}

		final int index = layoutMode == LAYOUT_MODE_ABOVE ? 0 : -1;
		addViewInLayout(child, index, params, true);

		final int itemHeight = getHeight();
		
		if(child.getTag().equals(0) || child.getTag().equals(mAdapter.getCount()-1)){
			child.measure(MeasureSpec.EXACTLY | params.width, MeasureSpec.EXACTLY | itemHeight);
		}else{
			child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY | itemHeight);
		}
	}

	private void positioinItems() {
		
		scrolling = mListOldLeft != mListLeft;
		
		if(scrolling){
			
			if(!scroller.isFinished()){
				
				if(mListLeft == scroller.getFinalX())
					return;
				
				mListOldLeft = scroller.getFinalX();
				scroller.abortAnimation();
				positionItems(mListOldLeft);
			}
			
			startScroll(mListOldLeft, 0, mListLeft - mListOldLeft, 0, 1000);
			scrolling = false;
			mListOldLeft = mListLeft;
		}else{
			positionItems(mListOldLeft);
		}
	}
	
	private void positionItems(int startLeft){

		int left = startLeft + mListLeftOffset;

		for (int i = 0; i < getChildCount(); i++) {
			final View child = getChildAt(i);

			final int width = child.getMeasuredWidth();// + mItemMargin;
			final int height = child.getMeasuredHeight();
			final int top = (getHeight() - height) / 2;

			child.layout(left, top, left + width, top + height);
			left += width;
		}
	}
	
	public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        if (!scroller.isFinished())
        	scroller.abortAnimation();
        scroller.startScroll(startX, startY, dx, dy, duration);
        invalidate();
    }
	
	private void measureScrollDistance(View v){
		
		if(v.getMeasuredWidth() * mAdapter.getCount() < getWidth()){
			return;
		}
		
		int tag = (Integer) v.getTag();
		int distance = 0;
		int mMid = (getWidth() - v.getMeasuredWidth()) / 2;
		int childMid = v.getMeasuredWidth() * (tag - 1) + getChildAt(0).getMeasuredWidth();
		int childTurnMid = v.getMeasuredWidth() * (mAdapter.getCount() - tag - 1) + getChildAt(getChildCount()-1).getMeasuredWidth();
		
		if(childMid < mMid){
			distance = v.getMeasuredWidth() * (tag - 1) + getChildAt(0).getMeasuredWidth() - v.getLeft();
		}else if(childTurnMid < mMid + v.getMeasuredWidth()){
			distance = (getWidth() - (mAdapter.getCount()-tag-1) * v.getMeasuredWidth() - getChildAt(getChildCount()-1).getMeasuredWidth()) - v.getLeft();
		}else{
			distance = (getWidth() - v.getMeasuredWidth())/2 - v.getLeft();
		}

		mListLeftStart = getChildAt(0).getLeft()
				- mListLeftOffset;
		mListLeft = mListLeftStart + distance;
		requestLayout();
	}

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
        	positionItems(scroller.getCurrX());
            
            postInvalidate();
        }
    }
	
	private void removeNonVisibleViews(final int offset) {
	    int childCount = getChildCount();

	    if (mLastItemPosition != mAdapter.getCount() -1 && childCount > 1) {
	        View firstChild = getChildAt(0);
	        //while (firstChild != null && firstChild.getRight() + mItemMargin + offset < 0) {
	        while (firstChild != null && firstChild.getRight() + offset < 0) {
	            removeViewInLayout(firstChild);
	            childCount--;
	            mCachedItemViews.addLast(firstChild);
	            mFirstItemPosition++;
	            mListLeftOffset += (firstChild.getMeasuredWidth());// + mItemMargin);
	            
	            if (childCount > 1) {
	                firstChild = getChildAt(0);  
	            } else {
	                firstChild = null;
	            }
	        }
	    }

	    if (mFirstItemPosition != 0 && childCount > 1) {
	        View lastChild = getChildAt(childCount - 1);
	        while (lastChild != null && lastChild.getLeft() + offset > getWidth()) {
	            removeViewInLayout(lastChild);
	            childCount--;
	            mCachedItemViews.addLast(lastChild);
	            mLastItemPosition--;

	            if (childCount > 1) {
	                lastChild = getChildAt(childCount - 1);
	            } else {
	                 lastChild = null;
	            }
	        }
	    }

	}
	
	private View getCachedView() {
	    
	    if (mCachedItemViews.size() != 0) {
	        return mCachedItemViews.removeFirst();
	    }

	    return null;
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		
		if(event.getAction() == KeyEvent.ACTION_DOWN){
			if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER || event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
				
				if(mItemClickListener != null){
					View v = getFocusedChild();
					mItemClickListener.onItemClick(v, ((Integer)v.getTag() - 1));
				}
				
				return true;
			}
		}
		
		return super.dispatchKeyEvent(event);
	}
	
	@Override
    protected int getChildDrawingOrder(int childCount, int i) {
		if(getFocusedChild() == null){
			return super.getChildDrawingOrder(childCount, i);
		}
		
		int position = (Integer)getFocusedChild().getTag() - (Integer)getChildAt(0).getTag();
        
        if (i == childCount - 1) {// 这是最后一个需要刷新的item
            return position;
        }
        if (i == position) {// 这是原本要在最后一个刷新的item
            return childCount - 1;
        }
        return i;
    }
	
	@Override
	public View focusSearch(View focused, int direction) {
		
		int position = (Integer)focused.getTag();
		
		if(position == 1 && direction == View.FOCUS_LEFT){
			return null;
		}else if(position == mAdapter.getCount() - 2 && direction == View.FOCUS_RIGHT){
			return null;
		}
		
		return super.focusSearch(focused, direction);
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
}
