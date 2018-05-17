package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentCount;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxAppointmentCountMapper {
    int countByExample(XcxAppointmentCountExample example);

    int deleteByExample(XcxAppointmentCountExample example);

    int insert(XcxAppointmentCount record);

    int insertSelective(XcxAppointmentCount record);

    List<XcxAppointmentCount> selectByExample(XcxAppointmentCountExample example);

    int updateByExampleSelective(@Param("record") XcxAppointmentCount record, @Param("example") XcxAppointmentCountExample example);

    int updateByExample(@Param("record") XcxAppointmentCount record, @Param("example") XcxAppointmentCountExample example);
}