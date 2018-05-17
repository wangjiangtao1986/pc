package com.wang.aishenhuo.pc.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsg;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSee;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxInfoService;
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
	public XcxInfoService xcxInfoService;

	@Autowired
	XcxUserService xcxUserService;

	@Autowired
	XcxMsgService xcxMsgService;



	@RequestMapping("/msg/add")
	public JSONObject add(XcxMsg xcxMsg,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();
		
		XcxMsg db = new XcxMsg();

		XcxUser user = xcxUserService.getXcxUser(sk);
		db.setUid(user.getId());
		db.setTime((int) System.currentTimeMillis());

//		$data['content'] = I('content','');
//		$data['img'] = htmlspecialchars_decode(I('img',''));
		
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
//		用户信息是否有意义，删除权限控制
		xcxMsg.setUid(user.getId());
		xcxMsg.setSee("0");
		
		List<XcxMsgSee> list = xcxMsgService.listXcxMsgByType(xcxMsg);


		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("data", list);
		
		return j;
	}


	@RequestMapping("/msg/get")
	public JSONObject get(XcxMsg xcxMsg,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		xcxMsg.setUid(user.getId());
		
		List<XcxMsg> list = xcxMsgService.selectByExample(xcxMsg);

		xcxMsgService.update(list);
		
		
		j.put("status", 1);
		j.put("msg", "消息加载成功");
		j.put("data", list);
		
		return j;
	}

//	public function get(){
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$where['msg.uid'] = $user['id'];
//		$where['msg.type'] = I('type');
//		$page = I('page','1');
//		$page_count = 20;
//		$limit = ($page-1)*$page_count;
//		$data = M('msg')->field('msg.*,user.avatarUrl,user.nickName')->table('__MSG__ msg')->join('__USER__ user on msg.fid=user.id','LEFT')->where($where)->limit($limit,$page_count)->order('msg.time desc')->select();
//		$see['see'] = 1;
//		foreach($data as $v){
//			$arr[] = $v['id'];
//		}
//		$str = implode(',',$arr);
//		$str = 'id in ('.$str.')';
//		M('msg')->where($str)->save($see);
//		$result['status'] = 1;
//		$result['msg'] = '消息加载成功';
//		$result['data'] = $data;		
//		exit(json_encode($result));
//	}
//	
//	public function getAll(){
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$where['uid'] = $user['id'];
//		$where['see'] = 0;
//		$data = M('msg')->field('type,count(*) as count')->where($where)->group('type')->select();
//		$result['status'] = 1;
//		$result['msg'] = '消息加载成功';
//		$result['data'] = $data;		
//		exit(json_encode($result));
//	}

}