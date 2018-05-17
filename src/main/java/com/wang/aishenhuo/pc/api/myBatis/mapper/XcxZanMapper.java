package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxZan;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxZanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxZanMapper {
    int countByExample(XcxZanExample example);

    int deleteByExample(XcxZanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XcxZan record);

    int insertSelective(XcxZan record);

    List<XcxZan> selectByExample(XcxZanExample example);

    XcxZan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XcxZan record, @Param("example") XcxZanExample example);

    int updateByExample(@Param("record") XcxZan record, @Param("example") XcxZanExample example);

    int updateByPrimaryKeySelective(XcxZan record);

    int updateByPrimaryKey(XcxZan record);
}