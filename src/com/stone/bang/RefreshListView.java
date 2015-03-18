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

	View header;// 顶部布局文件
	int headerHeight;// 顶部布局文件高度
	int firstVisibleItem;// 当前第一个可见item的位置
	int scrollState;// listview当前滚动状态
	boolean isRemark;// 标记，当前是在listview最顶端摁下的
	int startY;// 按下时的Y值

	int state;// 当前的状态
	final int NONE = 0;// 正常状态
	final int PULL = 1;// 提示下拉状态
	final int RELEASE = 2;// 提示释放状态
	final int REFRESHING = 3;// 刷新状态

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

		// 1.当前header左右的边距
		// 2.内边距
		// 3.子布局的宽度
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
