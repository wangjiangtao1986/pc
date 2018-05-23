package com.wang.aishenhuo.pc.api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxNoticeMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxNotice;

@Service(value = "xcxNoticeService")
public class XcxNoticeService {

	@Autowired
	private XcxNoticeMapper xcxNoticeMapper;

	
	public XcxNotice selectByPrimaryKey(String id) {
		return xcxNoticeMapper.selectByPrimaryKey(id);
	}

//	public List<XcxNotice> selectByExample(XcxNotice record) {
//		List<XcxNotice> list = new ArrayList<XcxNotice>();
//			XcxNoticeExample e = new XcxNoticeExample();
//			Criteria c = e.createCriteria();
//			list = xcxNoticeMapper.selectByExample(e);
//		return list;
//	}


	public int insertSelective(XcxNotice xcxNotice) {
		xcxNotice.setId(UUID.randomUUID().toString());
		return xcxNoticeMapper.insertSelective(xcxNotice);
	}

	public int deleteByPrimaryKey(XcxNotice xcxNotice) {
		return xcxNoticeMapper.deleteByPrimaryKey(xcxNotice.getId());
	}

}