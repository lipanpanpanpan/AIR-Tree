package com.lipan.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipan.crud.bean.User;
import com.lipan.crud.bean.UserExample;
import com.lipan.crud.bean.UserExample.Criteria;
import com.lipan.crud.dao.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> getAll() {
		// TODO Auto-generated method stub
		
		return userMapper.selectByExample(null);
	}
	
		/**
		 * 用户保存
		 * @param user
		 */
	
	public  void saveUser(User user) {
		userMapper.insertSelective(user);

	}

		/**
		 * 检验用户名是否可用
		 * count==0返回true 
		 * true代表当前用户名可用
		 * false代表当前用户名不可用
		 * @param username
		 * @return
		 */
		public boolean checkUser(String username) {
			// TODO Auto-generated method stub
			UserExample example=new UserExample();
			Criteria criteria= example.createCriteria();
			//拼装条件
			criteria.andUsernameEqualTo(username);
			long count=userMapper.countByExample(example);
			
			return count==0;
		}

		/**
		 * 按照用户id查询用户
		 * @param id
		 * @return
		 */
		public User getUser(Integer id) {
			// TODO Auto-generated method stub
			User user=userMapper.selectByPrimaryKey(id);
			return user;
		}

		/**
		 * 用户更新
		 * @param user
		 */
		public void updateUser(User user) {
			// TODO Auto-generated method stub
			userMapper.updateByPrimaryKeySelective(user);
		}

		/**
		 * 用户删除
		 * @param id
		 */
		public void deleteUser(Integer id) {
			// TODO Auto-generated method stub
			userMapper.deleteByPrimaryKey(id);
		}

		public void deleteBatch(List<Integer> ids) {
			// TODO Auto-generated method stub
			UserExample example=new UserExample();
			//创建条件
			Criteria criteria=example.createCriteria();
			//delete from xxx where userid in ....
			criteria.andUseridIn(ids);
			userMapper.deleteByExample(example);
			
			
	
		}

	

}
