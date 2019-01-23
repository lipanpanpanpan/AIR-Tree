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
import com.lipan.crud.bean.User;
import com.lipan.crud.dao.UserMapper;
import com.lipan.crud.service.UserService;
/**
 * 处理用户的增删改查请求的
 * @author lipanpanpan
 *
 */
@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	/**
	 * 单个批量二合一
	 * 批量删除：1-2-3-4
	 * 单个删除：1
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/{ids}",method=RequestMethod.DELETE)
	public Msg deleteUserById(@PathVariable("ids")String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Integer> del_ids=new ArrayList<Integer>();
			String[] str_ids=ids.split("-");
			//组装id的数组
			for(String string:str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			
			userService.deleteBatch(del_ids);
		}else {
			//单个删除
			Integer id=Integer.parseInt(ids);
			userService.deleteUser(id);
		}
		
		return Msg.success();
	}
	
	/**
	 * 如果直接发送ajax请求
	 * 封装的数据
	 * User [userid=2134, username=null, password=null, type=null]
	 * 
	 * 问题：
	 * 请求体中有数据，但是User对象封装不上
	 * update user where userid=1；
	 * Tomcat的问题：
	 * 	1、将请求体中的数据，封装成一个map
	 * 	2、request。getParameter("username")就会从这个map中取值
	 * 	3、SpringMVC封装POJO对象的时候，
	 * 			会把POJO中每个属性的值，request.getParameter("password");
	 * ajax发送PUT请求引发的血案：
	 * 	put请求：请求体中的数据，request，getParameter("password")拿不到
	 * 	Tomcat一看是PUT不会封装请求体中的数据为map，只有post形式的请求才封装请求体为map
	 *  * org.apache.catalina.connector.Request--parseParameters() (3111);
	 * 
	 * protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
                success = true;
                return;
            }
	 * 
	 * 
	 * 解决方案；
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 1、配置上HttpPutFormContentFilter；
	 * 2、他的作用；将请求体中的数据解析包装成一个map。
	 * 3、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
	 * 
	 * 
	 * 用户更新方法
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/{userid}",method=RequestMethod.PUT)
	public Msg saveUser(User user,HttpServletRequest request) {
		System.out.println("请求体中的值："+request.getParameter("type"));
		System.out.println("将要更新的用户数据："+user);
		userService.updateUser(user);
		return Msg.success();
	}
	
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getUser(@PathVariable("id")Integer id) {
		//从路径中取出的id占位符的
		User user=userService.getUser(id);
		return Msg.success().add("user",user);
	}
	
	
	/**
	 * 检验用户名是否可用
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("username")String username){
		//先判断用户名是否是合法的表达式;
		String regx = "(^[a-zA-Z0-9_-]{4,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!username.matches(regx)){
			return Msg.fail().add("va_msg", "用户名必须是4-16位数字和字母的组合或者2-5位中文");
		}
		
		//数据库用户名重复校验
		boolean b = userService.checkUser(username);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "用户名不可用");
		}
	}
	
	
	/**
	 * 用户保存
	 * 1、支持JSR303校验
	 * 2、导入Hibernate-Validator
	 */
	@RequestMapping(value="/user",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveUser(@Valid User user,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map=new HashMap<String, Object>();
			List<FieldError> errors=result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			userService.saveUser(user);
			return Msg.success();
		}
		
	}
	
	
	/**
	 * 查询用户数据（分页查询）
	 * @return
	 */
	@RequestMapping("/users")
	@ResponseBody
	public Msg getUserWithJson(@RequestParam(value="pn",defaultValue="1") Integer pn) {
		PageHelper.startPage(pn,5);
		//startPage后面紧跟的这个查询就是一个分页查询
		
		List<User> users=userService.getAll();
		//使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
		//封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page=new PageInfo(users,5);
		return Msg.success().add("pageInfo",page);
	}

	//处理的是users请求
	//@RequestMapping("/users")
	//没传值过来，则默认为第一页
	public String getUser(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model) {
		//这不是一个分页查询
		//为了简单，引入PageHelper分页
		//在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn,5);
		//startPage后面紧跟的这个查询就是一个分页查询
		
		List<User> users=userService.getAll();
		//使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
		//封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page=new PageInfo(users,5);
		model.addAttribute("pageInfo",page);
		
		//来到list页面进行展示
		return "userList";
	}

}
