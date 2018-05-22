package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxUser;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxUserMapper {
    int countByExample(XcxUserExample example);

    int deleteByExample(XcxUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(XcxUser record);

    int insertSelective(XcxUser record);

    List<XcxUser> selectByExample(XcxUserExample example);

    XcxUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") XcxUser record, @Param("example") XcxUserExample example);

    int updateByExample(@Param("record") XcxUser record, @Param("example") XcxUserExample example);

    int updateByPrimaryKeySelective(XcxUser record);

    int updateByPrimaryKey(XcxUser record);
}