package com.bob.bank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bob.bank.dao.UserDAO;
import com.bob.bank.entities.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(method = RequestMethod.GET)
	public List<User> list(Model model) {
		List<User> users = userDAO.findAll();
		model.addAttribute("users", users);
		return users;
	}

}
