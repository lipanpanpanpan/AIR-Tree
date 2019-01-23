package com.lipan.crud.dao;

import com.lipan.crud.bean.CreativeClusterSequence;
import com.lipan.crud.bean.CreativeClusterSequenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreativeClusterSequenceMapper {
    long countByExample(CreativeClusterSequenceExample example);

    int deleteByExample(CreativeClusterSequenceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CreativeClusterSequence record);

    int insertSelective(CreativeClusterSequence record);

    List<CreativeClusterSequence> selectByExampleWithBLOBs(CreativeClusterSequenceExample example);

    List<CreativeClusterSequence> selectByExample(CreativeClusterSequenceExample example);

    CreativeClusterSequence selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CreativeClusterSequence record, @Param("example") CreativeClusterSequenceExample example);

    int updateByExampleWithBLOBs(@Param("record") CreativeClusterSequence record, @Param("example") CreativeClusterSequenceExample example);

    int updateByExample(@Param("record") CreativeClusterSequence record, @Param("example") CreativeClusterSequenceExample example);

    int updateByPrimaryKeySelective(CreativeClusterSequence record);

    int updateByPrimaryKeyWithBLOBs(CreativeClusterSequence record);
}