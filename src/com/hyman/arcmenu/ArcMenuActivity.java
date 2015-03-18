package com.hyman.arcmenu;

import java.util.ArrayList;
import java.util.List;

import com.hyman.arcmenu.ArcMenu.OnMenuItemClickListener;
import com.stone.songtaste.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

public class ArcMenuActivity extends Activity {

	private ListView mListView;
	private ArcMenu mArcMenu;
	private List<String> mData;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hyman_arc_menu_activity);

		initData();
		initView();
		mListView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mData));
		initEvent();
	}

	private void initData() {
		mData = new ArrayList<String>();

		for (int i = 'A'; i < 'Z'; i++) {
			mData.add((char) i + "");
		}
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.id_listview);
		mArcMenu = (ArcMenu) findViewById(R.id.id_menu);
	}

	private void initEvent() {
		mListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (mArcMenu.isOpen()) {
					mArcMenu.toggleMenu(600);
				}
			}
		});

		mArcMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void onClick(View view, int pos) {
				Toast.makeText(ArcMenuActivity.this, pos + ":" + view.getTag(),
						Toast.LENGTH_SHORT).show();

			}

		});
	}

}
