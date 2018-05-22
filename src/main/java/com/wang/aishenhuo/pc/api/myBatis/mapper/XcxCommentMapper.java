package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxComment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxCommentExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxCommentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxCommentMapper {
    int countByExample(XcxCommentExample example);

    int deleteByExample(XcxCommentExample example);

    int deleteByPrimaryKey(String id);

    int insert(XcxCommentWithBLOBs record);

    int insertSelective(XcxCommentWithBLOBs record);

    List<XcxCommentWithBLOBs> selectByExampleWithBLOBs(XcxCommentExample example);

    List<XcxComment> selectByExample(XcxCommentExample example);

    XcxCommentWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") XcxCommentWithBLOBs record, @Param("example") XcxCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") XcxCommentWithBLOBs record, @Param("example") XcxCommentExample example);

    int updateByExample(@Param("record") XcxComment record, @Param("example") XcxCommentExample example);

    int updateByPrimaryKeySelective(XcxCommentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(XcxCommentWithBLOBs record);

    int updateByPrimaryKey(XcxComment record);
}