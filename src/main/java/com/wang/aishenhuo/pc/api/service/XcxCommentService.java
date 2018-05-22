package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wang.aishenhuo.pc.api.myBatis.mapper.XcxCommentMapper;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxComment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxCommentExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxCommentExample.Criteria;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxCommentWithBLOBs;

@Service(value = "xcxCommentService")
public class XcxCommentService {

	@Autowired
	private XcxCommentMapper xcxCommentMapper;

//	public List<XcxComment> listXcxComment(XcxComment xcxComment) {
//		return xcxCommentMapper.selectByExample(new XcxCommentExample());
//	}
	
	public XcxCommentWithBLOBs getXcxComment(String id) {
		return xcxCommentMapper.selectByPrimaryKey(id);
	}

	public List<XcxCommentWithBLOBs> selectByExample(XcxComment record) {
		List<XcxCommentWithBLOBs> list = new ArrayList<XcxCommentWithBLOBs>();
		if(!ObjectUtils.isEmpty(record)&&!StringUtils.isEmpty(record.getIid())) {
			XcxCommentExample e = new XcxCommentExample();
			Criteria c = e.createCriteria();
			c.andIidEqualTo(record.getIid());
			list = xcxCommentMapper.selectByExampleWithBLOBs(e);
		}
		return list;
	}

	public int insertSelective(XcxCommentWithBLOBs xcxComment) {
		xcxComment.setId(UUID.randomUUID().toString());
		return xcxCommentMapper.insertSelective(xcxComment);
	}

	public int deleteByPrimaryKey(XcxComment xcxComment) {
		return xcxCommentMapper.deleteByPrimaryKey(xcxComment.getId());
	}

	public int count(XcxComment xcxComment) {
		if(!ObjectUtils.isEmpty(xcxComment)&&!StringUtils.isEmpty(xcxComment.getIid())&&!StringUtils.isEmpty(xcxComment.getType())) {
			XcxCommentExample e = new XcxCommentExample();
			Criteria c = e.createCriteria();
			c.andIidEqualTo(xcxComment.getIid());
			c.andTypeEqualTo(xcxComment.getType());
			return xcxCommentMapper.countByExample(e);
		} else {
			return 0;
		}
	}

	public int updateByPrimaryKey(XcxComment record) {
		return xcxCommentMapper.updateByPrimaryKey(record);
	}
}