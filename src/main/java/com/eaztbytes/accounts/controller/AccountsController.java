package com.eaztbytes.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eaztbytes.accounts.config.AccountsServiceConfig;
import com.eaztbytes.accounts.model.Accounts;
import com.eaztbytes.accounts.model.Customer;
import com.eaztbytes.accounts.model.Properties;
import com.eaztbytes.accounts.repository.AccountsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository; 
	

	@Autowired
	AccountsServiceConfig accountsConfig;
	
	@GetMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {
		
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		
		if(accounts != null) {
			return accounts; 
		} else { 
			return null;
		}
	}
	
	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
				accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}

}
