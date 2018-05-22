package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	
	public XcxAppointment getXcxAppointment(String id) {
		return xcxAppointmentMapper.selectByPrimaryKey(id);
	}

	public List<XcxAppointment> selectByExample(XcxAppointment record) {
		List<XcxAppointment> list = new ArrayList<XcxAppointment>();
		if(!ObjectUtils.isEmpty(record)&&(!StringUtils.isEmpty(record.getUid())||!StringUtils.isEmpty(record.getIid()))) {
			XcxAppointmentExample e = new XcxAppointmentExample();
			Criteria c = e.createCriteria();
			if(!ObjectUtils.isEmpty(record.getUid())) {
				c.andUidEqualTo(record.getUid());
			}
			if(!StringUtils.isEmpty(record.getIid())) {
				c.andIidEqualTo(record.getIid());
			}
			e.setOrderByClause(" time desc ");
			list = xcxAppointmentMapper.selectByExample(e);
		}
		return list;
	}


	public int insertSelective(XcxAppointment record) {
		return xcxAppointmentMapper.insertSelective(record);
	}


	public int updateByPrimaryKey(XcxAppointment record) {
		return xcxAppointmentMapper.updateByPrimaryKey(record);
	}
	

}