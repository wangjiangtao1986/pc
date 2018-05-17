package com.wang.aishenhuo.pc.api.service;

import java.util.ArrayList;
import java.util.List;

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

	
	public List<XcxComment> listXcxComment(XcxComment xcxComment) {
		return xcxCommentMapper.selectByExample(new XcxCommentExample());
	}
	
	public XcxComment getXcxComment(Integer id) {
		return xcxCommentMapper.selectByPrimaryKey(id);
	}

	public List<XcxComment> selectByExample(XcxComment record) {
		List<XcxComment> list = new ArrayList<XcxComment>();
		XcxCommentExample e = new XcxCommentExample();
		Criteria c = e.createCriteria();
		if(!ObjectUtils.isEmpty(record)) {
			c.andIidEqualTo(record.getIid());
			list = xcxCommentMapper.selectByExample(e);
		}
		return list;
	}


	public int insertSelective(XcxComment xcxComment) {
		XcxCommentWithBLOBs record = new XcxCommentWithBLOBs();
		return xcxCommentMapper.insertSelective(record);
	}

	public int deleteByPrimaryKey(XcxComment xcxComment) {
		return xcxCommentMapper.deleteByPrimaryKey(xcxComment.getId());
	}


	private List<Integer> getIds(List<XcxComment> list) {
		List<Integer> l = new ArrayList();
		for(int i=0;i<list.size();i++){
			l.add(list.get(i).getId());
		}
		return l;
	}

	public int count(XcxComment xcxComment) {
		XcxCommentExample e = new XcxCommentExample();
		Criteria c = e.createCriteria();
		c.andIidEqualTo(xcxComment.getIid());
		c.andTypeEqualTo(xcxComment.getType());
		return xcxCommentMapper.countByExample(e);
	}
	

}