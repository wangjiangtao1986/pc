package com.wang.aishenhuo.pc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxNotice;
import com.wang.aishenhuo.pc.api.service.XcxNoticeService;

/**
 * @author 王江涛
 */
@RestController
public class XcxNoticeController {

	@Autowired
	XcxNoticeService xcxNoticeService;

	/**
	 * 根据ID获取声明
	 * 
	 * 
	 * @param sk
	 * @return
	 */
	@RequestMapping("/notice/index")
	public JSONObject index(XcxNotice record) {
		record = xcxNoticeService.selectByPrimaryKey(record.getId());
		JSONObject jo = new JSONObject();
		jo.put("status", "1");
		jo.put("msg", "获取成功");
		jo.put("data", record);
		return jo;
	}
}