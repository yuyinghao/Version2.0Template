package com.hitv.android.uiversion2.custom;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ExpandView extends LinearLayout {

	private Bitmap mBitmap;
	private int linearDuration = 500;
	private int expandDuration = 800;
	private int dismissDuration = 1500;
	private Paint mpPaint;
	private Paint bitPaint;
	private int resultLinear = 40; // 划线动画执行距离完成启动下一个动画的距离 （为防止动画启动延迟产生的卡顿）
	private int resultBitMap = 30; // 画图动画距离启动下次动画的距离 （为防止动画启动延迟产生的卡顿）
	private int type = 1; // 1 为展开动画 2 为回缩动画
	private ValueAnimator valueAnimator = null;
	private ValueAnimator valueAnimator2 = null;
	private int radus = 8;
	private int velua = 0;
	private int veluaLinear = 0;
	private View view;
	private boolean isStart = false; // 保证动画只启动一次
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				velua = 0;
				invalidate();
				break;
			case 1:
				start(2);
				break;
			default:
				break;
			}
		};
	};

	public ExpandView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inite();
	}

	public void setView(View view) {
		this.view = view;
	}

	public ExpandView(Context context) {
		super(context);
		inite();
	}

	public void inite() {
		bitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mpPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mpPaint.setStrokeWidth(5);
		mpPaint.setColor(Color.BLACK);
	}

	public void setRadus(int radus) {
		this.radus = radus;
	}

	public void setDistance(int linear, int bit) {
		resultBitMap = bit;
		resultLinear = linear;
	}

	public void setDuration(int linearDuration, int expandDuration,
			int dismissDuration) {
		this.linearDuration = linearDuration;
		this.expandDuration = expandDuration;
		this.dismissDuration = dismissDuration;
	}

	public void setLinearPaintColor(int color) {
		mpPaint.setColor(color);
	}

	public void start(int type) {
		this.type = type;
		valueAnimator.cancel();
		valueAnimator2.cancel();
		handler.removeMessages(1);
		handler.sendEmptyMessage(0);
		isStart = true;
		if (type == 1) {
			valueAnimator.start();
		} else if (type == 2) {
			valueAnimator2.start();
		}
	}

	public void initedAnimation() {
		clearAnimations();
		mBitmap = getBitmap();
		valueAnimator = ValueAnimator.ofInt(0, mBitmap.getWidth() - radus * 2);
		valueAnimator2 = ValueAnimator.ofInt(0, mBitmap.getHeight());
		valueAnimator.setDuration(linearDuration);
		valueAnimator2.setDuration(expandDuration);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				veluaLinear = (Integer) animation.getAnimatedValue();
				if (veluaLinear > (mBitmap.getWidth() - resultLinear)&& type == 1) {
					if (isStart) {
						valueAnimator2.start();
						isStart = false;
					}
				}
				postInvalidate();
			}
		});

		valueAnimator2.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				velua = (Integer) animation.getAnimatedValue();
				postInvalidate();
				veluaLinear = 0;
			}
		});
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mBitmap != null && !mBitmap.isRecycled()) {
			Rect src;
			Rect des;
			if (type == 1) {
				canvas.drawLine(mBitmap.getWidth() - radus, 0,mBitmap.getWidth() - radus - veluaLinear, 0,mpPaint);
				src = new Rect(0, 0, mBitmap.getWidth(), velua);
				des = new Rect(0, 0, mBitmap.getWidth(), velua);
			} else {
				canvas.drawLine(mBitmap.getWidth() - radus, 0, radus+ veluaLinear, 0, mpPaint);
				src = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight()- velua);
				des = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight()- velua);
			}
			canvas.drawBitmap(mBitmap, src, des, bitPaint);
			if (velua == mBitmap.getHeight() && type == 1) {
				handler.removeMessages(1);
				handler.sendEmptyMessageDelayed(1, dismissDuration);
			}
			if (type == 2 && velua >= mBitmap.getHeight() - resultBitMap) {
				if (isStart) {
					valueAnimator.start();
					isStart = false;
				}
			}
		}
	}

	public void clearAnimations() {
		if (valueAnimator != null)
			valueAnimator.cancel();
		if (valueAnimator2 != null)
			valueAnimator.cancel();
		handler.removeMessages(1);
		veluaLinear=0;
		handler.sendEmptyMessage(0);
		if (mBitmap != null && !mBitmap.isRecycled()) {
			mBitmap.recycle();
		}

	}

	private Bitmap getBitmap() {
		// 打开图像缓存
		view.setDrawingCacheEnabled(true);
		// 必须调用measure和layout方法才能成功保存可视组件的截图到png图像文件
		// 测量View大小
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		// 发送位置和尺寸到View及其所有的子View
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		Bitmap bitmap = view.getDrawingCache();
		android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) getLayoutParams();
		params.width=bitmap.getWidth();
		params.height=bitmap.getHeight();
		setLayoutParams(params);
		return bitmap;
	}
}
