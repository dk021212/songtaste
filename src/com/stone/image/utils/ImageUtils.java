package com.stone.image.utils;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public final class ImageUtils {

	public static Bitmap compactBitmap(String url) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = null;
		// 由于设置inJustDecodeBounds为true,因此执行下面代码后bitmap为空
		// url="/sdcard/image.jpg"
		bitmap = BitmapFactory.decodeFile(url, options);
		// 计算缩放比例，由于是固定比例缩放，只用高或宽其中一个数据进行计算即可
		int scale = (int) (options.outHeight / (float) 200);
		// 因为结果为int型，如果相除后值为0.n,则结果将是0
		if (scale <= 0) {
			scale = 1;
		}

		options.inSampleSize = scale;
		options.inPreferredConfig = Bitmap.Config.ARGB_4444;
		options.inPurgeable = true;
		options.inInputShareable = true;
		options.inJustDecodeBounds = false;

		// 重新读入图片，注意此时已经把options.inJustDecodeBounds设为false
		bitmap = BitmapFactory.decodeFile(url, options);
		return bitmap;
	}

	public static Bitmap readBitMap(Context context, int resId) {
		BitmapFactory.Options option = new BitmapFactory.Options();
		option.inPreferredConfig = Bitmap.Config.RGB_565;
		option.inPurgeable = true;
		option.inInputShareable = true;

		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, option);
	}

}
