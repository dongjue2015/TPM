package com.sys.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {

	
	@RequestMapping(value = "user.htmls")
	public String indexPage() {	

		return "login";
	}	

	
	
	


}
