package com.lipan.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipan.crud.bean.PoiData;
import com.lipan.crud.bean.PoiDataExample;
import com.lipan.crud.bean.PoiDataExample.Criteria;
import com.lipan.crud.dao.PoiDataMapper;
import com.lipan.crud.test.poidata;
@Service
public class PoiDataService {
	
	@Autowired
	PoiDataMapper poiDataMapper;
	/**
	 * 查询所有POI兴趣点
	 * @return
	 */
	
	public List<PoiData> getAll() {
		// TODO Auto-generated method stub
		return poiDataMapper.selectByExampleWithBLOBs(null);
	}
	/**
	 * 兴趣点保存方法
	 * @param poiData
	 */
	public void savePoiData(PoiData poiData) {
		// TODO Auto-generated method stub
		poiDataMapper.insertSelective(poiData);
	}
	
	/**
	 * 按照兴趣点id查询兴趣点
	 * @param id
	 * @return
	 */
	public  PoiData getPoiData(Integer id) {
		// TODO Auto-generated method stub
		PoiData poiData =poiDataMapper.selectByPrimaryKey(id);
		return poiData;
	}
	/**
	 * 兴趣点信息更新
	 * @param poiData
	 */
	public void updatePoiData(PoiData poiData) {
		// TODO Auto-generated method stub
		poiDataMapper.updateByPrimaryKeyWithBLOBs(poiData);
	}

	/**
	 * 兴趣点删除
	 * @param id
	 */
	public void deletePoiData(Integer id) {
		// TODO Auto-generated method stub
		poiDataMapper.deleteByPrimaryKey(id);		
	}
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		PoiDataExample example=new PoiDataExample();
		Criteria criteria=example.createCriteria();
		//拼接条件
		criteria.andPoiidIn(ids);
		poiDataMapper.deleteByExample(example);
	
	}
}



