package com.bob.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bob.bank.entities.Transaction;

@Repository
public class TransactionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Transaction> findTransactions(long accountId) {
		setupSession();
		return session.createQuery(
				"from Transaction as t where t.accountId = " + accountId)
				.list();
	}

	@Transactional
	public Transaction findSingleTransaction(long id) {
		setupSession();
		return (Transaction) session.get(Transaction.class, id);
	}

	@Transactional
	public Transaction createTransaction(Transaction transaction) {
		setupSession();
		session.save(transaction);
		return transaction;
	}

	public void setupSession() {
		session = sessionFactory.getCurrentSession();
	}

}
