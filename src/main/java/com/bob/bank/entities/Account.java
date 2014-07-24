package com.bob.bank.entities;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.map.ObjectMapper;

@Entity
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(columnNames = "nickname") })
public class Account {

	private long id;
	private float balance;
	private String accountNumber;
	private String nickName;
	private User user;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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
