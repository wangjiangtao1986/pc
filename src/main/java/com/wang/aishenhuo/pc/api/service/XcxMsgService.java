package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxMsgMapper;
import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxMsgSeeMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsg;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgExample.Criteria;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSee;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSeeExample;

@Service(value = "xcxMsgService")
public class XcxMsgService {

	@Autowired
	private XcxMsgMapper xcxMsgMapper;
	@Autowired
	private XcxMsgSeeMapper xcxMsgSeeMapper;

	
	public List<XcxMsg> listXcxMsg(XcxMsg xcxMsg) {
		return xcxMsgMapper.selectByExample(new XcxMsgExample());
	}
	
	public XcxMsg getXcxMsg(Integer id) {
		return xcxMsgMapper.selectByPrimaryKey(id);
	}

	public List<XcxMsg> selectByExample(XcxMsg record) {
		List<XcxMsg> list = new ArrayList<XcxMsg>();
		XcxMsgExample e = new XcxMsgExample();
		Criteria c = e.createCriteria();
		if(!ObjectUtils.isEmpty(record)) {
			list = xcxMsgMapper.selectByExample(e);
		}
		return list;
	}


	public int insertSelective(XcxMsg xcxMsg) {
		return xcxMsgMapper.insertSelective(xcxMsg);
	}

	public int deleteByPrimaryKey(XcxMsg xcxMsg) {
		return xcxMsgMapper.deleteByPrimaryKey(xcxMsg.getId());
	}

	public List<XcxMsgSee> listXcxMsgByType(XcxMsg xcxMsg) {
		XcxMsgSeeExample example = new XcxMsgSeeExample();
		com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSeeExample.Criteria c =  example.createCriteria();
		c.andUidEqualTo(xcxMsg.getUid());
		return xcxMsgSeeMapper.selectByExample(example);
	}

	public void update(List<XcxMsg> list) {
		XcxMsg record = new XcxMsg();
		record.setSee("1");
		XcxMsgExample e = new XcxMsgExample();
		Criteria c = e.createCriteria();
		c.andIdIn(getIds(list));
		xcxMsgMapper.updateByExampleSelective(record, e);
	}

	private List<Integer> getIds(List<XcxMsg> list) {
		List<Integer> l = new ArrayList();
		for(int i=0;i<list.size();i++){
			l.add(list.get(i).getId());
		}
		return l;
	}
	

}