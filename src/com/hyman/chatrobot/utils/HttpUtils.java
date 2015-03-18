package com.hyman.chatrobot.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;

import android.util.Log;

import com.google.gson.Gson;
import com.hyman.chatrobot.bean.ChatMessage;
import com.hyman.chatrobot.bean.Result;
import com.hyman.chatrobot.bean.ChatMessage.Type;
import com.stone.songstate.utility.SysConsts;

public class HttpUtils {

	private static final String URL = "http://www.tuling123.com/openapi/api";
	private static final String API_KEY = "aca89a0b9e4c936e3d150f2416169e12";

	public static ChatMessage sendMessage(String msg) {
		ChatMessage chatMessage = new ChatMessage();
		String jsonRes = doGet(msg);
		Gson gson = new Gson();
		Result result = null;

		try {
			result = gson.fromJson(jsonRes, Result.class);
			chatMessage.setMsg(result.getText());
		} catch (Exception e) {
			chatMessage.setMsg("服务器繁忙，请稍后再试");
		}
		chatMessage.setDate(new Date());
		chatMessage.setType(Type.IN);
		return chatMessage;
	}

	public static String doGet(String msg) {
		String result = "";
		String url = setParams(msg);
		ByteArrayOutputStream baos = null;
		InputStream is = null;

		try {
			java.net.URL urlNet = new java.net.URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlNet
					.openConnection();
			conn.setReadTimeout(5000);
			conn.setConnectTimeout(5000);
			is = conn.getInputStream();
			int len = -1;
			byte[] buf = new byte[128];
			baos = new ByteArrayOutputStream();

			while ((len = is.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}
			baos.flush();
			result = new String(baos.toByteArray());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Log.d(SysConsts.SYSTEM_TAG, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Log.d(SysConsts.SYSTEM_TAG, e.getMessage());
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				Log.d(SysConsts.SYSTEM_TAG, e.getMessage());
			}

			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				Log.d(SysConsts.SYSTEM_TAG, e.getMessage());
			}
		}

		return result;
	}

	private static String setParams(String msg) {
		String url = "";
		try {
			url = MessageFormat.format("{0}?key={1}&info={2}", URL, API_KEY,
					URLEncoder.encode(msg, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Log.d(SysConsts.SYSTEM_TAG, e.getMessage());
		}

		return url;
	}

}
