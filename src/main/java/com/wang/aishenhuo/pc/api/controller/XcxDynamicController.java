package com.wang.aishenhuo.pc.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wang.aishenhuo.pc.api.bean.XcxDynamicAndXcxComment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxComment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxCommentWithBLOBs;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamic;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamicWithBLOBs;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxCommentService;
import com.wang.aishenhuo.pc.api.service.XcxDynamicService;
import com.wang.aishenhuo.pc.api.service.XcxInfoService;
import com.wang.aishenhuo.pc.api.service.XcxUserService;

/**
 * 
 * 动态
 * 
 * @author 王江涛
 */
@RestController
public class XcxDynamicController {

	@Autowired
	XcxInfoService xcxInfoService;
	@Autowired
	XcxUserService xcxUserService;
	@Autowired
	XcxDynamicService xcxDynamicService;
	@Autowired
	XcxCommentService xcxCommentService;

    @Value("${page.pageSize}")
    int pageSize;
    
    /**
     * 添加动态
     * 
     * 
     * 
     * @param db
     * @param sk
     * @return
     */
	@RequestMapping("/dynamic/add")
	public JSONObject add(XcxDynamicWithBLOBs db,String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			db.setUid(user.getId());
			db.setTime((int) (new Date().getTime()/1000));
			db.setNickname(user.getName());
			db.setAvatarurl(user.getAvatarurl());
			int i = xcxDynamicService.insertSelective(db);
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
	 * 删除动态
	 * 
	 * 
	 * @param xcxDynamic
	 * @param sk
	 * @param request
	 * @return
	 */
	@RequestMapping("/dynamic/del")
	public JSONObject del(XcxDynamic xcxDynamic,String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
//			用户信息是否有意义，删除权限控制
			xcxDynamic.setUid(user.getId());
			int i = xcxDynamicService.deleteByPrimaryKey(xcxDynamic);
			if(i>0) {
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

	/**
	 * 动态列表
	 * @param sk
	 * @param page
	 * @return
	 */
	@RequestMapping("/dynamic/getlist")
	public JSONObject getlist(String sk,int page) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			XcxDynamic xcxDynamic = new XcxDynamic();
			xcxDynamic.setUid(user.getId());
	        PageHelper.startPage(page,pageSize); // 设置分页，参数1=页数，参数2=每页显示条数
			List<XcxDynamicWithBLOBs> list = xcxDynamicService.selectByExampleWithBLOBs(xcxDynamic);
			
			j.put("status", 1);
			j.put("msg", "获取成功");
			j.put("list", addDC(list));
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		return j;
	}

	private List<XcxDynamicAndXcxComment> addDC(List<XcxDynamicWithBLOBs> list) {
		XcxComment record = new XcxComment();
		record.setType("dynamic");
		
		List<XcxCommentWithBLOBs> clist = xcxCommentService.selectDynamicComments(record,list);
		
		List<XcxDynamicAndXcxComment> dclist = new ArrayList<XcxDynamicAndXcxComment>();
		for(int i=0;i<list.size();i++) {
			XcxDynamicAndXcxComment s = new XcxDynamicAndXcxComment();
			BeanUtils.copyProperties(list.get(i), s);
			s.setList(createDC(clist, s));
			dclist.add(s);
		}
		
		return dclist;
	}

	private List<XcxCommentWithBLOBs> createDC(List<XcxCommentWithBLOBs> clist, XcxDynamicAndXcxComment s) {
		List<XcxCommentWithBLOBs> xclist = new ArrayList<XcxCommentWithBLOBs>();
		for(int j1=0;j1<clist.size();j1++) {
			if(clist.get(j1).getIid().equals(s.getId())){
				xclist.add(clist.get(j1));
			}
		}
		return xclist;
	}

}