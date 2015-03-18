package com.hyman.flowlayout;


import com.stone.songtaste.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


public class FlowActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hyman_flow_layout_activity);
	}

	
}
