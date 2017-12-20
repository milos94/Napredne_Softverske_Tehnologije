package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexPage(@RequestParam (defaultValue = "world", required = false) String name, Model model) {
		model.addAttribute("name", name);
		return "index";
	}
}
