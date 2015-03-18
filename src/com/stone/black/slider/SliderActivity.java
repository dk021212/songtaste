package com.stone.black.slider;

import com.stone.black.slider.view.SlidingMenu;
import com.stone.songtaste.R;

import static com.stone.black.slider.view.SlidingMenu.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SliderActivity extends Activity implements OnClickListener {
	
	
	private SlidingMenu mLeftMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.black_sliding);
		
		mLeftMenu=(SlidingMenu)findViewById(R.id.id_menu);
		Button btnPositive=(Button)findViewById(R.id.btnPositive);
		Button btnNegative=(Button)findViewById(R.id.btnNegative);
		
		btnPositive.setOnClickListener(this);
		btnNegative.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) {
		int id=v.getId();
		switch(id){
		case R.id.btnPositive:
			mLeftMenu.scroll(POSITIVE_TO);
			break;
		case R.id.btnNegative:
			mLeftMenu.scroll(NEGATIVE_TO);
			break;
		}		
	}
}
