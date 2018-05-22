package com.wang.aishenhuo.pc.api.controller;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxComment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxCommentWithBLOBs;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxInfo;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsg;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxZan;
import com.wang.aishenhuo.pc.api.service.XcxCommentService;
import com.wang.aishenhuo.pc.api.service.XcxInfoService;
import com.wang.aishenhuo.pc.api.service.XcxMsgService;
import com.wang.aishenhuo.pc.api.service.XcxUserService;
import com.wang.aishenhuo.pc.api.service.XcxZanService;

/**
 * 
 * 评论
 * 
 * @author 王江涛
 *
 */
@RestController
public class XcxCommentController {
	@Autowired
	XcxUserService xcxUserService;
	@Autowired
	XcxCommentService xcxCommentService;
	@Autowired
	XcxMsgService xcxMsgService;
	@Autowired
	XcxInfoService xcxInfoService;
	@Autowired
	XcxZanService xcxZanService;

	@Value("${page.pageSize}")
    int pageSize;


    /**
     * 发表评论（回复评论）
     * 
     * 添加描述及图片
     * 
     * 发送通知 msg
     * 
     * @param xcxComment
     * @param sk
     * @param request
     * @return
     */
	@RequestMapping("/comment/add")
	public JSONObject add(XcxCommentWithBLOBs xcxComment, String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			xcxComment.setUid(user.getId());
			xcxComment.setAvatarurl(user.getAvatarurl());
			xcxComment.setTime((int) System.currentTimeMillis());
			if(StringUtils.isEmpty(xcxComment.getImg())){
				xcxComment.setImg("[]");
			}
			xcxComment.setId(UUID.randomUUID().toString());
			int i = xcxCommentService.insertSelective(xcxComment);
			if(i>0) {
				j.put("status", 1);
				j.put("comment", "发表成功");
				
				addMsg(xcxComment, user);
			} else {
				j.put("status", 0);
				j.put("comment", "发表失败");
			}
		} else {
			j.put("status", 0);
			j.put("comment", "发表失败");
		}
		return j;
	}

	/**
	 * 评论总数
	 * 
	 * 
	 * 
	 * @param xcxComment
	 * @param sk
	 * @param request
	 * @return
	 */
	@RequestMapping("/comment/get_count")
	public JSONObject get_count(XcxComment xcxComment) {
		JSONObject j = new JSONObject();
		int data = xcxCommentService.count(xcxComment);
		j.put("status", 1);
		j.put("comment", "评论总数加载成功");
		j.put("data", data);
		return j;
	}


	/**
	 * 加载评论信息
	 * 
	 * 
	 * 
	 * @param xcxComment
	 * @param page
	 * @return
	 */
	@RequestMapping("/comment/get")
	public JSONObject get(XcxComment xcxComment,int page) {
		JSONObject j = new JSONObject();
        PageHelper.startPage(page,pageSize); // 设置分页，参数1=页数，参数2=每页显示条数
		List<XcxCommentWithBLOBs> data = xcxCommentService.selectByExample(xcxComment);
		j.put("status", 1);
		j.put("comment", "评论加载成功");
		j.put("data", data);
		return j;
	}


	/**
	 * 为评论点赞
	 * 
	 * 
	 * 
	 * @param cid
	 * @param sk
	 * @param request
	 * @return
	 */
	@RequestMapping("/comment/zan")
	public JSONObject zan(XcxZan xcxZan,String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			xcxZan.setUid(user.getId());
			List<XcxZan> zanData = xcxZanService.selectByExample(xcxZan);
			if(null!=zanData && zanData.size()>0) {
				j.put("status", 0);
				j.put("msg", "你已经赞过了");
			} else {
				xcxZan.setTime((int) System.currentTimeMillis());
				xcxZanService.insertSelective(xcxZan);
				
				XcxCommentWithBLOBs xcxComment = xcxCommentService.getXcxComment(xcxZan.getCid());
				xcxComment.setZan(xcxComment.getZan()+1);
				xcxCommentService.updateByPrimaryKey(xcxComment);

				zanMsg(user, xcxComment);
				
				j.put("status", 1);
				j.put("msg", "点赞成功");
				j.put("zan", 1);
			}
		} else {
			j.put("status", 0);
			j.put("msg", "点赞失败");
		}
		return j;
	}

	private void addMsg(XcxCommentWithBLOBs xcxComment, XcxUser user) {
		XcxInfo xcxInfo = xcxInfoService.getXcxInfo(xcxComment.getIid());
		XcxMsg xcxMsg = new XcxMsg();
		xcxMsg.setContent("回复了您的信息 :" + xcxComment.getContent());
		xcxMsg.setType("comment");
		xcxMsg.setUrl("/pages/" + xcxComment.getType() + "/index?id=" + xcxComment.getIid());
		xcxMsg.setTime((int) System.currentTimeMillis());
		
		if(null!=user) {
			xcxMsg.setAvatarurl(user.getAvatarurl());
			xcxMsg.setNickname(user.getNickname());
//			两个字段需要区别对待
			xcxMsg.setUid(user.getId());
		}
		if(null!=xcxInfo && !StringUtils.isEmpty(xcxInfo.getUid())) {
			xcxMsg.setFid(xcxInfo.getUid());
		}
		
		xcxMsgService.insertSelective(xcxMsg);
	}

	private void zanMsg(XcxUser user, XcxCommentWithBLOBs xcxComment) {
		XcxMsg xcxMsg = new XcxMsg();
		xcxMsg.setAvatarurl(user.getAvatarurl());
		xcxMsg.setNickname(user.getNickname());
		xcxMsg.setType("zan");
		xcxMsg.setContent("赞了你的评论 :" + xcxComment.getContent());
		xcxMsg.setUrl("/pages/info/index?id=" + xcxComment.getIid());
		xcxMsg.setTime((int) System.currentTimeMillis());
		xcxMsg.setUid(user.getId());
		xcxMsg.setFid(xcxComment.getUid());
		xcxMsgService.insertSelective(xcxMsg);
	}
}