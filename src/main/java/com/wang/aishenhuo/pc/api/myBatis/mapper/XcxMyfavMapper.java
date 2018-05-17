package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxMyfav;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMyfavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxMyfavMapper {
    int countByExample(XcxMyfavExample example);

    int deleteByExample(XcxMyfavExample example);

    int insert(XcxMyfav record);

    int insertSelective(XcxMyfav record);

    List<XcxMyfav> selectByExampleWithBLOBs(XcxMyfavExample example);

    List<XcxMyfav> selectByExample(XcxMyfavExample example);

    int updateByExampleSelective(@Param("record") XcxMyfav record, @Param("example") XcxMyfavExample example);

    int updateByExampleWithBLOBs(@Param("record") XcxMyfav record, @Param("example") XcxMyfavExample example);

    int updateByExample(@Param("record") XcxMyfav record, @Param("example") XcxMyfavExample example);
}