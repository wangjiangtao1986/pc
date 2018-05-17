package com.wang.aishenhuo.pc.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsg;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSee;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxPassenger;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxAppointmentService;
import com.wang.aishenhuo.pc.api.service.XcxMsgService;
import com.wang.aishenhuo.pc.api.service.XcxPassengerService;
import com.wang.aishenhuo.pc.api.service.XcxUserService;

/**
 * 添加SK加解密方案
 * 放开微信接口代码注释
 * @author Administrator
 *
 */
@RestController
public class XcxAppointmentController {

	@Autowired
	public XcxAppointmentService xcxAppointmentService;

	@Autowired
	XcxUserService xcxUserService;

	@Autowired
	XcxMsgService xcxMsgService;
	
	@Autowired
	XcxPassengerService xcxPassengerService;
	

	@RequestMapping("/appointment/add")
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


	@RequestMapping("/appointment/getAll")
	public JSONObject getAll(XcxMsg xcxMsg,String sk, HttpServletRequest request) {

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


	@RequestMapping("/appointment/get")
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

	@RequestMapping("/appointment/mycount")
	public JSONObject mycount(XcxMsg xcxMsg,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		xcxMsg.setUid(user.getId());
		
		List<XcxMsg> list = xcxMsgService.selectByExample(xcxMsg);

		if(null!=list && list.size()>0){
			xcxMsgService.update(list);
		} else {
			list = new ArrayList<XcxMsg>();
		}
		
		j.put("status", 1);
		j.put("msg", "消息加载成功");
		j.put("data", list);
		
		return j;
	}

	@RequestMapping("/appointment/my")
	public JSONObject my(XcxAppointment xcxAppointment,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		xcxAppointment.setUid(user.getId());
		
		List<XcxAppointment> info = xcxAppointmentService.selectByExample(xcxAppointment);

		if(null!=info && info.size()>0){
		} else {
			info = new ArrayList<XcxAppointment>();
		}
		
		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("data", info);
		
		return j;
	}

	@RequestMapping("/appointment/getPassenger")
	public JSONObject getPassenger(XcxPassenger xcxPassenger,String sk, HttpServletRequest request) {

		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		xcxPassenger.setUid(user.getId());
//		appointment.status
		List<XcxPassenger> info = xcxPassengerService.selectByExample(xcxPassenger);

		if(null!=info && info.size()>0){
		} else {
			info = new ArrayList<XcxPassenger>();
		}
		
		j.put("status", 1);
		j.put("msg", "获取成功");
		j.put("data", info);
		
		return j;
	}

//	public function getPassenger(){
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$infoObj = D('Info');
//		$info = $infoObj->field('info.id,info.phone,info.departure,info.destination,info.
//	time,appointment.status')->table('__INFO__ info')->join('__APPOINTMENT__ appointment on info.id=appointment.iid',
//			'RIGHT')->where('appointment.uid = "'.$user['id'].'"')->order('appointment.time desc')->select();
//		$result['status'] = 1;
//		$result['msg'] = '获取成功';
//		$result['data'] = $info;
//		exit(json_encode($result));	
//	}

//	public function my(){
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$infoObj = D('Info');
//		$info = $infoObj->field('info.id,appointment.*')->table('__INFO__ info')->join('__APPOINTMENT__ appointment on info.id=appointment.iid','RIGHT')->where('info.uid = "'.$user['id'].'"')->order('appointment.time desc')->select();
//		$result['status'] = 1;
//		$result['msg'] = '获取成功';
//		$result['data'] = $info;
//		exit(json_encode($result));
//	}
	
	

//	public function add(){
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$data['uid'] = $user['id'];
//		$data['iid'] = I('iid','');
//		$data['name'] = I('name','');
//		$data['phone'] = I('phone','');
//		$data['surplus'] = I('surplus','');
//		$data['time'] = time();
//		
//		$rules = array(
//			array('name','require','请输入姓名'),
//			array('phone','require','请输入手机号码'),
//			array('phone','/^1[34578]\d{9}$/','手机号码格式错误'),
//			array('surplus','require','请选择人数')
//	   );
//	   
//	   $where['uid'] = $data['uid'];
//	   $where['iid'] = $data['iid'];
//	   $info = M('appointment')->where($where)->find();
//	   if(!empty($info)){
//			$result['status'] = 0;
//			$result['msg'] = '请不要重复预约';
//			exit(json_encode($result));
//	   }
//	   
//		if($id = M('appointment')->validate($rules)->add($data)){
//			$infoObj = D('Info');
//			$info = $infoObj->field('info.*,user.openId')->table('__INFO__ info,__USER__ user')->where('info.uid=user.id and info.id = "'.$data['iid'].'"')->find();
//			
//			$postData['touser'] = $info['openId'];
//			$postData['template_id'] = 'l5gcjhy3C_Tu-mjhoCNHOrbW4P7xlRw72dzu3iZ5tVw';
//			$postData['page'] = '/pages/appointment/index?id='.$id;
//			$postData['form_id'] = i('form_id');
//			$postData['data']['keyword1']['value'] = $data['name'];
//			$postData['data']['keyword2']['value'] = $data['phone'];
//			$postData['data']['keyword3']['value'] = $info['destination'];
//			$postData['data']['keyword4']['value'] = $info['departure'];
//			$postData['data']['keyword5']['value'] = date('Y-m-d H:i',$info['time']);
//			sendMessage($postData);
//			msg('notice',$info['uid'],'10000',$data['name'].'预约了您发布的拼车信息,请及时处理',$postData['page']);
//			$result['status'] = 1;
//			$result['msg'] = '预约成功';
//			
//		}else{
//			$result['status'] = 0;
//			$result['msg'] = '预约失败';
//		}
//		exit(json_encode($result));
//			
//	}
//	
//	
//	public function mycount(){
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$infoObj = D('Info');
//		$info = $infoObj->table('__INFO__ info')->join('__APPOINTMENT__ appointment on info.id=appointment.iid','RIGHT')->where('info.uid = "'.$user['id'].'" and appointment.status = 0')->count();
//		$result['status'] = 1;
//		$result['msg'] = '获取成功';
//		$result['data'] = $info;
//		exit(json_encode($result));
//	}
//	
//	public function getPassenger(){
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$infoObj = D('Info');
//		$info = $infoObj->field('info.id,info.phone,info.departure,info.destination,info.time,appointment.status')->table('__INFO__ info')->join('__APPOINTMENT__ appointment on info.id=appointment.iid','RIGHT')->where('appointment.uid = "'.$user['id'].'"')->order('appointment.time desc')->select();
//		$result['status'] = 1;
//		$result['msg'] = '获取成功';
//		$result['data'] = $info;
//		exit(json_encode($result));	
//	}
//
//		
//	public function detail(){
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$infoObj = D('Info');
//		$info = $infoObj->field('info.id,info.departure,info.destination,info.time,appointment.*')->table('__INFO__ info')->join('__APPOINTMENT__ appointment on info.id=appointment.iid','RIGHT')->where('appointment.id = "'.I('id').'" and info.uid = "'.$user['id'].'"')->find();
//		$result['status'] = 1;
//		$result['msg'] = '获取成功';
//		$result['data'] = $info;
//		exit(json_encode($result));
//	}
//	
//	public function submit(){
//		$u = D('User');
//		$user = $u->getUserInfo(vaild_sk(I('sk')));
//		$infoObj = D('Info');
//		$app['status'] = I('type');
//		$info = $infoObj->field('info.*,appointment.uid as aid')->table('__INFO__ info')->join('__APPOINTMENT__ appointment on info.id=appointment.iid','RIGHT')->where('appointment.id = "'.I('id').'" and info.uid = "'.$user['id'].'"')->find();
//		if(empty($info)){
//			$result['status'] = 0;
//			$result['msg'] = '信息错误';
//		}else{
//			$where['id'] = I('id');
//			
//			if(M('appointment')->where($where)->save($app)){
//				$user = M('User')->find($info['aid']);
//				$postData['touser'] = $user['openId'];
//				$postData['form_id'] = i('form_id');
//				$postData['page'] = '/pages/info/index?id='.$info['id'];
//				if($app['status'] == 1){
//					$postData['template_id'] = 'rIfea-FXQNJa9Jh05jGyZO4-v-UfHjWJn0vNGwjSivc';
//					$postData['data']['keyword1']['value'] = date('Y-m-d H:i',$info['time']);
//					$postData['data']['keyword2']['value'] = $info['departure'];
//					$postData['data']['keyword3']['value'] = $info['name'];
//					$postData['data']['keyword4']['value'] = $info['phone'];
//					$content = $info['name'].'同意了您的拼车请求,请及时与车主('.$info['phone'].')取得联系';
//				}else{
//					$postData['template_id'] = 'ZuLTdhAVhXd7MTV0-TUyQjLSoF5taZVYM0IHalqZmJ4';
//					$postData['data']['keyword1']['value'] = $info['departure'].' -> '.$info['destination'];
//					$postData['data']['keyword2']['value'] = date('Y-m-d H:i',$info['time']);
//					$postData['data']['keyword3']['value'] = '拼车人数已满';
//					$postData['data']['keyword4']['value'] = '建议选择其他时间拼车';
//					$content = $info['name'].'拒绝了您的拼车请求,原因是拼车人数已满,建议选择其他时间拼车';
//				}
//				sendMessage($postData);	
//				msg('notice',$info['aid'],'10000',$content,$postData['page']);		
//				
//				$result['status'] = 1;
//				$result['msg'] = '操作成功';
//			}else{			
//				$result['status'] = 0;
//				$result['msg'] = '操作失败';
//			}
//		}
//		
//		exit(json_encode($result));
//		
//	}

}