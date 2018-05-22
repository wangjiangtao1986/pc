package com.wang.aishenhuo.pc.api.myBatis.mapper;

import com.wang.aishenhuo.pc.api.myBatis.model.XcxFav;
import com.wang.aishenhuo.pc.api.myBatis.model.XcxFavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XcxFavMapper {
    int countByExample(XcxFavExample example);

    int deleteByExample(XcxFavExample example);

    int deleteByPrimaryKey(String id);

    int insert(XcxFav record);

    int insertSelective(XcxFav record);

    List<XcxFav> selectByExample(XcxFavExample example);

    XcxFav selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") XcxFav record, @Param("example") XcxFavExample example);

    int updateByExample(@Param("record") XcxFav record, @Param("example") XcxFavExample example);

    int updateByPrimaryKeySelective(XcxFav record);

    int updateByPrimaryKey(XcxFav record);
}