package com.stone.black.zhy;

import com.stone.black.zhy.FragmentOne.IBtnOneClickListener;
import com.stone.black.zhy.FragmentTwo.IBtnTwoClickListener;
import com.stone.songtaste.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class ZhyActivity extends FragmentActivity implements
		IBtnOneClickListener, IBtnTwoClickListener {

	private FragmentOne mFOne;
	private FragmentTwo mFTwo;
	private FragmentThree mFThree;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.black_zhy_activity);

		if (savedInstanceState == null) {
			mFOne = new FragmentOne();
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction tx = fm.beginTransaction();
			tx.add(R.id.id_content, mFOne, "ONE");
			tx.commit();
		}
	}

	@Override
	public void btnOneClick() {
		if (mFTwo == null) {
			mFTwo = new FragmentTwo();
			mFTwo.setBtnTwoClickListener(this);
		}

		FragmentManager fm = this.getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.id_content, mFTwo, "TWO");
		transaction.addToBackStack(null);
		transaction.commit();
	}

	@Override
	public void btnTwoClick() {
		if (mFThree == null) {
			mFThree = new FragmentThree();
		}

		FragmentManager fm = this.getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.hide(mFTwo);
		transaction.add(R.id.id_content, mFThree, "THREE");
		transaction.addToBackStack(null);
		transaction.commit();
	}
}