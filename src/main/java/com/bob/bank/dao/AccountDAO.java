package com.bob.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bob.bank.entities.Account;

@Repository
public class AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Account> findAll(long userId) {
		session = sessionFactory.getCurrentSession();
		return session.createQuery(
				"from Account as a where a.userId=" + userId)
				.list();
	}

	@Transactional
	public Account findAccount(long id) {
		setupSession();
		Account account = (Account) session.get(Account.class, id);
		return account;
	}

	@Transactional
	public void deleteAccount(long id) {
		setupSession();
		session.delete("id", id);
	}

	@Transactional
	public Account createAccount(Account account) {
		setupSession();
		session.save(account);
		return account;
	}

	@Transactional
	public void updateAccount(Account account) {
		setupSession();
		session.update(account);
	}

	public void setupSession() {
		session = sessionFactory.getCurrentSession();
	}
}
