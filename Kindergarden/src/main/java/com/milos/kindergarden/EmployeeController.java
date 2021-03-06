package com.milos.kindergarden;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Scope;
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
import com.milos.kindergarden.security.EmployeeUserDetails;
import com.milos.kindergarden.services.AccountService;
import com.milos.kindergarden.services.ClassService;
import com.milos.kindergarden.services.EmployeeService;
import com.milos.kindergarden.services.GuardianService;
import com.milos.kindergarden.services.KidService;
import com.milos.kindergarden.services.PaymentService;

@Controller
@Scope("session")
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
	
	@RequestMapping(value = "/employee/refresh", method = RequestMethod.GET)
	public String refresh(Model model) {
		
		guardianService.refresh();
		kidService.refresh();
		accountService.refresh();
		employeeService.refresh();
		paymentService.refresh();
		classService.refresh();
		
		return employeePage(model);
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
	
	@RequestMapping(value = "/employee/addKid", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/employee/addGuardian", method = RequestMethod.POST)
	public String addGuardian(@ModelAttribute(value = "newGuardian") Guardian guardian,
							@RequestParam(value = "accountId", required = true) Long accountId,
							Model model) {
		guardian.setAccount(accountService.findById(accountId));
		guardianService.save(guardian);
		model.addAttribute("newGuardian", new Guardian());
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/addAccount", method = RequestMethod.POST)
	public String addAccount(@ModelAttribute(value = "newAccount") Account account, Model model) {
		accountService.save(account);
		model.addAttribute("newAccount", new Account());
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/addPayment", method = RequestMethod.POST)
	public String addPayment(@ModelAttribute(value = "newPayment") Payment payment,
							@RequestParam(value = "date", required = true) String date,
							@RequestParam(value = "time", required = true) String time,
							@RequestParam(value = "accountId", required = true) Long accountId,
							Model model) {
		if(date.equals("")) {
			date = LocalDate.now().toString();
		}
		if(time.equals("")) {
			time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		}
		
		LocalDateTime parsed = LocalDateTime.parse(date + 'T' + time);
		payment.setDate(parsed);
		
		Account acc = accountService.findById(accountId);
		acc.setBalance(acc.getBalance() - payment.getAmount());
		
		payment.setAccount(acc);
		paymentService.save(payment);
		
		model.addAttribute("newPayment", new Payment());
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/addEmployee", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute(value = "newEmployee") Employee employee,
							@RequestParam(value = "date", required = true) String date,
							Model model) {
		if(date.equals("")) {
			employee.setDateOfEmployment(LocalDate.now());
		}
		else {
			employee.setDateOfEmployment(LocalDate.parse(date));
		}
		employeeService.save(employee);
		model.addAttribute("newEmployee", new Employee());
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/selectGuardian", method = RequestMethod.GET)
	public String selectGuardian(@RequestParam(value = "id", required = true) Long id, Model model) {
		model.addAttribute("newGuardian", guardianService.findById(id));
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/deleteGuardian", method = RequestMethod.GET)
	public String deleteGuardian(@RequestParam(value = "id", required = true) Long id, Model model) {
		Guardian guardian = guardianService.findById(id);
		kidService.findAll().forEach(k -> k.getGuardians().remove(guardian));
		guardianService.delete(guardian);
		model.addAttribute("newGuardian", new Guardian());
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/selectAccount", method = RequestMethod.GET)
	public String selectAccount(@RequestParam(value = "id", required = true) Long id, Model model) {
		model.addAttribute("newAccount", accountService.findById(id));
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/deleteAccount", method = RequestMethod.GET)
	public String deleteAccount(@RequestParam(value = "id", required = true) Long id, Model model) {
		Account account = accountService.findById(id);
		
		for(Guardian g : guardianService.findAll()) {
			if(g.getAccount().equals(account)) {
				g.setAccount(null);
			}
		}
		accountService.delete(account);
		model.addAttribute("newAccount", new Account());
		
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/selectEmployee", method = RequestMethod.GET)
	public String selectEmployee(@RequestParam(value = "id", required = true) Long id, Model model) {
		model.addAttribute("newEmployee", employeeService.findById(id));
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/deleteEmployee", method = RequestMethod.GET)
	public String deleteEmployee(@RequestParam(value = "id", required = true) Long id, Model model) {
		employeeService.delete(employeeService.findById(id));
		model.addAttribute("newEmployee", new Employee());
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/selectKid", method = RequestMethod.GET)
	public String selectKid(@RequestParam(value = "id", required = true) Long id, Model model) {
		model.addAttribute("newKid", kidService.findById(id));
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/deleteKid", method = RequestMethod.GET)
	public String deleteKid(@RequestParam(value = "id", required = true) Long id, Model model) {
		Kid kid = kidService.findById(id);
		guardianService.findAll().forEach(g -> g.getKids().remove(kid));
		kidService.delete(kid);
		model.addAttribute("newKid", new Kid());
		return "employee_page";
	}
	
	@RequestMapping(value = "/employee/addRelation", method = RequestMethod.POST)
	public String addRelation(@RequestParam(value = "kidId") Long kidId,
							@RequestParam(value = "guardianId") Long guardianId,
							@RequestParam(value = "relation") String relation) {
		
		Guardian guardian = guardianService.findById(guardianId);
		Kid kid = kidService.findById(kidId);
		
		guardian.getKids().add(kid);
		kid.getGuardians().add(guardian);
		
		guardianService.save(guardian);
		return "employee_page";
	}
	
}
