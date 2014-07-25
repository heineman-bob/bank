package com.bob.bank.controllers;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bob.bank.dao.AccountDAO;
import com.bob.bank.entities.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountDAO accountDAO;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Account> findSingleAccount(@PathVariable long id) {
		Account account = accountDAO.findAccount(id);
		if (account != null) {
			return new ResponseEntity<Account>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<Account>(account, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/ByUser/{userId}")
	public ResponseEntity<List<Account>> getUserAccounts(
			@PathVariable long userId) {
		List<Account> accounts = accountDAO.findAll(userId);
		if (accounts.size() > 0) {
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Account>>(accounts,
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Account> deleteAccount(@PathVariable long id) {
		if (accountDAO.findAccount(id) != null) {
			accountDAO.deleteAccount(id);
			return new ResponseEntity<Account>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createAccount(@RequestBody String account,
			UriComponentsBuilder builder) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Account newAccount = mapper.readValue(account, Account.class);
		HttpHeaders headers = new HttpHeaders();

		accountDAO.createAccount(newAccount);
		return new ResponseEntity<String>(newAccount.toString(), headers,
				HttpStatus.OK);
	}
}
