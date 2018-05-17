package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxInfo;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxInfoMapper {
    int countByExample(XcxInfoExample example);

    int deleteByExample(XcxInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(XcxInfo record);

    int insertSelective(XcxInfo record);

    List<XcxInfo> selectByExampleWithBLOBs(XcxInfoExample example);

    List<XcxInfo> selectByExample(XcxInfoExample example);

    XcxInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") XcxInfo record, @Param("example") XcxInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") XcxInfo record, @Param("example") XcxInfoExample example);

    int updateByExample(@Param("record") XcxInfo record, @Param("example") XcxInfoExample example);

    int updateByPrimaryKeySelective(XcxInfo record);

    int updateByPrimaryKeyWithBLOBs(XcxInfo record);

    int updateByPrimaryKey(XcxInfo record);
}