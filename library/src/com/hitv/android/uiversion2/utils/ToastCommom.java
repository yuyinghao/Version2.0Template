package com.hitv.android.uiversion2.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hitv.android.uiversion2.R;

public class ToastCommom {
	private static ToastCommom toastCommom = new ToastCommom();  
    
    private Toast toast;  
      
    private ToastCommom(){  
    }  
      
    public static ToastCommom createToastConfig(){  
        return toastCommom;  
    }  
      
    /**  
     * 显示Toast  
     * @param context  
     * @param root  
     * @param tvString  
     */  
    
    public void ToastShow(Context context,ViewGroup root, int duration){  
        View layout = LayoutInflater.from(context).inflate(R.layout.toast_layout,root);  
        toast = new Toast(context);  
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 15);  
        toast.setDuration(duration);  
        toast.setView(layout);  
        toast.show();
    }
}
