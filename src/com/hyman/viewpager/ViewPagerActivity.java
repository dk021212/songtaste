package com.hyman.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.stone.black.utils.ImageHelper;
import com.stone.songtaste.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ViewPagerActivity extends Activity {

	public ViewPager mViewPager;
	private int[] mImgIds = new int[] { R.drawable.guide_image1,
			R.drawable.guide_image2, R.drawable.guide_image3 };
	private List<ImageView> mImageViews = new ArrayList<ImageView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hyman_viewpager);

		initData();
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		mViewPager.setAdapter(new PagerAdapter() {

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(mImageViews.get(position));
				return mImageViews.get(position);
			}

			@Override
			public int getCount() {
				return mImgIds.length;
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}
			
			@Override
			public void destroyItem(ViewGroup container,int position,Object object){
				container.removeView(mImageViews.get(position));
			}

		});
	}

	private void initData() {
		for (int i=0;i<mImgIds.length; i++) {
			ImageView imageView = new ImageView(getApplicationContext());
			imageView.setTag(i+1);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageView.setImageBitmap(ImageHelper.readBitMap(getApplicationContext(), mImgIds[i]));
			mImageViews.add(imageView);
		}

	}

	

}
