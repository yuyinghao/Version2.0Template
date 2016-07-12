package com.hitv.android.uiversion2.utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class QRUtils {

	private static final int BLACK = 0xff000000;

	public static void createQRCode(final ImageView imageView,
			final String str, final int width) {
		
		if(TextUtils.isEmpty(str)){
			return;
		}

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Bitmap bitmap = (Bitmap) msg.obj;
				imageView.setImageBitmap(bitmap);
			}
		};

		ThreadPoolController.getInstance().fetchData(new Runnable() {

			@Override
			public void run() {
				Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
				hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
				BitMatrix matrix = null;
				try {
					matrix = new MultiFormatWriter().encode(str,
							BarcodeFormat.QR_CODE, width, width);
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (matrix == null) {
					return;
				}
				int width = matrix.getWidth();
				int height = matrix.getHeight();
				int[] pixels = new int[width * height];

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						if (matrix.get(x, y)) {
							pixels[y * width + x] = BLACK;
						}
					}
				}
				Bitmap bitmap = Bitmap.createBitmap(width, height,
						Bitmap.Config.ARGB_8888);
				bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
				Message msg = Message.obtain();
				msg.obj = bitmap;
				handler.sendMessage(msg);
			}
		});

	}

	/**
	 * 生成二维码Bitmap
	 * 
	 * @param content
	 *            内容
	 * @param widthPix
	 *            图片宽度
	 * @param heightPix
	 *            图片高度
	 * @param logoBm
	 *            二维码中心的Logo图标（可以为null）
	 * @param filePath
	 *            用于存储二维码图片的文件路径
	 * @return 生成二维码及保存文件是否成功
	 */
	public static void createQRImage(final String content, final String logoB,
			final ImageView imageView, final int width) {

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(final Message msg) {

				if(msg.what == 0){
					imageView.setImageBitmap((Bitmap)msg.obj);
				}else if(msg.what == 1){
					final Bitmap bit = (Bitmap) msg.obj;
					ImageLoader.getInstance().loadImage(logoB, new ImageSize(msg.arg1, msg.arg1),
							ImageLoaderOptions.optionsByNullImg,
							new ImageLoadingListener() {

								@Override
								public void onLoadingStarted(String arg0, View arg1) {
								}

								@Override
								public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
								}

								@Override
								public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {

									Bitmap logoBB = addLogo(bit, arg2);

									imageView.setImageBitmap(logoBB);

								}
								@Override
								public void onLoadingCancelled(String arg0, View arg1) {
								}
							});
				}
				
			}
		};

		ThreadPoolController.getInstance().fetchData(new Runnable() {

			@Override
			public void run() {
				try {
					if (TextUtils.isEmpty(content)) {
						return;
					}
					// 配置参数
					Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
					hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
					// 容错级别
					hints.put(EncodeHintType.ERROR_CORRECTION,
							ErrorCorrectionLevel.H);
					// 设置空白边距的宽度
					// hints.put(EncodeHintType.MARGIN, 2); //default is 4

					// 图像数据转换，使用了矩阵转换
					BitMatrix bitMatrix = null;

					try {
						bitMatrix = new QRCodeWriter().encode(content,
								BarcodeFormat.QR_CODE, width, width);
					} catch (WriterException e) {
						e.printStackTrace();
					}
					if (bitMatrix == null) {
						return;
					}
					int width = bitMatrix.getWidth();
					int height = bitMatrix.getHeight();
					int[] pixels = new int[width * height];

					// 下面这里按照二维码的算法，逐个生成二维码的图片，
					// 两个for循环是图片横列扫描的结果
					for (int y = 0; y < height; y++) {
						for (int x = 0; x < width; x++) {
							if (bitMatrix.get(x, y)) {
								pixels[y * width + x] = 0xff000000;
							}
							/*
							 * else { pixels[y * WIDTH + x] = 0xffffffff; }
							 */
						}
					}

					// 生成二维码图片的格式，使用ARGB_8888
					Bitmap bitmap = Bitmap.createBitmap(width, height,
							Bitmap.Config.ARGB_8888);
					bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
					
					Message m = Message.obtain();
					m.what = 0;
					m.obj = bitmap;
					handler.sendMessage(m);

					Message msg = Message.obtain();
					msg.what = 1;
					msg.arg1 = width/6;
					msg.obj = bitmap;
					handler.sendMessage(msg);

					// 必须使用compress方法将bitmap保存到文件中再进行读取。直接返回的bitmap是没有任何压缩的，内存消耗巨大！
					// return bitmap != null &&
					// bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new
					// FileOutputStream(filePath));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 在二维码中间添加Logo图案
	 */
	private static Bitmap addLogo(Bitmap src, Bitmap logo) {
		if (src == null) {
			return null;
		}

		if (logo == null) {
			return src;
		}

		// 获取图片的宽高
		int srcWidth = src.getWidth();
		int srcHeight = src.getHeight();
		int logoWidth = logo.getWidth();
		int logoHeight = logo.getHeight();

		if (srcWidth == 0 || srcHeight == 0) {
			return null;
		}

		if (logoWidth == 0 || logoHeight == 0) {
			return src;
		}

		// logo大小为二维码整体大小的1/5
		float scaleFactor = srcWidth * 1.0f / 6 / logoWidth;
		Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight,
				Bitmap.Config.ARGB_8888);
		try {
			Canvas canvas = new Canvas(bitmap);
			canvas.drawBitmap(src, 0, 0, null);
			canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
			canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2,
					(srcHeight - logoHeight) / 2, null);

			canvas.save(Canvas.ALL_SAVE_FLAG);
			canvas.restore();
		} catch (Exception e) {
			bitmap = null;
			e.getStackTrace();
		}

		return bitmap;
	}

}
