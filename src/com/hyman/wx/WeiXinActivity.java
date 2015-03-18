package com.hyman.wx;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.readystatesoftware.viewbadger.BadgeView;
import com.stone.songstate.utility.SysConsts;
import com.stone.songtaste.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeiXinActivity extends FragmentActivity implements OnClickListener {

	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mData;

	private TextView mTvChat;
	private TextView mTvFriend;
	private TextView mTvContact;
	private LinearLayout mLlChat;
	private LinearLayout mLlFriend;
	private LinearLayout mLlContact;
	private BadgeView mBadgeView;

	private ImageView mTabLine;
	private int mScreen1_3;

	private int mCurrentPageIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hyman_wx_activity);

		initTabLine();
		initView();
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		mTvChat = (TextView) findViewById(R.id.id_tv_chat);
		mTvFriend = (TextView) findViewById(R.id.id_tv_friend);
		mTvContact = (TextView) findViewById(R.id.id_tv_contact);
		mLlChat = (LinearLayout) findViewById(R.id.id_ll_chat);
		mLlFriend=(LinearLayout)findViewById(R.id.id_ll_friend);
		mLlContact=(LinearLayout)findViewById(R.id.id_ll_contact);
		
		mLlChat.setOnClickListener(this);
		mLlFriend.setOnClickListener(this);
		mLlContact.setOnClickListener(this);

		mData = new ArrayList<Fragment>();

		ChatMainFragment tab01 = new ChatMainFragment();
		FriendMainTabFragment tab02 = new FriendMainTabFragment();
		ContactMainTabFragment tab03 = new ContactMainTabFragment();

		mData.add(tab01);
		mData.add(tab02);
		mData.add(tab03);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public Fragment getItem(int arg0) {
				return mData.get(arg0);
			}

			@Override
			public int getCount() {
				return mData.size();
			}

		};
		

		mViewPager.setAdapter(mAdapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPx) {
				Log.d(SysConsts.SYSTEM_TAG,
						MessageFormat
								.format("page_index={0}, position={1}, positionOffset={2}, positionOffsetPx={3}",
										mCurrentPageIndex, position,
										positionOffset, positionOffsetPx));
				LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
						.getLayoutParams();
				lp.leftMargin = (int) ((position + positionOffset) * mScreen1_3);

				mTabLine.setLayoutParams(lp);

			}

			@Override
			public void onPageSelected(int position) {
				resetTextView();
				switch (position) {
				case 0:
					if (mBadgeView != null) {
						mLlChat.removeView(mBadgeView);
					}

					mBadgeView = new BadgeView(WeiXinActivity.this);
					mBadgeView.setText("17");
					mLlChat.addView(mBadgeView);

					mTvChat.setTextColor(Color.parseColor("#008000"));
					break;

				case 1:
					mTvFriend.setTextColor(Color.parseColor("#008000"));
					break;

				case 2:
					mTvContact.setTextColor(Color.parseColor("#008000"));
					break;
				}

				mCurrentPageIndex = position;
			}

		});
		
	}

	protected void resetTextView() {
		mTvChat.setTextColor(Color.BLACK);
		mTvFriend.setTextColor(Color.BLACK);
		mTvContact.setTextColor(Color.BLACK);

	}

	private void initTabLine() {
		mTabLine = (ImageView) findViewById(R.id.id_iv_tabline);
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		mScreen1_3 = outMetrics.widthPixels / 3;
		LayoutParams lp = mTabLine.getLayoutParams();
		lp.width = mScreen1_3;
		mTabLine.setLayoutParams(lp);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.id_ll_chat:
			this.mViewPager.setCurrentItem(0);
			break;
		case R.id.id_ll_friend:
			this.mViewPager.setCurrentItem(1);
			break;
		case R.id.id_ll_contact:
			this.mViewPager.setCurrentItem(2);
			break;
		}
		
	}
}
