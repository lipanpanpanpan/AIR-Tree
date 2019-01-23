package com.lipan.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lipan.crud.bean.User;
import com.lipan.crud.dao.PoiDataMapper;
import com.lipan.crud.dao.UserMapper;

/**
 * 测试dao层的工作
 * @author lipanpanpan
 *
 *1、导入SpringTEST模块
 *2、使用@ContextConfiguration来指定Spring配置文件的位置
 *3、直接autowired要用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	/**
	 * 测试UserMapper
	 */
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 测试PoiDataMapper
	 */
	@Autowired
	PoiDataMapper poiDataMapper;
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD() {
		System.out.println(userMapper);
		System.out.println(poiDataMapper);
		//1.插入几个用户
//		userMapper.insertSelective(new User(null,"张三","123455","0"));
//		userMapper.insertSelective(new User(null,"李四","123434","1"));
		//2、批量插入多个用户
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		for(int i=0;i<100;i++) {
			String uid=UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new User(null,uid,uid+"1234","0"));
		}
		System.out.println("批量完成");
		System.out.println(poiDataMapper.selectByExample(null));
	
	}
	
}
