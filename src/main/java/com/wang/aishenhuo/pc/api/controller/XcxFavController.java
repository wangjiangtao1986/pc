package com.wang.aishenhuo.pc.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxFav;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMyfav;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxFavService;
import com.wang.aishenhuo.pc.api.service.XcxMyfavService;
import com.wang.aishenhuo.pc.api.service.XcxUserService;

/**
 * 添加用户默认头像 添加翻页查询组件
 * 
 * @author Administrator
 *
 */
@RestController
public class XcxFavController {

	@Autowired
	public XcxMyfavService xcxMyfavService;
	
	@Autowired
	public XcxFavService xcxFavService;

	@Autowired
	XcxUserService xcxUserService;

	@RequestMapping("/fav/addFav")
	public JSONObject addFav(XcxFav xcxFav, String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		xcxFav.setUid(user.getId());
		xcxFav.setTime((int) System.currentTimeMillis());
		int i = xcxFavService.insertSelective(xcxFav);

		if (i > 0) {
			j.put("status", 1);
			j.put("msg", "收藏成功");
		} else {
			j.put("status", 0);
			j.put("msg", "收藏失败");
		}
		
		return j;
	}

	@RequestMapping("/fav/delFav")
	public JSONObject delFav(XcxFav xcxFav, String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		xcxFav.setUid(user.getId());
		xcxFav.setTime((int) System.currentTimeMillis());
		int i = xcxFavService.deleteByExample(xcxFav);
		if (i > 0) {
			j.put("status", 1);
			j.put("msg", "取消收藏成功");
		} else {
			j.put("status", 0);
			j.put("msg", "取消收藏失败");
		}
		
		return j;
	}


	@RequestMapping("/fav/isFav")
	public JSONObject isFav(XcxFav xcxFav, String sk, HttpServletRequest request) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		xcxFav.setUid(user.getId());
		xcxFav.setTime((int) System.currentTimeMillis());
		List<XcxFav> i = xcxFavService.selectByExample(xcxFav);
		if (null!=i && i.size() > 0) {
			j.put("status", 1);
			j.put("msg", "已收藏");
		} else {
			j.put("status", 0);
			j.put("msg", "取消收藏失败");
		}
		return j;
	}

	@RequestMapping("/fav/myFav")
	public JSONObject myFav(String sk, HttpServletRequest request) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		XcxMyfav xcxFav = new XcxMyfav();
		xcxFav.setUid(user.getId());
		List<XcxMyfav> data = xcxMyfavService.selectByExample(xcxFav);
		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("data", data);
		return j;
	}

}