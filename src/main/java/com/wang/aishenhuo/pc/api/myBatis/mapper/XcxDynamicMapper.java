package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamic;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamicExample;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxDynamicWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxDynamicMapper {
    int countByExample(XcxDynamicExample example);

    int deleteByExample(XcxDynamicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XcxDynamicWithBLOBs record);

    int insertSelective(XcxDynamicWithBLOBs record);

    List<XcxDynamicWithBLOBs> selectByExampleWithBLOBs(XcxDynamicExample example);

    List<XcxDynamic> selectByExample(XcxDynamicExample example);

    XcxDynamicWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XcxDynamicWithBLOBs record, @Param("example") XcxDynamicExample example);

    int updateByExampleWithBLOBs(@Param("record") XcxDynamicWithBLOBs record, @Param("example") XcxDynamicExample example);

    int updateByExample(@Param("record") XcxDynamic record, @Param("example") XcxDynamicExample example);

    int updateByPrimaryKeySelective(XcxDynamicWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(XcxDynamicWithBLOBs record);

    int updateByPrimaryKey(XcxDynamic record);
}