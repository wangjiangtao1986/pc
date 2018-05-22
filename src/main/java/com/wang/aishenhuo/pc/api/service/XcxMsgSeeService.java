package com.wang.aishenhuo.pc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxMsgSeeMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsg;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSee;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSeeExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSeeExample.Criteria;

@Service(value = "xcxMsgSeeService")
public class XcxMsgSeeService {

	@Autowired
	private XcxMsgSeeMapper xcxMsgSeeMapper;

	public List<XcxMsgSee> listXcxMsgByType(XcxMsg xcxMsg) {
		XcxMsgSeeExample example = new XcxMsgSeeExample();
		Criteria c =  example.createCriteria();
		c.andUidEqualTo(xcxMsg.getUid());
		return xcxMsgSeeMapper.selectByExample(example);
	}

}