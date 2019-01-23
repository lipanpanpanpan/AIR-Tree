package com.lipan.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lipan.crud.bean.Msg;
import com.lipan.crud.bean.PoiData;
import com.lipan.crud.service.PoiDataService;

/**
 * 处理poiData增删改查功能
 * @author lipanpanpan
 *
 */
@Controller
public class PoiDataController {
	@Autowired
	PoiDataService poiDataService;
	
	
	/**
	 * 单个批量删除二合一
	 * 批量删除：1-2-3
	 * 单个删除：1
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/poiData/{ids}",method=RequestMethod.DELETE)
	public Msg deletePoiData(@PathVariable("ids")String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Integer> del_ids=new ArrayList<>();
			
			String[] str_ids=ids.split("-");
			for (String string : str_ids) {
				//组装id的集合
				del_ids.add(Integer.parseInt(string));
			}
				poiDataService.deleteBatch(del_ids);
		}else {
			//单个删除
			Integer id=Integer.parseInt(ids);
			poiDataService.deletePoiData(id);
		}
		return Msg.success();
	}
	
	
	
	/**
	 *  定义兴趣点保存
	 * @param poiData
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/poiData",method=RequestMethod.POST)
	public Msg savePoiData(@Valid PoiData poiData,BindingResult result) {
		if(result.hasErrors()){
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			poiDataService.savePoiData(poiData);
			return Msg.success();
		}
			
	}
	
	/**
	 * 兴趣点更新方法
	 * @param poiData
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/poiData/{poiid}",method=RequestMethod.PUT)
	public Msg savePoiData(PoiData poiData,HttpServletRequest request) {
		System.out.println("请求体中的值："+request.getParameter("poilat"));
		System.out.println("将要更新的兴趣点的数据："+poiData);
		poiDataService.updatePoiData(poiData);
		return Msg.success()	;
	}
	
	/**
	 * 根据id查询兴趣点
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/poiData/{id}",method=RequestMethod.GET)
	public Msg getPoiData(@PathVariable("id")Integer id) {
		PoiData poiData = poiDataService.getPoiData(id);
		
		return Msg.success().add("poiData",poiData);
	}
	
	
	/**
	 * 查询POI数据（分页查询）
	 * @return
	 */
	/**
	 * ResponseBody要想正常工作，需要导入jackson包
	 * @param pn
	 * @return
	 */
	@RequestMapping("/poiDatas")
	@ResponseBody
	public Msg getPoiDataWithJson(@RequestParam(value="pn",defaultValue="1") Integer pn) {
		//这不是一个分页查询
				PageHelper.startPage(pn,5);
				//startPage后面紧跟的这个查询就是一个分页查询
			
				List<PoiData> poiDatas=poiDataService.getAll();
				
				//使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
				//封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
				PageInfo page=new PageInfo(poiDatas,5);
				return Msg.success().add("pageInfo",page);
				
	}
	
	//@RequestMapping("/poiDatas")
	public String getPoiDatas(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model) {
		
		PageHelper.startPage(pn,5);
		//startPage后面紧跟的这个查询就是一个分页查询
	
		List<PoiData> poiDatas=poiDataService.getAll();
		
		//使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
		//封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page=new PageInfo(poiDatas,5);
		model.addAttribute("pageInfo2",page);
		
		return "poiDataList";
	}
	
	@RequestMapping("/poidatas")
	@ResponseBody
	public Msg getPoiDatasWithJson() {
		//这不是一个分页查询
				List<PoiData> poiDatas=poiDataService.getAll();
			
				PageInfo page=new PageInfo(poiDatas,5);
				return Msg.success().add("pageInfo",page);
				
	}

}
