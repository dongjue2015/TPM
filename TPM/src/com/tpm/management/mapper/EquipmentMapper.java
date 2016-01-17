package com.tpm.management.mapper;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.tpm.management.model.EquipmentEntity;

public interface EquipmentMapper {
	    void save(EquipmentEntity electricAppliance);  
	    boolean update(EquipmentEntity electricAppliance);  
	    boolean delete(int id);  
	    EquipmentEntity findById(int id);  
	    List<EquipmentEntity> findAll(PageBounds pageBounds,HashMap<String, String> map);  

}
