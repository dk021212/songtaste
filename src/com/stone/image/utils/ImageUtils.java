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
		// ��������inJustDecodeBoundsΪtrue,���ִ����������bitmapΪ��
		// url="/sdcard/image.jpg"
		bitmap = BitmapFactory.decodeFile(url, options);
		// �������ű����������ǹ̶��������ţ�ֻ�ø߻������һ�����ݽ��м��㼴��
		int scale = (int) (options.outHeight / (float) 200);
		// ��Ϊ���Ϊint�ͣ���������ֵΪ0.n,��������0
		if (scale <= 0) {
			scale = 1;
		}

		options.inSampleSize = scale;
		options.inPreferredConfig = Bitmap.Config.ARGB_4444;
		options.inPurgeable = true;
		options.inInputShareable = true;
		options.inJustDecodeBounds = false;

		// ���¶���ͼƬ��ע���ʱ�Ѿ���options.inJustDecodeBounds��Ϊfalse
		bitmap = BitmapFactory.decodeFile(url, options);
		return bitmap;
	}

	public static Bitmap readBitMap(Context context, int resId) {
		BitmapFactory.Options option = new BitmapFactory.Options();
		option.inPreferredConfig = Bitmap.Config.RGB_565;
		option.inPurgeable = true;
		option.inInputShareable = true;

		// ��ȡ��ԴͼƬ
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, option);
	}

}
