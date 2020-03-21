package com.atguigu.scw.user.mapper;

import com.atguigu.scw.user.bean.TMemberCert;
import com.atguigu.scw.user.bean.TMemberCertExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface TMemberCertMapper {
    long countByExample(TMemberCertExample example);

    int deleteByExample(TMemberCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMemberCert record);

    int insertSelective(TMemberCert record);

    List<TMemberCert> selectByExample(TMemberCertExample example);

    TMemberCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMemberCert record, @Param("example") TMemberCertExample example);

    int updateByExample(@Param("record") TMemberCert record, @Param("example") TMemberCertExample example);

    int updateByPrimaryKeySelective(TMemberCert record);

    int updateByPrimaryKey(TMemberCert record);
}