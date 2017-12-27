package com.milos.kindergarden.serviceimplemtations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Kid;
import com.milos.kindergarden.repositories.KidCrudRepository;
import com.milos.kindergarden.services.KidService;

@Service
public class KidServiceImpl implements KidService {
	
	private KidCrudRepository repository;
	private List<Kid> kids;
	private int hashCode;
	
	private void load() {
		kids = repository.findAll();
		hashCode = kids.hashCode();
	}
	
	public KidServiceImpl() {

	}
	
	public KidServiceImpl(KidCrudRepository repository, List<Kid> kids) {
		super();
		this.repository = repository;
		this.kids = kids;
	}

	public KidCrudRepository getRepository() {
		return repository;
	}

	public void setRepository(KidCrudRepository repository) {
		this.repository = repository;
	}

	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}
	
	/* (non-Javadoc)
	 * @see com.milos.kindergarden.serviceimplemtations.KidService#findAll()
	 */
	@Override
	public List<Kid> findAll(){
		if(kids == null) {
			this.load();
		}
		return kids;
	}

	@Override
	public Kid findById(Long id) {
		if(kids == null) {
			return repository.findById(id);
		}
		return kids.stream()
				.filter(kid -> kid.getId() == id)
				.findFirst().orElse(null);
	}
}
