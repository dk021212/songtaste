package com.hyman.chatrobot.test;

import com.hyman.chatrobot.utils.HttpUtils;
import com.stone.songstate.utility.SysConsts;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestHttp extends AndroidTestCase {

	public void test() {
		String res = HttpUtils.doGet("给我讲个笑话");
		Log.i(SysConsts.SYSTEM_TAG, res);
		res = HttpUtils.doGet("给我讲个鬼故事");
		Log.e(SysConsts.SYSTEM_TAG, res);
		res = HttpUtils.doGet("你好");
		Log.e(SysConsts.SYSTEM_TAG, res);
		res = HttpUtils.doGet("你真美");
		Log.e(SysConsts.SYSTEM_TAG, res);
	}

}
