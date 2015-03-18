package com.stone.touchevent;

import com.stone.songstate.utility.SysConsts;
import com.stone.songtaste.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class TouchEventActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch_event);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.w(SysConsts.SYSTEM_TAG,
				"TouchEventActivity | dispatchTouchEvent --> "
						+ TouchEventUtil.getTouchAction(ev.getAction()));
		return super.dispatchTouchEvent(ev);
	}

	public boolean onTouchEvent(MotionEvent event) {
		Log.w(SysConsts.SYSTEM_TAG, "TouchEventActivity | onTouchEvent --> "
				+ TouchEventUtil.getTouchAction(event.getAction()));
		return super.onTouchEvent(event);
	}

}
