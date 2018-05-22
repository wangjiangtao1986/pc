package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxAppointmentCountMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentCount;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentCountExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentCountExample.Criteria;

@Service(value = "xcxAppointmentCountService")
public class XcxAppointmentCountService {

	@Autowired
	private XcxAppointmentCountMapper xcxAppointmentCountMapper;

	public List<XcxAppointmentCount> selectByExample(XcxAppointmentCount record) {
		List<XcxAppointmentCount> list = new ArrayList<XcxAppointmentCount>();
		XcxAppointmentCountExample e = new XcxAppointmentCountExample();
		Criteria c = e.createCriteria();
		c.andUidEqualTo(record.getUid());
		if(!ObjectUtils.isEmpty(record)) {
			list = xcxAppointmentCountMapper.selectByExample(e);
		}
		return list;
	}

}