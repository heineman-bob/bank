package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entities.User;


@Repository
public class UserDAO {
	
	@Autowired private SessionFactory sessionFactory;
	
	@Transactional
	public List<User> findAll() {
	  Session session = sessionFactory.getCurrentSession();
	  List users = session.createQuery("from Pizza").list();
	  return users;
	}
	
}
