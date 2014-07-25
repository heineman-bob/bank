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
		setupSession();
		return session.createQuery("from User").list();
	}

	@Transactional
	public User findUser(long id) {
		setupSession();
		return (User) session.createQuery("from User user where user.id=" + id)
				.uniqueResult();
	}

	@Transactional
	public void deleteUser(long id) {
		setupSession();
		User user = (User) session.load(User.class, id);
		session.delete(user);
	}

	@Transactional
	public void createUser(User newUser) {
		setupSession();
		session.save(newUser);
	}

	@Transactional
	public void updateUser(User user) {
		setupSession();
		session.update(user);
	}

	public void setupSession() {
		session = sessionFactory.getCurrentSession();
	}
}
