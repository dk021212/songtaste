package com.stone.mylooper;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.stone.songstate.utility.SysConsts;
import com.stone.songtaste.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstLooper extends Activity {

	Button startButton;
	Button endButton;
	Handler handler = new Handler();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.black_my_looper_one);

		startButton = (Button) findViewById(R.id.btnStart);
		endButton = (Button) findViewById(R.id.btnStop);

		startButton.setOnClickListener(new ButtonClickListener());
		endButton.setOnClickListener(new ButtonClickListener());
	}

	Runnable going = new Runnable() {

		@Override
		public void run() {
			Log.i(SysConsts.SYSTEM_TAG,
					MessageFormat.format("going {0}",
							new GregorianCalendar().get(Calendar.SECOND)));
			handler.postDelayed(going,1000);
		}

	};
	
	void begin(){
		handler.post(going);
	}
	
	void stop(){
		handler.removeCallbacks(going);
	}

	class ButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.btnStart:
				begin();
				break;
			case R.id.btnStop:
				stop();
				break;
			}

		}

	}

}
