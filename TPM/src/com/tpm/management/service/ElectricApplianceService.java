package com.tpm.management.service;

import java.util.List;

import com.tpm.management.model.EquipmentEntity;

public interface ElectricApplianceService {
	  List<EquipmentEntity>  findALL(String equipmentType) throws Exception;
}
