package com.wang.aishenhuo.pc.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentCount;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentDetail;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxInfo;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsg;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSee;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxPassenger;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.service.XcxAppointmentCountService;
import com.wang.aishenhuo.pc.api.service.XcxAppointmentDetailService;
import com.wang.aishenhuo.pc.api.service.XcxAppointmentService;
import com.wang.aishenhuo.pc.api.service.XcxInfoService;
import com.wang.aishenhuo.pc.api.service.XcxMsgSeeService;
import com.wang.aishenhuo.pc.api.service.XcxMsgService;
import com.wang.aishenhuo.pc.api.service.XcxPassengerService;
import com.wang.aishenhuo.pc.api.service.XcxUserService;
import com.wang.utiles.Constant;
import com.wang.utiles.WXUtil;

/**
 * 预约
 * 
 * 
 * @author 王江涛
 *
 */
@RestController
public class XcxAppointmentController {

	@Autowired
	XcxAppointmentService xcxAppointmentService;
	@Autowired
	XcxAppointmentCountService xcxAppointmentCountService;
	@Autowired
	XcxAppointmentDetailService xcxAppointmenDetailService;
	@Autowired
	XcxUserService xcxUserService;
	@Autowired
	XcxMsgService xcxMsgService;
	@Autowired
	XcxMsgSeeService xcxMsgSeeService;
	@Autowired
	XcxPassengerService xcxPassengerService;
	@Autowired
	XcxInfoService xcxInfoService;

    @Value("${page.pageSize}")
    int pageSize;
    
    Constant constant;
	

	@RequestMapping("/appointment/getAll")
	public JSONObject getAll(XcxMsg xcxMsg,String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())){
			xcxMsg.setUid(user.getId());
			List<XcxMsgSee> list = xcxMsgSeeService.listXcxMsgByType(xcxMsg);
			j.put("status", 1);
			j.put("msg", "获取成功");
			j.put("data", list);
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		return j;
	}


	/**
	 * 获取消息提醒列表
	 * 
	 * 
	 * 
	 * @param xcxMsg
	 * @param sk
	 * @param request
	 * @return
	 */
	@RequestMapping("/appointment/get")
	public JSONObject get(XcxMsg xcxMsg,String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			xcxMsg.setUid(user.getId());
			List<XcxMsg> list = xcxMsgService.selectByExample(xcxMsg);
			xcxMsgService.update(list);
			j.put("status", 1);
			j.put("msg", "消息加载成功");
			j.put("data", list);
		} else {
			j.put("status", 0);
			j.put("msg", "消息加载失败");
		}
		return j;
	}

	/**
	 * 获取预约消息汇总
	 * 
	 * 
	 * 
	 * @param sk
	 * @return
	 */
	@RequestMapping("/appointment/mycount")
	public JSONObject mycount(String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			XcxAppointmentCount recored = new XcxAppointmentCount();
			recored.setUid(user.getId());
			long data = 0;
			List<XcxAppointmentCount> list = xcxAppointmentCountService.selectByExample(recored);
			if(null!=list && list.size()>0){
				data = list.get(0).getCount();
			} else {
				
			}
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
	 * 获取我的预约信息
	 * 
	 * 
	 * 
	 * @param xcxAppointment
	 * @param sk
	 * @return
	 */
	@RequestMapping("/appointment/my")
	public JSONObject my(XcxAppointment xcxAppointment,String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			xcxAppointment.setUid(user.getId());
			List<XcxAppointment> info = xcxAppointmentService.selectByExample(xcxAppointment);
			if(null!=info && info.size()>0){
			} else {
				info = new ArrayList<XcxAppointment>();
			}
			j.put("status", 1);
			j.put("msg", "获取成功");
			j.put("data", info);
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		
		return j;
	}

	/**
	 * 获取约车人信息
	 * 
	 * 
	 * 
	 * @param xcxPassenger
	 * @param page
	 * @param sk
	 * @param request
	 * @return
	 */
	@RequestMapping("/appointment/getPassenger")
	public JSONObject getPassenger(XcxPassenger xcxPassenger,int page, String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null!=user && !StringUtils.isEmpty(user.getId())) {
			xcxPassenger.setUid(user.getId());
//	        PageHelper.startPage(page,pageSize); // 设置分页，参数1=页数，参数2=每页显示条数
			List<XcxPassenger> info = xcxPassengerService.selectByExample(xcxPassenger);
			if(null!=info && info.size()>0){
				j.put("status", 1);
				j.put("msg", "获取成功");
				j.put("data", info);
			} else {
				info = new ArrayList<XcxPassenger>();
				j.put("status", 0);
				j.put("msg", "获取失败");
				j.put("data", info);
			}
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		return j;
	}
	
	/**
	 * 预约请求处理
	 * 
	 * 
	 * 
	 * @param record
	 * @param sk
	 * @param form_id
	 * @return
	 */
	@RequestMapping("/appointment/submit")
	public JSONObject submit(XcxAppointment record,String sk,String form_id) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		record.setUid(user.getId());
		XcxAppointment appointment = xcxAppointmentService.getXcxAppointment(record.getId());
		
		XcxInfo xcxInfo = xcxInfoService.getXcxInfo(appointment.getIid());
		
		if(ObjectUtils.isEmpty(xcxInfo)){
			j.put("status", 0);
			j.put("msg", "信息错误");
		} else {
			appointment.setStatus(record.getStatus());
			xcxAppointmentService.updateByPrimaryKey(appointment);
			
//			XcxUser xcxUser = xcxUserService.getXcxUser(appointment.getUid());

			String url = "/pages/info/index?id="+xcxInfo.getId();
			String content = "";
			if(1==record.getStatus()){
				content = "同意了您的拼车请求,请及时与车主(" + xcxInfo.getPhone() + ")取得联系";
			} else {
				content = "拒绝了您的拼车请求,原因是拼车人数已满,建议选择其他时间拼车";
			}
			
			addMsg(user, appointment, xcxInfo, url, content);
//			VQ__f7ZjHSWuRAFIELYSNO4BV2F2ohUuOOAK21Xb2vg	预约成功通知
//			-NjzozcjANxJKskb1Nxp8C-no3NFa62gOiWOf08soQw	拒绝

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
			
			j.put("status", 1);
			j.put("msg", "获取成功");
		}
		return j;
	}

	
	/**
	 * 预约详情获取
	 * 
	 * 
	 * 
	 * @param record
	 * @param sk
	 * @param request
	 * @return
	 */
	@RequestMapping("/appointment/detail")
	public JSONObject detail(XcxAppointmentDetail record,String sk) {
		JSONObject j = new JSONObject();
		XcxUser user = xcxUserService.getXcxUser(sk);
		record.setUid(user.getId());
		List<XcxAppointmentDetail> list = xcxAppointmenDetailService.selectByExample(record);
		if(null!=list && list.size()>0) {
			j.put("status", 1);
			j.put("msg", "获取成功");
			j.put("data", list.get(0));
		} else {
			j.put("status", 0);
			j.put("msg", "获取失败");
		}
		return j;
	}

	/**
	 * 预约
	 * 
	 * 
	 * 
	 * @param xcxAppointment
	 * @param sk
	 * @param form_id
	 * @param request
	 * @return
	 */
	@RequestMapping("/appointment/add")
	public JSONObject add(XcxAppointment xcxAppointment,String sk,String form_id) {
		JSONObject j = new JSONObject();

		XcxUser user = xcxUserService.getXcxUser(sk);
		if(null==user || StringUtils.isEmpty(user.getId())) {
			j.put("status", 0);
			j.put("msg", "预约失败");
		} else {
			xcxAppointment.setUid(user.getId());
			xcxAppointment.setTime((int) System.currentTimeMillis());
			List<XcxAppointment> info = xcxAppointmentService.selectByExample(xcxAppointment);

			if(null!=info && info.size()>10) {
				j.put("status", 0);
				j.put("msg", "请不要重复预约");
			} else {
//					array('phone','/^1[34578]\d{9}$/','手机号码格式错误'),
				if(StringUtils.isEmpty(xcxAppointment.getName())||
						StringUtils.isEmpty(xcxAppointment.getPhone())||
						ObjectUtils.isEmpty(xcxAppointment.getSurplus())) {
					j.put("status", 0);
					j.put("msg", "预约失败");
				} else {

					xcxAppointment.setId(UUID.randomUUID().toString());
					xcxAppointmentService.insertSelective(xcxAppointment);
					
					XcxInfo xcxInfo = xcxInfoService.getXcxInfo(xcxAppointment.getIid());

					sendWXMsg(xcxAppointment, form_id, xcxInfo);

					addMsg(xcxAppointment, user, xcxInfo);

					j.put("status", 1);
					j.put("msg", "预约成功");
				}
			}
		}
		return j;
	}

	/**
	 * 发起预约信息提醒，微信端
	 * 
	 * 
	 * 
	 * @param xcxAppointment
	 * @param form_id
	 * @param xcxInfo
	 */
	private void sendWXMsg(XcxAppointment xcxAppointment, String form_id, XcxInfo xcxInfo) {
		XcxUser touser = xcxUserService.getXcxUser(xcxInfo.getUid());

		JSONObject message = new JSONObject();
		JSONObject data = new JSONObject();
		message.put("touser", touser.getOpenid());
		message.put("template_id", "gf5Oe4d84pGu67IzL13T6abROq4vD9_wInYvKZfHzEc");
		message.put("form_id", form_id);
		message.put("page", "/pages/appointment/index?id=" + xcxAppointment.getIid());

		data.put("keyword1", xcxInfo.getDate() + " " + xcxInfo.getTime());
		data.put("keyword2", xcxAppointment.getName());
		data.put("keyword3", xcxAppointment.getPhone());
		data.put("keyword4", xcxInfo.getDestination());
		data.put("keyword5", xcxInfo.getDeparture());
		data.put("keyword6", xcxInfo.getPrice());
		
		message.put("data", data);
		
		WXUtil.sendMessage(constant, message, WXUtil.getToken(constant));
	}

	/**
	 * 发送预约提醒，内部
	 * 
	 * 
	 * 
	 * @param xcxAppointment
	 * @param user
	 * @param xcxInfo
	 */
	private void addMsg(XcxAppointment xcxAppointment, XcxUser user, XcxInfo xcxInfo) {
		XcxMsg xcxMsg = new XcxMsg();
		xcxMsg.setContent(xcxAppointment.getName() + "预约了您发布的拼车信息,请及时处理 ");
		xcxMsg.setAvatarurl(user.getAvatarurl());
		xcxMsg.setNickname(user.getNickname());
		xcxMsg.setType("notice");
		xcxMsg.setUrl("/pages/appointment/index?id=" + xcxAppointment.getIid());
		xcxMsg.setTime((int) System.currentTimeMillis());
//					两个字段需要区别对待
		xcxMsg.setUid("10000");
		xcxMsg.setFid(xcxInfo.getUid());
		xcxMsgService.insertSelective(xcxMsg);
	}


	private void addMsg(XcxUser user, XcxAppointment appointment, XcxInfo xcxInfo, String url, String content) {
		XcxMsg xcxMsg = new XcxMsg();
		xcxMsg.setContent(content);
		xcxMsg.setAvatarurl(user.getAvatarurl());
		xcxMsg.setNickname(user.getNickname());
		xcxMsg.setType("notice");
		xcxMsg.setUrl(url);
		xcxMsg.setTime((int) System.currentTimeMillis());
		
//			两个字段需要区别对待
		xcxMsg.setUid(xcxInfo.getUid());
		xcxMsg.setFid(appointment.getId());
		
		xcxMsgService.insertSelective(xcxMsg);
	}

}