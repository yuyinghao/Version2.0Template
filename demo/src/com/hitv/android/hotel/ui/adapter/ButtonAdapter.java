package com.hitv.android.hotel.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hitv.android.hotel.R;
import com.hitv.android.hotel.bean.Category;
import com.hitv.android.uiversion2.adapter.GroupAdapter;
import com.hitv.android.uiversion2.custom.TypeButton;

public class ButtonAdapter extends GroupAdapter<Category> {

	public ButtonAdapter(Context context) {
		super(context);
	}

	@Override
	protected View createView(int position, View convertView, ViewGroup parent) {
		
		TypeButton button = (TypeButton)LayoutInflater.from(mContext).inflate(R.layout.button_item, parent, false);
		
		button.setText(list.get(position).getCategoryName());
		
		return button;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

}
