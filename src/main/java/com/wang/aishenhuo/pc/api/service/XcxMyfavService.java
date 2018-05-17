package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxMyfavMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMyfav;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMyfavExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMyfavExample.Criteria;

@Service(value = "xcxMyfavService")
public class XcxMyfavService {

	@Autowired
	private XcxMyfavMapper xcxMyfavMapper;


	public List<XcxMyfav> selectByExample(XcxMyfav record) {
		List<XcxMyfav> list = new ArrayList<XcxMyfav>();
		XcxMyfavExample e = new XcxMyfavExample();
		Criteria c = e.createCriteria();
		if(!ObjectUtils.isEmpty(record)) {

			c.andUidEqualTo(record.getUid());

			list = xcxMyfavMapper.selectByExample(e);
		}
		return list;
	}


}