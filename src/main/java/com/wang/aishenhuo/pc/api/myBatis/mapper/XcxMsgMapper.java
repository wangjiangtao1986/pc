package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsg;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxMsgMapper {
    int countByExample(XcxMsgExample example);

    int deleteByExample(XcxMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XcxMsg record);

    int insertSelective(XcxMsg record);

    List<XcxMsg> selectByExampleWithBLOBs(XcxMsgExample example);

    List<XcxMsg> selectByExample(XcxMsgExample example);

    XcxMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XcxMsg record, @Param("example") XcxMsgExample example);

    int updateByExampleWithBLOBs(@Param("record") XcxMsg record, @Param("example") XcxMsgExample example);

    int updateByExample(@Param("record") XcxMsg record, @Param("example") XcxMsgExample example);

    int updateByPrimaryKeySelective(XcxMsg record);

    int updateByPrimaryKeyWithBLOBs(XcxMsg record);

    int updateByPrimaryKey(XcxMsg record);
}