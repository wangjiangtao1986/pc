package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxZanMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxZan;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxZanExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxZanExample.Criteria;

@Service(value = "xcxZanService")
public class XcxZanService {

	@Autowired
	private XcxZanMapper xcxZanMapper;

	
	public List<XcxZan> listXcxZan(XcxZan xcxZan) {
		return xcxZanMapper.selectByExample(new XcxZanExample());
	}
	
	public XcxZan getXcxZan(String id) {
		return xcxZanMapper.selectByPrimaryKey(id);
	}

	public List<XcxZan> selectByExample(XcxZan record) {
		List<XcxZan> list = new ArrayList<XcxZan>();
		XcxZanExample e = new XcxZanExample();
		Criteria c = e.createCriteria();
		if(!ObjectUtils.isEmpty(record)) {
			c.andCidEqualTo(record.getCid());
			c.andUidEqualTo(record.getUid());
			list = xcxZanMapper.selectByExample(e);
		}
		return list;
	}


	public int insertSelective(XcxZan xcxZan) {
		return xcxZanMapper.insertSelective(xcxZan);
	}

	public int deleteByPrimaryKey(XcxZan xcxZan) {
		return xcxZanMapper.deleteByPrimaryKey(xcxZan.getId());
	}

	public int count(XcxZan record) {
		XcxZanExample e = new XcxZanExample();
		Criteria c = e.createCriteria();
		c.andCidEqualTo(record.getCid());
		c.andUidEqualTo(record.getUid());
		return xcxZanMapper.countByExample(e);
	}
	

}