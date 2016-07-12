package com.hitv.android.uiversion2.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.process.FocusProcessor;

public class SlideLayout extends LinearLayout{

    private Scroller scroller;
    
    private MyDispatchKeyListener backListener;
    
    public interface MyDispatchKeyListener{
    	public boolean onBackKeyClick();
    }
    
	public SlideLayout(Context context) {
		super(context);
		initView(context);
	}
	
	public SlideLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public SlideLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}
	
	private void initView(Context context){
		setOrientation(VERTICAL);
		scroller = new Scroller(context);
	}
	
	public void setBackClickListener(MyDispatchKeyListener myDispatchKeyListener){
		this.backListener = myDispatchKeyListener;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = measureWidth(widthMeasureSpec);
		int height = measureHeight(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(width, height);
		
	}
	
	private int measureWidth(int widthMeasureSpec){
		int result = 0;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		
		switch(widthMode){
		case MeasureSpec.AT_MOST:
		case MeasureSpec.EXACTLY:
			result = widthSize;
			break;
		}
		
		return result;
	}
	
	private int measureHeight(int widthMeasureSpec, int heightMeasureSpec){

		return (int)getContext().getResources().getDimension(R.dimen.slidelayoutheight);
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
    
    @Override
    public View focusSearch(View focused, int direction) {
    	
    	if(getChildCount() > 0){
    		
    		if(isContaint(getChildAt(0), focused)){
    			if(direction == View.FOCUS_UP){
    				View view = FocusProcessor.focusCache.get(FocusProcessor.OTHER_FOCUSE_VIEW);
    				return view == null ? super.focusSearch(focused, direction) : view;
    			}
    			
    		}else if(direction == View.FOCUS_UP){
    			return FocusFinder.getInstance().findNextFocus(this, focused, direction);
    		}
    	}
    	
    	return super.focusSearch(focused, direction);
    	
    }
    
    /**
     * focused 这个是否是 view 或者是否包含在view里面
     * @param view
     * @param focused
     * @return
     */
    private boolean isContaint(View view, View focused){
    	
    	if(view.equals(focused)){
    		return true;
    	}else{
    		if(view instanceof ViewGroup){
    			boolean flag = false;
    			int count = ((ViewGroup) view).getChildCount();
    			for(int i = 0; i < count; i++){
    				View child = ((ViewGroup) view).getChildAt(i);
    				if(isContaint(child, focused)){
    					flag = true;
    					break;
    				}
    			}
    			return flag;
    		}else{
    			return false;
    		}
    	}
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
    	boolean flag = false;
    	if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK && backListener != null){
    		flag = backListener.onBackKeyClick();
		}
    	
    	if(!flag){
    		flag = super.dispatchKeyEvent(event);
    	}
    	return flag;
    }
    
}
