package com.tpm.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/airCompressorStation")
public class ControlSystem {
	
	@RequestMapping(value = "controlSystem.do", method = RequestMethod.GET)
	public String login() throws Exception {

		return "/list";
	}

}
