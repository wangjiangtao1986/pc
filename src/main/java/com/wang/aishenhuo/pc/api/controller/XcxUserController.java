package com.wang.aishenhuo.pc.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxUserService;
import com.wang.utiles.Constant;
import com.wang.utiles.WXUtil;

/**
 * 用户信息管理，用户登录，
 * 添加SK加解密方案
 * 放开微信接口代码注释
 * @author 王江涛
 *
 */
@RestController
public class XcxUserController {

	@Autowired
	XcxUserService xcxUserService;
	
	@Autowired 
	Constant constant;

	/**
	 * 用户信息认证
	 * 
	 * 判断用户是否已经登录或存在
	 * 
	 * @param sk
	 * @return
	 */
	@RequestMapping("/user/vaild_sk")
	public String vaild_sk(String sk) {
		JSONObject jo = new JSONObject();
		if(StringUtils.isEmpty(sk)){
			jo.put("status", "-1");
			jo.put("登录已过期", "-1");
			return jo.toJSONString();
		} else {
			
		}
		return "";
	}

    //获取openid 加密后返回 String
    private JSONObject get3rdSession(JSONObject data) {
    	return data;
    }

    /**
     * 微信登录
     * 
     * openid获取，微信用户信息获取
     * 初始化用户基本信息
     * 
     * @param code
     * @param encryptedData
     * @param iv
     * @param request
     * @return
     */
	@RequestMapping("/user/login")
	public JSONObject login(String code,String encryptedData,String iv,HttpServletRequest request) {
    	JSONObject j = new JSONObject();
		JSONObject data = WXUtil.getOpenid(code,constant);
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
    
    /**
     * 个人信息维护
     * 
     * 
     * 
     * @param params
     * @return
     */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/user/editUser")
	public JSONObject editUser(@RequestBody Map<String, Object> params) {
		JSONObject jo = new JSONObject();
		JSONObject jp = new JSONObject(params);
		if(null==params) {
			Map userInfo = (Map) jp.get("userInfo");
			XcxUser user = xcxUserService.getXcxUser(jp.getString("sk"));
			user.setAvatarurl(userInfo.get("avatarurl").toString());
			user.setName(userInfo.get("name").toString());
			user.setNickname(userInfo.get("nickName").toString());
			user.setPhone(userInfo.get("phone").toString());
			user.setProvince(userInfo.get("province").toString());
			user.setCity(userInfo.get("city").toString());
			user.setCounty(userInfo.get("county").toString());
			user.setGender(userInfo.get("gender").toString());
			
//			user.setCountry(userInfo.get("country").toString());
//			user.setVehicle(userInfo.get("vehicle").toString());
			
			xcxUserService.updateByPrimaryKey(user);
			jo.put("status", 1);
			jo.put("msg", "修改成功");
			jo.put("user", user);
			
		}
		return jo;
	}

//    //生成返回给客户端的3rdsession 
//    private function get3rdSession($openid,$session_key)
//    {
//        $session3rd = $this->randomFromDev(168);
//        S($session3rd, $openid,2592000);
//        return $session3rd;
//    }
//
}