package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSee;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxMsgSeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxMsgSeeMapper {
    int countByExample(XcxMsgSeeExample example);

    int deleteByExample(XcxMsgSeeExample example);

    int insert(XcxMsgSee record);

    int insertSelective(XcxMsgSee record);

    List<XcxMsgSee> selectByExample(XcxMsgSeeExample example);

    int updateByExampleSelective(@Param("record") XcxMsgSee record, @Param("example") XcxMsgSeeExample example);

    int updateByExample(@Param("record") XcxMsgSee record, @Param("example") XcxMsgSeeExample example);
}