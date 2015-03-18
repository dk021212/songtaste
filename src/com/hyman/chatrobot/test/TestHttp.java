package com.hyman.chatrobot.test;

import com.hyman.chatrobot.utils.HttpUtils;
import com.stone.songstate.utility.SysConsts;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestHttp extends AndroidTestCase {

	public void test() {
		String res = HttpUtils.doGet("���ҽ���Ц��");
		Log.i(SysConsts.SYSTEM_TAG, res);
		res = HttpUtils.doGet("���ҽ��������");
		Log.e(SysConsts.SYSTEM_TAG, res);
		res = HttpUtils.doGet("���");
		Log.e(SysConsts.SYSTEM_TAG, res);
		res = HttpUtils.doGet("������");
		Log.e(SysConsts.SYSTEM_TAG, res);
	}

}
