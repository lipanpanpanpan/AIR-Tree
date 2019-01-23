package com.lipan.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipan.crud.bean.CreativeCluster;
import com.lipan.crud.dao.CreativeClusterMapper;



@Service
public class CreativeClusterService {
	@Autowired
	CreativeClusterMapper creativeClusterMapper;
	/**
	 * 查询所有
	 * @return
	 */
	
	public List<CreativeCluster> getAll() {
		// TODO Auto-generated method stub
		return creativeClusterMapper.selectByExampleWithBLOBs(null);
	}
}
