package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxInfoMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxInfo;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxInfoExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxInfoExample.Criteria;

@Service(value = "xcxInfoService")
public class XcxInfoService {

	@Autowired
	private XcxInfoMapper xcxInfoMapper;

	
	public List<XcxInfo> listXcxInfo() {
		return xcxInfoMapper.selectByExample(new XcxInfoExample());
	}
	
	public XcxInfo getXcxInfo(String id) {
		return xcxInfoMapper.selectByPrimaryKey(id);
	}

	public List<XcxInfo> selectByExample(XcxInfo record) {
		List<XcxInfo> list = new ArrayList<XcxInfo>();
		XcxInfoExample e = new XcxInfoExample();
		Criteria c = e.createCriteria();
		if(!ObjectUtils.isEmpty(record)) {

			if(!StringUtils.isEmpty(record.getDate())){
				c.andDateEqualTo(record.getDate());
			}

			if(!StringUtils.isEmpty(record.getDeparture())){
				c.andDepartureEqualTo(record.getDeparture());
			}

			if(!StringUtils.isEmpty(record.getDestination())){
				c.andDestinationEqualTo(record.getDestination());
			}

			if(!StringUtils.isEmpty(record.getUid())){
				c.andUidEqualTo(record.getUid());
			}

			list = xcxInfoMapper.selectByExample(e);
		}
		return list;
	}


	public int insertSelective(XcxInfo xcxInfo) {
		return xcxInfoMapper.insertSelective(xcxInfo);
	}

	public int count(XcxInfo xcxInfo) {
		XcxInfoExample e = new XcxInfoExample();
		Criteria c = e.createCriteria();
		c.andUidEqualTo(xcxInfo.getUid());
		return xcxInfoMapper.countByExample(e);
	}
	

}