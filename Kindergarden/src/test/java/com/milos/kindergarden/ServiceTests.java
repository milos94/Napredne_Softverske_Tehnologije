package com.milos.kindergarden;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.milos.kindergarden.services.AccountService;
import com.milos.kindergarden.services.ClassService;
import com.milos.kindergarden.services.ClassroomService;
import com.milos.kindergarden.services.EmployeeService;
import com.milos.kindergarden.services.GuardianService;
import com.milos.kindergarden.services.KidService;
import com.milos.kindergarden.services.PaymentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {
	
	@Autowired
	AccountService accountService;
	@Autowired
	ClassService classsService;
	@Autowired
	ClassroomService classroomService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	GuardianService guardianService;
	@Autowired 
	KidService kidService;
	@Autowired
	PaymentService paymentService;
	
	@Test
	public void accountServiceTest() {
		assertNotNull("AccountService: findAll",accountService.findAll());
		assertNotNull("AccountService: findById 10001",accountService.findById((long)10001));		
	}
	
	@Test
	public void classServiceTest() {
		assertNotNull("ClassService: findAll",classsService.findAll());
		assertNotNull("ClassService: findById 101",classsService.findById((long)101));
		assertNotNull("ClassService: findByName Avengers",classsService.findByName("Avengers"));
	}
	
	@Test
	public void classroomServiceTest() {
		assertNotNull("ClassroomService: findAll",classroomService.findAll());
		assertNotNull("ClassroomService: findById 1001",classroomService.findById((long)1001));
	}
	
	@Test
	public void employeeServiceTest() {
		assertNotNull("EmployeeService: findAll",employeeService.findAll());
		assertNotNull("EmployeeService: findById 11",employeeService.findById((long)11));
		assertNotNull("EmployeeService: findByFirstNameAndLastname John Jones",
						employeeService.findByFirstNameAndLastName("John", "Jones"));
	}
	
	@Test
	public void guardianServiceTest() {
		assertNotNull("GuardianService: findAll",guardianService.findAll());
		assertNotNull("GuardianService: findById 1",guardianService.findById((long) 1));
		assertNotNull("GuardianService: findByFirstNameAndLastName Clark Kent",
						guardianService.findByFirstNameAndLastName("Clark", "Kent"));
	}
	
	@Test
	public void kidServiceTest() {
		assertNotNull("KidService: findAll",kidService.findAll());
		assertNotNull("KidService: findById 1",kidService.findById((long)1));
	}
	
	@Test
	public void paymentServiceTest() {
		assertNotNull("PaymentService: findAll",paymentService.findAll());	
		assertNotNull("PaymentService: findById 3",paymentService.findById((long)3));
	}

	

}
