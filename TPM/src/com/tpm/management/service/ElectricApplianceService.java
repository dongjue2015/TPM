package com.tpm.management.service;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.tpm.management.model.EquipmentEntity;

public interface ElectricApplianceService {
	  List<EquipmentEntity>  findALL(PageBounds pageBounds,HashMap map) throws Exception;
}
