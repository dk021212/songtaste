package com.hyman.arcmenu;

import java.text.MessageFormat;

import com.stone.songstate.utility.SysConsts;
import com.stone.songtaste.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class ArcMenu extends ViewGroup implements OnClickListener {

	private static final int POS_LEFT_TOP = 0;
	private static final int POS_LEFT_BOTTOM = 1;
	private static final int POS_RIGHT_TOP = 2;
	private static final int POS_RIGHT_BOTTOM = 3;

	private Position mPosition = Position.LEFT_TOP;
	private int mRadius;

	// 菜单状态
	private Status mCurrentStatus = Status.OPEN;

	// 菜单主按钮
	private View mMainButton;

	private OnMenuItemClickListener mMenuItemClickListener;

	private enum Position {
		LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM
	}

	private enum Status {
		OPEN, CLOSE
	}

	/**
	 * 
	 * 点击子菜单回调接口
	 *
	 */
	public interface OnMenuItemClickListener {
		void onClick(View view, int pos);
	}

	public void setOnMenuItemClickListener(
			OnMenuItemClickListener onMenuItemClickListener) {
		this.mMenuItemClickListener = onMenuItemClickListener;
	}

	public ArcMenu(Context context) {
		this(context, null);
	}

	public ArcMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ArcMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				100, getResources().getDisplayMetrics());

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.ArcMenu, defStyle, 0);
		int pos = a.getInt(R.styleable.ArcMenu_position, POS_LEFT_TOP);

		switch (pos) {
		case POS_LEFT_TOP:
			mPosition = Position.LEFT_TOP;
			break;
		case POS_LEFT_BOTTOM:
			mPosition = Position.LEFT_BOTTOM;
			break;
		case POS_RIGHT_TOP:
			mPosition = Position.RIGHT_TOP;
			break;
		case POS_RIGHT_BOTTOM:
			mPosition = Position.RIGHT_BOTTOM;
			break;
		}

		mRadius = (int) a.getDimension(R.styleable.ArcMenu_radius, TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
						getResources().getDisplayMetrics()));

		Log.i(SysConsts.SYSTEM_TAG,
				MessageFormat.format("position={0}, radius={1}", pos, mRadius));
		a.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			// 测量child
			measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed) {
			layoutMainButton();

			int count = getChildCount();
			View child;
			int cl, ct;
			int cWidth, cHeight;

			for (int i = 0; i < count - 1; i++) {
				child = getChildAt(i + 1);
				cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
				ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));

				cWidth = child.getMeasuredWidth();
				cHeight = child.getMeasuredHeight();

				if (mPosition == Position.LEFT_BOTTOM
						|| mPosition == Position.RIGHT_BOTTOM) {
					ct = getMeasuredHeight() - cHeight - ct;
				}

				if (mPosition == Position.RIGHT_TOP
						|| mPosition == Position.RIGHT_BOTTOM) {
					cl = getMeasuredWidth() - cWidth - cl;
				}
				child.layout(cl, ct, cl + cWidth, ct + cHeight);
			}
		}
	}

	/**
	 * 定位主菜单按钮
	 */
	private void layoutMainButton() {
		mMainButton = getChildAt(0);
		mMainButton.setOnClickListener(this);

		int l = 0;
		int t = 0;

		int width = mMainButton.getMeasuredWidth();
		int height = mMainButton.getMeasuredHeight();

		switch (mPosition) {
		case LEFT_TOP:
			l = 0;
			t = 0;
			break;
		case LEFT_BOTTOM:
			l = 0;
			t = getMeasuredHeight() - height;
			break;
		case RIGHT_TOP:
			l = getMeasuredWidth() - width;
			t = 0;
			break;
		case RIGHT_BOTTOM:
			l = getMeasuredWidth() - width;
			t = getMeasuredHeight() - height;
			break;
		}

		mMainButton.layout(l, t, l + width, t + height);
	}

	@Override
	public void onClick(View v) {
		rotateMainButton(v, 0f, 360f, 300);
		toggleMenu(300);
	}

	private void rotateMainButton(View v, float start, float end, int duration) {
		RotateAnimation anim = new RotateAnimation(start, end,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		anim.setDuration(duration);
		anim.setFillAfter(true);
		v.startAnimation(anim);
	}

	private void changeStatus() {
		mCurrentStatus = (mCurrentStatus == Status.CLOSE ? Status.OPEN
				: Status.CLOSE);
	}

	public boolean isOpen() {
		return mCurrentStatus == Status.OPEN;
	}

	public void toggleMenu(int duration) {
		int count = getChildCount();

		int cl, ct;
		int xflag, yflag;
		AnimationSet animSet;
		Animation tranAnim;
		RotateAnimation rotateAnim;

		for (int i = 0; i < count - 1; i++) {
			final View childView = getChildAt(i + 1);
			childView.setVisibility(View.VISIBLE);

			// end 0,0
			// start
			cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
			ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));

			xflag = 1;
			yflag = 1;

			if (mPosition == Position.LEFT_TOP
					|| mPosition == Position.LEFT_BOTTOM) {
				xflag = -1;
			}

			if (mPosition == Position.LEFT_TOP
					|| mPosition == Position.RIGHT_TOP) {
				yflag = -1;
			}
			animSet = new AnimationSet(true);
			tranAnim = null;

			// to open
			if (mCurrentStatus == Status.CLOSE) {
				tranAnim = new TranslateAnimation(xflag * cl, 0, yflag * ct, 0);
				childView.setClickable(true);
				childView.setFocusable(true);
			} else {// to close
				tranAnim = new TranslateAnimation(0, xflag * cl, 0, yflag * ct);
				childView.setClickable(false);
				childView.setFocusable(false);
			}
			tranAnim.setFillAfter(true);
			tranAnim.setDuration(duration);
			tranAnim.setStartOffset((i * 100) / count);

			tranAnim.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					if (mCurrentStatus == Status.CLOSE) {
						childView.setVisibility(View.GONE);
					}
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

			});

			// Rotate Animation
			rotateAnim = new RotateAnimation(0, 720,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			rotateAnim.setDuration(duration);
			rotateAnim.setFillAfter(true);

			animSet.addAnimation(rotateAnim);
			animSet.addAnimation(tranAnim);
			childView.startAnimation(animSet);

			final int pos = i + 1;

			childView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mMenuItemClickListener != null) {
						mMenuItemClickListener.onClick(childView, pos);
					}
					changeStatus();
					menuItemAnim(pos - 1);
				}

			});
		}

		changeStatus();
	}

	private void menuItemAnim(int pos) {
		int count = getChildCount();
		View childView;

		for (int i = 0; i < count - 1; i++) {
			childView = getChildAt(i + 1);
			if (i == pos) {
				childView.startAnimation(scaleBigAnim(300));
			} else {
				childView.startAnimation(scaleSmallAnim(300));
			}

			childView.setClickable(false);
			childView.setFocusable(false);
		}
	}

	private Animation scaleSmallAnim(int duration) {
		AnimationSet animationSet = new AnimationSet(true);
		ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.0f);
		animationSet.addAnimation(scaleAnim);
		animationSet.addAnimation(alphaAnimation);
		animationSet.setDuration(duration);
		animationSet.setFillAfter(true);
		return animationSet;
	}

	private Animation scaleBigAnim(int duration) {
		AnimationSet animationSet = new AnimationSet(true);
		ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.0f);
		animationSet.addAnimation(scaleAnim);
		animationSet.addAnimation(alphaAnimation);
		animationSet.setDuration(duration);
		animationSet.setFillAfter(true);
		return animationSet;
	}
}
