package com.hitv.android.uiversion2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.receiver.CheckoutReciever;
import com.hitv.android.uiversion2.utils.SPUtils;

public class ReminderDialog extends Dialog{
	
	private Handler handler = new Handler();
	
	private TextView daojishi;
	
	private ImageView pressButton;
	private TextView pressText;
	
	private Runnable myRun = new Runnable() {
		
		@Override
		public void run() {
			
			if(currentTime == 1){
				dismiss();
			}else{
				currentTime--;
				
				if(currentTime == 2){
					win.setBackgroundDrawableResource(R.drawable.mengban);
					pressButton.setImageResource(R.drawable.press_down);
					pressText.setText(R.string.toviewmore);
				}
				if(currentTime <= 3){
					daojishi.setText(currentTime + "");
				}
				
				
				handler.postDelayed(myRun, 1000);
			}
		}
	};

	public ReminderDialog(Context context) {
		super(context, R.style.ReminderDialog);
	}
	
	private int currentTime;
	private Window win;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder_dialog);
		
		SPUtils.put(getContext(), CheckoutReciever.JUSNCHECKIN, false);
		
		win = getWindow();
		win.setBackgroundDrawableResource(R.drawable.mengban1);
		win.getDecorView().setPadding(0, 0, 0, 0);
		WindowManager.LayoutParams lp = win.getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.MATCH_PARENT;
		win.setAttributes(lp);
		initView();
	}

	private void initView() {
		daojishi = (TextView)findViewById(R.id.daojishi);
		pressButton = (ImageView)findViewById(R.id.press_button);
		pressText = (TextView)findViewById(R.id.press_text);
		currentTime = 5;
		daojishi.setText("3");
		pressButton.setImageResource(R.drawable.press_ok);
		pressText.setText(R.string.tosave);
		handler.postDelayed(myRun, 1000);
	}

	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN){
			if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN || event.getKeyCode() == KeyEvent.KEYCODE_BACK){
				dismiss();
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}
