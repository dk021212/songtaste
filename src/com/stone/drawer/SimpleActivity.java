package com.stone.drawer;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;

import com.stone.songtaste.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

public class SimpleActivity extends FragmentActivity implements OnClickListener {
	private ViewPager mViewPager;
	private List<Fragment> mFragmentList;
	private DrawerLayout mDrawerLayout;
	private ListView mLvMenu;
	private Button mBtnMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fabric.with(this, new Crashlytics());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.black_drawer_simple_activity);
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mBtnMenu = (Button) findViewById(R.id.id_btn_menu);
		mBtnMenu.setOnClickListener(this);

		mLvMenu = (ListView) findViewById(R.id.id_lv_menu);

		initViewPager();
		
		throw new RuntimeException("This is a crash");
	}

	private void initViewPager() {
		mViewPager = (ViewPager) findViewById(R.id.id_view);
		mFragmentList = new ArrayList<Fragment>();

		SimpleFragment fragment01 = SimpleFragment
				.newInstance("The First One!!!");
		mFragmentList.add(fragment01);
		SimpleFragment fragment02 = SimpleFragment
				.newInstance("The Second One!!!");
		mFragmentList.add(fragment02);
		SimpleFragment fragment03 = SimpleFragment
				.newInstance("The Third One!!!");
		mFragmentList.add(fragment03);

		mViewPager.setAdapter(new SimpleFragmentAdapter(
				getSupportFragmentManager(), mFragmentList));
		mViewPager.setCurrentItem(0);
	}

	@Override
	public void onClick(View v) {
		if (mDrawerLayout.isDrawerOpen(mLvMenu)) {
			mDrawerLayout.closeDrawer(mLvMenu);
		} else {
			mDrawerLayout.openDrawer(mLvMenu);
		}

	}

}
