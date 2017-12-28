package com.milos.kindergarden;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TeacherController {
	
	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String teacherPage(Model model) {
		return "teacher_page";
	}

}
