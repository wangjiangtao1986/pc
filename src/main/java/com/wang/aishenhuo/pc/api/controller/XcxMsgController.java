package com.wang.aishenhuo.pc.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
 * 列表分页
 * 附件插入
 * 删除控制
 * 
 * @author Administrator
 *
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

	@RequestMapping("/msg/add")
	public JSONObject add(XcxMsg xcxMsg,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();
		
		XcxMsg db = new XcxMsg();

		XcxUser user = xcxUserService.getXcxUser(sk);
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
		return j;
	}


	@RequestMapping("/msg/getall")
	public JSONObject getall(XcxMsg xcxMsg,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		xcxMsg.setUid(user.getId());
		xcxMsg.setSee("0");
		
		List<XcxMsgSee> list = xcxMsgSeeService.listXcxMsgByType(xcxMsg);

		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("data", list);
		
		return j;
	}


	@RequestMapping("/msg/get")
	public JSONObject get(XcxMsg xcxMsg,String sk, int page, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
//		type
		xcxMsg.setUid(user.getId());

        PageHelper.startPage(page,pageSize); // 设置分页，参数1=页数，参数2=每页显示条数
		List<XcxMsg> list = xcxMsgService.selectByExample(xcxMsg);

		xcxMsgService.update(list);
		
		
		j.put("status", 1);
		j.put("msg", "消息加载成功");
		j.put("data", list);
		
		return j;
	}
}