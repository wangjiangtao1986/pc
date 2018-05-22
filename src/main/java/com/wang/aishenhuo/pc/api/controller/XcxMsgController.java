package com.wang.aishenhuo.pc.api.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsg;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSee;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxInfoService;
import com.wang.aishenhuo.pc.api.service.XcxMsgSeeService;
import com.wang.aishenhuo.pc.api.service.XcxMsgService;
import com.wang.aishenhuo.pc.api.service.XcxUserService;

/**
 * 
 * 通知消息
 * 
 * @author 王江涛
 */
@RestController
public class XcxMsgController {

	@Autowired
	XcxInfoService xcxInfoService;
	@Autowired
	XcxUserService xcxUserService;
	@Autowired
	XcxMsgService xcxMsgService;
	@Autowired
	XcxMsgSeeService xcxMsgSeeService;
    @Value("${page.pageSize}")
    int pageSize;

    /**
     * 添加消息\发布消息
     * 
     * @param xcxMsg
     * @param sk
     * @param request
     * @return
     */
	@RequestMapping("/msg/add")
	public JSONObject add(XcxMsg xcxMsg,String sk) {
		JSONObject j = new JSONObject();
		XcxMsg db = new XcxMsg();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user&&!StringUtils.isEmpty(user.getId())) {
			db.setUid(user.getId());
			db.setTime((int) System.currentTimeMillis());
			int i = xcxMsgService.insertSelective(db);
			if(i>0) {
				j.put("status", 1);
				j.put("msg", "发表成功");
			} else {
				j.put("status", 0);
				j.put("msg", "发表失败");
			}
		} else {
			j.put("status", 0);
			j.put("msg", "发表失败");
		}
		return j;
	}

	/**
	 * msg 汇总 未读统计
	 * 
	 * 
	 * 
	 * @param xcxMsg
	 * @param sk
	 * @param request
	 * @return
	 */
	@RequestMapping("/msg/getall")
	public JSONObject getall(XcxMsg xcxMsg,String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user&&!StringUtils.isEmpty(user.getId())) {
			xcxMsg.setUid(user.getId());
			xcxMsg.setSee("0");
			List<XcxMsgSee> list = xcxMsgSeeService.listXcxMsgByType(xcxMsg);
			j.put("status", 1);
			j.put("msg", "获取成功");
			j.put("data", list);
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		return j;
	}


	/**
	 * 获取消息列表
	 * 
	 * 
	 * 
	 * @param xcxMsg
	 * @param sk
	 * @param page
	 * @return
	 */
	@RequestMapping("/msg/get")
	public JSONObject get(XcxMsg xcxMsg,String sk, int page) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user&&!StringUtils.isEmpty(user.getId())) {
			xcxMsg.setUid(user.getId());
	        PageHelper.startPage(page,pageSize); // 设置分页，参数1=页数，参数2=每页显示条数
			List<XcxMsg> list = xcxMsgService.selectByExample(xcxMsg);
			xcxMsgService.update(list);
			j.put("status", 1);
			j.put("msg", "消息加载成功");
			j.put("data", list);
		} else {
			j.put("status", 0);
			j.put("msg", "消息加载失败");
		}
		return j;
	}
}