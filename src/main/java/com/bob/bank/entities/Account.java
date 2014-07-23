package com.bob.bank.entities;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.ObjectMapper;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue
	private long id;
	private long userId;
	private float balance;
	private long accountNumber;
	private String nickName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
