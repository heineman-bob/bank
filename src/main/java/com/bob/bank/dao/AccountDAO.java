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

	@Transactional
	public List<Account> findAll(long userId) {
		session = sessionFactory.getCurrentSession();
		List accounts = session.createQuery(
				"from Account account where account.userId=" + userId).list();
		return accounts;
	}

	@Transactional
	public Account findAccount(long id) {
		session = sessionFactory.getCurrentSession();
		Account account = (Account) session.get(Account.class, id);
		return account;
	}

}
