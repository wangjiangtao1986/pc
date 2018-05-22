package com.wang.utiles;

import com.alibaba.fastjson.JSONObject;

public class WXUtil {

    /**
     * 获取session_key 及 openid
     * 
     * 
     * 
     * @param code
     * @param constant
     * @return
     */
	public static JSONObject getOpenid(String code,Constant constant) {
    	String url = constant.getJscode2session()
    				+ "appid=" + constant.getAppid() + "&"
    	 			+ "secret=" + constant.getSecret() + "&"
    	 			+ "js_code=" + code + "&"
    	 			+ "grant_type=authorization_code";
    	return (JSONObject) JSONObject.parse(HttpClient.httpPostUrl(url, new JSONObject()));
    }

	/**
	 * 获取用户access_token
	 * 
	 * 
	 * 
	 * @param constant
	 * @return
	 */
	public static String getToken(Constant constant) {
    	String url = constant.getToken()
    				+ "appid=" + constant.getAppid() + "&"
    	 			+ "secret=" + constant.getSecret() + "&"
    	 			+ "grant_type=client_credential";
		String access_token = HttpClient.httpPostUrl(url, null);
		JSONObject j = (JSONObject) JSONObject.parse(access_token);
		return j.getString("access_token");
	}

	/**
	 * 发送微信消息
	 *
	 * 
	 * 
	 * @param constant
	 * @param message
	 * @param token
	 * @return
	 */
	public static String sendMessage(Constant constant, JSONObject message, String token) {
		return HttpClient.httpPostUrl(constant.getSend() + "access_token=" + token, message);
	}
}
