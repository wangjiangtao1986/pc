package com.wang.aishenhuo.pc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxUserMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUserExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUserExample.Criteria;

@Service(value = "xcxUserService")
public class XcxUserService {

	@Autowired
	private XcxUserMapper xcxUserMapper;

	
	public List<XcxUser> listXcxUser() {
		return xcxUserMapper.selectByExample(new XcxUserExample());
	}
	

	public XcxUser getXcxUser(XcxUser xcxUser) {
		if(!ObjectUtils.isEmpty(xcxUser)) {
			if(!ObjectUtils.isEmpty(xcxUser.getId())) {
				return selectByPrimaryKey(xcxUser.getId());
			} else if(!StringUtils.isEmpty(xcxUser.getOpenid())) {
				return getXcxUser(xcxUser.getOpenid());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public XcxUser selectByPrimaryKey(String id) {
		return xcxUserMapper.selectByPrimaryKey(id);
	}

	public XcxUser getXcxUser(String openid) {
		XcxUserExample e = new XcxUserExample();
		Criteria c = e.createCriteria();
		c.andOpenidEqualTo(openid);
		List<XcxUser> list = xcxUserMapper.selectByExample(e);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}


	public int insertSelective(XcxUser record) {
		return xcxUserMapper.insertSelective(record);
	}


	public int updateByPrimaryKey(XcxUser record) {
		return xcxUserMapper.updateByPrimaryKey(record);
	}
	

}