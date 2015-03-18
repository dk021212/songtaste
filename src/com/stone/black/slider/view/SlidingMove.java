package com.stone.black.slider.view;

import java.text.MessageFormat;

import com.nineoldandroids.view.ViewHelper;
import com.stone.songstate.utility.SysConsts;
import com.stone.songtaste.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SlidingMove extends HorizontalScrollView {

	private int mWidth;
	private ImageView mImg02;

	public SlidingMove(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		LinearLayout wrapper=(LinearLayout)getChildAt(0);
		mImg02=(ImageView) wrapper.findViewById(R.id.id_img2);
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		if (changed) {
			
			this.scrollTo(200, 0);
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		Log.d(SysConsts.SYSTEM_TAG, MessageFormat.format("l={0}", l));
		ViewHelper.setTranslationX(mImg02, l);
	}

}
