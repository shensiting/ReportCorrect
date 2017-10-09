package org.gzhmc.report4gzhmc.service.impl;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.UserMapper;
import org.gzhmc.report4gzhmc.model.User;
import org.gzhmc.report4gzhmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public int add(User t) {		
		return userMapper.add(t);
	}

	@Override
	public int delete(int cId) {		
		return userMapper.delete(cId);
	}

	@Override
	public int update(User t) {		
		return userMapper.update(t);
	}

	@Override
	public int updateSelective(User t) {		
		return userMapper.updateSelective(t);
	}

	@Override
	public User getById(int cId) {		
		return userMapper.getById(cId);
	}

	@Override
	public List<User> getAll() {
		List<User> users =userMapper.getAll();
		return users;
	}

	@Override
	public User getByUserName(String username) {		
		return userMapper.getByUserName(username);
	}

	@Override
	public int getMaxcId() {		
		return userMapper.getMaxcId();
	}

	

}
