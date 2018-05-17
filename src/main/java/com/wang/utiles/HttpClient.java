package com.wang.utiles;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class HttpClient {


	public static void main(String[] args) {

//
//		JSONObject para = JSONObject.fromObject("{\"data\": {\"n_id\": \"1\", \"description\": \"\", \"order_no\": \"20170427162110858113\", \"submerno\": \"307110700000098\", \"timestamp\": \"1493296757934\", \"app_id\": \"307110700000098\", \"paytype\": \"jsapi\", \"amount\": \"3500\", \"channel\": \"wechat\", \"sign\": \"06dff9ead2049abf2aa1359c974a08e2\", \"transaction_id\": \"4007052001201704278582708138\"}, \"trade_msg\": \"A successful deal\", \"success\": \"true\", \"trade_state\": \"TRADE_SUCCESS\"}");
//		String mechalkey = httpPostUrl("http://searchshop.jfgle.com/phone/confirmBondOrderPay.action", para);
//
//		System.out.println("mechalkey = " + mechalkey);

	}

	public static String httpPostUrl(String url, JSONObject para) {
		return httpPostUrl(url,para.toString());
	}
	
	private static String httpPostUrl(String url, String str) {
		String result = "";
		BufferedReader in = null;
		DataOutputStream out = null;
		System.out.println("request：" + str);

		try {

			URL realUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

			connection.setDoInput(true);

			connection.setDoOutput(true);

			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			connection.setInstanceFollowRedirects(true);

			connection.setRequestProperty("Content-Type", "application/text; charset=UTF-8");

			connection.connect();

			out = new DataOutputStream(connection.getOutputStream());

			String token = "d5f224c9f83874da5b5025794c773e8e";

			out.write(str.getBytes("UTF-8"));

			out.flush();

			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String lines;

			StringBuffer sbf = new StringBuffer();

			while ((lines = reader.readLine()) != null) {

				lines = new String(lines.getBytes(), "utf-8");

				sbf.append(lines);

			}

			result = sbf.toString();
			reader.close();

			connection.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}
}
