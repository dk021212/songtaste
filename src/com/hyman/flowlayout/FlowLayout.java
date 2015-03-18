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

	// �洢���е�View
	private List<List<View>> mAllViews = new ArrayList<List<View>>();

	// ÿһ�еĸ߶�
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

		// ��¼ÿһ�еĿ����߶�
		int lineWidth = 0;
		int lineHeight = 0;

		// �õ��ڲ�Ԫ�صĸ���
		int childCount = getChildCount();
		View child;
		int childWidth, childHeight;

		for (int i = 0; i < childCount; i++) {
			child = getChildAt(i);

			// ������View�Ŀ�͸�
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			// �õ�LayoutParams
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();

			// ��Viewռ�ݵĿ��
			childWidth = child.getMeasuredWidth() + lp.leftMargin
					+ lp.rightMargin;
			childHeight = child.getMeasuredHeight() + lp.topMargin
					+ lp.bottomMargin;

			// ����
			if (lineWidth + childWidth > sizeWidth) {
				// �Աȵõ������
				width = Math.max(width, lineWidth);
				// ����lineWidth
				lineWidth = childWidth;
				// ��¼�и�
				height += lineHeight;
				lineHeight = childHeight;
			} else {// δ����
					// �����п�
				lineWidth += childWidth;
				// �õ���ǰ�����ĸ߶�
				lineHeight = Math.max(lineHeight, childHeight);
			}

			// ���һ���ؼ�
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

		// ��ǰViewGroup�Ŀ��
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

			// �����Ҫ����
			if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width) {
				// ��¼LineHeight
				mLineHeight.add(lineHeight);
				// ��¼��ǰ�е�Views
				mAllViews.add(lineViews);

				// �����п���и�
				lineWidth = 0;
				lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
				// �������ǵ�View�ļ���
				lineViews = new ArrayList<View>();
			}
			lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
			lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
					+ lp.bottomMargin);
			lineViews.add(child);
		}// for end

		// �������һ��
		mLineHeight.add(lineHeight);
		mAllViews.add(lineViews);

		// ������View��λ��
		int left = 0;
		int top = 0;

		// ����
		int lineNum = mAllViews.size();

		for (int i = 0; i < lineNum; i++) {
			
		}
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

}
