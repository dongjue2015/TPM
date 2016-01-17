package com.sys.management.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.management.mapper.UserDaoMapper;
import com.sys.management.model.UserEntity;
import com.sys.management.service.UserService;

@Service  
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDaoMapper userMapper;

	@Override
	public UserEntity findUserByUserNameAndPassword(UserEntity user) throws Exception {
		UserEntity entity = userMapper.findUserByUserNameAndPassword(user);
		return entity;
	}

}
