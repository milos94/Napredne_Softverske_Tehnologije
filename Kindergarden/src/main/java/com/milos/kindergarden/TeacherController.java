package com.milos.kindergarden;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.milos.kindergarden.models.Class;
import com.milos.kindergarden.models.Classroom;
import com.milos.kindergarden.models.Employee;
import com.milos.kindergarden.models.Kid;
import com.milos.kindergarden.security.EmployeeUserDetails;
import com.milos.kindergarden.services.ClassService;
import com.milos.kindergarden.services.ClassroomService;
import com.milos.kindergarden.services.EmployeeService;
import com.milos.kindergarden.services.KidService;

@Controller
@Scope("session")
@SessionAttributes({"kids", "teachers", "classes", "classrooms", "newClass", "teacher"})
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
		model.addAttribute("teachers", employeeService.findByType("Teacher"));
		model.addAttribute("classes", classService.findAll());
		model.addAttribute("classrooms", classroomService.findAll());
		
		model.addAttribute("newClass", new Class());
		
		return "teacher_page";
	}
	
	@RequestMapping(value = "/teacher/addKidToClass", method = RequestMethod.POST)
	public String addKidToClass(@RequestParam(value = "kidId") Long kidId,
								@RequestParam(value = "classId") Long classId,
								Model model) {
		Class cls = classService.findById(classId);
		Kid kid = kidService.findById(kidId);
		kid.setGroup(cls);
		kidService.save(kid);
		return "teacher_page";
	}
	
	@RequestMapping(value = "/teacher/selectClass", method = RequestMethod.GET)
	public String selectClass(@RequestParam(value = "id") Long classId,
								Model model) {
		model.addAttribute("newClass", classService.findById(classId));
		return "teacher_page";
	}
	
	@RequestMapping(value = "/teacher/deleteClass", method = RequestMethod.GET)
	public String deleteClass(@RequestParam(value = "id") Long classId,
								Model model) {
		Class cls = classService.findById(classId);
		
		for(Kid k : kidService.findAll()) {
			if(k.getGroup() != null && k.getGroup().equals(cls)) {
				k.setGroup(null);
			}
		}
		
		classroomService.findAll().forEach(clsrm -> clsrm.getClasses().remove(cls));
		
		employeeService.findAll().forEach(t -> t.getClasses().remove(cls));
		
		classService.delete(cls);
		model.addAttribute("newClass", new Class());
		return "teacher_page";
	}
	
	@RequestMapping(value = "/teacher/addClass", method = RequestMethod.POST)
	public String addClass(@ModelAttribute(value = "newClass") Class cls,
							@RequestParam(value = "classroomId") Long classroomId,
							Model model) {
		Classroom classroom = classroomService.findById(classroomId);
		if(!cls.getClassroom().equals(classroom)) {
			cls.setClassroom(classroomService.findById(classroomId));
		}
		classService.save(cls);
		model.addAttribute("newClass", new Class());
		
		return "teacher_page";
	}
	
	@RequestMapping(value = "/teacher/addRelation", method = RequestMethod.POST)
	public String addRelation(@RequestParam(value = "teacherId") Long teacherId,
							  @RequestParam(value = "classId") Long classId,
							  Model model) {
		
		Employee teacher = employeeService.findById(teacherId);
		Class cls = classService.findById(classId);
		
		teacher.getClasses().add(cls);
		cls.getTeachers().add(teacher);
		
		classService.save(cls);
		
		return "teacher_page";
	}
}
