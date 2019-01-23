package com.lipan.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lipan.crud.bean.CreativeCluster;
import com.lipan.crud.bean.Msg;
import com.lipan.crud.bean.PoiData;
import com.lipan.crud.service.CreativeClusterService;

/**
 * 处理CreativeCluster查询功能
 * @author lipanpanpan
 *
 */
@Controller
public class CreativeClusterController {
	@Autowired
	CreativeClusterService  creativeClusterService;
	
	
	@RequestMapping("/creativeClusters")
	@ResponseBody
	public Msg getCreativeClustersWithJson() {
		//这不是一个分页查询
				List<CreativeCluster> creativeClusters=creativeClusterService.getAll();
			
				PageInfo page=new PageInfo(creativeClusters,5);
				return Msg.success().add("pageInfo",page);
				
	}

	
}
