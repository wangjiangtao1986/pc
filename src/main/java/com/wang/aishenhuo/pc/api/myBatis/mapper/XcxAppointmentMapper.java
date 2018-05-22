package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointment;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxAppointmentMapper {
    int countByExample(XcxAppointmentExample example);

    int deleteByExample(XcxAppointmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(XcxAppointment record);

    int insertSelective(XcxAppointment record);

    List<XcxAppointment> selectByExample(XcxAppointmentExample example);

    XcxAppointment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") XcxAppointment record, @Param("example") XcxAppointmentExample example);

    int updateByExample(@Param("record") XcxAppointment record, @Param("example") XcxAppointmentExample example);

    int updateByPrimaryKeySelective(XcxAppointment record);

    int updateByPrimaryKey(XcxAppointment record);
}