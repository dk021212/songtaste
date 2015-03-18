package com.hyman.chatrobot;

import java.text.SimpleDateFormat;
import java.util.List;

import com.hyman.chatrobot.bean.ChatMessage;
import com.hyman.chatrobot.bean.ChatMessage.Type;
import com.stone.songtaste.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatMessageAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<ChatMessage> mData;

	public ChatMessageAdapter(Context context, List<ChatMessage> data) {
		mInflater = LayoutInflater.from(context);
		mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		ChatMessage chatMessage = mData.get(position);
		if (chatMessage.getType() == Type.IN) {
			return 0;
		}

		return 1;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ChatMessage chatMessage = mData.get(position);
		ViewHolder viewHolder = null;

		if (convertView == null) {
			if (getItemViewType(position) == 0) {
				convertView = mInflater.inflate(
						R.layout.hyman_chat_robot_item_from_msg, parent, false);
				viewHolder = new ViewHolder();
				viewHolder.tvDate = (TextView) convertView
						.findViewById(R.id.id_from_msg_date);
				viewHolder.tvMessage = (TextView) convertView
						.findViewById(R.id.id_from_msg_info);
			} else {
				convertView = mInflater.inflate(
						R.layout.hyman_chat_robot_item_to_msg, parent, false);
				viewHolder=new ViewHolder();
				viewHolder.tvDate=(TextView)convertView.findViewById(R.id.id_to_msg_date);
				viewHolder.tvMessage=(TextView)convertView.findViewById(R.id.id_to_msg_info);
			}
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder=(ViewHolder)convertView.getTag();
		}
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		viewHolder.tvDate.setText(df.format(chatMessage.getDate()));
		viewHolder.tvMessage.setText(chatMessage.getMsg());
		return convertView;
	}

	private final class ViewHolder {
		TextView tvDate;
		TextView tvMessage;
	}
}
