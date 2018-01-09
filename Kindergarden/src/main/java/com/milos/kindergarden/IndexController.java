package com.milos.kindergarden;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milos.kindergarden.security.GuardianUserDetails;

@Controller
public class IndexController {
	
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String indexPage(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		if(session != null) {
			session.invalidate();
		}
		return "index";
	}
	
	
	@RequestMapping(value = "/guardian", method = RequestMethod.GET)
	public String guardianPage(Model model) {
		GuardianUserDetails guardianUserDetails = (GuardianUserDetails) SecurityContextHolder.getContext()
													.getAuthentication().getPrincipal();
		model.addAttribute("guardian", guardianUserDetails.getUser());
		return "guardian_page";
	}
	
	@ExceptionHandler
	public String exceptionHandler() {
		return "error_page";
	}
	
}
