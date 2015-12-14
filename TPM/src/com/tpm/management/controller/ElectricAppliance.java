package com.tpm.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tpm.management.mapper.EquipmentMapper;
import com.tpm.management.service.ElectricApplianceService;

@Controller
//@RequestMapping("airCompressorStation")
public class ElectricAppliance {
	@Autowired
	private  ElectricApplianceService electricApplianceService;
	@RequestMapping(value = "airCompressorStationelectricAppliance.do", method = RequestMethod.GET)
	public String electricAppliance(HttpServletRequest request, ModelMap modelMap) throws Exception {

		
	List list=	electricApplianceService.findALL("");
	request.setAttribute("list", list);
		return "/list";
	}
}
