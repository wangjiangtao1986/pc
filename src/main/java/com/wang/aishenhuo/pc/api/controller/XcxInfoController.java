package com.wang.aishenhuo.pc.api.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxInfo;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxInfoService;
import com.wang.aishenhuo.pc.api.service.XcxUserService;

/**
 * 拼车信息
 * 
 * @author 王江涛
 *
 */
@RestController
public class XcxInfoController {

	@Autowired
	XcxInfoService xcxInfoService;
	@Autowired
	XcxUserService xcxUserService;

    @Value("${page.pageSize}")
    int pageSize;
    
    /**
     * 首页，根据客户要求，检索拼车信息 
     * 	
     * 	有待商议后续实现
     * 
     * @param start
     * @param over
     * @param date
     * @param page
     * @param request
     * @return
     */
	@RequestMapping("/info/lists")
	public JSONObject lists(String start, String over, String date, int page) {
		JSONObject j = new JSONObject();
		XcxInfo xcxInfo = new XcxInfo();
		xcxInfo.setDate(date);
		xcxInfo.setDeparture(start);
		xcxInfo.setDestination(over);
        PageHelper.startPage(page,pageSize); // 设置分页，参数1=页数，参数2=每页显示条数
        List<XcxInfo> list = xcxInfoService.selectByExample(xcxInfo);
		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("list", list);
		return j;
	}

	/**
	 * 添加拼车信息
	 * 
	 * @param xcxInfo
	 * @param sk
	 * @param request
	 * @return
	 */
	@RequestMapping("/info/add")
	public JSONObject add(XcxInfo xcxInfo, String sk) {
		JSONObject j = new JSONObject();
		//添加
		if(StringUtils.isEmpty(xcxInfo.getId())) {

			XcxUser user = xcxUserService.getXcxUser(sk);
			xcxInfo.setUid(user.getId());
			xcxInfo.setAddtime((int) System.currentTimeMillis());
			xcxInfo.setAvatarurl(user.getAvatarurl());
			
			xcxInfo.setId(UUID.randomUUID().toString());
			int i = xcxInfoService.insertSelective(xcxInfo);
			if (i > 0) {
				j.put("status", 1);
				j.put("msg", "发布成功");
				j.put("info", xcxInfo.getId());
				if (StringUtils.isEmpty(user.getPhone())) {
					user.setPhone(xcxInfo.getPhone());
				}
				if (StringUtils.isEmpty(user.getVehicle())) {
					user.setVehicle(xcxInfo.getVehicle());
				}
				if (StringUtils.isEmpty(user.getName())) {
					user.setName(xcxInfo.getName());
				}
				xcxUserService.updateByPrimaryKey(user);
			} else {
				j.put("status", 0);
				j.put("msg", "发布失败");
			}
		} else {
			int i = xcxInfoService.updateByPrimaryKeySelective(xcxInfo);
			if (i > 0) {
				j.put("status", 1);
				j.put("msg", "修改成功");
				j.put("info", xcxInfo.getId());
			} else {
				j.put("status", 0);
				j.put("msg", "修改失败");
			}
		}
		
		return j;
	}

	/**
	 * 获取拼车信息详情
	 * 
	 * 根据信息ID
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/info/index")
	public JSONObject index(String id, HttpServletRequest request) {
		JSONObject j = new JSONObject();
		XcxInfo xcxInfo = xcxInfoService.getXcxInfo(id);
		if (ObjectUtils.isEmpty(xcxInfo)) {
			j.put("status", 0);
			j.put("msg", "没有找到该信息");
		} else {
			j.put("status", 1);
			j.put("msg", "获取成功");
			j.put("data", xcxInfo);
		}
		return j;
	}

	/**
	 * 获取拼车信息汇总
	 * 
	 * 
	 * @param sk
	 * @param request
	 * @return
	 */
	@RequestMapping("/info/mycount")
	public JSONObject mycount(String sk, HttpServletRequest request) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			XcxInfo xcxInfo = new XcxInfo();
			xcxInfo.setUid(user.getId());
			int data = xcxInfoService.count(xcxInfo);
			j.put("status", 1);
			j.put("msg", "获取成功");
			j.put("data", data);
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		return j;
	}

	/**
	 * 我发布的拼车信息 列表
	 * 
	 * 	
	 * 
	 * @param sk
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping("/info/mylist")
	public JSONObject mylist(String sk,int page) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			XcxInfo xcxInfo = new XcxInfo();
			xcxInfo.setUid(user.getId());
	        PageHelper.startPage(page,pageSize); // 设置分页，参数1=页数，参数2=每页显示条数
			List<XcxInfo> data = xcxInfoService.selectByExample(xcxInfo);
			j.put("status", 1);
			j.put("msg", "获取成功");
			j.put("data", data);
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		return j;
	}
	
	/**
	 * 删除拼车信息
	 * 
	 * 
	 * 
	 * 
	 * @param xcxInfo
	 * @param sk
	 * @return
	 */
	@RequestMapping("/info/del")
	public JSONObject del(XcxInfo xcxInfo, String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			xcxInfo.setUid(user.getId());
			int data = xcxInfoService.deleteByExample(xcxInfo);
			if(data>0) {
				j.put("status", 1);
				j.put("msg", "删除成功");
			} else {
				j.put("status", 0);
				j.put("msg", "删除失败");
			}
		} else {
			j.put("status", 0);
			j.put("msg", "删除失败");
		}
		return j;
	}
}