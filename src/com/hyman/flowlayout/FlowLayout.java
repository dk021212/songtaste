package com.hyman.flowlayout;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.stone.songstate.utility.SysConsts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

	// 存储所有的View
	private List<List<View>> mAllViews = new ArrayList<List<View>>();

	// 每一行的高度
	private List<Integer> mLineHeight = new ArrayList<Integer>();

	public FlowLayout(Context context) {
		this(context, null);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		// Log.d(SysConsts.SYSTEM_TAG, MessageFormat.format(
		// "sizeWidth={0}, modeWidth={1}, sizeHeight={2}, modeHeight={3}",
		// sizeWidth, modeWidth, sizeHeight, modeHeight));

		// warp content
		int width = 0;
		int height = 0;

		// 记录每一行的宽度与高度
		int lineWidth = 0;
		int lineHeight = 0;

		// 得到内部元素的个数
		int childCount = getChildCount();
		View child;
		int childWidth, childHeight;

		for (int i = 0; i < childCount; i++) {
			child = getChildAt(i);

			// 测量子View的宽和高
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			// 得到LayoutParams
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();

			// 子View占据的宽度
			childWidth = child.getMeasuredWidth() + lp.leftMargin
					+ lp.rightMargin;
			childHeight = child.getMeasuredHeight() + lp.topMargin
					+ lp.bottomMargin;

			// 换行
			if (lineWidth + childWidth > sizeWidth) {
				// 对比得到最大宽度
				width = Math.max(width, lineWidth);
				// 重置lineWidth
				lineWidth = childWidth;
				// 记录行高
				height += lineHeight;
				lineHeight = childHeight;
			} else {// 未换行
					// 叠加行宽
				lineWidth += childWidth;
				// 得到当前行最大的高度
				lineHeight = Math.max(lineHeight, childHeight);
			}

			// 最后一个控件
			if (i == childCount - 1) {
				width = Math.max(lineWidth, width);
				height += lineHeight;
			}
		}

		Log.d(SysConsts.SYSTEM_TAG, MessageFormat.format("width={0}", width));

		setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth
				: width, modeHeight == MeasureSpec.EXACTLY ? sizeHeight
				: height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mAllViews.clear();
		mLineHeight.clear();

		// 当前ViewGroup的宽度
		int width = getWidth();

		int lineWidth = 0;
		int lineHeight = 0;

		List<View> lineViews = new ArrayList<View>();

		int childCount = getChildCount();
		View child;
		MarginLayoutParams lp;
		int childWidth, childHeight;

		for (int i = 0; i < childCount; i++) {
			child = getChildAt(i);
			lp = (MarginLayoutParams) child.getLayoutParams();
			childWidth = child.getMeasuredWidth();
			childHeight = child.getMeasuredHeight();

			// 如果需要换行
			if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width) {
				// 记录LineHeight
				mLineHeight.add(lineHeight);
				// 记录当前行的Views
				mAllViews.add(lineViews);

				// 重置行宽和行高
				lineWidth = 0;
				lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
				// 重置我们的View的集合
				lineViews = new ArrayList<View>();
			}
			lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
			lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
					+ lp.bottomMargin);
			lineViews.add(child);
		}// for end

		// 处理最后一行
		mLineHeight.add(lineHeight);
		mAllViews.add(lineViews);

		// 设置子View的位置
		int left = 0;
		int top = 0;

		// 行数
		int lineNum = mAllViews.size();

		for (int i = 0; i < lineNum; i++) {
			
		}
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

}
