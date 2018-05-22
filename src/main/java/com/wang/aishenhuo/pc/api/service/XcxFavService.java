package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxFavMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxFav;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxFavExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxFavExample.Criteria;

@Service(value = "xcxFavService")
public class XcxFavService {

	@Autowired
	private XcxFavMapper xcxFavMapper;

	
	public List<XcxFav> listXcxFav() {
		return xcxFavMapper.selectByExample(new XcxFavExample());
	}
	
	public XcxFav getXcxFav(String id) {
		return xcxFavMapper.selectByPrimaryKey(id);
	}

	public List<XcxFav> selectByExample(XcxFav record) {
		List<XcxFav> list = new ArrayList<XcxFav>();
		XcxFavExample e = new XcxFavExample();
		Criteria c = e.createCriteria();
		if(!ObjectUtils.isEmpty(record)) {

			c.andUidEqualTo(record.getUid());
			c.andIidEqualTo(record.getIid());

			list = xcxFavMapper.selectByExample(e);
		}
		return list;
	}


	public int insertSelective(XcxFav xcxFav) {
		return xcxFavMapper.insertSelective(xcxFav);
	}



	public int deleteByPrimaryKey(XcxFav xcxFav) {
		return xcxFavMapper.deleteByPrimaryKey(xcxFav.getId());
	}
	
	public int deleteByExample(XcxFav xcxFav) {
		XcxFavExample e = new XcxFavExample();
		Criteria c = e.createCriteria();
		c.andUidEqualTo(xcxFav.getUid());
		c.andIidEqualTo(xcxFav.getIid());
		return xcxFavMapper.deleteByExample(e);
	}
	
	public int count(XcxFav xcxFav) {
		XcxFavExample e = new XcxFavExample();
		Criteria c = e.createCriteria();
		c.andUidEqualTo(xcxFav.getUid());
		return xcxFavMapper.countByExample(e);
	}
	

}