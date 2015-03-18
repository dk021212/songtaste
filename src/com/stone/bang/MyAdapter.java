package com.stone.bang;

import java.util.ArrayList;

import com.stone.songtaste.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	ArrayList<ApkEntity> mApkList;
	LayoutInflater mInflater;

	public MyAdapter(Context context, ArrayList<ApkEntity> apkList) {
		this.mApkList = apkList;
		this.mInflater = LayoutInflater.from(context);
	}

	public void onDateChange(ArrayList<ApkEntity> apkList) {
		this.mApkList = apkList;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return this.mApkList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.mApkList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ApkEntity entity = mApkList.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.bang_item_layout, null);
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item3_apkname);
			holder.tvDes = (TextView) convertView
					.findViewById(R.id.item3_apkdes);
			holder.tvInfo = (TextView) convertView
					.findViewById(R.id.item3_apkinfo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvName.setText(entity.getName());
		holder.tvDes.setText(entity.getDes());
		holder.tvInfo.setText(entity.getInfo());

		return convertView;
	}

	class ViewHolder {
		TextView tvName;
		TextView tvDes;
		TextView tvInfo;
	}

}
