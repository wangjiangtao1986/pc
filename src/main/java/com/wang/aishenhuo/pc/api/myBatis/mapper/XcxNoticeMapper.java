package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxNotice;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxNoticeMapper {
    int countByExample(XcxNoticeExample example);

    int deleteByExample(XcxNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XcxNotice record);

    int insertSelective(XcxNotice record);

    List<XcxNotice> selectByExampleWithBLOBs(XcxNoticeExample example);

    List<XcxNotice> selectByExample(XcxNoticeExample example);

    XcxNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XcxNotice record, @Param("example") XcxNoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") XcxNotice record, @Param("example") XcxNoticeExample example);

    int updateByExample(@Param("record") XcxNotice record, @Param("example") XcxNoticeExample example);

    int updateByPrimaryKeySelective(XcxNotice record);

    int updateByPrimaryKeyWithBLOBs(XcxNotice record);

    int updateByPrimaryKey(XcxNotice record);
}