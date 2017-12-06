package com.milos.kindergarden;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.milos.kindergarden.repositories.AccountCrudRepository;
import com.milos.kindergarden.repositories.ClassroomCrudRepository;
import com.milos.kindergarden.repositories.EmployeeCrudRepository;
import com.milos.kindergarden.repositories.GroupCrudRepository;
import com.milos.kindergarden.repositories.GuardianCrudRepository;
import com.milos.kindergarden.repositories.KidCrudRepository;
import com.milos.kindergarden.repositories.PaymentCrudRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = KindergardenApplication.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class KindergardenApplicationTests {
	
	@Autowired
	private AccountCrudRepository accountCrudRepository;
	
	@Autowired
	private ClassroomCrudRepository classroomCrudRepositroy;
	
	@Autowired
	private EmployeeCrudRepository employeeCrudRepository;
	
	@Autowired
	private GroupCrudRepository groupCrudRepository;
	
	@Autowired
	private GuardianCrudRepository guardianCrudRepository;
	
	@Autowired
	private KidCrudRepository kidCrudRepository;
	
	@Autowired
	private PaymentCrudRepository paymentCrudRepository;
	
	@Test
	public void accountRepositoryTest() {
		assertThat(accountCrudRepository.findAll().size()).isGreaterThan(0);
	}
	
	@Test
	public void classroomRepositoryTest() {
		
		assertThat(classroomCrudRepositroy.findAll().size()).isGreaterThan(0);
	}
	
	@Test
	public void employeeRepositoryTest() {
		
		assertThat(employeeCrudRepository.findAll().size()).isGreaterThan(0);
	}
	
	@Test
	public void groupRepositoryTest() {
		
		assertThat(groupCrudRepository.findAll().size()).isGreaterThan(0);
	}
	
	@Test
	public void guardianRepositoryTest() {
		
		assertThat(guardianCrudRepository.findAll().size()).isGreaterThan(0);
	}
	
	@Test
	public void kidRepositoryTest() {
		
		assertThat(kidCrudRepository.findAll().size()).isGreaterThan(0);
	}
	
	@Test
	public void paymenyRepositoryTest() {
		
		assertThat(paymentCrudRepository.findAll().size()).isGreaterThan(0);
	}

}
