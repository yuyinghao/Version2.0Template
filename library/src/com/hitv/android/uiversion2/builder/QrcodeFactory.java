package com.hitv.android.uiversion2.builder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hitv.android.uiversion2.R;
import com.hitv.android.uiversion2.utils.QRUtils;

public class QrcodeFactory {

	public static View getQrCode(Context context, String content) {

		View view = LayoutInflater.from(context)
				.inflate(R.layout.qr_code, null);

		ImageView qrImage = (ImageView) view.findViewById(R.id.qrcode_image);
		QRUtils.createQRCode(qrImage, content, (int)context.getResources().getDimension(R.dimen.qrwidth));

		return view;
	}

	public static View getQrCode(Context context, String content, String logo, String detail) {
		View view = LayoutInflater.from(context)
				.inflate(R.layout.qr_code, null);

		ImageView qrImage = (ImageView) view.findViewById(R.id.qrcode_image);
		TextView qrDetail = (TextView)view.findViewById(R.id.qrcode_detail);
		QRUtils.createQRImage(content, logo, qrImage, (int)context.getResources().getDimension(R.dimen.qrwidth));

		if(!TextUtils.isEmpty(detail)){
			qrDetail.setText(detail);
		}
		
		return view;
	}

}