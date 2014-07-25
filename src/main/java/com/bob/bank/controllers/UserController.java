package com.bob.bank.controllers;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bob.bank.dao.UserDAO;
import com.bob.bank.entities.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<User> list(Model model) {
		List<User> users = userDAO.findAll();
		model.addAttribute("users", users);
		return users;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<User> singleUser(@PathVariable long id) {
		User user = userDAO.findUser(id);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		if (userDAO.findUser(id) != null) {
			userDAO.deleteUser(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> updateUser(@RequestBody String user,
			UriComponentsBuilder builder) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User updatedUser = mapper.readValue(user, User.class);
		HttpHeaders headers = new HttpHeaders();

		userDAO.updateUser(updatedUser);
		return new ResponseEntity<String>(updatedUser.toString(), headers,
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody String user,
			UriComponentsBuilder builder) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User newUser = mapper.readValue(user, User.class);
		HttpHeaders headers = new HttpHeaders();

		userDAO.createUser(newUser);
		return new ResponseEntity<String>(newUser.toString(), headers,
				HttpStatus.OK);
	}
}
