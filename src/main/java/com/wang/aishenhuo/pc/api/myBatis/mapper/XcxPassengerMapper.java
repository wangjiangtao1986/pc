package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxPassenger;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxPassengerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxPassengerMapper {
    int countByExample(XcxPassengerExample example);

    int deleteByExample(XcxPassengerExample example);

    int insert(XcxPassenger record);

    int insertSelective(XcxPassenger record);

    List<XcxPassenger> selectByExample(XcxPassengerExample example);

    int updateByExampleSelective(@Param("record") XcxPassenger record, @Param("example") XcxPassengerExample example);

    int updateByExample(@Param("record") XcxPassenger record, @Param("example") XcxPassengerExample example);
}