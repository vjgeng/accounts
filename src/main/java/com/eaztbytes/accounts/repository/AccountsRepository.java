package com.eaztbytes.accounts.repository;

import org.springframework.data.repository.CrudRepository;

import com.eaztbytes.accounts.model.Accounts;

public interface AccountsRepository extends CrudRepository<Accounts, Long> {
	
	Accounts findByCustomerId(int customerId);

}
