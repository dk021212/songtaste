package com.stone.black.zhy;

import com.stone.songtaste.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentOne extends Fragment implements OnClickListener {

	private Button mBtnInput;

	public interface IBtnOneClickListener {
		void btnOneClick();
	}

	private static final String TAG = "FRAGMENT_ONE";

	@Override
	public View onCreateView(LayoutInflater layoutInflater,
			ViewGroup container, Bundle saveInstanceState) {
		Log.e(TAG, "onCreateView");

		View view = layoutInflater.inflate(R.layout.black_zhy_one, container,
				false);
		mBtnInput = (Button) view.findViewById(R.id.btnInput);
		mBtnInput.setOnClickListener(this);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.e(TAG, "onCreate");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e(TAG, "onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy");
	}

	@Override
	public void onClick(View v) {
		// FragmentTwo fTwo = new FragmentTwo();
		// FragmentManager fm = getFragmentManager();
		// FragmentTransaction transaction = fm.beginTransaction();
		// transaction.replace(R.id.id_content, fTwo);
		// transaction.addToBackStack(null);
		// transaction.commit();

		if (getActivity() instanceof IBtnOneClickListener) {
			((IBtnOneClickListener) getActivity()).btnOneClick();
		}

	}

}
