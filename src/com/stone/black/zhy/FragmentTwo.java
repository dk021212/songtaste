package com.stone.black.zhy;

import com.stone.songtaste.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentTwo extends Fragment implements OnClickListener {

	private Button mBtnInput;;
	private IBtnTwoClickListener iBtnTwoClickListener;
	
	public interface IBtnTwoClickListener{
		void btnTwoClick();
	}
	
	//设置回调接口
	public void setBtnTwoClickListener(IBtnTwoClickListener btnTwoClickListener){
		this.iBtnTwoClickListener=btnTwoClickListener;
	}

	@Override
	public View onCreateView(LayoutInflater layoutInflater,
			ViewGroup container, Bundle saveInstanceState) {
		View view = layoutInflater.inflate(R.layout.black_zhy_two, container,
				false);
		mBtnInput = (Button) view.findViewById(R.id.btnInput);
		mBtnInput.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
//		FragmentThree fThree=new FragmentThree();
//		FragmentManager fm=this.getFragmentManager();
//		FragmentTransaction transaction=fm.beginTransaction();
//		transaction.hide(this);
//		transaction.add(R.id.id_content, fThree,"THREE");
//		transaction.addToBackStack(null);
//		transaction.commit();

		if(iBtnTwoClickListener!=null){
			iBtnTwoClickListener.btnTwoClick();
		}
	}

}
