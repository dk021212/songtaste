package com.hyman.chatrobot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hyman.chatrobot.bean.ChatMessage;
import com.hyman.chatrobot.bean.ChatMessage.Type;
import com.hyman.chatrobot.utils.HttpUtils;
import com.stone.songtaste.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ChatRobotActivity extends Activity {

	private ListView mLvMsg;
	private ChatMessageAdapter mAdapter;
	private List<ChatMessage> mData;

	private EditText mEtInputMsg;
	private Button mBtnSendMsg;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			ChatMessage fromMessage = (ChatMessage) msg.obj;
			mData.add(fromMessage);
			mAdapter.notifyDataSetChanged();
			mLvMsg.setSelection(mData.size() - 1);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hyman_chat_robot_activity);

		initView();
		initData();
		initListener();
	}

	private void initListener() {
		mBtnSendMsg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String toMsg = mEtInputMsg.getText().toString();
				if (TextUtils.isEmpty(toMsg)) {
					Toast.makeText(ChatRobotActivity.this, "发送消息不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}

				ChatMessage toMessage = new ChatMessage();
				toMessage.setDate(new Date());
				toMessage.setMsg(toMsg);
				toMessage.setType(Type.OUT);
				mData.add(toMessage);
				mAdapter.notifyDataSetChanged();
				mLvMsg.setSelection(mData.size() - 1);
				mEtInputMsg.setText("");

				new Thread() {
					public void run() {
						ChatMessage fromMessage = HttpUtils.sendMessage(toMsg);
						Message m = Message.obtain();
						m.obj = fromMessage;
						mHandler.sendMessage(m);
					}
				}.start();

			}

		});

	}

	private void initData() {
		mData = new ArrayList<ChatMessage>();
		mData.add(new ChatMessage("你好，小石头为你服务", Type.IN, new Date()));
		mAdapter = new ChatMessageAdapter(this, mData);
		mLvMsg.setAdapter(mAdapter);
	}

	private void initView() {
		mLvMsg = (ListView) findViewById(R.id.id_listview_msgs);
		mEtInputMsg = (EditText) findViewById(R.id.id_input_msg);
		mBtnSendMsg = (Button) findViewById(R.id.id_send_msg);
	}

}
