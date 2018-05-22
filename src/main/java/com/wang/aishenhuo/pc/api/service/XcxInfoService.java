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
	
	public XcxInfo getXcxInfo(String id) {
		return xcxInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 支持跨天查询
	 * 
	 * @param record
	 * @return
	 */
	public List<XcxInfo> selectByExample(XcxInfo record) {
		List<XcxInfo> list = new ArrayList<XcxInfo>();
		XcxInfoExample e = new XcxInfoExample();
		Criteria c = e.createCriteria();
		if(!ObjectUtils.isEmpty(record)) {

			if(!StringUtils.isEmpty(record.getDate())){
				c.andDateEqualTo(record.getDate());
			}

			if(!StringUtils.isEmpty(record.getDeparture())){
//				c.andDepartureEqualTo(record.getDeparture());
				c.andDepartureLike(record.getDeparture());
			}

			if(!StringUtils.isEmpty(record.getDestination())){
//				c.andDestinationEqualTo(record.getDestination());
				c.andDestinationLike(record.getDestination());
			}

			if(!StringUtils.isEmpty(record.getUid())){
				c.andUidEqualTo(record.getUid());
			}

			e.setOrderByClause(" date asc, time asc, addtime desc  ");
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

	public int deleteByExample(XcxInfo xcxInfo) {
		XcxInfoExample e = new XcxInfoExample();
		Criteria c = e.createCriteria();
		if(!ObjectUtils.isEmpty(xcxInfo) && !StringUtils.isEmpty(xcxInfo.getUid()) && !StringUtils.isEmpty(xcxInfo.getId())) {
			if(!StringUtils.isEmpty(xcxInfo.getUid())){
				c.andUidEqualTo(xcxInfo.getUid());
			}
			if(!StringUtils.isEmpty(xcxInfo.getId())){
				c.andIdEqualTo(xcxInfo.getId());
			}
			return xcxInfoMapper.deleteByExample(e);
		}
		return 0;
	}
}