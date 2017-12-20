package com.milos.kindergarden.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.milos.kindergarden.models.Account;

public interface AccountCrudRepository extends CrudRepository<Account, Long> {

	List<Account> findAll();
	//Account findByID(Long id);
	//Account findOne();
}
