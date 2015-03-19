package com.stone.drawer;

import com.stone.songtaste.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SimpleFragment extends Fragment {

	public final static String TEXT_VALUE = "TextValue";

	static SimpleFragment newInstance(String s) {
		SimpleFragment fragment = new SimpleFragment();
		Bundle bundle = new Bundle();
		bundle.putString(TEXT_VALUE, s);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstance) {
		Bundle args = getArguments();
		String text = args != null ? args.getString(TEXT_VALUE)
				: "Default Value";
		View view = inflater.inflate(R.layout.black_drawer_simple_fragment,
				container, false);
		TextView tvView=(TextView)view.findViewById(R.id.id_text);
		tvView.setText(text);
		return view;
	}
}
