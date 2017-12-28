package com.milos.kindergarden;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.milos.kindergarden.repositories.AccountCrudRepository;
import com.milos.kindergarden.repositories.ClassCrudRepository;
import com.milos.kindergarden.repositories.ClassroomCrudRepository;
import com.milos.kindergarden.repositories.EmployeeCrudRepository;
import com.milos.kindergarden.repositories.GuardianCrudRepository;
import com.milos.kindergarden.repositories.KidCrudRepository;
import com.milos.kindergarden.repositories.PaymentCrudRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {
	
	@Autowired
	AccountCrudRepository accRepo;
	@Autowired
	ClassCrudRepository clsRepo;
	@Autowired
	ClassroomCrudRepository classroomRepo;
	@Autowired
	EmployeeCrudRepository emplRepo;
	@Autowired
	GuardianCrudRepository guardRepo;
	@Autowired
	KidCrudRepository kidRepo;
	@Autowired
	PaymentCrudRepository payRepo;
	
	@Test
	public void accountRepoTest() {
		assertNotNull("AccountCrudRepository: findAll",accRepo.findAll());
		assertNotNull("AccountCrudRepository: findById 10001",accRepo.findById((long)10001));		
	}
	
	@Test
	public void classRepoTest() {
		assertNotNull("ClassCrudRepository: findAll",clsRepo.findAll());
		assertNotNull("ClassCrudRepository: findById 101",clsRepo.findById((long)101));
		assertNotNull("ClassCrudRepository: findByName Avengers",clsRepo.findByName("Avengers"));
	}
	
	@Test
	public void classroomRepoTest() {
		assertNotNull("ClassroomCrudRepository: findAll",classroomRepo.findAll());
		assertNotNull("ClassroomCrudRepository: findById 1001",classroomRepo.findById((long)1001));
	}
	
	@Test
	public void employeeRepoTest() {
		assertNotNull("EmployeeCrudRepository: findAll",emplRepo.findAll());
		assertNotNull("EmployeeCrudRepository: findById 11",emplRepo.findById((long)11));
		assertNotNull("EmployeeCrudRepository: findByFirstNameAndLastname John Jones",
						emplRepo.findByFirstNameAndLastName("John", "Jones"));
	}
	
	@Test
	public void guardianRepoTest() {
		assertNotNull("GuardianCrudRepository: findAll",guardRepo.findAll());
		assertNotNull("GuardianCrudRepository: findById 1",guardRepo.findById((long) 1));
		assertNotNull("GuardianCrudRepository: findByFirstNameAndLastName Clark Kent",
						guardRepo.findByFirstNameAndLastName("Clark", "Kent"));
	}
	
	@Test
	public void kidRepoTest() {
		assertNotNull("KidCrudRepository: findAll",kidRepo.findAll());
		assertNotNull("KidCrudRepository: findById 1",kidRepo.findById((long)1));
	}
	
	@Test
	public void paymentRepoTest() {
		assertNotNull("PaymentCrudRepository: findAll",payRepo.findAll());	
		assertNotNull("PaymentCrudRepository: findById 3",payRepo.findById((long)3));
	}

}
