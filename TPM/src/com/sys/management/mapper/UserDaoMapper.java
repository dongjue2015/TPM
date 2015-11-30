package com.sys.management.mapper;

import java.util.List;

import com.sys.management.model.UserEntity;

public interface UserDaoMapper {
	    void save(UserEntity user) throws Exception;  
	    boolean update(UserEntity user) throws Exception;  
	    boolean delete(int id) throws Exception;  
		UserEntity findUserByUserNameAndPassword(UserEntity user) throws Exception;

	    
	    UserEntity findById(int id) throws Exception;  
	    List<UserEntity> findAll() throws Exception;  

}
