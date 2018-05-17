package com.wang.aishenhuo.pc.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamic;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamicWithBLOBs;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxCommentService;
import com.wang.aishenhuo.pc.api.service.XcxDynamicService;
import com.wang.aishenhuo.pc.api.service.XcxInfoService;
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
public class XcxDynamicController {
	

	@Autowired
	public XcxInfoService xcxInfoService;

	@Autowired
	XcxUserService xcxUserService;

	@Autowired
	XcxDynamicService xcxDynamicService;

	@Autowired
	XcxCommentService xcxCommentService;


	@RequestMapping("/dynamic/add")
	public JSONObject add(XcxDynamic xcxDynamic,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();
		
		XcxDynamicWithBLOBs db = new XcxDynamicWithBLOBs();

		XcxUser user = xcxUserService.getXcxUser(sk);
		db.setUid(user.getId());
		db.setTime((int) System.currentTimeMillis());
		db.setZan(xcxDynamic.getZan());
		db.setNickname(user.getName());
		db.setAvatarurl(user.getAvatarurl());

//		$data['content'] = I('content','');
//		$data['img'] = htmlspecialchars_decode(I('img',''));
		
		int i = xcxDynamicService.insertSelective(db);
		

		if(i>0) {
			j.put("status", 1);
			j.put("msg", "发表成功");
		} else {
			j.put("status", 0);
			j.put("msg", "发表失败");
		}
		return j;
	}


	@RequestMapping("/dynamic/del")
	public JSONObject del(XcxDynamic xcxDynamic,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
//		用户信息是否有意义，删除权限控制
		xcxDynamic.setUid(user.getId());
		
		int i = xcxDynamicService.deleteByPrimaryKey(xcxDynamic);

		if(i>0) {
			j.put("status", 1);
			j.put("msg", "删除成功");
		} else {
			j.put("status", 0);
			j.put("msg", "删除失败");
		}
		return j;
	}

	@RequestMapping("/dynamic/getlist")
	public JSONObject getlist(XcxDynamic xcxDynamic,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
//		用户信息是否有意义，删除权限控制
		xcxDynamic.setUid(user.getId());
		
		List<XcxDynamic> list = xcxDynamicService.selectByExample(xcxDynamic);

//		xcxCommentService

//		foreach($list as $v){
//			$arr[] = $v['id'];
//		}
//		$str = '('.implode(',',$arr).')';
//		$where = 'iid in '.$str.' and type = "dynamic"';
//		
//		$comObj = D('Comment');
//		
//		$comment = $comObj->table('__COMMENT__ comment')->field('comment.id,comment.iid,comment.reply,comment.content,user.nickName')->join('__USER__ user ON user.id = comment.uid','LEFT')->where($where)->order('comment.time desc')->select();
//		$arr = array();
//		foreach($comment as $k=>$v){
//			$arr[$v['iid']][] = $v;
//		}
//		foreach($list as $k=>$v){
//			$list[$k]['comment'] = $arr[$v['id']];
//		}

		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("list", list);
		
		return j;
	}

}