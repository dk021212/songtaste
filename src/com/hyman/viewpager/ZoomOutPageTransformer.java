package com.hyman.viewpager;

import java.text.MessageFormat;
import com.stone.songstate.utility.SysConsts;
import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

	private static final float MIN_SCALE = 0.85f;
	private static final float MIN_ALPHA = 0.5f;

	@SuppressLint("NewApi")
	@Override
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		int pageHeight = view.getHeight();

		// Log.d(SysConsts.SYSTEM_TAG,
		// MessageFormat.format("view={0}, position={1}", view.getTag(),
		// position));

		if (position < -1) {
			// [-Infinity,-1)
			// The page is always off_screen to the left
			view.setAlpha(0);
		} else if (position <= 1) {// a页滑动至b页；a页从0.0 ~ -1；b页从1 ~ 0.0
			// [-1,1]
			// Modify the default slide transition to shrink the page as well
			float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));

			// Log.d(SysConsts.SYSTEM_TAG,
			// MessageFormat.format("view={0}, scaleFactor={1}", view.getTag(),
			// scaleFactor));

			float vertMargin = pageHeight * (1 - scaleFactor) / 2;
			float horzMargin = pageWidth * (1 - scaleFactor) / 2;

			// if(view.getTag().toString().equals("2")){
			// Log.d(SysConsts.SYSTEM_TAG,
			// MessageFormat.format("view={0}, vertMargin={1}", view.getTag(),
			// vertMargin));
			// }

			if (position < 0) { // [-1,0)
				if (view.getTag().toString().equals("1")) {
					Log.d(SysConsts.SYSTEM_TAG, MessageFormat.format(
							"view={0}, transaltionX={1}", view.getTag(),
							(horzMargin - vertMargin / 2)));
				}
				
				view.setTranslationX(horzMargin - vertMargin / 2);
			} else {// [0,1]

//				if (view.getTag().toString().equals("2")) {
//					Log.d(SysConsts.SYSTEM_TAG, MessageFormat.format(
//							"view={0}, transaltionX={1}", view.getTag(),
//							(-horzMargin + vertMargin / 2)));
//				}
				view.setTranslationX(-horzMargin + vertMargin / 2);
			}

			// Scale the page down (between MIN_SCALE and 1)
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);

			// Fade the page relative to its size
			view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
					/ (1 - MIN_SCALE) * (1 - MIN_ALPHA));
		} else {
			// (1,+Infinity]
			// The page is always off-screen to the right
			view.setAlpha(0);
		}
	}

}
