package com.milos.kindergarden;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milos.kindergarden.security.GuardianUserDetails;

@Controller
public class IndexController {
	
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String indexPage(Model model) {
		return "index";
	}
	
	
	@RequestMapping(value = "/guardian", method = RequestMethod.GET)
	public String guardianPage(Model model) {
		GuardianUserDetails guardianUserDetails = (GuardianUserDetails) SecurityContextHolder.getContext()
													.getAuthentication().getPrincipal();
		model.addAttribute("guardian", guardianUserDetails.getUser());
		return "guardian_page";
	}
	
}
