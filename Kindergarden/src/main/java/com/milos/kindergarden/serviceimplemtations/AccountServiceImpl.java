package com.milos.kindergarden.serviceimplemtations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Account;
import com.milos.kindergarden.repositories.AccountCrudRepository;
import com.milos.kindergarden.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountCrudRepository repository;
	private List<Account> accounts;
	private int hashCode;
	
	private void load() {
		accounts = repository.findAll();
		hashCode = accounts.hashCode();
	}
	
	public AccountServiceImpl() {

	}

	public AccountServiceImpl(AccountCrudRepository repository, List<Account> accounts) {
		super();
		this.repository = repository;
		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public AccountCrudRepository getRepository() {
		return repository;
	}

	public void setRepository(AccountCrudRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Account> findAll() {
		if(accounts == null) {
			this.load();
		}
		return accounts;
	}

	@Override
	public Account findById(Long id) {
		if(accounts == null) {
			return repository.findById(id);
		}
		return accounts.stream().filter(acc -> acc.getId() == id).findFirst().get();
	}
	
	

}
