package com.lipan.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lipan.crud.bean.CreativeClusterSequence;
import com.lipan.crud.bean.Msg;
import com.lipan.crud.service.CreativeClusterSequenceService;

/**
 * 处理CreativeClusterSequence查询功能
 * @author lipanpanpan
 *
 */
@Controller
public class CreativeClusterSequenceController {
	@Autowired
	CreativeClusterSequenceService creativeClusterSequenceService;
	@RequestMapping("/creativeClusterSequences")
	@ResponseBody
	public Msg getCreativeClusterSequencesWithJson() {
		//这不是一个分页查询
				List<CreativeClusterSequence> creativeClusterSequences=creativeClusterSequenceService.getAll();
			
				PageInfo page=new PageInfo(creativeClusterSequences,5);
				return Msg.success().add("pageInfo",page);
				
	}

	
}
