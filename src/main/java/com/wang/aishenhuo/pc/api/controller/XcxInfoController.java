package com.wang.aishenhuo.pc.api.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxInfo;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxInfoService;
import com.wang.aishenhuo.pc.api.service.XcxUserService;

/**
 * 添加用户默认头像 添加翻页查询组件
 * 
 * @author Administrator
 *
 */
@RestController
public class XcxInfoController {

	@Autowired
	public XcxInfoService xcxInfoService;

	@Autowired
	XcxUserService xcxUserService;

	@RequestMapping("/info/lists")
	public JSONObject lists(String start, String over, String date, String page, HttpServletRequest request) {
		// 日期、起点、终点
		// 用户表关联信息表头像
		// 翻页

		JSONObject j = new JSONObject();

		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("list", xcxInfoService.listXcxInfo());

		return j;
	}

	@RequestMapping("/info/add")
	public JSONObject add(XcxInfo xcxInfo, String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		// JSONObject skj = JSONObject.parseObject(sk);
		// XcxUser user = xcxUserService.getXcxUser(skj.getString("openid"));

		XcxUser user = xcxUserService.getXcxUser(sk);
		xcxInfo.setUid(user.getId());
		xcxInfo.setAddtime((int) System.currentTimeMillis());
		xcxInfo.setId(UUID.randomUUID().toString());
		xcxInfo.setAvatarurl(user.getAvatarurl());
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

		return j;
	}

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

	@RequestMapping("/info/mycount")
	public JSONObject mycount(String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		XcxInfo xcxInfo = new XcxInfo();
		xcxInfo.setUid(user.getId());

		int data = xcxInfoService.count(xcxInfo);

		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("data", data);

		return j;
	}

	@RequestMapping("/info/mylist")
	public JSONObject mylist(String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		XcxInfo xcxInfo = new XcxInfo();
		xcxInfo.setUid(user.getId());

		List<XcxInfo> data = xcxInfoService.selectByExample(xcxInfo);

		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("data", data);

		return j;
	}


	// 添加拼车信息
	// public function add(){
	// $u = D('User');
	// $user = $u->getUserInfo(vaild_sk(I('sk')));
	// $data = $_POST;
	// $data['uid'] = $user['id'];
	// $i = D('Info');
	// if (!($id=($i->addInfo($data)))){
	// $result['status'] = 0;
	// $result['msg'] = $i->getError();
	// }else{
	// $result['status'] = 1;
	// $result['msg'] = '发布成功';
	// $result['info'] = $id;
	//
	// if($user['vehicle'] == ''){
	// $u->vehicle = $data['vehicle'];
	// }
	// if($user['phone'] == ''){
	// $u->phone = $data['phone'];
	// }
	// if($user['name'] == ''){
	// $u->name = $data['name'];
	// }
	// $u->save();
	// }
	// exit(json_encode($result));
	// }
	//
	// public function index(){
	// $i = D('Info');
	// $data = $i->getInfo(I('id'));
	// //dump($i->getLastSql());
	// if(empty($data)){
	// $result['status'] = 0;
	// $result['msg'] = '没有找到该信息';
	// }else{
	// $result['status'] = 1;
	// $result['msg'] = '获取成功';
	// $result['data'] = $data;
	// }
	// exit(json_encode($result));
	// }
	//
	//
	// public function lists(){
	// $i = D('Info');
	// $start = I('start','');
	// $over = I('over','');
	// $date = I('date','');
	// $where = 'info.status = 1 and info.time >= "'.time().'"';
	//
	// if($date != ''){
	// $where .= ' and info.date <= "'.$date.'"';
	// }
	//
	// if($start != ''){
	// $where .= ' and info.departure like "%'.$start.'%"';
	// }
	//
	// if($over != ''){
	// $where .= ' and info.destination like "%'.$over.'%"';
	// }
	// $page = I('page','1');
	// $page_count = 20;
	// $limit = ($page-1)*$page_count;
	//
	// $list = $i->table('__INFO__
	// info')->field('info.*,user.avatarUrl')->join('__USER__ user ON user.id =
	// info.uid','LEFT')->where($where)->limit($limit,$page_count)->order('time
	// asc')->select();
	// //dump($i->getLastSql());
	// $result['status'] = 1;
	// $result['msg'] = '获取成功';
	// $result['list'] = $list;
	// exit(json_encode($result));
	// }
	//
	//
	// public function mylist(){
	// $u = D('User');
	// $user = $u->getUserInfo(vaild_sk(I('sk')));
	// $i = D('Info');
	// $where['uid'] = $user['id'];
	// $page = I('page','1');
	// $page_count = 20;
	// $limit = ($page-1)*$page_count;
	// $data = $i->where($where)->limit($limit,$page_count)->order('addtime
	// desc')->select();
	// $result['status'] = 1;
	// $result['msg'] = '获取成功';
	// $result['data'] = $data;
	// exit(json_encode($result));
	// }
	//
	// public function del(){
	// $u = D('User');
	// $i = D('Info');
	// $user = $u->getUserInfo(vaild_sk(I('sk')));
	// $where['uid'] = $user['id'];
	// $where['id'] = I('id');
	// if($i->where($where)->delete() > 0){
	// $result['status'] = 1;
	// $result['msg'] = '删除成功';
	// }else{
	// $result['status'] = 0;
	// $result['msg'] = '删除失败';
	// }
	// exit(json_encode($result));
	// }

}