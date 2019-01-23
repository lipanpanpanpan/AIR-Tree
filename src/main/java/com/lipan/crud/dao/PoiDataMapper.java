package com.lipan.crud.dao;

import com.lipan.crud.bean.PoiData;
import com.lipan.crud.bean.PoiDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PoiDataMapper {
    long countByExample(PoiDataExample example);

    int deleteByExample(PoiDataExample example);

    int deleteByPrimaryKey(Integer poiid);

    int insert(PoiData record);

    int insertSelective(PoiData record);

    List<PoiData> selectByExampleWithBLOBs(PoiDataExample example);

    List<PoiData> selectByExample(PoiDataExample example);

    PoiData selectByPrimaryKey(Integer poiid);

    int updateByExampleSelective(@Param("record") PoiData record, @Param("example") PoiDataExample example);

    int updateByExampleWithBLOBs(@Param("record") PoiData record, @Param("example") PoiDataExample example);

    int updateByExample(@Param("record") PoiData record, @Param("example") PoiDataExample example);

    int updateByPrimaryKeySelective(PoiData record);

    int updateByPrimaryKeyWithBLOBs(PoiData record);

    int updateByPrimaryKey(PoiData record);
}