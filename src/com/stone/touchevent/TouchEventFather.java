package com.stone.touchevent;

import java.text.MessageFormat;
import com.stone.songstate.utility.SysConsts;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchEventFather extends LinearLayout {

	public TouchEventFather(Context context) {
		super(context);
	}

	public TouchEventFather(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e(SysConsts.SYSTEM_TAG, MessageFormat.format(
				"TouchEventFather|dispatchTouchEvent-->{0}",
				TouchEventUtil.getTouchAction(ev.getAction())));
		return super.dispatchTouchEvent(ev);
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i(SysConsts.SYSTEM_TAG, MessageFormat.format(
				"TouchEventFather|onInterceptTouchEvent-->{0}",
				TouchEventUtil.getTouchAction(ev.getAction())));
		return super.onInterceptTouchEvent(ev);
	}

	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouchEvent(MotionEvent ev) {
		Log.d(SysConsts.SYSTEM_TAG, MessageFormat.format(
				"TouchEventFather|onTouchEvent-->{0}",
				TouchEventUtil.getTouchAction(ev.getAction())));
		return super.onTouchEvent(ev);
	}
}
