package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxAppointmentMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentExample.Criteria;

@Service(value = "xcxAppointmentService")
public class XcxAppointmentService {

	@Autowired
	private XcxAppointmentMapper xcxAppointmentMapper;

	
	public List<XcxAppointment> listXcxAppointment() {
		return xcxAppointmentMapper.selectByExample(new XcxAppointmentExample());
	}
	
	public XcxAppointment getXcxAppointment(Integer id) {
		return xcxAppointmentMapper.selectByPrimaryKey(id);
	}

	public List<XcxAppointment> selectByExample(XcxAppointment record) {
		List<XcxAppointment> list = new ArrayList<XcxAppointment>();
		XcxAppointmentExample e = new XcxAppointmentExample();
		Criteria c = e.createCriteria();
		c.andUidEqualTo(record.getUid());
		
		e.setOrderByClause(" time desc ");
		
		if(!ObjectUtils.isEmpty(record)) {
			list = xcxAppointmentMapper.selectByExample(e);
		}
		return list;
	}


	public int insertSelective(XcxAppointment xcxAppointment) {
		return xcxAppointmentMapper.insertSelective(xcxAppointment);
	}
	

}