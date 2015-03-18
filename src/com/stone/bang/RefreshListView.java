package com.stone.bang;

import java.text.MessageFormat;

import com.stone.songstate.utility.SysConsts;
import com.stone.songtaste.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class RefreshListView extends ListView implements OnScrollListener {

	View header;// ���������ļ�
	int headerHeight;// ���������ļ��߶�
	int firstVisibleItem;// ��ǰ��һ���ɼ�item��λ��
	int scrollState;// listview��ǰ����״̬
	boolean isRemark;// ��ǣ���ǰ����listview������µ�
	int startY;// ����ʱ��Yֵ

	int state;// ��ǰ��״̬
	final int NONE = 0;// ����״̬
	final int PULL = 1;// ��ʾ����״̬
	final int RELEASE = 2;// ��ʾ�ͷ�״̬
	final int REFRESHING = 3;// ˢ��״̬

	public RefreshListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		header = inflater.inflate(R.layout.bang_header_layout, null);
		headerHeight = header.getMeasuredHeight();

	}

	private void measureView(View view) {
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		// 1.��ǰheader���ҵı߾�
		// 2.�ڱ߾�
		// 3.�Ӳ��ֵĿ��
		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
		Log.d(SysConsts.SYSTEM_TAG, MessageFormat.format(
				"p.width={0},spec.width={1}", p.width, width));
		int height;
		int tempHeight = p.height;

		if (tempHeight > 0) {
			height = MeasureSpec.makeMeasureSpec(tempHeight,
					MeasureSpec.EXACTLY);
		} else {
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}

}
