package com.milos.kindergarden;

import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.milos.kindergarden.models.Employee;
import com.milos.kindergarden.models.Guardian;
import com.milos.kindergarden.models.Kid;
import com.milos.kindergarden.models.Payment;
import com.milos.kindergarden.security.EmployeeUserDetails;

@Controller
public class EmployeeController {

	@RequestMapping(value ="/employee", method = RequestMethod.GET)
	public String employeePage(Model model) {
		EmployeeUserDetails employeeUserDetails = (EmployeeUserDetails) SecurityContextHolder.getContext()
													.getAuthentication().getPrincipal();
		
		model.addAttribute("emp",employeeUserDetails.getUser());
		model.addAttribute("newKid", new Kid());
		model.addAttribute("newPayment", new Payment());
		model.addAttribute("newGuardian", new Guardian());
		model.addAttribute("newEmployee", new Employee());
		return "employee_page";
	}
	
	@RequestMapping(value = "/addKid", method = RequestMethod.POST)
	public String addKid(@ModelAttribute(value = "newKid") Kid kid, 
						@RequestParam(value = "date", required = true) String dateOfBirth,
						@RequestParam(value = "groupId", required = true) Long groupId,
						Model model) {
		
		kid.setDateOfBirth(LocalDate.parse(dateOfBirth));
		return employeePage(model);
	}
	
	@RequestMapping(value = "/addGuardian", method = RequestMethod.POST)
	public String addGuardian(@ModelAttribute(value = "newGuardian") Guardian guardian,
							@RequestParam(value = "accountId", required = true) String accountId, Model model) {
		
		return employeePage(model);
	}
}
