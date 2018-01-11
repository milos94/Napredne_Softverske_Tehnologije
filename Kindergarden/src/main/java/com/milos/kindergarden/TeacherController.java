package com.milos.kindergarden;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.milos.kindergarden.security.EmployeeUserDetails;
import com.milos.kindergarden.services.ClassService;
import com.milos.kindergarden.services.ClassroomService;
import com.milos.kindergarden.services.EmployeeService;
import com.milos.kindergarden.services.KidService;

import com.milos.kindergarden.models.Class;

@Controller
@SessionAttributes({"kids", "teachers", "classes", "classrooms", "newClass"})
public class TeacherController {
	
	KidService kidService;
	ClassroomService classroomService;
	ClassService classService;
	EmployeeService employeeService;
	
	
	
	public TeacherController(KidService kidService, ClassroomService classroomService, ClassService classService,
			EmployeeService employeeService) {
		super();
		this.kidService = kidService;
		this.classroomService = classroomService;
		this.classService = classService;
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/teacher/refresh", method = RequestMethod.GET)
	public String refresh(Model model) {
		
		kidService.refresh();
		classroomService.refresh();
		classService.refresh();
		employeeService.refresh();
		
		return teacherPage(model);
	}
	
	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String teacherPage(Model model) {
		EmployeeUserDetails employeeUserDetails = (EmployeeUserDetails) SecurityContextHolder.getContext()
																		.getAuthentication().getPrincipal();
		model.addAttribute("teacher", employeeUserDetails.getUser());
		
		model.addAttribute("kids", kidService.findAll());
		model.addAttribute("teachers", employeeService.findByType("teacher"));
		model.addAttribute("classes", classService.findAll());
		model.addAttribute("classrooms", classroomService.findAll());
		
		model.addAttribute("newClass", new Class());
		
		return "teacher_page";
	}

}
