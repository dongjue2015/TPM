package com.sys.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.management.mapper.MenuMapper;
import com.sys.management.model.MenuEntity;
import com.sys.management.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper menuMapper;
	@Override
	public List<MenuEntity> findByResourceType(String resourceType)
			throws Exception {
		// TODO Auto-generated method stub
		return menuMapper.findByResourceType(resourceType);
	}

}
