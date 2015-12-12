package com.sys.management.service;

import java.util.List;

import com.sys.management.model.MenuEntity;

public interface MenuService {
    List<MenuEntity>  findByResourceType(String resourceType) throws Exception;
}
