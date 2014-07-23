package com.bob.bank.entities;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.ObjectMapper;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;
	private String username;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (IOException e) {
			/*
			 * At this point we know there was an exception with jacksons api
			 * but we know that the initial mapping was a success so we should
			 * not surface this to the customer but rather log this exception so
			 * we can debug later.
			 * 
			 * Lets return a generic message instead
			 */
			return "Success!";
		}
	}
}
