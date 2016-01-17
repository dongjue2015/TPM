package com.tpm.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.tpm.management.mapper.EquipmentMapper;
import com.tpm.management.service.ElectricApplianceService;

@Controller
//@RequestMapping("airCompressorStation")
public class ElectricAppliance<V> {
	@Autowired
	private  ElectricApplianceService electricApplianceService;
	@RequestMapping(value = "airCompressorStationelectricAppliance.do", method = RequestMethod.GET)
	public String electricApplianceInit(HttpServletRequest request, ModelMap modelMap) throws Exception {

		
		
		   return "/list";
	}
	
	
	
	@RequestMapping(value = "airCompressorStationelectricApplianceSearch.do", method = RequestMethod.GET)
	public String electricApplianceSearch(HttpServletRequest request, ModelMap modelMap) throws Exception {

		
		
		
			
			String pageNow = request.getParameter("page");
			String pageRow = request.getParameter("pageRow");
			int page = 1;
			int limit = 4;
			if (pageNow != null && !"".equals(pageNow)) {
				page = Integer.parseInt(pageNow);
			}
			if (pageRow != null && !"".equals(pageRow)) {
				limit = Integer.parseInt(pageRow);
			}
			String sortString = "id.desc";
			Order.formString(sortString);
			PageBounds pageBounds = new PageBounds(page, limit);
	
		
			
			
		   HashMap  map = new HashMap<String, String>();
		   map.put("id", "1");
		   List list=	electricApplianceService.findALL(pageBounds,map);
		
		   System.out.println(list.size());
		   
		   request.setAttribute("page", pageNow);
		   request.setAttribute("list", list);
		   return "/list_table";
	}
}
