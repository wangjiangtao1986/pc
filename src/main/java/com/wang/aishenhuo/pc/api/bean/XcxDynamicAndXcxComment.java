package com.wang.aishenhuo.pc.api.bean;

import java.util.List;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxCommentWithBLOBs;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamicWithBLOBs;

public class XcxDynamicAndXcxComment extends XcxDynamicWithBLOBs {
	private List<XcxCommentWithBLOBs> list;
	public List<XcxCommentWithBLOBs> getList() {
		return list;
	}
	public void setList(List<XcxCommentWithBLOBs> list) {
		this.list = list;
	}
}
