package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxAppointmentDetailMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentDetail;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentDetailExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentDetailExample.Criteria;

@Service(value = "xcxAppointmentDetailService")
public class XcxAppointmentDetailService {

	@Autowired
	private XcxAppointmentDetailMapper xcxAppointmentDetailMapper;

	public List<XcxAppointmentDetail> selectByExample(XcxAppointmentDetail record) {
		List<XcxAppointmentDetail> list = new ArrayList<XcxAppointmentDetail>();
		if(!ObjectUtils.isEmpty(record)&&!StringUtils.isEmpty(record.getUid())&&!StringUtils.isEmpty(record.getId())) {
			XcxAppointmentDetailExample e = new XcxAppointmentDetailExample();
			Criteria c = e.createCriteria();
			c.andUidEqualTo(record.getUid());
			c.andIdEqualTo(record.getId());
			list = xcxAppointmentDetailMapper.selectByExample(e);
		}
		return list;
	}

}