package com.stone.drawer;

import com.stone.songtaste.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SimpleFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstance) {
		View view = inflater.inflate(R.layout.black_drawer_simple_fragment,
				container, false);
		return view;
	}
}
