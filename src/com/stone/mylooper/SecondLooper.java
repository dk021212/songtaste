package com.stone.mylooper;

import java.text.MessageFormat;

import com.stone.songstate.utility.SysConsts;
import com.stone.songtaste.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class SecondLooper extends Activity {

	Button startButton = null;
	Button stopButton = null;
	ProgressBar progressbar = null;
	int i=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.black_my_looper_two);

		startButton = (Button) findViewById(R.id.btnStart);
		stopButton = (Button) findViewById(R.id.btnStop);
		progressbar = (ProgressBar) findViewById(R.id.progressBar);

		startButton.setOnClickListener(new ButtonOnclickListener());
		stopButton.setOnClickListener(new StopOnclickListener());
	}

	class ButtonOnclickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			progressbar.setVisibility(View.VISIBLE);
			// 将run放入message queue中
			handler.post(run);
		}

	}

	class StopOnclickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			handler.removeCallbacks(run);
			progressbar.setVisibility(View.GONE);
		}
	}

	Runnable run = new Runnable() {

		@Override
		public void run() {
			i += 10;
			Message msg = handler.obtainMessage();
			msg.arg1 = i;

			// 让线程延迟一秒
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			Log.i(SysConsts.SYSTEM_TAG, MessageFormat.format("run {0}%", i));
			if (i > 100) {
				handler.removeCallbacks(run);
				progressbar.setVisibility(View.GONE);
			}
		}

	};

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			progressbar.setProgress(msg.arg1);
			// 将run放入message queue中
			handler.post(run);
		}
	};

}
