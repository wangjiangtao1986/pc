package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxMsgMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsg;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgExample.Criteria;

@Service(value = "xcxMsgService")
public class XcxMsgService {

	@Autowired
	private XcxMsgMapper xcxMsgMapper;

	
	public List<XcxMsg> listXcxMsg(XcxMsg xcxMsg) {
		return xcxMsgMapper.selectByExample(new XcxMsgExample());
	}
	
	public XcxMsg getXcxMsg(String id) {
		return xcxMsgMapper.selectByPrimaryKey(id);
	}

	public List<XcxMsg> selectByExample(XcxMsg record) {
		List<XcxMsg> list = new ArrayList<XcxMsg>();
		if(!ObjectUtils.isEmpty(record)) {
			XcxMsgExample e = new XcxMsgExample();
			Criteria c = e.createCriteria();
			c.andTypeEqualTo(record.getType());
			list = xcxMsgMapper.selectByExampleWithBLOBs(e);
		}
		return list;
	}


	public int insertSelective(XcxMsg xcxMsg) {
		return xcxMsgMapper.insertSelective(xcxMsg);
	}

	public int deleteByPrimaryKey(XcxMsg xcxMsg) {
		return xcxMsgMapper.deleteByPrimaryKey(xcxMsg.getId());
	}

	public void update(List<XcxMsg> list) {
		if(null!=list&&list.size()>0){
			XcxMsg record = new XcxMsg();
			record.setSee("1");
			XcxMsgExample e = new XcxMsgExample();
			Criteria c = e.createCriteria();
			c.andIdIn(getIds(list));
			xcxMsgMapper.updateByExampleSelective(record, e);
		}
	}

	private List<String> getIds(List<XcxMsg> list) {
		List<String> l = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			l.add(list.get(i).getId());
		}
		return l;
	}
	

}