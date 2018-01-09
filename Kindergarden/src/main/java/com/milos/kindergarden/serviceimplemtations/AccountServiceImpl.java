package com.milos.kindergarden.serviceimplemtations;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Account;
import com.milos.kindergarden.repositories.AccountCrudRepository;
import com.milos.kindergarden.services.AccountService;

@Service
@Transactional
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
	
	@Autowired
	public AccountServiceImpl(AccountCrudRepository repository) {
		super();
		this.repository = repository;;
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
		return accounts.stream()
				.filter(acc -> acc.getId().equals(id))
				.findFirst().orElse(null);
	}

	@Override
	public Account save(Account newAccount) {
		Account acc = accounts.stream()
								.filter(ac -> ac.getId().equals(newAccount.getId()))
								.findFirst().orElse(null);
		if(acc == null) {
			accounts.add(newAccount);
		}
		else {
			Collections.replaceAll(accounts, acc, newAccount);
		}
		return repository.save(newAccount);
	}
	
	@Override
	public void delete(Account account) {
		accounts.remove(account);
		repository.delete(account);
	}
	
	@Override
	public void refresh() {
		this.load();
	}

}
