package com.stone.black.zhy;

import com.stone.songtaste.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentThree extends Fragment implements OnClickListener {

	private Button mBtnInput;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.black_zhy_three, container, false);
		mBtnInput = (Button) view.findViewById(R.id.btnInput);
		mBtnInput.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getActivity(), "I am a button in FRAGMENT THREE!!",
				Toast.LENGTH_LONG).show();
	}

}
