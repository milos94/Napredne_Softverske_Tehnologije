package com.milos.kindergarden.serviceimplemtations;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Kid;
import com.milos.kindergarden.repositories.KidCrudRepository;
import com.milos.kindergarden.services.KidService;

@Service
@Transactional
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
	
	@Autowired
	public KidServiceImpl(KidCrudRepository repository) {
		super();
		this.repository = repository;
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
				.filter(kid -> kid.getId().equals(id))
				.findFirst().orElse(null);
	}

	@Override
	public Kid save(Kid newKid) {
		Kid kid = kids.stream().filter(kk -> kk.getId().equals(newKid.getId())).findFirst().orElse(null);
		
		if(kid == null) {
			kids.add(newKid);
		}
		else {
			Collections.replaceAll(kids, kid, newKid);
		}
		
		return repository.save(newKid);
	}
	
	@Override
	public void delete(Kid kid) {
		kids.remove(kid);
		repository.delete(kid);
	}
	
	@Override
	public void refresh() {
		this.load();
	}
}
