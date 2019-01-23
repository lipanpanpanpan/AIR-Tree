package com.lipan.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipan.crud.bean.CreativeClusterSequence;
import com.lipan.crud.dao.CreativeClusterSequenceMapper;
@Service
public class CreativeClusterSequenceService {
	@Autowired
	CreativeClusterSequenceMapper creativeClusterSequenceMapper;
	/**
	 * 查询所有
	 * @return
	 */
	public List<CreativeClusterSequence> getAll() {
		
		return creativeClusterSequenceMapper.selectByExampleWithBLOBs(null) ;
	}

}
