package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxPassengerMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxPassenger;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxPassengerExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxPassengerExample.Criteria;

@Service(value = "xcxPassengerService")
public class XcxPassengerService {

	@Autowired
	private XcxPassengerMapper xcxPassengerMapper;

	
	public List<XcxPassenger> listXcxPassenger(XcxPassenger xcxPassenger) {
		return xcxPassengerMapper.selectByExample(new XcxPassengerExample());
	}

	public List<XcxPassenger> selectByExample(XcxPassenger record) {
		List<XcxPassenger> list = new ArrayList<XcxPassenger>();
		XcxPassengerExample e = new XcxPassengerExample();
		Criteria c = e.createCriteria();
		c.andUidEqualTo(record.getUid());
		if(!ObjectUtils.isEmpty(record)) {
			list = xcxPassengerMapper.selectByExample(e);
		}
		return list;
	}


	public int insertSelective(XcxPassenger xcxPassenger) {
		return xcxPassengerMapper.insertSelective(xcxPassenger);
	}

}