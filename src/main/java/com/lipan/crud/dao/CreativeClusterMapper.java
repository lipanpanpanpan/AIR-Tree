package com.lipan.crud.dao;

import com.lipan.crud.bean.CreativeCluster;
import com.lipan.crud.bean.CreativeClusterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreativeClusterMapper {
    long countByExample(CreativeClusterExample example);

    int deleteByExample(CreativeClusterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CreativeCluster record);

    int insertSelective(CreativeCluster record);

    List<CreativeCluster> selectByExampleWithBLOBs(CreativeClusterExample example);

    List<CreativeCluster> selectByExample(CreativeClusterExample example);

    CreativeCluster selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CreativeCluster record, @Param("example") CreativeClusterExample example);

    int updateByExampleWithBLOBs(@Param("record") CreativeCluster record, @Param("example") CreativeClusterExample example);

    int updateByExample(@Param("record") CreativeCluster record, @Param("example") CreativeClusterExample example);

    int updateByPrimaryKeySelective(CreativeCluster record);

    int updateByPrimaryKeyWithBLOBs(CreativeCluster record);

    int updateByPrimaryKey(CreativeCluster record);
}