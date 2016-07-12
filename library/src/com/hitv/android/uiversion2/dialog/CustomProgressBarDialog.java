package com.hitv.android.uiversion2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hitv.android.uiversion2.R;

public class CustomProgressBarDialog extends Dialog {
	private LayoutInflater inflater;
	private Context mContext;

	/**
	 * @param context
	 */
	public CustomProgressBarDialog(Context context) {
		super(context, R.style.NoTitleDialog);
		this.mContext = context;
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.progress_dialog_view, null);
		setContentView(layout);
		Window window = getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.width = 1920;
		params.height = 1080;
		params.gravity = Gravity.CENTER;
		params.dimAmount = 0; // 去背景遮盖
		window.setAttributes(params);
		this.setCancelable(false);
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			dismiss();
		}
		return super.dispatchKeyEvent(event);
	}
}
