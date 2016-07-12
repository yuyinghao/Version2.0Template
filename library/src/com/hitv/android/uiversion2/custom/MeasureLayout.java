package com.hitv.android.uiversion2.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class MeasureLayout extends LinearLayout{

	public MeasureLayout(Context context) {
		super(context);
		initView();
	}
	
	public MeasureLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}
	
	public MeasureLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView(){
		setOrientation(VERTICAL);
		setClipChildren(false);
		setPadding(0, 45, 0, 0);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
	}

	protected int measureWidth(int widthMeasureSpec){
		
		int result = 0;
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		switch(mode){
		case MeasureSpec.AT_MOST:
		case MeasureSpec.EXACTLY:
			result = MeasureSpec.getSize(widthMeasureSpec);
			break;
		case MeasureSpec.UNSPECIFIED:
			break;
		}
		
		return result;
	}
	
	protected int measureHeight(int heightMeasureSpec){
		int mCount = getChildCount();
		int result = 0;
		for(int i = 0; i < mCount; i++){
			View view = getChildAt(i);
			result += view.getMeasuredHeight();
			LayoutParams params = (LayoutParams)view.getLayoutParams();
			result += params.topMargin;
			result += params.bottomMargin;
		}
		
		return result;
	}
}
