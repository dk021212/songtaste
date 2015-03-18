package com.stone.black.slider;

import com.nineoldandroids.view.ViewHelper;
import com.stone.songtaste.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class Move extends Activity implements OnClickListener {

	ImageView mImgMove;
	ImageView mImg02;
	HorizontalScrollView mScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.black_move);

		Button btnMove = (Button) findViewById(R.id.btnMove);
		btnMove.setOnClickListener(this);
		mImgMove = (ImageView) findViewById(R.id.imgMove);
		mScrollView = (HorizontalScrollView) findViewById(R.id.hsvMove);
		
		mImg02=(ImageView)findViewById(R.id.id_img2);
	}

	@Override
	public void onClick(View v) {
		ViewHelper.setTranslationX(mImgMove, 100);
		mScrollView.scrollTo(100, 0);
		ViewHelper.setTranslationX(mImg02, 100);
	}

}
