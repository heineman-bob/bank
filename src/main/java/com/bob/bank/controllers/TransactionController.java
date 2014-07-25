package com.bob.bank.controllers;

import java.io.IOException;
import java.util.List;

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

import com.bob.bank.dao.TransactionDAO;
import com.bob.bank.entities.Transaction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionDAO transactionDAO;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Transaction> singleTransaction(@PathVariable long id) {
		Transaction transaction = transactionDAO.findSingleTransaction(id);
		if (transaction != null) {
			return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
		} else {
			return new ResponseEntity<Transaction>(transaction,
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/account/transaction/{accountId}")
	public ResponseEntity<List<Transaction>> allTransactions(
			@PathVariable long accountId) {
		List<Transaction> transactions = transactionDAO
				.findTransactions(accountId);
		if (transactions.size() > 0) {
			return new ResponseEntity<List<Transaction>>(transactions,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Transaction>>(transactions,
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createTransaction(
			@RequestBody String transaction, UriComponentsBuilder builder)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Transaction newTransaction = mapper.readValue(transaction,
				Transaction.class);
		HttpHeaders headers = new HttpHeaders();

		transactionDAO.createTransaction(newTransaction);
		return new ResponseEntity<String>(newTransaction.toString(), headers,
				HttpStatus.OK);

	}
}
