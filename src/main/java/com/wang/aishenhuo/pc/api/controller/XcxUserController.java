package com.wang.aishenhuo.pc.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxUserService;
import com.wang.utiles.HttpClient;

/**
 * 添加SK加解密方案
 * 放开微信接口代码注释
 * @author Administrator
 *
 */
@RestController
public class XcxUserController {

	@Autowired
	XcxUserService xcxUserService;

	@RequestMapping("/user/vaild_sk")
	public String vaild_sk(String sk) {
		JSONObject jo = new JSONObject();
		if(StringUtils.isEmpty(sk)){
			jo.put("status", "-1");
			jo.put("登录已过期", "-1");
			return jo.toJSONString();
		} else {
			
		}
		return sk;
	}

    //获取openid 加密后返回 String
    private JSONObject get3rdSession(JSONObject data) {
    	return data;
    }
    
	
	
	@RequestMapping("/user/login")
	public JSONObject login(String code,String encryptedData,String iv,HttpServletRequest request) {
    	JSONObject j = new JSONObject();
		JSONObject data = getOpenid(code);
		String openid = data.get("openid").toString();
		XcxUser xcxUser = xcxUserService.getXcxUser(openid);

		if(ObjectUtils.isEmpty(xcxUser)) {//如果第一次登陆
			JSONObject userInfo = getUserInfo(data.get("session_key").toString(),encryptedData,iv);
			xcxUser = new XcxUser();
			xcxUser.setAvatarurl(userInfo.getString("avatarUrl"));
			xcxUser.setCity(userInfo.getString("city"));
			xcxUser.setCountry(userInfo.getString("country"));
			xcxUser.setGender(userInfo.getString("gender"));
			xcxUser.setLanguage(userInfo.getString("language"));
			xcxUser.setNickname(userInfo.getString("nickName"));
			xcxUser.setOpenid(userInfo.getString("openId"));
			xcxUser.setProvince(userInfo.getString("province"));
			xcxUser.setPhone(userInfo.getString("phone"));
			xcxUser.setCounty(userInfo.getString("county"));
			xcxUser.setVehicle(userInfo.getString("vehicle"));
			xcxUser.setName(userInfo.getString("nickName"));

			xcxUserService.insertSelective(xcxUser);
		} else {

		}
		xcxUser = xcxUserService.getXcxUser(openid);

		 j.put("user", xcxUser);
		 j.put("sk", openid);

        return j;
	}

    //获取session_key
    private JSONObject getOpenid(String code) {
    	String url = "https://api.weixin.qq.com/sns/jscode2session?"
    				+ "appid=wxa4a76bcefc655c8f&"
    	 			+ "secret=d5da2b0cba4777b55f5aa973f09cb00b&"
    	 			+ "js_code=" + code + "&"
    	 			+ "grant_type=authorization_code";
    	return (JSONObject) JSONObject.parse(HttpClient.httpPostUrl(url, new JSONObject()));
    }
    
    private JSONObject getUserInfo(String sessionKey, String encryptedData, String iv) {        
//    	WXBizDataCrypt wc = new WXBizDataCrypt("wxa4a76bcefc655c8f", sessionKey);
//    	String data = wc.decryptData(encryptedData, iv);
//    	
////    	{"openId":"oqBel5CmzAIgU_7aaXhHh3nP7KYU",
//    	"nickName":"王江涛","gender":1,"language":"zh_CN","city":"Changping","province":"Beijing",
//    	"country":"China","avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJjQHE2nTlkhVmy6Z8v96YnOSlicSuxq0jHdje6pNccrQ8jv0CQmzAic13ianIhsREicFwTH2wdphlYww/132"
//    		,"watermark":{"timestamp":1526285428,"appid":"wxa4a76bcefc655c8f"}}
//    	System.out.println("data = " + data);
    	
//        if (errCode == 0) {
//            return $data;
//        } else {
//            return false;
//        }

//        return JSONObject.parseObject(data);
    	
    	return JSONObject.parseObject("{\"openId\":\"oqBel5CmzAIgU_7aaXhHh3nP7KYU\",\"nickName\":\"王江涛\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Changping\",\"province\":\"Beijing\",\"country\":\"China\",\"avatarUrl\":\"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJjQHE2nTlkhVmy6Z8v96YnOSlicSuxq0jHdje6pNccrQ8jv0CQmzAic13ianIhsREicFwTH2wdphlYww/132\",\"watermark\":{\"timestamp\":1526285428,\"appid\":\"wxa4a76bcefc655c8f\"}}");
    }

    
    
}