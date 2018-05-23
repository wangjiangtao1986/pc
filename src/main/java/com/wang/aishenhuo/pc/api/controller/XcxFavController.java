package com.wang.aishenhuo.pc.api.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
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
	XcxMyfavService xcxMyfavService;
	@Autowired
	XcxFavService xcxFavService;
	@Autowired
	XcxUserService xcxUserService;

    @Value("${page.pageSize}")
    int pageSize;
    
    
    /**
     * 添加收藏
     * 
     * 
     * 
     * @param xcxFav
     * @param sk
     * @param request
     * @return
     */
	@RequestMapping("/fav/addFav")
	public JSONObject addFav(XcxFav xcxFav, String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			xcxFav.setUid(user.getId());
			xcxFav.setTime((int) (new Date().getTime()/1000));
			xcxFav.setId(UUID.randomUUID().toString());
			int i = xcxFavService.insertSelective(xcxFav);
			if (i > 0) {
				j.put("status", 1);
				j.put("msg", "收藏成功");
			} else {
				j.put("status", 0);
				j.put("msg", "收藏失败");
			}
		} else {
			j.put("status", 0);
			j.put("msg", "收藏失败");
		}
		return j;
	}

	/**
	 * 取消收藏
	 * 
	 * 
	 * 
	 * @param xcxFav
	 * @param sk
	 * @return
	 */
	@RequestMapping("/fav/delFav")
	public JSONObject delFav(XcxFav xcxFav, String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			xcxFav.setUid(user.getId());
			xcxFav.setTime((int) (new Date().getTime()/1000));
			int i = xcxFavService.deleteByExample(xcxFav);
			if (i > 0) {
				j.put("status", 1);
				j.put("msg", "取消收藏成功");
			} else {
				j.put("status", 0);
				j.put("msg", "取消收藏失败");
			}
		} else {
			j.put("status", 0);
			j.put("msg", "取消收藏失败");
		}
		return j;
	}


	/**
	 * 收藏状态
	 * 
	 * 
	 * 
	 * @param xcxFav
	 * @param sk
	 * @return
	 */
	@RequestMapping("/fav/isFav")
	public JSONObject isFav(XcxFav xcxFav, String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			xcxFav.setUid(user.getId());
			xcxFav.setTime((int) (new Date().getTime()/1000));
			List<XcxFav> i = xcxFavService.selectByExample(xcxFav);
			if (null!=i && i.size() > 0) {
				j.put("status", 1);
				j.put("msg", "已收藏");
			} else {
				j.put("status", 0);
				j.put("msg", "未收藏");
			}
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		return j;
	}

	/**
	 * 我的收藏
	 * 
	 * 
	 * 
	 * @param sk
	 * @param page
	 * @return
	 */
	@RequestMapping("/fav/myFav")
	public JSONObject myFav(String sk, int page) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			XcxMyfav xcxFav = new XcxMyfav();
			xcxFav.setUid(user.getId());
	        PageHelper.startPage(page,pageSize); // 设置分页，参数1=页数，参数2=每页显示条数
			List<XcxMyfav> data = xcxMyfavService.selectByExample(xcxFav);
			j.put("status", 1);
			j.put("msg", "获取成功");
			j.put("data", data);
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		return j;
	}

}