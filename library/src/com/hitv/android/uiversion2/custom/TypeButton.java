package com.hitv.android.uiversion2.custom;

import java.util.Locale;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.utils.TypefaceUtils;

public class TypeButton extends Button{

	private int type;
    private boolean noChange;
	
	public TypeButton(Context context) {
		super(context);
		initView(context, null);
	}
	
	public TypeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}
	
	public TypeButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context, attrs);
	}
	
	private void initView(Context context, AttributeSet attrs){
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TypeTextView);
        type = array.getInteger(R.styleable.TypeButton_typeb, 0);
		noChange = array.getBoolean(R.styleable.TypeButton_nochangeb, false);
        
		setType();
		
        array.recycle();
	}
	
	private void setType(){
		
		if("en".equals(Locale.getDefault().getLanguage()) || noChange){
			switch(type){
			case 0:
				setTypeface(TypefaceUtils.getInstance(getContext()).getTypefaceGothamBook());
				break;
			case 1:
				setTypeface(TypefaceUtils.getInstance(getContext()).getTypefaceGothamMedium());
				break;
			}
		}else{
			setTypeface(TypefaceUtils.getInstance(getContext()).getTypefaceFzltzhjt());
		}
	}
	
	public void setType(int type){
		this.type = type;
	}

}
