package com.milos.kindergarden;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexPage(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String employeePage(Model model) {
		
		return "employee_page";
	}
	
	@RequestMapping(value = "/guardian", method = RequestMethod.GET)
	public String guardianPage(Model model) {
		return "guardian_page";
	}
}
