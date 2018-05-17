package com.wang.aishenhuo.pc.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxComment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxCommentService;
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
public class XcxCommentController {
	

	@Autowired
	XcxUserService xcxUserService;

	@Autowired
	XcxCommentService xcxCommentService;



	@RequestMapping("/comment/add")
	public JSONObject add(XcxComment xcxComment,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();
		
		XcxComment db = new XcxComment();

		XcxUser user = xcxUserService.getXcxUser(sk);
		db.setUid(user.getId());
		db.setTime((int) System.currentTimeMillis());

//		request.getParameter(name);
//		StringEscapeUtils.escapeHtml("
//		
//		$data['content'] = I('content','');
//		$data['img'] = htmlspecialchars_decode(I('img',''));
		
		int i = xcxCommentService.insertSelective(db);
		

		if(i>0) {
			j.put("status", 1);
			j.put("comment", "发表成功");
		} else {
			j.put("status", 0);
			j.put("comment", "发表失败");
		}
		return j;
	}


	@RequestMapping("/comment/get_count")
	public JSONObject get_count(XcxComment xcxComment,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
//		用户信息是否有意义，删除权限控制
//		xcxComment.setUid(user.getId());
//		$where['iid'] = I('id');
		
		int data = xcxCommentService.count(xcxComment);

		j.put("status", 1);
		j.put("comment", "评论总数加载成功");
		j.put("data", data);
		
		return j;
	}


	@RequestMapping("/comment/get")
	public JSONObject get(XcxComment xcxComment,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();
		List<XcxComment> list = xcxCommentService.selectByExample(xcxComment);
		
		j.put("status", 1);
		j.put("comment", "评论加载成功");
		j.put("data", list);
		
		return j;
	}

//
//	public function add()  //添加评论
//	{
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$data['uid'] = $user['id'];
//		$data['iid'] = I('iid');
//		$data['type'] = I('type','info');
//		$data['content'] = I('content','');
//		$data['reply'] = I('reply','');
//		$data['img'] = htmlspecialchars_decode(I('img',''));
//		$C = D('Comment');
//		if($id = $C->addComment($data)){
//			$result['status'] = 1;
//			$result['msg'] = '评论成功';
//			$result['id'] = $id;
//			
//			$i = D('Info');
//			$info = $i->getInfo(I('iid'));
//			if($info['uid'] != $user['id']){
//				msg('comment',$info['uid'],$user['id'],'回复了您的信息 :'.$data['content'],'/pages/'.$data['type'].'/index?id='.$data['iid']);
//			}
//		}else{
//			$result['status'] = 0;
//			$result['msg'] = '评论失败';
//		}		
//		exit(json_encode($result));
//	}
//
//
//	public function zan()
//	{
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$data['uid'] = $user['id'];
//		$data['cid'] = I('cid');
//		$zanObj = M('zan');
//		$zanData = $zanObj->where($data)->find();
//		if(empty($zanData)){
//			$data['time'] = time();
//			$zanObj->add($data);  //写入赞的数据表
//			$C = D('Comment');
//			$com['id'] = $data['cid'];
//			$C->where($com)->setInc('zan');  //评论赞+1
//			$data = $C->where($com)->find();			
//			if($data['uid'] != $user['id']){
//				msg('zan',$data['uid'],$user['id'],'赞了你的评论:'.$data['content'],'/pages/info/index?id='.$data['iid']);
//			}
//			$result['status'] = 1;
//			$result['msg'] = '点赞成功';
//			$result['zan'] = $data['zan'];
//		}else{
//			$result['status'] = 0;
//			$result['msg'] = '你已经赞过了';
//		}
//
//		exit(json_encode($result));		
//	}
}