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
	private Session session;

	@Transactional
	public List<User> findAll() {
		session = sessionFactory.getCurrentSession();
		List users = session.createQuery("from User").list();
		return users;
	}

	@Transactional
	public User findUser(long id) {
		session = sessionFactory.getCurrentSession();
		User user = (User) session.createQuery(
				"from User user where user.id=" + id).uniqueResult();
		return user;
	}

	@Transactional
	public void deleteUser(long id) {
		session = sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, id);
		session.delete(user);
	}

	@Transactional
	public void createUser(User newUser) {
		session = sessionFactory.getCurrentSession();
		session.save(newUser);
	}
}
