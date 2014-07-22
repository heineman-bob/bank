package com.bob.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bob.bank.entities.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List users = session.createQuery("from User").list();
		return users;
	}

	@Transactional
	public User findUser(String firstName) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session
				.createQuery("from User where User.firstName=" + firstName);
		return user;
	}

}
