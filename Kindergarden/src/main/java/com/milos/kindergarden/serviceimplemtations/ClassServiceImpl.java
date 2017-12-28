package com.milos.kindergarden.serviceimplemtations;

import com.milos.kindergarden.repositories.ClassCrudRepository;
import com.milos.kindergarden.services.ClassService;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Class;

@Service
public class ClassServiceImpl implements ClassService {
	
	private ClassCrudRepository repository;
	private List<Class> classes;
	private int hashCode;
	
	private void load() {
		classes = repository.findAll();
		hashCode = classes.hashCode();
	}
	
	public ClassServiceImpl() {

	}

	@Autowired
	public ClassServiceImpl(ClassCrudRepository repository) {
		super();
		this.repository = repository;
	}

	public ClassCrudRepository getRepository() {
		return repository;
	}

	public void setRepository(ClassCrudRepository repository) {
		this.repository = repository;
	}
	
	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}
	
	/* (non-Javadoc)
	 * @see com.milos.kindergarden.serviceimplemtations.ClassService#findAll()
	 */
	@Override
	public List<Class> findAll(){
		if(classes == null) {
			this.load();
		}
		return classes;
	}

	@Override
	public Class findById(Long id) {
		if(classes == null) {
			return repository.findById(id);
		}
		return classes.stream()
				.filter(cls -> cls.getId().equals(id))
				.findFirst().orElse(null);
	}

	@Override
	public Class findByName(String name) {
		if(classes == null) {
			return repository.findByName(name);
		}
		return classes.stream()
				.filter(cls -> cls.getName().equals(name))
				.findFirst().orElse(null);
	}

	@Override
	public Class save(Class newClass) {
		Class cls = classes.stream()
							.filter(cl -> cl.getId().equals(newClass.getId()))
							.findFirst().orElse(null);
		if(cls == null) {
			classes.add(cls);
		}
		else {
			Collections.replaceAll(classes, cls, newClass);
		}
		
		return repository.save(newClass);
	}
	
	@Override
	public void delete(Class clas) {
		classes.remove(clas);
		repository.delete(clas);
	}
}
