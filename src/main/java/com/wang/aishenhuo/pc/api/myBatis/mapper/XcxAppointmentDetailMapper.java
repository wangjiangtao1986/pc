package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentDetail;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxAppointmentDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxAppointmentDetailMapper {
    int countByExample(XcxAppointmentDetailExample example);

    int deleteByExample(XcxAppointmentDetailExample example);

    int insert(XcxAppointmentDetail record);

    int insertSelective(XcxAppointmentDetail record);

    List<XcxAppointmentDetail> selectByExample(XcxAppointmentDetailExample example);

    int updateByExampleSelective(@Param("record") XcxAppointmentDetail record, @Param("example") XcxAppointmentDetailExample example);

    int updateByExample(@Param("record") XcxAppointmentDetail record, @Param("example") XcxAppointmentDetailExample example);
}