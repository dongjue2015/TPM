package com.sys.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sys.management.model.MenuEntity;
import com.sys.management.model.UserEntity;
import com.sys.management.service.MenuService;
import com.sys.management.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() throws Exception {

		return "/login";
	}

	@RequestMapping(value = "index.do", method = RequestMethod.POST)
	public String index(HttpServletRequest request, ModelMap modelMap) throws Exception {
		
		String userName =request.getParameter("userName");
		
		String password =request.getParameter("password");
		
		UserEntity userParams= new  UserEntity();
		
		userParams.setUserName(userName);
		
		userParams.setPassword(password);
		
		UserEntity user = userService.findUserByUserNameAndPassword(userParams);
      
		if (user!=null&&userName.equals(user.getUserName())&&password.equals(user.getPassword())) {
			return "/index";
			
		}

		return "/login";
	}

	// {"accessPath":"loupanchart.html","checked":false,"delFlag":0,"parentID":3,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":8,"resourceName":"³Ð×â·½¹ÜÀí","resourceOrder":0,"resourceType":""}
	@RequestMapping(value = "menuView.do", method = RequestMethod.POST)
	@ResponseBody
	public String menuView(HttpServletRequest request, ModelMap modelMap) throws Exception {
		
		String resourceType= request.getParameter("resourceType");
		
	    List<MenuEntity> menuList=	menuService.findByResourceType(resourceType);
	    
	    
	    String json=    JSONObject.toJSONString(menuList);
	    
	    System.out.println(json);


		return json;
	}
}
