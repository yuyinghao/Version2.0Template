package com.hitv.android.uiversion2.adapter;

import java.util.ArrayList;
import java.util.List;

import com.hitv.android.uiversion2.interfaces.OnItemFocusChangeListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class GroupAdapter<T> extends BaseAdapter {
	
	protected List<T> list;
	protected Context mContext;
	protected LayoutInflater inflater;	
	protected OnItemFocusChangeListener listener;
	
	public GroupAdapter(Context context){
		this.mContext = context;
		inflater = LayoutInflater.from(mContext);
		list = new ArrayList<T>();
	}
	
	public void setListener(OnItemFocusChangeListener listener){
		this.listener = listener;
	}

	@Override
	public int getCount() {
		return list.size() + 2;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getItemViewType(int position) {
		
		if(position == 0 || position == getCount() - 1){
			return 0;
		}else{
			return 1;
		}
		
	}
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return createView(position, convertView, parent);
	}
	
	public void addContents(List<T> list){
		this.list.clear();
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	public void addContent(T obj){
		list.add(obj);
		notifyDataSetChanged();
	}
	public void clear(){
		list.clear();
		notifyDataSetChanged();
	}
	public void deleteContent(int position){
		list.remove(position);
		notifyDataSetChanged();
	}

	protected abstract View createView(int position, View convertView, ViewGroup parent);
}
