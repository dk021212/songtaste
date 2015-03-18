package com.stone.drawer;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentAdapter extends FragmentPagerAdapter {
	
	List<Fragment> mList;

	public SimpleFragmentAdapter(FragmentManager fm,List<Fragment> list) {
		super(fm);
		this.mList=list;
	}

	@Override
	public Fragment getItem(int position) {
		return mList.get(position);
	}

	@Override
	public int getCount() {
		return mList.size();
	}
}
