package com.stone.black.slider.view;

import java.text.MessageFormat;

import com.nineoldandroids.view.ViewHelper;
import com.stone.songstate.utility.SysConsts;
import com.stone.songtaste.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class SlidingMenu extends HorizontalScrollView {

	private LinearLayout mWrapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;

	private int mMenuWidth;

	// dp
	private int mMenuRightPadding = 50;

	private boolean once;

	private boolean isOpen;

	public final static int POSITIVE_TO = 1;
	public final static int NEGATIVE_TO = 2;
	public final static int POSITIVE_BY = 3;
	public final static int NEGATIVE_BY = 4;

	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		// 获取自定义属性
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.BlackSlidingMenu, defStyle, 0);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.BlackSlidingMenu_blackRightPadding:
				int rightPadding = (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources()
								.getDisplayMetrics());
				mMenuRightPadding = a.getDimensionPixelSize(attr, rightPadding);
				break;
			}
		}

		a.recycle();

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
	}

	public SlidingMenu(Context context) {
		this(context, null);
	}

	/**
	 * 设置子View的宽和高，设置自己的宽和高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		if (!once) {
			mWrapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWrapper.getChildAt(0);
			mContent = (ViewGroup) mWrapper.getChildAt(1);
			mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth
					- mMenuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;
			once = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 通过设置偏移量，将menu隐藏
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {

			this.scrollTo(mMenuWidth, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			Log.d(SysConsts.SYSTEM_TAG, "onTouchEvent.ACTION_UP");
			// 隐藏在左边的宽度
			int scrollX = getScrollX();
			if (scrollX >= mMenuWidth / 2) {
				this.smoothScrollTo(mMenuWidth, 0);
			} else {
				this.smoothScrollTo(0, 0);
				isOpen = true;
			}

			return true;
		}

		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);

		float scale = l * 1.0f / mMenuWidth;// 1~0
		float leftScale = 1f - 0.3f * scale;// 0.7~1
		float rightScale = 0.7f + 0.3f * scale;// 1~0.7
		float leftAlpha = 0.6f + 0.4f * (1 - scale);// 0.6~1

		Log.d(SysConsts.SYSTEM_TAG, MessageFormat.format(
				"width={0}, l={1}, scale={2}, offset={3}", mMenuWidth, l,
				scale, mMenuWidth * scale));
		ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.8f);

		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);

		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
	}

	public void scroll(int type) {
		switch (type) {
		case POSITIVE_TO:
			// this.smoothScrollTo(100, 0);
			ViewHelper.setTranslationX(mMenu, -100);
			break;
		case NEGATIVE_TO:
			this.smoothScrollTo(-50, 0);
			break;
		case POSITIVE_BY:
			this.smoothScrollBy(200, 0);
			break;
		case NEGATIVE_BY:
			this.smoothScrollBy(-200, 0);
			break;
		}
	}

}
