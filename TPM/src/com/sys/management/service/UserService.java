package com.sys.management.service;

import com.sys.management.model.UserEntity;

public interface UserService {
	
	public UserEntity findUserByUserNameAndPassword(UserEntity user) throws Exception;

}
