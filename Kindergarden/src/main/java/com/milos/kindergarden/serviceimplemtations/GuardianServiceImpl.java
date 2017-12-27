package com.milos.kindergarden.serviceimplemtations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Guardian;
import com.milos.kindergarden.repositories.GuardianCrudRepository;
import com.milos.kindergarden.services.GuardianService;

@Service
public class GuardianServiceImpl implements GuardianService {
	
	private GuardianCrudRepository repository;
	private List<Guardian> guardians;
	private int hashCode;
	
	private void load() {
		guardians = repository.findAll();
		hashCode = guardians.hashCode();
	}
	
	public GuardianServiceImpl() {

	}
	
	@Autowired
	public GuardianServiceImpl(GuardianCrudRepository repositroy) {
		super();
		this.repository = repositroy;
	}

	public GuardianCrudRepository getRepositroy() {
		return repository;
	}

	public void setRepositroy(GuardianCrudRepository repositroy) {
		this.repository = repositroy;
	}

	public List<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}
	
	/* (non-Javadoc)
	 * @see com.milos.kindergarden.serviceimplemtations.GuardianService#findAll()
	 */
	@Override
	public List<Guardian> findAll(){
		if(guardians == null) {
			this.load();
		}
		return guardians;
	}
	
	/* (non-Javadoc)
	 * @see com.milos.kindergarden.serviceimplemtations.GuardianService#findByFirstNameAndLastName(java.lang.String, java.lang.String)
	 */
	@Override
	public Guardian findByFirstNameAndLastName(String firstName, String lastName) {
		if(guardians == null) {
			return repository.findByFirstNameAndLastName(firstName, lastName);
		}
		return guardians.stream()
				.filter(guard -> guard.getFirstName().equals(firstName) && guard.getLastName().equals(lastName))
				.findFirst().orElse(null);
	}

	@Override
	public Guardian findById(Long id) {
		return guardians.stream()
				.filter(guard -> guard.getId() == id)
				.findFirst().orElse(null);
	}
	

	
}
