package com.milos.kindergarden;

import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.milos.kindergarden.models.Account;
import com.milos.kindergarden.models.Employee;
import com.milos.kindergarden.models.Guardian;
import com.milos.kindergarden.models.Kid;
import com.milos.kindergarden.models.Payment;
import com.milos.kindergarden.repositories.KidCrudRepository;
import com.milos.kindergarden.security.EmployeeUserDetails;
import com.milos.kindergarden.services.AccountService;
import com.milos.kindergarden.services.ClassService;
import com.milos.kindergarden.services.EmployeeService;
import com.milos.kindergarden.services.GuardianService;
import com.milos.kindergarden.services.KidService;
import com.milos.kindergarden.services.PaymentService;

@Controller
@SessionAttributes({"emp","newKid","newPayment","newGuardian","newEmployee","newAccount",
					"guardians","kids","accounts","payments","employees","groups"})
public class EmployeeController {
	
	GuardianService guardianService;
	KidService kidService;
	AccountService accountService;
	EmployeeService employeeService;
	PaymentService paymentService;
	ClassService classService;

	public EmployeeController(GuardianService guardianService, KidService kidService, AccountService accountService,
			EmployeeService employeeService, PaymentService paymentService, ClassService classService) {
		super();
		this.guardianService = guardianService;
		this.kidService = kidService;
		this.accountService = accountService;
		this.employeeService = employeeService;
		this.paymentService = paymentService;
		this.classService = classService;
	}

	@RequestMapping(value ="/employee", method = RequestMethod.GET)
	public String employeePage(Model model) {
		EmployeeUserDetails employeeUserDetails = (EmployeeUserDetails) SecurityContextHolder.getContext()
													.getAuthentication().getPrincipal();
		
		model.addAttribute("emp",employeeUserDetails.getUser());
		model.addAttribute("newKid", new Kid());
		model.addAttribute("newPayment", new Payment());
		model.addAttribute("newGuardian", new Guardian());
		model.addAttribute("newEmployee", new Employee());
		model.addAttribute("newAccount", new Account());
		
		model.addAttribute("guardians", guardianService.findAll());
		model.addAttribute("kids", kidService.findAll());
		model.addAttribute("accounts", accountService.findAll());
		model.addAttribute("payments", paymentService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("groups", classService.findAll());
		return "employee_page";
	}
	
	@RequestMapping(value = "/addKid", method = RequestMethod.POST)
	public String addKid(@ModelAttribute(value = "newKid") Kid kid, 
						@RequestParam(value = "date", required = true) String dateOfBirth,
						@RequestParam(value = "groupId", required = true) Long groupId,
						Model model) {
		
		kid.setDateOfBirth(LocalDate.parse(dateOfBirth));
		kid.setGroup(classService.findById(groupId));
		kidService.save(kid);
		model.addAttribute("newKid", new Kid());
		return "employee_page";
	}
	
	@RequestMapping(value = "/addGuardian", method = RequestMethod.POST)
	public String addGuardian(@ModelAttribute(value = "newGuardian") Guardian guardian,
							@RequestParam(value = "accountId", required = true) String accountId,
							Model model) {
		
		return "employee_page";
	}
	
	@RequestMapping(value = "/selectKid", method = RequestMethod.GET)
	public String selectKid(@RequestParam(value = "id", required = true) Long id, Model model) {
		model.addAttribute("newKid", kidService.findById(id));
		return "employee_page";
	}
	
}
