package com.tpm.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
//@RequestMapping("airCompressorStation")
public class ControlSystem {
	
	@RequestMapping(value = "airCompressorStationcontrolSystem.do", method = RequestMethod.GET)
	public String controlSystem(HttpServletRequest request, ModelMap modelMap) throws Exception {

		return "/list";
	}

}
