package com.sys.management.mapper;

import java.util.List;

import com.sys.management.model.MenuEntity;
import com.sys.management.model.UserEntity;

public interface MenuMapper {
	    void save(MenuEntity menu);  
	    boolean update(MenuEntity menu);  
	    boolean delete(int id);  
	    MenuEntity findById(int id);  
	    List<MenuEntity> findAll();  
	    List<MenuEntity>  findByResourceType(String resourceType);

}
