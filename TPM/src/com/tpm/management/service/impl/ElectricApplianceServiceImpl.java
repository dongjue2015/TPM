package com.tpm.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpm.management.mapper.EquipmentMapper;
import com.tpm.management.model.EquipmentEntity;
import com.tpm.management.service.ElectricApplianceService;

@Service
public class ElectricApplianceServiceImpl implements ElectricApplianceService  {
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Override
	public List<EquipmentEntity> findALL(String equipmentType) throws Exception {
		
		
		return equipmentMapper.findAll();
	}

}
