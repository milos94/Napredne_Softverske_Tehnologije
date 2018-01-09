package com.milos.kindergarden.services;

import java.util.List;

import com.milos.kindergarden.models.Account;

public interface AccountService {
	
	List<Account> findAll();
	
	Account findById(Long id);
	
	Account save(Account newAccount);
	
	void delete(Account account);

	void refresh();
}
