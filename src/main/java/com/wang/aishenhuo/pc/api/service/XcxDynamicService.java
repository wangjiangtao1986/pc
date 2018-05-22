package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxDynamicMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamic;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamicExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamicExample.Criteria;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamicWithBLOBs;

@Service(value = "xcxDynamicService")
public class XcxDynamicService {

	@Autowired
	private XcxDynamicMapper xcxDynamicMapper;

	
	public List<XcxDynamic> listXcxDynamic() {
		return xcxDynamicMapper.selectByExample(new XcxDynamicExample());
	}
	
	public XcxDynamic getXcxDynamic(String id) {
		return xcxDynamicMapper.selectByPrimaryKey(id);
	}

	public List<XcxDynamic> selectByExample(XcxDynamic record) {
		List<XcxDynamic> list = new ArrayList<XcxDynamic>();
		XcxDynamicExample e = new XcxDynamicExample();
		Criteria c = e.createCriteria();
		c.andUidEqualTo(record.getUid());
		if(!ObjectUtils.isEmpty(record)) {
			list = xcxDynamicMapper.selectByExample(e);
		}
		return list;
	}

	public List<XcxDynamicWithBLOBs> selectByExampleWithBLOBs(XcxDynamic record) {
		List<XcxDynamicWithBLOBs> list = new ArrayList<XcxDynamicWithBLOBs>();
		XcxDynamicExample e = new XcxDynamicExample();
		Criteria c = e.createCriteria();
		c.andUidEqualTo(record.getUid());
		if(!ObjectUtils.isEmpty(record)) {
			list = xcxDynamicMapper.selectByExampleWithBLOBs(e);
		}
		return list;
	}



	public int insertSelective(XcxDynamicWithBLOBs xcxDynamic) {
		return xcxDynamicMapper.insertSelective(xcxDynamic);
	}

	public int deleteByPrimaryKey(XcxDynamic xcxDynamic) {
		return xcxDynamicMapper.deleteByPrimaryKey(xcxDynamic.getId());
	}
	

}